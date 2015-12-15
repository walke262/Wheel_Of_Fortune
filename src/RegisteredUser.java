
import java.io.Serializable;
import static java.lang.Integer.parseInt;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author tylerjohnson
 */
public class RegisteredUser extends Person implements Serializable{
    
    private String password;
    private int gamesPlayed;
    private int gamesWon;
    private int phrasesSolved;
    private int totalWinnings;
    
    public RegisteredUser(){
        super();
        password = "";
        gamesPlayed = 0;
        gamesWon = 0;
        phrasesSolved = 0;
        totalWinnings = 0;
    }
    
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
    
    public void setPassword(String password){
        this.password = password;
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
    
    public String serialize(){
        return this.getUserName() + "\t" + password + "\t" + this.getCurrentBalance() + "\t" + gamesPlayed +
                "\t" + gamesWon + "\t"+ phrasesSolved + "\t"+ totalWinnings;
    }
    public void deserialize(String input){
        String [] values = input.split("\t");
        
        this.setUserName(values[0]);
        this.setPassword(values[1]);
        this.setCurrentBalance(parseInt(values[2]));
        this.setGamesPlayed(parseInt(values[3]));
        this.setGamesWon(parseInt(values[4]));
        this.setPhrasesSolved(parseInt(values[5]));
        this.setTotalWinnings(parseInt(values[6]));
        
    }
    
}
