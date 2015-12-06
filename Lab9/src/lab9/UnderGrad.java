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
public class UnderGrad extends Student{
    private int major;
    private int school;
    
    public UnderGrad(String firstName, String middleInit, String lastName, String puid, String address, String state, int zipCode, int country, int major, int school){
        super(firstName, middleInit, lastName, puid, address, state, zipCode, country);
        this.major = major;
    }

    public int getMajor() {
        return major;
    }

    public void setMajor(int major) {
        this.major = major;
    }

    public int getSchool() {
        return school;
    }

    public void setSchool(int school) {
        this.school = school;
    }
    
    
    
    
}
