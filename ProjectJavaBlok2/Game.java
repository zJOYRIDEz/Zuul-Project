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
    private Room previousRoom;
    private Room roomStack;
        
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
        Room firstRoom, litRoom, twilightRoom, swordRoom, placeholderRoom, basement;
      
        // create the rooms
        firstRoom = new Room("A dark room, the air heavy with sewage stink. ");
        litRoom = new Room("The room is lit by a single torch, yet gives no relief of the stink. ");
        twilightRoom = new Room("A corridor of twilight, you are faintly able to make out a humming noise in the distance. you see a carpet with something shiny lying on it, do you want to check it? ");
        swordRoom = new Room("The room is warm, an interesting contrast to the cold dampness of the previous rooms. The humming noise from before is almost deafening. You spot something glimmering in the room. ");
        placeholderRoom = new Room("This room is a placeholder!");
        basement = new Room("The carpet was a trap, as soon as you stepped on it you fell in a hole. you now find yourself in a dank and foul smelling basement with a staircase. ");
        
        
        //initialize the exits
        firstRoom.setExit("north", litRoom);
        
        litRoom.setExit("south", firstRoom);
        litRoom.setExit("north", twilightRoom);
        
        twilightRoom.setExit("south", litRoom);
        twilightRoom.setExit("north", swordRoom);
        twilightRoom.setExit("check", basement);
        
        swordRoom.setExit("south", twilightRoom);
        swordRoom.setExit("north", placeholderRoom);
        swordRoom.setExit("east", placeholderRoom);
        swordRoom.setExit("west", placeholderRoom);
        
      
        
        basement.setExit("up", firstRoom);
        
        currentRoom = firstRoom;  // start game in the first room
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
        System.out.println("Thank you for playing.");
    }
     
    private void checkRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Check what?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = null;
        if(direction.equals("carpet")) {
            System.out.println("There is a key here, do you want to take it?");
        }
        }
    
    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("The sunrays streak through the cracked roof." );
        System.out.println("your eyes slowly begin to open.");
        System.out.println("You find yourself in a unfamiliar room.");
        System.out.println("There is a single, distant light source.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
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
        else if(commandWord.equals("back"))  {
            backRoom(command);
        }
        // else command not recognised.
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
        System.out.println("You don't know where you are.");
        System.out.println("the only thing you know is that you are.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to in to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
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
        if(nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }
    
   
    private void backRoom(Command command)
    {
        Room previousRoom = currentRoom;
        if(previousRoom == null) {
            System.out.println("You can't go back!");
        } 
        else {
            currentRoom = previousRoom;
            System.out.println(currentRoom.getLongDescription());   
        }
    
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
