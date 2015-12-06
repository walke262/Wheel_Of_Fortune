/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mitchdunc
 */

import java.util.Random;

public class Wheel {
    private Random randomGen;
    private int maxPrice;
    int lastSpin;


    public Wheel(){
        randomGen = new Random();
        maxPrice = 2000;
    }
    
   
    public int Spin(){
        int randomInt;
       
        //Gives random bankruptcy 
        if(randomGen.nextInt(100) <= 5){
            lastSpin = -1;
            return -1;
        }
        
        //Assigns random value, rounds it to 50
        randomInt = randomGen.nextInt(maxPrice);
        randomInt = ((randomInt + 50) / 50 * 50);
        lastSpin = randomInt;
        return randomInt;
    }
    
    public int LastSpin(){
        return lastSpin;
    }
    
}
