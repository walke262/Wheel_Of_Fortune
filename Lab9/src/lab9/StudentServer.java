/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab9;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mitchdunc
 */
public class StudentServer {
    private int portNum;
    private ServerSocket s;
    private Socket incoming;
    private String incomingString;
    
    public StudentServer(){
        this.portNum = 8190;
        try {
            s = new ServerSocket(this.portNum);
        } catch (IOException ex) {
            Logger.getLogger(StudentServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public StudentServer(int portNum){
        this.portNum = portNum;
        try {
            s = new ServerSocket(this.portNum);
        } catch (IOException ex) {
            Logger.getLogger(StudentServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void acceptClient(){
        try {
            incoming = s.accept();//accept a connection from a client
            System.out.println("Client Accepted");
        } catch (IOException ex) {
            Logger.getLogger(StudentServer.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(StudentServer.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(StudentServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void close() {
        try {
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(StudentServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void main(){
         try {
            
            boolean over = false;
            while (!over) //put in a loop that keeps running
            {
                
                try {
                    InputStream inStream = incoming.getInputStream(); // the INPUT stream handler
                    OutputStream outStream = incoming.getOutputStream(); // the OUTPUT stream handler
                    Scanner in = new Scanner(inStream); //setup of input
                    PrintWriter out = new PrintWriter(outStream, true); // sends output
                    boolean done = false;
                    while (!done && in.hasNextLine()) //while there are lines to read, for this connection
                    {
                        String lineIn = in.nextLine();
                        System.out.println(lineIn.trim());
                        out.println("Server replied : " + lineIn);
                        out.flush();
                        if (lineIn.trim().equals("BYE")) //to kill the server, enter "BYE" from the client
                        {
                            done = true;
                        }
                    }
                } catch (Exception exc1) {
                    exc1.printStackTrace();
                }
            }
        } catch (Exception exc2) {
            exc2.printStackTrace();
        }
    }
}

