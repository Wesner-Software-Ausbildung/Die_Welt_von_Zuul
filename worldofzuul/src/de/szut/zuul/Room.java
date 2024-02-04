package de.szut.zuul;
import java.util.HashMap;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class Room 
{
    private String description;


    // Für die 3 Teil habe ich HashMap in die Klasse "Room" erstellt. Danach soll ich die "Methode" - "getExit" und "exitsToString" ändern.
    private HashMap<String, Room> exits;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */

    // Für die 3 Teil habe ich den Konstruktor geändert. Er soll jetzt HashMap nutzen.
    public Room(String description) 
    {
        this.description = description;
        this.exits = new HashMap<String, Room>();
    }

    //Ich habe diese Methode erstellt, weil die Attribute in dieser Klasse jetzt "Private" sind. (2 Aufgabe, 1 Schritt)
    public Room getExit(String direction) {
        return exits.get(direction);
    }

    //Ich habe diese Methode erstellt, weil die Attribute in dieser Klasse jetzt "Private" sind. (2 Aufgabe, 2 Schritt.)
    //Zum dritten Schritt sollte ich die Methode "exitsToString" ändern.
    public String exitsToString() {
        StringBuilder exits = new StringBuilder("");
        if(getExit("north") != null)
            exits.append("north ");
        if(getExit("south ") != null)
            exits.append("south ");
        if(getExit("east") != null)
            exits.append("east ");
        if(getExit("west") != null)
            exits.append("west ");
        if(getExit("up") != null)
            exits.append("up ");
        if(getExit("down") != null)
            exits.append("down ");

        return exits.toString();
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east exit.
     * @param south The south exit.
     * @param west The west exit.
     * @param up The up exit.
     * @param down The down exit.
     */

    //Ich habe diese Methode ergänzt, weil ich hier ein paar neue Richtungen erstellen wollte (1 Aufgabe)
    //Zum dritten Schritt sollte ich die Methode "setExits" zum "setExit" umwandeln.

    public void setExit(String direction, Room neighbour) {
        this.exits.put(direction, neighbour);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    //Hier habe ich die Methode "getLongDescription" erstellt, die mir die Beschreibung der Zimmer von der Klasse "Game" zurückgeben. (4 Schritt)
    public String getLongDescription() {
        return "You are " + description + "\nExits: " + exitsToString();
    }
}
