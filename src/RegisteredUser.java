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
    int gamesPlayed;
    int gamesWon;
    int phrasesSolved;
    int totalWinnings;
    
    public RegisteredUser(String name, int currentBalance, String password, int gamesPlayed, int gamesWon, int phrasesSolved, int totalWinnings){
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
    
    public int getGamesPlayed(){
        return this.gamesPlayed;
    }
    
    public int getGamesWon(){
        return this.gamesWon;
    }
    
    public int getPhrasesSolved(){
        return this.phrasesSolved;
    }
    
    public int getTotalWinnings(){
        return this.totalWinnings;
    }
    
    public void setGamesPlayed(int gamesPlayed){
        this.gamesPlayed = gamesPlayed;
    }
    
    public void setGamesWon(int gamesWon){
        this.gamesWon = gamesWon;
    }
    
    public void setPhrasesSolved(int phrasesSolved){
        this.phrasesSolved = phrasesSolved;
    }
    
    public void setTotalWinnings(int totalWinnings){
        this.totalWinnings = totalWinnings;
    }
    /* A common need will be to increase the statistics of a player by a certain amount, and the following methods add a specified amount to the respective
    data fields.
    */
    public void increaseTotalWinnings(int amount){
        this.totalWinnings += amount;
    }
    
    public void increasePhrasesSolved(int amount){
        this.phrasesSolved += amount;
    }
    
    public void increaseGamesPlayed(int amount){
        this.gamesPlayed += amount;
    }
    
    public void increaseGamesWon(int amount){
        this.gamesWon += amount;
    }
    
}
