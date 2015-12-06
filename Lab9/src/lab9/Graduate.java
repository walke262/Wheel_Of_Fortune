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
public class Graduate extends Student{
    int major;
    int school;
    boolean thesis;
    boolean nonThesis;
    boolean phd;
    boolean ms;
    
    public Graduate(String firstName, String middleInit, String lastName, String puid, String address, String state, int zipCode, int country, int major, int school, boolean thesis, boolean nonThesis, boolean phd, boolean ms){
        super(firstName, middleInit, lastName, puid, address, state, zipCode, country);
        this.major = major;
        this.school = school;
        this.thesis = thesis;
        this.nonThesis = nonThesis;
        this.phd = phd;
        this.ms = ms;
        
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

    public boolean isThesis() {
        return thesis;
    }

    public void setThesis(boolean thesis) {
        this.thesis = thesis;
    }

    public boolean isNonThesis() {
        return nonThesis;
    }

    public void setNonThesis(boolean nonThesis) {
        this.nonThesis = nonThesis;
    }

    public boolean isPhd() {
        return phd;
    }

    public void setPhd(boolean phd) {
        this.phd = phd;
    }

    public boolean isMs() {
        return ms;
    }

    public void setMs(boolean ms) {
        this.ms = ms;
    }

    
    
}
