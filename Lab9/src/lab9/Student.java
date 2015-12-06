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
abstract class Student {
    
    private String firstName;
    private String middleInit;
    private String lastName;
    private String puid;
    private String address;
    private String state;
    private int zipCode;
    private int country;

    public Student(String firstName, String middleInit, String lastName, String puid, String address, String state, int zipCode, int country) {
        this.firstName = firstName;
        this.middleInit = middleInit;
        this.lastName = lastName;
        this.puid = puid;
        this.address = address;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
    }

   

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleInit() {
        return middleInit;
    }

    public void setMiddleInit(String middleInit) {
        this.middleInit = middleInit;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPuid() {
        return puid;
    }

    public void setPuid(String puid) {
        this.puid = puid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public int getCountry() {
        return country;
    }

    public void setCountry(int country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    
    
    
}
