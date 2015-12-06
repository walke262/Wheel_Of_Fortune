/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author tylerjohnson
 */

import java.util.UUID;

public class Guest extends Person {
    
    public Guest(){
      super("Guest", 0);
    }
    
    public void setUserName(){
        super.setUserName(generateUserName());
    }
    
    public static String generateUserName(){
        String userName = UUID.randomUUID().toString();
        return userName;
    }
     
    
    // for testing the generation of random userNames
    public static void main(String[] args){
        String test = generateUserName();
        System.out.println(test);
        
    }
    
}
