package de.szut.zuul;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
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

        // initialise room exits
/*      marketsquare.setExits(tavern, templePyramid, null, sacrificialSite, null, null);
        templePyramid.setExits(null, wizardsRoom, null, marketsquare, hut, cellar);
        tavern.setExits(null, hut, marketsquare, null, null, null);
        sacrificialSite.setExits(null, marketsquare, null, null, null, cave);
        hut.setExits(null, jungle, templePyramid, tavern, null, null);
        jungle.setExits(null, null, null, hut, null, null);
        secretPassage.setExits(null, cellar, null, cave, null, null);
        cave.setExits(null, secretPassage, beach, null, sacrificialSite, null);
        beach.setExits(cave, null, null, null, null, null);
        //Ich habe hier Richtungen erstellt.
        cellar.setExits(null, null, null, secretPassage, templePyramid, null);
        wizardsRoom.setExits(null, null, null, null, null, templePyramid);*/

        // Für dritte Teil sollte ich Aufruf der Methode "setExist" auf "setExit" ändern, weil die erste Methode nicht meer funktioniert.
        marketsquare.setExit("north", tavern);
        marketsquare.setExit("east", templePyramid);
        marketsquare.setExit("south", null);
        marketsquare.setExit("west", sacrificialSite);
        marketsquare.setExit("up", null);
        marketsquare.setExit("down", null);

        templePyramid.setExit("north", hut);
        templePyramid.setExit("east", null);
        templePyramid.setExit("south", null);
        templePyramid.setExit("west", marketsquare);
        templePyramid.setExit("up", wizardsRoom);
        templePyramid.setExit("down", cellar);

        tavern.setExit("north", null);
        tavern.setExit("east", hut);
        tavern.setExit("south", marketsquare);
        tavern.setExit("west", null);
        tavern.setExit("up", null);
        tavern.setExit("down", null);

        sacrificialSite.setExit("north", null);
        sacrificialSite.setExit("east", marketsquare);
        sacrificialSite.setExit("south", null);
        sacrificialSite.setExit("west", null);
        sacrificialSite.setExit("up", null);
        sacrificialSite.setExit("down", cave);

        hut.setExit("north", null);
        hut.setExit("east", jungle);
        hut.setExit("south", templePyramid);
        hut.setExit("west", tavern);
        hut.setExit("up", null);
        hut.setExit("down", null);

        jungle.setExit("north", null);
        jungle.setExit("east", null);
        jungle.setExit("south", null);
        jungle.setExit("west", hut);
        jungle.setExit("up", null);
        jungle.setExit("down", null);

        secretPassage.setExit("north", null);
        secretPassage.setExit("east", cellar);
        secretPassage.setExit("south", null);
        secretPassage.setExit("west", cave);
        secretPassage.setExit("up", null);
        secretPassage.setExit("down", null);

        cave.setExit("north", null);
        cave.setExit("east", secretPassage);
        cave.setExit("south", beach);
        cave.setExit("west", null);
        cave.setExit("up", sacrificialSite);
        cave.setExit("down", null);

        beach.setExit("north", cave);
        beach.setExit("east", null);
        beach.setExit("south", null);
        beach.setExit("west", null);
        beach.setExit("up", null);
        beach.setExit("down", null);

        cellar.setExit("north", null);
        cellar.setExit("east", null);
        cellar.setExit("south", null);
        cellar.setExit("west", secretPassage);
        cellar.setExit("up", templePyramid);
        cellar.setExit("down", null);

        wizardsRoom.setExit("north", null);
        wizardsRoom.setExit("east", null);
        wizardsRoom.setExit("south", null);
        wizardsRoom.setExit("west", null);
        wizardsRoom.setExit("up", null);
        wizardsRoom.setExit("down", templePyramid);

        currentRoom = marketsquare;  // start game on marketsquare
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
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
    private boolean processCommand(Command command) 
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

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("through the jungle. At once there is a glade. On it there a buildings...");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("   go quit help");
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
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            printRoomInformation();
        }
    }

    private void printRoomInformation() {
        System.out.println("You are " + currentRoom.getDescription());
        System.out.print("Exits: " + currentRoom.exitsToString());
        System.out.println();
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
}
