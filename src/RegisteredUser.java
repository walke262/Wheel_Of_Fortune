/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author tylerjohnson
 */
public class RegisteredUser extends Person {
    
    private String password;
    double gamesPlayed;
    double gamesWon;
    double phrasesSolved;
    double totalWinnings;
    
    public RegisteredUser(String name, double currentBalance, String password, double gamesPlayed, double gamesWon, double phrasesSolved, double totalWinnings){
        super(name, currentBalance);
        this.password = password;
        this.gamesPlayed = gamesPlayed;
        this.gamesWon = gamesWon;
        this.phrasesSolved = phrasesSolved;
        this.totalWinnings = totalWinnings;
        
    }
    //Getters and Setters for each data member (Use getters/setters from Person for username and currentBalance)
    public String getPassword(){
        return this.password;
    }
    
    public double getGamesPlayed(){
        return this.gamesPlayed;
    }
    
    public double getGamesWon(){
        return this.gamesWon;
    }
    
    public double getPhrasesSolved(){
        return this.phrasesSolved;
    }
    
    public double getTotalWinnings(){
        return this.totalWinnings;
    }
    
    public void setGamesPlayed(double gamesPlayed){
        this.gamesPlayed = gamesPlayed;
    }
    
    public void setGamesWon(double gamesWon){
        this.gamesWon = gamesWon;
    }
    
    public void setPhrasesSolved(double phrasesSolved){
        this.phrasesSolved = phrasesSolved;
    }
    
    public void setTotalWinnings(double totalWinnings){
        this.totalWinnings = totalWinnings;
    }
    /* A common need will be to increase the statistics of a player by a certain amount, and the following methods add a specified amount to the respective
    data fields.
    */
    public void increaseTotalWinnings(double amount){
        this.totalWinnings += amount;
    }
    
    public void increasePhrasesSolved(double amount){
        this.phrasesSolved += amount;
    }
    
    public void increaseGamesPlayed(double amount){
        this.gamesPlayed += amount;
    }
    
    public void increaseGamesWon(double amount){
        this.gamesWon += amount;
    }
    
}
