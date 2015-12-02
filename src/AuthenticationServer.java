/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Vincent
 */
public class AuthenticationServer {

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Login Server");
        MyServerSocket loginServer = new MyServerSocket(8189);
        ArrayList<String> loginVariables;
        boolean over = false;
        //File path to the .txt file that stores the username and password
        //String filename =;
        String userID = null;
        String password = null;
        
        try {
            while (!over) {
                boolean isLoginSuccess = false;
                Socket acceptedLoginClient = loginServer.acceptingConnection();
                System.out.println("Client accepted.");
                boolean done = false;

                while (!done) {
                    Object loginCredentials;
                    loginCredentials = loginServer.receiveObject(acceptedLoginClient);
                    if (loginCredentials instanceof ArrayList) {
                        loginVariables = (ArrayList) loginCredentials;
                        //FileReader fileReader = new FileReader(filename);
                        //BufferedReader bufferedReader = new BufferedReader(fileReader);
                        
                        //userID = bufferedReader.readLine().trim();
                        //password = bufferedReader.readLine().trim();
                        
                        if (userID.equalsIgnoreCase(loginVariables.get(0)) && password.equals(loginVariables.get(1))) {
                            isLoginSuccess = true;
                        }
                        
                        loginServer.sendMessage(isLoginSuccess, acceptedLoginClient);
                        //bufferedReader.close();
                    }
                    
                    else if (loginCredentials instanceof String) {
                        String message = (String) loginCredentials;
                        if (message.equalsIgnoreCase("bye")) {
                            done = true;
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
    
}
