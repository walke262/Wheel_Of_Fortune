
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

            while (!over) {
                for (int i = 0; i < 3; i++) {
                    acceptedClient[i] = gameServer.acceptingConnection();
                    inputStream[i] = acceptedClient[i].getInputStream();
                    outputStream[i] = acceptedClient[i].getOutputStream();
                    objectInputStream[i] = new ObjectInputStream(inputStream[i]);
                    objectOutputStream[i] = new ObjectOutputStream(outputStream[i]);
                    gameServer.playerInfo[i] = (Person) gameServer.receiveObject(acceptedClient[i]);

                }
                boolean done = false;

                while (!done) {
                    for (int i = 0; i < 3; i++) {
                        gameClientObjects = gameServer.receiveObject(acceptedClient[i]);
                    }
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
                            gameServer.playerInfo[gameServer.currentPlayer].increaseCurrentBalance(gameServer.wheel.LastSpin());
                            
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
                                    
                                    for (int i = 0; i < 3; i++) {
                                        gameServer.sendMessage("Player " + indexOfHighestScore + " has been chosen for the final round.", acceptedClient[i]);
                                    }
                                    gameServer.sendMessage("The prize you are playing for is..." + gameServer.prize.amount(), acceptedClient[indexOfHighestScore]);
                                    gameServer.sendMessage(gameServer.phrase.get(gameServer.phraseIndex).displayPhrase(), acceptedClient[indexOfHighestScore]);
                                    gameServer.sendMessage("You have 4 letter guesses.", acceptedClient[indexOfHighestScore]);
                                    String guess;
                                    for (int i = 0; i < 4; i++) {
                                        guess = (String) gameServer.receiveObject(acceptedClient[indexOfHighestScore]);
                                        gameServer.phrase.get(gameServer.phraseIndex).guessLetter(guess);
                                        gameServer.sendObject(gameServer.phrase.get(gameServer.phraseIndex).displayPhrase(), acceptedClient[indexOfHighestScore]);
                                    }
                                    
                                    gameServer.sendMessage("You have 3 phrase guesses", acceptedClient[indexOfHighestScore]);
                                    String guessPhrase;
                                    int totalWinnings = gameServer.playerInfo[indexOfHighestScore].getCurrentBalance();
                                    for (int i = 0; i < 3; i++) {
                                        guessPhrase = (String) gameServer.receiveObject(acceptedClient[indexOfHighestScore]);
                                        isCorrect = gameServer.phrase.get(gameServer.phraseIndex).guessPhrase(guessPhrase);
                                        if (isCorrect) {
                                            totalWinnings += gameServer.prize.amount();
                                            gameServer.sendMessage("You have guessed correctly! You win ..." + totalWinnings, acceptedClient[indexOfHighestScore]);
                                            break;
                                        }
                                    }
                                    
                                    if (!isCorrect) {
                                        gameServer.sendMessage("You have lost. But you still win..." + totalWinnings, acceptedClient[indexOfHighestScore]);
                                    }
                                    
                                }
                            }
                            
                            for (int i = 0; i < 3; i++) {
                                gameServer.sendMessage(gameServer.phrase.get(gameServer.phraseIndex).displayPhrase(), acceptedClient[i]);
                            }
                            isCorrect = false;
                        }
                        
                        gameServer.updatePlayerTurn();
                    }

                    else if (gameClientObjects instanceof Boolean) {
                        //If the thing has been spun
                        isSpun = (Boolean) gameClientObjects;
                        if (isSpun) {
                            //The wheel has been spun
                            if (gameServer.wheel.Spin() < 0) {
                                gameServer.playerInfo[gameServer.currentPlayer].setCurrentBalance(0);
                                for (int i = 0; i < 3; i++) {
                                    for (int j = 0; j < 3; j++) {
                                        gameServer.sendObject(gameServer.playerInfo[j], acceptedClient[i]);
                                    }
                                }
                                gameServer.updatePlayerTurn();
                            }
                            else {
                                gameServer.sendMessage("Guess.", acceptedClient[gameServer.currentPlayer]);
                            }
                        }
                        else {
                            //The wheel has not been spun
                        }
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
    
}
