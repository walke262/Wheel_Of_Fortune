/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vincent
 */
public class AuthenticationServer extends MyServerSocket {

    /**
     * @param args the command line arguments
     */
    public AuthenticationServer(int port) {
        super(port);
    }

    public static void main(String[] args) {
        ArrayList<RegisteredUser> loginRegisteredUsers = new ArrayList<>();
        RegisteredUser ru = null;
        FileReader fileReader = null;
        // TODO code application logic here
        System.out.println("Login Server");
        AuthenticationServer loginServer = new AuthenticationServer(8189);
        String[] loginVariables = new String[2];
        boolean over = false;
        int index = 0;
        boolean isExisting = false;

            //File path to the .txt file that stores the username and password
        //URL res = loginServer.getClass().getClassLoader().getResource("Users.txt");
        File f = new File("./Users.txt");
            //String filename = res.getFile();

        try {
            if (f.exists()) {
                fileReader = new FileReader(f);
                String line;
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                //Loads the file into the arrayList
                while ((line = bufferedReader.readLine()) != null) {
                    ru = new RegisteredUser();
                    ru.deserialize(line);
                    loginRegisteredUsers.add(ru);
                }
                bufferedReader.close();
                fileReader.close();
                System.out.println("Finished reading file...");
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(AuthenticationServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AuthenticationServer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException ex) {
                    Logger.getLogger(AuthenticationServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        try {
            while (!over) {
                System.out.println("Looking for client...");
                Socket acceptedLoginClient = loginServer.acceptingConnection();
                System.out.println("Client accepted.");
                boolean done = false;

                while (!done) {
                    Object loginCredentials;
                    loginCredentials = loginServer.receiveObject(acceptedLoginClient);

                    //Logs into the game.
                    if (loginCredentials instanceof String[]) {
                        RegisteredUser temp = null;
                        loginVariables = (String[]) loginCredentials;

                        for (int i = 0; i < loginRegisteredUsers.size(); i++) {
                            if (loginVariables[0].equalsIgnoreCase(loginRegisteredUsers.get(i).getUserName()) && loginVariables[1].equals(loginRegisteredUsers.get(i).getPassword())) {
                                temp = loginRegisteredUsers.get(i);
                                break;
                            }
                        }

                        loginServer.sendObject(temp, acceptedLoginClient);
                    } else if (loginCredentials instanceof RegisteredUser) {
                        RegisteredUser checkUser = (RegisteredUser) loginCredentials;
                        for (int i = 0; i < loginRegisteredUsers.size(); i++) {
                            if (checkUser.getUserName().equalsIgnoreCase(loginRegisteredUsers.get(i).getUserName())) {
                                checkUser = null;
                                isExisting = true;
                                break;
                            }
                        }

                        if (!isExisting) {
                            loginRegisteredUsers.add(checkUser);

                        }
                        loginServer.sendObject(checkUser, acceptedLoginClient);
                    } else if (loginCredentials instanceof String) {
                        String message = (String) loginCredentials;
                        if (message.equalsIgnoreCase("bye")) {
                            done = true;
                            if (f.exists())
                            {
                                f.delete();
                                f.createNewFile();
                                FileWriter fileWriter = new FileWriter(f, true);
                                System.out.println("Writing to server...");
                                for (RegisteredUser r : loginRegisteredUsers)
                                {
                                    fileWriter.write(r.serialize() + '\n');
                                    fileWriter.flush();
                                }
                                fileWriter.close();
                            }
                            else
                            {
                                f.createNewFile();
                                FileWriter fileWriter = new FileWriter(f, true);
                                System.out.println("Writing to server...");
                                for (RegisteredUser r : loginRegisteredUsers)
                                {
                                    fileWriter.write(r.serialize() + '\n');
                                    fileWriter.flush();
                                }
                                fileWriter.close();
                            }

                            System.out.println("Client Closed.");
                            acceptedLoginClient.close();
                        } else if (message.equalsIgnoreCase("shut down")) {
                            done = true;
                            over = true;
                            System.out.println("Shutting down server...");
                            acceptedLoginClient.close();
                            loginServer.close();
                        }
                        else if (message.equals("LEADERBOARD"))
                        {
                            ArrayList<RegisteredUser> ranking = new ArrayList<>();
                            for (RegisteredUser r : loginRegisteredUsers)
                            {
                                ranking.add(r);
                            }
                            for (int i = 0; i<ranking.size()-1; i++)
                            {
                                for (int x = 1; x<ranking.size() -i; x++)
                                {                                    
                                    if (ranking.get(x-1).getTotalWinnings() < ranking.get(x).getTotalWinnings())
                                    {
                                        RegisteredUser temp = ranking.get(x-1);
                                        ranking.set(x-1, ranking.get(i));
                                        ranking.set(x, temp);
                                    }
                                }
                            }
                            loginServer.sendObject(ranking, acceptedLoginClient);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
