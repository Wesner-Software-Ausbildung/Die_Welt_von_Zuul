package de.szut.zuul;
import java.util.HashMap;

public class Room 
{
    private String description;


    // Für die 3 Teil habe ich HashMap in die Klasse "Room" erstellt. Danach soll ich die "Methode" - "getExit" und "exitsToString" ändern.
    // Fur Teil 6 habe ich die andere HashMap für die Gegenstände (Item), die ich weiter erstellen soll.
    private HashMap<String, Room> exits;
    private HashMap<String, Item> items;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */

    // Für die 3 Teil habe ich den Konstruktor geändert. Er soll jetzt HashMap nutzen.
    // Für Teil 6 habe ich hier diese HashMap initialisiert.
    public Room(String description) 
    {
        this.description = description;
        this.exits = new HashMap<String, Room>();
        this.items = new HashMap<String, Item>();
    }

    //Ich habe diese Methode erstellt, weil die Attribute in dieser Klasse jetzt "Private" sind. (2 Aufgabe, 1 Schritt)
    public Room getExit(String direction) {
        return exits.get(direction);
    }

    //Ich habe diese Methode erstellt, weil die Attribute in dieser Klasse jetzt "Private" sind. (2 Aufgabe, 2 Schritt.)
    //Zum dritten Schritt sollte ich die Methode "exitsToString" ändern.
    public String exitsToString() {
        StringBuilder exits = new StringBuilder();
        if (getExit("north") != null) {
            exits.append("north ");
        }
        if (getExit("south") != null) {
            exits.append("south ");
        }
        if (getExit("east") != null) {
            exits.append("east ");
        }
        if (getExit("west") != null)
            exits.append("west ");
        if (getExit("up") != null)
            exits.append("up ");
        if (getExit("down") != null)
            exits.append("down ");

        return exits.toString();
    }

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
    //Für 6 Schritt sollte ich diese Methode ändern, um die Gegenstände in der Konsole zu zeigen.
    public String getLongDescription() {

        StringBuilder itemDescription = new StringBuilder();
        for (Item item : items.values()) {
            itemDescription.append("\n- ").append(item.toString());
        }
        return "You are " + description + "\nExits: " + exitsToString() + "\nItems in this room: " + itemDescription.toString();
    }

        // Für Teil 6 habe ich "putItem" erstellt, um die Gegenstände von der Klasse "Item" zu bekommen
        // und die Gegenstände in Raum abgelegt.
    public void putItem (String name, String description, double weight) {
        Item newItem = new Item (name, description, weight);
        this.items.put(name, newItem);
    }

    public Item getItem (String itemName) {
        return this.items.get(itemName);
    }
    // Mit dieser Methode kann ich den Gegenstand aus maine Tasche löschen.
    public Item removeItem (String name) {
        if (this.items.containsKey(name)) {
            return this.items.remove(name);
        }
        else {
            return null;
        }
    }
}
