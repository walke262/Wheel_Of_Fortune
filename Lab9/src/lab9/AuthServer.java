/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab9;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mitchdunc
 */
public class AuthServer implements Serializable{
    private int portNum;
    private ServerSocket s;
    private Socket incoming;
    private String incomingString;
    
    public AuthServer(){
        this.portNum = 8189;
        try {
            s = new ServerSocket(this.portNum);
        } catch (IOException ex) {
            Logger.getLogger(AuthServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public AuthServer(int portNum){
        this.portNum = portNum;
        try {
            s = new ServerSocket(this.portNum);
        } catch (IOException ex) {
            Logger.getLogger(AuthServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void acceptClient(){
        try {
            incoming = s.accept();//accept a connection from a client
            System.out.println("Client Accepted");
        } catch (IOException ex) {
            Logger.getLogger(AuthServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void acceptString(){
        InputStream inStream;
        try {
            inStream = incoming.getInputStream();
            Scanner in = new Scanner(inStream);
            incomingString = in.nextLine();
            System.out.println("Client Said: " + incomingString);
        } catch (IOException ex) {
            Logger.getLogger(AuthServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void echoString() {
        OutputStream outStream;
        try {
            outStream = incoming.getOutputStream();
            PrintWriter out = new PrintWriter(outStream, true);
            
            out.println("Server Replied: " + incomingString);
            //out.flush();
            //System.out.println("echo");
        } catch (IOException ex) {
            Logger.getLogger(AuthServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void close() {
        try {
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(AuthServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean authUser(){
        String filename = "/Users/mitchdunc/Documents/OneDrive/Classes/CNIT 325/WheelOfFortune/Wheel_Of_Fortune/Lab9/src/lab9/login.txt";
        
        String line = null;
        
        try{
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            line = bufferedReader.readLine();
            //System.out.println(line);
            line += "\n" + bufferedReader.readLine();
            System.out.println(line);
            
            while((line = bufferedReader.readLine()) != null){
                System.out.println(line);
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex){
            System.out.println("Unable to open file '" + filename + "'");
            
        }
        catch(IOException ex){
            System.out.println("Error reading file '"+ filename + "'");
        }
        
        if(line.equals(incomingString)){
            return true;
        }
        else{
            return false;
        }
        
        
        
    }
    

}

