
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Colig
 */
public class Phrase {
    private String phrase;
    private String category;
    private String lettersGuessed;
    private boolean guessed = false;

    public Phrase(String phrase, String category) {
        phrase = phrase.toUpperCase();
        category = category.toUpperCase();
        this.phrase = phrase;
        this.category = category;
    }

    public Phrase(String phrase, String category, String lettersGuessed) {
        phrase = phrase.toUpperCase();
        category = category.toUpperCase();
        lettersGuessed = lettersGuessed.toUpperCase();
        this.phrase = phrase;
        this.category = category;
        this.lettersGuessed = lettersGuessed;
        
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLettersGuessed() {
        return lettersGuessed;
    }

    public void setLettersGuessed(String lettersGuessed) {
        this.lettersGuessed = lettersGuessed;
    }

    public boolean isGuessed() {
        return guessed;
    }

    public void setGuessed(boolean guessed) {
        this.guessed = guessed;
    }
    
    public String displayPhrase()
    {
        String temp;
        temp = phrase;
        temp = temp.replaceAll("[^ " + lettersGuessed + "]", "_");
        temp = temp.replaceAll("_", "_ ");
        return temp;
    }
    
    public boolean guessLetter(String guess)
    {
        if (!guess.matches("[a-zA-Z]"))
        {
            JOptionPane.showMessageDialog(null, "Your guess must be a single letter.");
            return false;
        }
        else if (guess.toUpperCase().matches("[" + lettersGuessed + "]"))
        {
            JOptionPane.showMessageDialog(null, "Your guess: \"" + guess + "\" has already been made.");
            return false;
        }
        else
        {
            lettersGuessed += guess.toUpperCase();
            if (displayPhrase().compareTo(phrase) == 0)
            {
                JOptionPane.showMessageDialog(null, "Conratulations, you guessed the phrase correctly!");
                guessed = true;
            }
            return true;
        }
    }
    
    public boolean guessPhrase(String guess)
    {
        if (guess.toUpperCase().compareTo(phrase) == 0)
        {
            JOptionPane.showMessageDialog(null, "Conratulations, you guessed the phrase correctly!");
            guessed = true;
            return true;
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Unfortunately your guess: \"" + guess + "\" was incorrect.");
            return false;
        }
    }
    
}
