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
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author mitchdunc
 */
public class AuthClient {
    
   private String host;
   private  int portNum;
   private Socket s;
    
    public AuthClient(){
        this.host = "localhost";
        this.portNum = 8189;
        
        
                try {
                    s = new Socket(this.host, this.portNum);
                    }
                catch(Exception ex) {
                    System.out.println(ex); 
                }
    }
    
    public AuthClient(String host, int portNum) {
        this.host = host;
        this.portNum = portNum;

        try {
            s = new Socket(this.host, this.portNum);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void sendString(String sendString){
        try {
                
                    OutputStream outStream = s.getOutputStream();
                     
                    PrintWriter out = new PrintWriter(outStream, true);
                    out.println(sendString);
                    
                    }
                catch(Exception ex) {
                    System.out.println(ex); 
                }
    }
    
    public String receiveString(){
        String incomingStream = "Error";
        try {
                    
                    
                    InputStream inStream = s.getInputStream(); 
                
                    Scanner in = new Scanner(inStream); 
                    
                    incomingStream = in.nextLine();
                    }
                catch(Exception ex) {
                    System.out.println(ex); 
                }
        
        return incomingStream;
    }
    
//    public void main(){
//        while(true){
//        try {
//            Socket s = new Socket(this.host, this.portNum);
//                try {
//                    //Scanner newIn = new Scanner(System.in);
//                    String inputString = newIn.nextLine();
//                
//                    OutputStream outStream = s.getOutputStream();
//                    
//                    InputStream inStream = s.getInputStream(); 
//                
//                    Scanner a = new Scanner(inStream); 
//                    PrintWriter out = new PrintWriter(outStream, true);
//                    out.println(inputString);
//                    System.out.println(a.nextLine());  
//                    }
//                catch(Exception ex) {
//                    System.out.println(ex); 
//                }
//                finally {
//                    s.close();      
//                }
//            }
//            catch(IOException ioexc){
//                ioexc.printStackTrace();
//            }
//        }
//    }
}
    
    
