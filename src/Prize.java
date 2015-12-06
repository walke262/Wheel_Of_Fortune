/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;

/**
 *
 * @author Alison
 */
public class Prize {

    private int amount;
            
    public Prize(int amount) {
        this.amount = amount;
    }

    public int credit(int amount) {
        return this.amount += amount;
    }


    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return String.format("[amount=%d]", amount);
    }        
            
            
            
            
            
    
}
