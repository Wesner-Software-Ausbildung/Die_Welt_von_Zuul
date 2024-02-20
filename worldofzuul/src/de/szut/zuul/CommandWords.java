package de.szut.zuul;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class CommandWords
{
    // a constant array that holds all valid command words
    // Für Teil 7 habe ich ein Paar neue Kommands erstellt.
    private static final String[] validCommands = {
        "go", "quit", "help", "look", "take", "drop"
    };

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        // nothing to do at the moment...
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }
    // Für Teil 5 habe ich die Methode "showAll" mit StringBuilder geschrieben, weil wir einige Probleme bei Command "help" hatten.
    // Die neue Methode soll alle Commands sammeln und richtig uns zeigen.
    public String showAll () {
        StringBuilder showAlls = new StringBuilder("");
        for (String command : validCommands) {
            showAlls.append(command).append(" ");
        }
        return showAlls.toString().trim();
    }
}
