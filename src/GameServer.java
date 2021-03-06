
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            FileReader fileReader = null;
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
            File f = new File("./Phrases.txt");

            // Load in some phrases
            try {
                ArrayList<Phrase> tempA = new ArrayList<>();
                if (f.exists()) {
                    fileReader = new FileReader(f);
                    String line;
                    BufferedReader bufferedReader = new BufferedReader(fileReader);

                    //Loads the file into the arrayList
                    while ((line = bufferedReader.readLine()) != null) {
                        Phrase p = new Phrase();
                        p.deSerialize(line);
                        tempA.add(p);
                    }
                    bufferedReader.close();
                    fileReader.close();
                    System.out.println("Finished reading file...");
                }
                
                // Choose the phrases randomly
                for (int i = 0; i < 5; i++)
                {
                    int index = gameServer.random.nextInt(tempA.size()-1);
                    gameServer.phrase.add(tempA.get(index));
                    tempA.remove(index);
                }

            } catch (FileNotFoundException ex) {
                Logger.getLogger(AuthenticationServer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(AuthenticationServer.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (fileReader != null) {
                    try {
                        fileReader.close();
                    } catch (IOException ex) {
                        Logger.getLogger(AuthenticationServer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
            for (Phrase p : gameServer.phrase)
            {
                System.out.println(p.getPhrase());
            }
            
            for (int i = 0; i < 3; i++) {
                acceptedClient[i] = gameServer.acceptingConnection();
                inputStream[i] = acceptedClient[i].getInputStream();
                outputStream[i] = acceptedClient[i].getOutputStream();
                objectInputStream[i] = new ObjectInputStream(inputStream[i]);
                objectOutputStream[i] = new ObjectOutputStream(outputStream[i]);
                gameServer.playerInfo[i] = (Person)gameServer.receiveObject(objectInputStream[i]);
            }
            

            //gameServer.phrase.add(new Phrase("The early bird gets the worm", "Phrase"));
            //gameServer.phrase.add(new Phrase("Chicken and rice", "On the menu"));
            
            for (Person p : gameServer.playerInfo)
            {
                p.setCurrentBalance(0);
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
                            if (gameServer.playerInfo[gameServer.currentPlayer] instanceof RegisteredUser)
                            {
                                ((RegisteredUser)gameServer.playerInfo[gameServer.currentPlayer]).setPhrasesSolved(((RegisteredUser)gameServer.playerInfo[gameServer.currentPlayer]).getPhrasesSolved() + 1);
                            }
                            gameServer.phraseIndex++;
                            if (gameServer.phraseIndex >= gameServer.phrase.size() - 1) {
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
                                    guessPhrase = (String) gameServer.receiveObject(objectInputStream[indexOfHighestScore]);
                                    isCorrect = gameServer.phrase.get(gameServer.phraseIndex).guessPhrase(guessPhrase);
                                    if (isCorrect) {
                                        totalWinnings += gameServer.prize.getAmount();
                                        gameServer.sendObjectToAll(gameServer.playerInfo[indexOfHighestScore].getUserName() + " has won the final round.\nTheir prize they are playing for is + " + gameServer.prize.getAmount(), objectOutputStream);
                                        gameServer.sendObjectToAll(gameServer.phrase.get(gameServer.phraseIndex), objectOutputStream);
                                        gameServer.sendObjectToAll(gameServer.playerInfo, objectOutputStream);
                                        gameServer.sendObjectToAll("END", objectOutputStream);
                                        
                                        if (gameServer.playerInfo[gameServer.currentPlayer] instanceof RegisteredUser) {
                                            ((RegisteredUser) gameServer.playerInfo[gameServer.currentPlayer]).setGamesWon(((RegisteredUser) gameServer.playerInfo[gameServer.currentPlayer]).getGamesWon()+ 1);
                                            ((RegisteredUser) gameServer.playerInfo[gameServer.currentPlayer]).setTotalWinnings(((RegisteredUser) gameServer.playerInfo[gameServer.currentPlayer]).getTotalWinnings() + gameServer.prize.getAmount());
                                        }
                                        
                                        ArrayList<RegisteredUser> ru = new ArrayList<>();
                                        for (Person p : gameServer.playerInfo) {

                                            if (p instanceof RegisteredUser) {
                                                ((RegisteredUser) p).setGamesPlayed(((RegisteredUser) p).getGamesPlayed() + 1);
                                                ((RegisteredUser) p).setTotalWinnings(((RegisteredUser) p).getTotalWinnings() + p.getCurrentBalance());
                                                ru.add((RegisteredUser) p);
                                            }

                                        }
                                        MyClientSocket client = new MyClientSocket("127.0.0.1", 8189);
                                        client.sendObject(ru);
                                        client.sendObject("bye");
                                        client.close();
                                        over = true;
                                        break;
                                    }
                                }

                                if (!isCorrect) {
                                    gameServer.sendObjectToAll(gameServer.playerInfo[indexOfHighestScore].getUserName() + " has lost the final round.", objectOutputStream);
                                    gameServer.sendObject("You have lost. But you still win..." + totalWinnings, objectOutputStream[indexOfHighestScore]);
                                    //gameServer.sendMessage("You have lost. But you still win..." + totalWinnings, acceptedClient[indexOfHighestScore]);
                                    gameServer.sendObjectToAll(gameServer.phrase.get(gameServer.phraseIndex), objectOutputStream);
                                    gameServer.sendObjectToAll(gameServer.playerInfo, objectOutputStream);
                                    gameServer.sendObjectToAll("END", objectOutputStream);
                                    ArrayList<RegisteredUser> ru = new ArrayList<>();
                                    for (Person p : gameServer.playerInfo) {

                                        if (p instanceof RegisteredUser) {
                                            ((RegisteredUser) p).setGamesPlayed(((RegisteredUser) p).getGamesPlayed() + 1);
                                            ((RegisteredUser) p).setTotalWinnings(((RegisteredUser) p).getTotalWinnings() + p.getCurrentBalance());
                                            ru.add((RegisteredUser) p);
                                        }

                                    }
                                    MyClientSocket client = new MyClientSocket("127.0.0.1", 8189);
                                    client.sendObject(ru);
                                    client.sendObject("bye");
                                    client.close();
                                    over = true;
                                }
                            }
                        }
                        
                        gameServer.sendObjectToAll(gameServer.phrase.get(gameServer.phraseIndex), objectOutputStream);
                        System.out.println(gameServer.phrase.get(gameServer.phraseIndex).displayPhrase());
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
            oos.flush();
            oos.reset();
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
                writer.flush();
                writer.reset();
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
