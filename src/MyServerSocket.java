

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
import java.net.ServerSocket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sabrina
 */
public class MyServerSocket {
    
    ServerSocket s = null;
    
    MyServerSocket(int port) {
        try {
            s = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(MyServerSocket.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    public Socket acceptingConnection() {
        try {
            return s.accept();
        } catch (IOException ex) {
            Logger.getLogger(MyServerSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void sendMessage(String message, Socket socket) {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(message);
        } catch (IOException ex) {
            Logger.getLogger(MyServerSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendMessage(boolean message, Socket socket) {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(message);
        } catch (IOException ex) {
            Logger.getLogger(MyServerSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendMessage(double message, Socket socket) {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(message);
        } catch (IOException ex) {
            Logger.getLogger(MyServerSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendObject(Object object, Socket socket) {
        try {
            OutputStream outStream = socket.getOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(outStream);
            out.writeObject(object);
        } catch (IOException ex) {
            Logger.getLogger(MyServerSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
    /**
     * @deprecated 
     * @param socket
     * @return 
     */
    public String receiveMessage(Socket socket) {
        try {
            Scanner in = new Scanner(socket.getInputStream());
            return in.nextLine();
        } catch (IOException ex) {
            Logger.getLogger(MyServerSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Object receiveObject(Socket socket) {
        try {
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream in = new ObjectInputStream(inputStream);
            return in.readObject();
        } catch (IOException ex) {
            Logger.getLogger(MyServerSocket.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MyServerSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
        
    public void close() {
        try {
            s.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
