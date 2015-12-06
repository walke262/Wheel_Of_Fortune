
import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author tylerjohnson
 */
public abstract class Person implements Serializable {
    
    private String userName;
    private int currentBalance;
    private String ip;
    
    public Person(String name, int amount){
        this.userName = name;
        this.currentBalance = amount;
    }
    
    public Person(String name){
        this.userName = name;
        this.currentBalance = 0;
    }
    
    public void setIp(String ip){
        this.ip = ip;
    }
    
    public void setUserName(String name){
        this.userName = name;
    }
    
    public void setCurrentBalance(int balance){
        this.currentBalance = balance;
    }
    
    public String getUserName(){
        return this.userName;
    }
    
    public String getIp(){
        return this.ip;
    }
    
    public int getCurrentBalance(){
        return this.currentBalance;
    }
    //Mehtod to increase the current balance of a player by a set amount
    public void increaseCurrentBalance(int amount){
        this.currentBalance += amount;
    }
}
