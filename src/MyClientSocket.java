

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sabrina
 */
 
public class MyClientSocket {
    
    private Socket s = null;
    private PrintWriter out = null;
    private Scanner in = null;
    
    MyClientSocket(String hostname, int port) {     
        try {
            s = new Socket(hostname, port);
            out = new PrintWriter(s.getOutputStream(), true);
            in = new Scanner(s.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(MyClientSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * @deprecated
     * @param message 
     */
    public void sendMessage(String message) {
        out.println(message);
    }
    
    public void sendObject(Object message) {
        try {
            OutputStream outStream = s.getOutputStream();
            ObjectOutputStream objectOut = new ObjectOutputStream(outStream);
            objectOut.writeObject(message);
        } catch (IOException ex) {
            Logger.getLogger(MyClientSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * @deprecated
     * @param message 
     */
    public void sendMessage(double message) {
        out.println(message);
    }
        
    public String readFromServer() {
        return in.nextLine();
    }
    
    public Object readObjectFromServer() {
        try {
            InputStream inStream = s.getInputStream();
            ObjectInputStream inObjectStream = new ObjectInputStream(inStream);
            return inObjectStream.readObject();
        } catch (IOException ex) {
            Logger.getLogger(MyClientSocket.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MyClientSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
    
    public void close() {
        try {
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(MyClientSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
