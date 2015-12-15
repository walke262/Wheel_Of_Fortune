/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
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
            
    ArrayList<RegisteredUser> loginRegisteredUsers = new ArrayList<>();
    RegisteredUser ru = null;
    
    public static void main(String[] args) {
        FileReader fileReader = null;
        try {
            // TODO code application logic here
            System.out.println("Login Server");
            AuthenticationServer loginServer = new AuthenticationServer(8189);
            ArrayList<String> loginVariables;
            boolean over = false;
            int index = 0;
            boolean isExisting = false;
            
            //File path to the .txt file that stores the username and password
            URL res = loginServer.getClass().getClassLoader().getResource("Users.txt");
            String filename = res.getFile();
            fileReader = new FileReader(filename);
            String line;
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            //Loads the file into the arrayList
            while ((line = bufferedReader.readLine()) != null) {
                loginServer.ru = new RegisteredUser();
                loginServer.ru.deserialize(line);
                loginServer.loginRegisteredUsers.add(loginServer.ru);
            }
            bufferedReader.close();
            fileReader.close();
            System.out.println("Finished reading file...");
            
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
                        if (loginCredentials instanceof ArrayList) {
                            RegisteredUser temp = null;
                            loginVariables = (ArrayList) loginCredentials;
                                                       
                            for (int i = 0; i < loginServer.loginRegisteredUsers.size(); i++) {
                                if (loginVariables.get(i).equalsIgnoreCase(loginServer.loginRegisteredUsers.get(i).getUserName()) && loginVariables.get(1).equalsIgnoreCase(loginServer.loginRegisteredUsers.get(i).getPassword())) {
                                    temp = loginServer.loginRegisteredUsers.get(i);
                                    break;
                                }
                            }
                     
                            loginServer.sendObject(temp, acceptedLoginClient);
                        }
                        
                        else if (loginCredentials instanceof RegisteredUser) {
                            RegisteredUser checkUser = (RegisteredUser) loginCredentials;
                            for (int i = 0; i < loginServer.loginRegisteredUsers.size(); i++) {
                                if (checkUser.getUserName().equalsIgnoreCase(loginServer.loginRegisteredUsers.get(i).getUserName())) {
                                    checkUser = null;
                                    isExisting = true;
                                    break;
                                }
                            }
                            
                            if (!isExisting) {
                                loginServer.loginRegisteredUsers.add(checkUser);
 
                            }
                            loginServer.sendObject(checkUser, acceptedLoginClient);  
                        }
                        
                        else if (loginCredentials instanceof String) {
                            String message = (String) loginCredentials;
                            if (message.equalsIgnoreCase("bye")) {
                                done = true;
                                FileWriter fileWriter = new FileWriter(filename, true);
                                System.out.println("Writing to server...");
                                if (!isExisting){
                                    fileWriter.write(loginServer.loginRegisteredUsers.get(index).serialize() + '\n');
                                    fileWriter.flush();
                                    index++;
                                }
                                fileWriter.close();
                                System.out.println("Client Closed.");
                                acceptedLoginClient.close();
                            }
                            else if (message.equalsIgnoreCase("shut down")) {
                                done = true;
                                over = true;
                                System.out.println("Shutting down server...");
                                acceptedLoginClient.close();
                                loginServer.close();
                            }
                        }
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(AuthenticationServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AuthenticationServer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fileReader.close();
            } catch (IOException ex) {
                Logger.getLogger(AuthenticationServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
