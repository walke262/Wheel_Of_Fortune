
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sabrina
 */
public class GameServer extends MyServerSocket{
    
     GameServer(int port) {
        super(port);
    }
    
    Person[] playerInfo = new Person[3];
    ArrayList<Phrase> phrase = new ArrayList<>();
    int phraseIndex = 0;
    Wheel wheel = new Wheel();
    int currentPlayer = 0;
    Random random = new Random();
    Prize prize = new Prize(random.nextInt(500000));
        
    public static void main(String[] args) {
        try {
            System.out.println("Game Server");
            GameServer gameServer = new GameServer(1337);
            String stringFromClient;
            boolean isSpun;
            boolean isCorrect = false;
            boolean over = false;
            Socket[] acceptedClient = new Socket[3];
            InputStream[] inputStream = new InputStream[3];
            OutputStream[] outputStream = new OutputStream[3];
            ObjectInputStream[] objectInputStream = new ObjectInputStream[3];
            ObjectOutputStream[] objectOutputStream = new ObjectOutputStream[3];
            Object gameClientObjects = "";
            
            for (int i = 0; i < 3; i++) {
                acceptedClient[i] = gameServer.acceptingConnection();
                inputStream[i] = acceptedClient[i].getInputStream();
                outputStream[i] = acceptedClient[i].getOutputStream();
                objectInputStream[i] = new ObjectInputStream(inputStream[i]);
                objectOutputStream[i] = new ObjectOutputStream(outputStream[i]);
                gameServer.playerInfo[i] = (Person)gameServer.receiveObject(objectInputStream[i]);
            }
            
            gameServer.phrase.add(new Phrase("The early bird gets the worm", "Phrase"));
            gameServer.phrase.add(new Phrase("Chicken and rice", "On the menu"));
            
            for (Person p : gameServer.playerInfo)
            {
                System.out.println(p.getUserName());
            }
            gameServer.sendObjectToAll(gameServer.playerInfo, objectOutputStream);
            gameServer.sendObjectToAll(gameServer.phrase.get(gameServer.phraseIndex), objectOutputStream);
            gameServer.sendObjectToAll(gameServer.wheel, objectOutputStream);        
            gameServer.sendObjectToAll(gameServer.playerInfo[gameServer.currentPlayer].getUserName() + "'s turn", objectOutputStream);
            gameServer.sendObject("SPIN", objectOutputStream[gameServer.currentPlayer]);

            while (!over)
            {                
                gameClientObjects = gameServer.receiveObject(objectInputStream[gameServer.currentPlayer]);
                
                if (gameClientObjects instanceof String) {
                    stringFromClient = (String) gameClientObjects;
                    if (stringFromClient.length() > 1) {
                        //This a guess for the phrase
                        isCorrect = gameServer.phrase.get(gameServer.phraseIndex).guessPhrase(stringFromClient);
                    }

                    else if (stringFromClient.length() == 1) {
                        //This is a guess for a letter
                        isCorrect = gameServer.phrase.get(gameServer.phraseIndex).guessLetter(stringFromClient);
                    }

                    if (isCorrect) {
                        gameServer.playerInfo[gameServer.currentPlayer].increaseCurrentBalance(gameServer.wheel.LastSpin() * gameServer.phrase.get(gameServer.phraseIndex).countMatches(stringFromClient));
                        gameServer.sendObjectToAll(gameServer.playerInfo[gameServer.currentPlayer].getUserName() +"'s guess of " + stringFromClient + "was correct.", objectOutputStream);
                        gameServer.sendObjectToAll(gameServer.phrase.get(gameServer.phraseIndex), objectOutputStream);
                        gameServer.sendObjectToAll(gameServer.playerInfo, objectOutputStream);

                        if (gameServer.phrase.get(gameServer.phraseIndex).isGuessed()){
                            gameServer.phraseIndex++;
                            if (gameServer.phraseIndex > gameServer.phrase.size() - 1) {
                                int highestScore = gameServer.playerInfo[0].getCurrentBalance();
                                int indexOfHighestScore = 0;

                                for (int i = 0; i < 3; i++) {
                                    if (highestScore < gameServer.playerInfo[i].getCurrentBalance()){
                                        highestScore = gameServer.playerInfo[i].getCurrentBalance();
                                        indexOfHighestScore = i;
                                    }
                                }

                                gameServer.phrase.get(gameServer.phraseIndex).guessLetter("r");
                                gameServer.phrase.get(gameServer.phraseIndex).guessLetter("s");
                                gameServer.phrase.get(gameServer.phraseIndex).guessLetter("t");
                                gameServer.phrase.get(gameServer.phraseIndex).guessLetter("l");
                                gameServer.phrase.get(gameServer.phraseIndex).guessLetter("n");
                                gameServer.phrase.get(gameServer.phraseIndex).guessLetter("e");
                                
                                gameServer.sendObjectToAll(gameServer.playerInfo[indexOfHighestScore].getUserName() + " has been chosen for the final round.\nTheir prize they are playing for is + " + gameServer.prize.getAmount(), objectOutputStream);
                                gameServer.sendObjectToAll(gameServer.phrase.get(gameServer.phraseIndex), objectOutputStream);
                                
                                String guess;
                                for (int i = 0; i < 4; i++) {
                                    gameServer.sendObject("The prize you are playing for is..." + gameServer.prize.getAmount() + "\nYou have " + (4 - i) + " letter guesses.", objectOutputStream[indexOfHighestScore]);
                                    gameServer.sendObject("TURNLETTER", objectOutputStream[indexOfHighestScore]);
                                    guess = (String) gameServer.receiveObject(objectInputStream[indexOfHighestScore]);
                                    gameServer.phrase.get(gameServer.phraseIndex).guessLetter(guess);
                                }

                                gameServer.sendObjectToAll(gameServer.phrase.get(gameServer.phraseIndex), objectOutputStream);

                                String guessPhrase;
                                int totalWinnings = gameServer.playerInfo[indexOfHighestScore].getCurrentBalance();
                                for (int i = 0; i < 3; i++) {
                                    gameServer.sendObject("The prize you are playing for is..." + gameServer.prize.getAmount() + "\nYou have " + (3 - i) + " phrase guesses.", objectOutputStream[indexOfHighestScore]);
                                    gameServer.sendObject("TURNPHRASE", objectOutputStream[indexOfHighestScore]);
                                    guessPhrase = (String) gameServer.receiveObject(acceptedClient[indexOfHighestScore]);
                                    isCorrect = gameServer.phrase.get(gameServer.phraseIndex).guessPhrase(guessPhrase);
                                    if (isCorrect) {
                                        totalWinnings += gameServer.prize.getAmount();
                                        gameServer.sendObjectToAll(gameServer.playerInfo[indexOfHighestScore].getUserName() + " has won the final round.\nTheir prize they are playing for is + " + gameServer.prize.getAmount(), objectOutputStream);
                                        gameServer.sendObjectToAll(gameServer.phrase.get(gameServer.phraseIndex), objectOutputStream);
                                        gameServer.sendObjectToAll(gameServer.playerInfo, objectOutputStream);
                                        gameServer.sendObjectToAll("END", objectOutputStream);
                                        over = true;
                                        break;
                                    }
                                }

                                if (!isCorrect) {
                                    gameServer.sendObjectToAll(gameServer.playerInfo[indexOfHighestScore].getUserName() + " has lost the final round.", objectOutputStream);
                                    gameServer.sendMessage("You have lost. But you still win..." + totalWinnings, acceptedClient[indexOfHighestScore]);
                                    gameServer.sendObjectToAll(gameServer.phrase.get(gameServer.phraseIndex), objectOutputStream);
                                    gameServer.sendObjectToAll(gameServer.playerInfo, objectOutputStream);
                                    gameServer.sendObjectToAll("END", objectOutputStream);
                                    over = true;
                                }
                            }
                        }
                        
                        gameServer.sendObjectToAll(gameServer.phrase.get(gameServer.phraseIndex), objectOutputStream);                                
                        isCorrect = false;
                    }
                    else
                    {
                        gameServer.sendObjectToAll(gameServer.playerInfo[gameServer.currentPlayer].getUserName() +"'s guess of " + stringFromClient + "was incorrect.", objectOutputStream);
                    }

                    gameServer.updatePlayerTurn();
                    gameServer.sendObjectToAll(gameServer.playerInfo[gameServer.currentPlayer].getUserName() + "'s turn", objectOutputStream);
                    gameServer.sendObject("SPIN", objectOutputStream[gameServer.currentPlayer]);
                }

                else if (gameClientObjects instanceof Boolean) {
                    //If the thing has been spun
                    isSpun = (Boolean) gameClientObjects;
                    if (isSpun) {
                        //The wheel has been spun
                        gameServer.wheel.Spin();
                        gameServer.sendObjectToAll(gameServer.wheel, objectOutputStream);

                        if (gameServer.wheel.LastSpin() < 0) {
                            gameServer.playerInfo[gameServer.currentPlayer].setCurrentBalance(0);
                            gameServer.sendObjectToAll(gameServer.playerInfo, objectOutputStream);
                            gameServer.updatePlayerTurn();
                            gameServer.sendObjectToAll(gameServer.playerInfo[gameServer.currentPlayer].getUserName() + "'s turn", objectOutputStream);
                            gameServer.sendObject("SPIN", objectOutputStream[gameServer.currentPlayer]);
                        }
                        else {
                            gameServer.sendObject("TURN", objectOutputStream[gameServer.currentPlayer]);
                        }
                    }
                    else {
                        //The wheel has not been spun
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }  
    }

    public void updatePlayerTurn() {
        currentPlayer++;
        if (currentPlayer > 2) {
            currentPlayer = 0;
        }
    }
    
    public void sendObject(Object obj, ObjectOutputStream oos)
    {
        try
        {
            oos.writeObject(obj);
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
    
    public void sendObjectToAll(Object obj, ObjectOutputStream[] oos)
    {
        try
        {
            for (ObjectOutputStream writer : oos)
            {
                writer.writeObject(obj);
            }
//            for (int i = 0; i < 3; i++)
//            {
//                oos[i].writeObject(obj);
//            }
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
    
    public Object receiveObject(ObjectInputStream ois)
    {
        Object temp = null;
        try
        {
            temp = ois.readObject();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return temp;
    }
}
