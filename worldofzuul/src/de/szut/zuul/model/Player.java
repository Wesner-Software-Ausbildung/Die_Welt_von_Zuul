package de.szut.zuul.model;

import de.szut.zuul.exceptions.ItemNotFoundException;
import de.szut.zuul.exceptions.ItemTooHeavyException;

import java.util.LinkedList;

public class Player {
    // In Teil 7 habe ich die Klasse "Player" erstellt und drinnen 2 Parameter und neue HashMap hinzugefügt.
    // In den Konstruktor habe ich die maximale Weight hinzugefügt und LinkedList initialisiert.
    private Room currentRoom;
    private double loadCapacity;
    private LinkedList<Item> items;


    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    public void goTo(Room newRoom) {
        this.currentRoom = newRoom;
    }

    public Player() {
        this.loadCapacity = 10.0;
        this.items = new LinkedList<>();
    }
    // In Teil 7 habe ich die Möglichkeit gemacht, die Gegenstände aufheben und lassen. Zuerst habe ich alles in die Methode "takeItem" erstellt,
    // aber später sollte ich diese Methode auf 2 Methode teilen - "calculateWeight" und "isTakePossible".
    public void takeItem (Item item) throws ItemTooHeavyException {
        if (item.getWeight() + calculateWeight() > this.loadCapacity) {
            throw new ItemTooHeavyException("The item is too heavy for you to carry.");
        }
        items.add(item);
    }


    private double calculateWeight() {
        double totalWeight = 0.0;
        for (Item i : this.items) {
            totalWeight += i.getWeight();
        }
        return totalWeight;
    }

    private boolean isTakePossible(Item item) {

         return item.getWeight() + this.calculateWeight() <= this.loadCapacity;
    }

    // In Teil 7 habe ich die Möglichkeit erstellt, um der Gegenstand in Raum zu lassen.
    public Item dropItem (String name) throws ItemNotFoundException {
        for (Item item : this.items) {
            if (item.getName().equals(name)) {
               this.items.remove(item);
                showStatus();
                return item;
            }
        }
        throw new ItemNotFoundException("You don't own this item!");
    }
    // Für Teil 7 habe ich die Methode "showStatus" erstellt, um den Status wahrend des Spiels zu sehen.
    public String showStatus() {

        String status = "> Status of the player \n";

        status += "loadCapacity: " + this.loadCapacity + "kg \n";

        status += "taken items: ";

        for (Item item : this.items) {

            status += item.getName() + ", " + item.getWeight() + "kg \n";

        }

        return status.toString();
    }

    
}
