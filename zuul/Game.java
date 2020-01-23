import java.util.*;
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
 * @author  Nick Anbergen
 * @version 2020.1.23
 */

public class Game 
{
    private Parser parser;
    private Room previousRoom;
    private Stack<Room> roomHistory;
    private Room currentRoom;
    private Player playerCommands;
    private StringLRoom stringL;
    private Language language;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        playerCommands = new Player(3, 0);
        stringL = new StringLRoom();
        parser = new Parser();
        roomHistory =  new Stack<Room>();
        createRooms();
    }

    /**
     * Create all the rooms and link their exits together. Also create items
     */
    private void createRooms()
    {
        Room darkRoom, darkHallway, diningRoom;
        
        // create the rooms
        darkRoom = new Room("in a dark room, the air heavy with the stink of sewage");
        darkHallway = new Room("in a dark hallway, dimly lit by torches");
        diningRoom = new Room("in what appears to be some sort of dining room"); 

        // initialise room exits
        darkRoom.setExit("north", darkHallway);

        darkHallway.setExit("north", diningRoom);
        darkHallway.setExit("south", darkRoom);

        diningRoom.setExit("south", darkHallway);
        
        // initialise room items
        darkRoom.addItem("note1", "I bet you don't know what's going on now do you? \nWell, not to worry, I've got you covered. \nIn here, I have scattered notes for you to find, it will explain who you are and why you are here. \nGood luck",1);
        
        darkHallway.addItem("note2", "You see, there is something peculiar about seeing someone traverse an unknown space. \nSometimes they actually explore. \nSometimes they stay put. \nSomething I've learned over the years is that humans truly are unpredictable when isolated", 1);
        
        diningRoom.addItem("note3", "When I found you, there really didn't seem to be much special about you. \nBut the more I observed you, the more I became convinced you were the real deal.", 1);
        
        previousRoom = darkRoom; // sets the previous room
        currentRoom = darkRoom;
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
        System.out.println("Thank you for playing, goodbye!");
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

        CommandWord commandWord = command.getCommandWord();
        //String commandWord = command.getCommandWord();
        switch (commandWord)
        {
            case UNKNOWN:
            System.out.println("I don't know what you mean...");
            break;
            case HELP:
            printHelp();
            break;
            case GO:
            goRoom(command);
            break;
            case QUIT:
            wantToQuit = quit(command);
            break;
            case LOOK:
            look();
            break;
            case EXAMINE:
            examine(command);
            break;
            case TAKE:
            takeItem(command);
            break;
            case DROP:
            dropItem(command);
            break;
            case BACK:
            goBack();
            break;
            case INVENTORY:
            checkInventory();
            break;
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
        System.out.println("You wake up in a dark room, unfamiliar to you");
        System.out.println("You don't know where you are, you just know that you are.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
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
        } else {
            roomHistory.push(currentRoom);
            currentRoom = nextRoom;
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

    /**
     * @author Nick Anbergen
     * @version 2020.1.19
     * Prints out the long description of the room, will also tell you if a room contains an item or not
     * If the room DOES contain an item it will also show the name of the item so the player can pick it up
     */
    private void look()
    {
        System.out.println(currentRoom.getLongDescription());
        if(currentRoom.getItemString() != null)
        {
            {
                System.out.println("This room contains an item.");
                System.out.println(currentRoom.getItemString());
            }
        } else {
            System.out.println("This room does not contain an item.");
        }
    }

    /**
     * @author Nick Anbergen, Jesse Kroes
     * @version 2020.1.20
     * Uses a stack to return to previous visited room(s).
     */
    private void goBack()
    {
        if(previousRoom == null) {
            System.out.println("You have nowhere to go back to.");
        } else {
            currentRoom = roomHistory.pop();
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /**
     * @author @Nick Anbergen
     * @version 2020.1.20
     * It browses through the inventory and prints all of the items in the players' inventory
     */
    private void checkInventory()
    {
        System.out.println(playerCommands.getInventoryString());
    }

    /**
     * @author Nick Anbergen
     * @version 2020.1.21
     * Picks up the item and adds it to the inventory of the player.
     */
    private void takeItem(Command command)
    {
        if(!command.hasSecondWord()) {
            System.out.println("Take what?");
            return;
        }

        String desiredItem = command.getSecondWord();

        // Remove it from the room's items
        Item temp = currentRoom.getItem(desiredItem);

        if (temp != null)
        {     
            // Add it to player's inventory
            playerCommands.addInventory(temp.getName(), temp.getDescription(), temp.getWeight());

            //Remove the item from the room after it has been picked up.
            currentRoom.delItem(desiredItem);
            // Refresh inventory
            System.out.println(currentRoom.getLongDescription());
            System.out.println(playerCommands.getInventoryString());
        }
    }

    /**
     * @author Nick Anbergen
     * @version 2020.1.21
     * Drops the specified item from the inventory and then puts it back into the room.
     */
    private void dropItem(Command command)
    {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know what to drop...
            System.out.println("Drop what?");
            return;
        }

        String droppedItem = command.getSecondWord();

        // Drop the speicified item

        Item temp = playerCommands.dropInventory(droppedItem);
        if (temp != null)
        {

            // Add it to the room's items
            currentRoom.addItem(temp.getName(), temp.getDescription(), temp.getWeight());

            // Refresh inventory
            System.out.println(currentRoom.getLongDescription());
            System.out.println(playerCommands.getInventoryString());
        }      

    }

    /**
     * @author Nick Anbergen
     * @version 2020.1.23
     * 
     * Calls the command Item.getExamineString, which returns all the information about an
     * item including its name, description and weight.
     */
    private void examine(Command command)
    {
        if(!command.hasSecondWord())
        {
            System.out.println("Examine what?");
            return;
        }
        
        String itemToExamine = command.getSecondWord();
        
        System.out.println(playerCommands.getExamineString(itemToExamine));
    }
}