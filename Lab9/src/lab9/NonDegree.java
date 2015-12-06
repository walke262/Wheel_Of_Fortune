/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab9;

/**
 *
 * @author mitchdunc
 */
public class NonDegree extends Student{
    
    private String college;
    
    public NonDegree(String firstName, String middleInit, String lastName, String puid, String address, String state, int zipCode, int country, String college){
        super(firstName, middleInit, lastName, puid, address, state, zipCode, country);
        this.college = college;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }
    
    
}

    

