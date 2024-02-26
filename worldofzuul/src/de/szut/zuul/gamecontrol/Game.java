package de.szut.zuul.gamecontrol;

import de.szut.zuul.model.Item;
import de.szut.zuul.model.Player;
import de.szut.zuul.model.Room;
import de.szut.zuul.exceptions.ItemNotFoundException;
import de.szut.zuul.exceptions.ItemTooHeavyException;

public class Game
{
    private Parser parser;
    private Player player;

        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        player = new Player();
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room marketsquare, templePyramid, tavern, sacrificialSite, hut, jungle, secretPassage, cave, beach, cellar, wizardsRoom;

        // create the rooms
        marketsquare = new Room("on the market square");
        templePyramid = new Room("in a temple pyramid");
        tavern = new Room("in the tavern at the market square");
        sacrificialSite = new Room("at a sacrificial site");
        hut = new Room("in a hut");
        jungle = new Room("in the jungle");
        secretPassage = new Room("in a secret passage");
        cave = new Room("in a cave");
        beach = new Room("on the beach");
        //Ich habe hier 2 neue Räume erstellt.
        cellar = new Room("in a cellar");
        wizardsRoom = new Room("in a wizard's room");

        // Für Teil 6 habe ich für 8 Zimmer die Gegenstände mit 3 Parameter erstellt.
        marketsquare.putItem("Bogen", "ein Bogen aus Holz", 0.5);
        cave.putItem("Schatz", "eine kleine Schatztruhe mit Münzen", 7.5);
        wizardsRoom.putItem("Pfeile", "Ein Köcher mit diversen Pfeilen", 1.0);
        jungle.putItem("Pflanze", "eine Heilpflanze", 0.5);
        jungle.putItem("Kakao", "ein kleiner Kakaobaum", 5);
        sacrificialSite.putItem("Messer", "ein sehr scharfes, großes Messer", 1);
        hut.putItem("Speer", "ein Speer mit dazugehöriger Schleuder", 5.0);
        tavern.putItem("Nahrung", "ein Teller mit deftigem Fleisch und Maisbrei", 0.5);
        cellar.putItem("Schmuck", "ein sehr hübscher Kopfschmuck", 1);

        // Für dritte Teil sollte ich Aufruf der Methode "setExist" auf "setExit" ändern, weil die erste Methode nicht meer funktioniert.
        marketsquare.setExit("north", tavern);
        marketsquare.setExit("east", templePyramid);
        marketsquare.setExit("west", sacrificialSite);

        templePyramid.setExit("north", hut);
        templePyramid.setExit("west", marketsquare);
        templePyramid.setExit("up", wizardsRoom);
        templePyramid.setExit("down", cellar);

        tavern.setExit("east", hut);
        tavern.setExit("south", marketsquare);

        sacrificialSite.setExit("east", marketsquare);
        sacrificialSite.setExit("down", cave);

        hut.setExit("east", jungle);
        hut.setExit("south", templePyramid);
        hut.setExit("west", tavern);

        jungle.setExit("west", hut);

        secretPassage.setExit("east", cellar);
        secretPassage.setExit("west", cave);

        cave.setExit("east", secretPassage);
        cave.setExit("south", beach);
        cave.setExit("up", sacrificialSite);

        beach.setExit("north", cave);

        cellar.setExit("west", secretPassage);
        cellar.setExit("up", templePyramid);

        wizardsRoom.setExit("down", templePyramid);

        player.goTo(marketsquare);  // start game on marketsquare
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() throws ItemNotFoundException, ItemTooHeavyException
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printRoomInformation();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */

    // Für Teil 5 sollte ich das Command "look" hinzufügen, um der Beschreibung des Rooms noch mal ansehen zu können.
    // Und einfach das neue Command zu sehen.
    private boolean processCommand(Command command) throws ItemNotFoundException, ItemTooHeavyException
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("look")) {
            look();
        }
        else if (commandWord.equals("take")) {
            takeItem(command);
        }
        else if (commandWord.equals("drop")) {
            dropItem(command);

        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    // Zur Aufgabe 5 sollte ich hier die Methode "showCommands" aufrufen.
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("through the jungle. At once there is a glade. You need to look around...");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(parser.showCommands());
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            player.goTo(nextRoom);
            printRoomInformation();
        }
    }
        //(Teil 4) Hier
    private void printRoomInformation() {
        System.out.println (player.getCurrentRoom().getLongDescription());
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    //In Teil 5 habe ich die Methode "look" geschrieben, um die "description" aus der Klasse Room aufzurufen.
    private void look() {
        System.out.println(player.getCurrentRoom().getLongDescription());
    }
    // Für Teil 7 habe ich die Möglichkeit erstellt, die Gegenstände mitzunehmen. Hier habe ich verschiedene Möglichkeiten beschreiben.
    private void takeItem (Command command) throws ItemNotFoundException, ItemTooHeavyException {
        if (!command.hasSecondWord()) {
            System.out.println("Take what?");
            return;
        }
        String itemName = command.getSecondWord();

        Item item = player.getCurrentRoom().getItem(itemName);

        if (item == null) {
            throw new ItemNotFoundException("There is no item like this");
        } else {
            try {
                player.getCurrentRoom().removeItem(itemName);
                player.takeItem(item);
                System.out.println("You took the item!");
            } catch (ItemTooHeavyException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(player.showStatus());
        System.out.println(player.getCurrentRoom().getLongDescription());
    }


    // Für Teil 7 habe ich die Möglichkeit erstellt, die Gegenstände in den Raum zu legen.
    private void dropItem (Command command) throws ItemNotFoundException {
        if (!command.hasSecondWord()) {
            System.out.println("Drop what? ");
            return;
        }

        String itemName = command.getSecondWord();

        try {
            Item item = player.dropItem(itemName);
            player.getCurrentRoom().putItem(itemName, item.description, item.getWeight());
            System.out.println("You dropped the " + itemName + ".");
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }
            System.out.println(player.showStatus());
            System.out.println(player.getCurrentRoom().getLongDescription());
    }
}
