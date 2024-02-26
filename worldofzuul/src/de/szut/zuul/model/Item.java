package de.szut.zuul.model;
import java.util.HashMap;

public class Item {

    public String name;
    public String description;
    private double weight;
    //Für Teil 6 habe ich die Klasse "Item" erstellt und die 3 Atribute hinzugefügt und initialisiert.
    //Dazu sollte ich toString Methode erstellen.
    public Item (String name, String description, double weight) {

        this.name = name;
        this.description = description;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.description + ", " + this.weight;
    }

    public double getWeight() {
        return this.weight;
    }

    public String getName() {
        return name;
    }
}
