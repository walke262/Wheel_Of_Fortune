/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author sclaypoo
 */
import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
    
    private Socket s = null;
    private ObjectInputStream objectInStream = null;
    private InputStream inStream = null;
    private Scanner in = null;
    private ObjectOutputStream objectOutStream = null;
    private OutputStream outStream = null;
    private PrintWriter out = null;
    private String ip = null;
    private int port = 0;

    public Client() {
    }
    
    public Client(String myIP, int myPort)
    {
        ip = myIP;
        port = myPort;
    }
    
    public void openConnection()
    {
        try
        {
            s = new Socket(ip, port);
            inStream = s.getInputStream();
            outStream = s.getOutputStream();
            objectOutStream = new ObjectOutputStream(outStream);
            objectInStream = new ObjectInputStream(inStream);

        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        
    }
    
    public void openConnection(String myIP, int myPort)
    {
        try
        {
            s = new Socket(myIP, myPort);
            
            inStream = s.getInputStream();
            outStream = s.getOutputStream();
            objectOutStream = new ObjectOutputStream(outStream);
            objectInStream = new ObjectInputStream(inStream);            
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
    
    public void closeConnection()
    {
        try
        {
            if (s != null)
            {
                objectOutStream.close();
                objectInStream.close();
                outStream.close();
                inStream.close();
                s.close();
            }
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
    
    public Object receiveMessage()
    {
        Object temp = null;
        try
        {
            temp = objectInStream.readObject();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return temp;
    }
    
    public void sendMessage(Object msg)
    {
        try
        {
            objectOutStream.writeObject(msg);
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        //out.println(msg);
    }
    
    public boolean getConnectionStatus()
    {
        return s.isConnected();
    }
    
}
