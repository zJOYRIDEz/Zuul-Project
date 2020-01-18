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
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game 
    {
    private Parser parser;
    private Room previousRoom;
    private Stack<Room> roomHistory;
    private Room currentRoom;
    private Item item;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
       createRooms();
       parser = new Parser();
       roomHistory =  new Stack<Room>();
    }
    
    /**
     * Create all the rooms and link their exits together. Also create items
     */
    private void createRooms()
    {
       Room darkRoom, darkHallway, diningRoom;
       Item ironSword, woodenShield;
    
       //create the items
       ironSword = new Item("Iron Sword", "A standard sword made of iron, it looks like it could've been used by a soldier", 1500, 15);
       woodenShield = new Item("Wooden Shield", "A worn and almost rotten wooden shield", 2000, 5);
       // create the rooms
       darkRoom = new Room("in a dark room, the air heavy with the stink of sewage");
       darkHallway = new Room("in a dark hallway, dimly lit by torches");
       diningRoom = new Room("in what appears to be some sort of dining room, as you hear footsteps approaching you scramble to find a hiding spot."); 
      
       // initialise room exits
       darkRoom.setExit("north", darkHallway);
       
       darkHallway.setExit("north", diningRoom);
       darkHallway.setExit("south", darkRoom);
       
       diningRoom.setExit("south", darkHallway);
       // initialise room items
       darkRoom.setItem(ironSword, darkRoom);
       darkRoom.setItem(woodenShield, darkRoom);
       
       previousRoom = darkRoom; // sets the previous room
       currentRoom = darkRoom;  // start game in the first room
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
        else if (commandWord.equals("look")) {
            look();
        }
        else if (commandWord.equals("pickUp")) {
            pickUp();
        } else if (commandWord.equals("back")) {
            goBack();
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
        System.out.println("around at the university.");
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
    
   private void look()
   {
        System.out.println(currentRoom.getLongDescription());
        if(currentRoom.getItem() != null)
        {
            for(int i = 1; i < 2; i++)
            {
            System.out.println("This room contains an item.");
            System.out.println(currentRoom.getItem());
            }
        } else {
            System.out.println("This room does not contain an item.");
        }
   }
    
   private void pickUp()
   {
        if(currentRoom.getItem() != null)
        {
            System.out.println("You have picked up the item.");
            System.out.println(currentRoom.getItem() + " has been added to your inventory.");
        } else {
            System.out.println("This room does not contain an item.");
        }
   }
   
   private void enterRoom(Room nextRoom)
   {
       previousRoom = currentRoom;
       currentRoom = nextRoom;
       System.out.println(currentRoom.getLongDescription());
   }
   
   private void goBack()
   {
       if(previousRoom == null) {
           System.out.println("You have nowhere to go back to.");
       } else {
           currentRoom = roomHistory.pop();
           System.out.println(currentRoom.getLongDescription());
       }
   }
}
