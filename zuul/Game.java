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
     * @author Jesse Kroes
     * @version 2020.1.22
     * make the game playable without bluej
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }

    /**
     * @author Jesse Kroes
     * @version 2020.1.22
     * Create all the rooms and link their exits together. Also create items
     */
    private void createRooms()
    {
        Room darkRoom, darkHallway, diningRoom, basement, kitchen, garden, prison, gate, freedom;

        // create the rooms
        darkRoom = new Room("in a dark room, the air heavy with the stink of sewage. \n There is a lever here, you wonder what it could activate");
        darkHallway = new Room("in a dark hallway, dimly lit by torches");
        diningRoom = new Room("in what appears to be some sort of dining room \n Your eyes wander to something shiny lying on the carpet, perhaps you should check it."); 
        basement = new Room("suddenly in a dirty, foul smelling basement");
        kitchen = new Room("in what appears to be the kitchen");
        garden = new Room("in a garden full of dead plants");
        prison = new Room("in a cold, creepy prison");
        gate = new Room("in front of an ominous gate, perhaps this is the exit");
        freedom = new Room("running from the mansion, freedom at last. No one's gonna believe this back at the pub");

        // initialise room exits
        darkRoom.setExit("pull" , basement);
        darkRoom.setExit("darkhallway", darkHallway);
        darkRoom.setExit("diningroom", diningRoom);
        darkRoom.setExit("prison", prison);
        darkRoom.setExit("garden", garden);
        darkRoom.setExit("kitchen", kitchen);
        darkRoom.setExit("basement", basement);

        darkHallway.setExit("darkroom", darkRoom);
        darkHallway.setExit("diningroom", diningRoom);
        darkHallway.setExit("prison", prison);
        darkHallway.setExit("garden", garden);
        darkHallway.setExit("kitchen", kitchen);
        darkHallway.setExit("basement", basement);

        diningRoom.setExit("check", basement);
        diningRoom.setExit("darkroom", darkRoom);
        diningRoom.setExit("darkhallway", darkHallway);
        diningRoom.setExit("prison", prison);
        diningRoom.setExit("garden", garden);
        diningRoom.setExit("kitchen", kitchen);
        diningRoom.setExit("basement", basement);

        kitchen.setExit("darkroom", darkRoom);
        kitchen.setExit("darkhallway", darkHallway);
        kitchen.setExit("diningroom", diningRoom);
        kitchen.setExit("basement", basement);
        kitchen.setExit("garden", garden);
        kitchen.setExit("prison", prison);

        garden.setExit("darkroom", darkRoom);
        garden.setExit("gate", gate);
        garden.setExit("darkhallway", darkHallway);
        garden.setExit("diningroom", diningRoom);
        garden.setExit("kitchen", kitchen);
        garden.setExit("prison", prison);
        garden.setExit("basement", basement);

        prison.setExit("darkroom", darkRoom);
        prison.setExit("darkhallway", darkHallway);
        prison.setExit("diningroom", diningRoom);
        prison.setExit("kitchen", kitchen);
        prison.setExit("basement", basement);
        prison.setExit("garden", garden);

        basement.setExit("darkroom", darkRoom);
        basement.setExit("darkhallway", darkHallway);
        basement.setExit("diningroom", diningRoom);
        basement.setExit("kitchen", kitchen);
        basement.setExit("prison", prison);
        basement.setExit("garden", garden);

        gate.setExit("leave", freedom);

        // initialise room items
        darkRoom.addItem("note1", " I am writing these notes to keep track of the events unfolding from this point. \n I am the butler of this place; \n This morning when I headed out to buy groceries, we found all the doors of the castle locked, \n we tried to unlock them, but our attempts were futile, \n we are currently all gathered in the dining room thinking about what to do next. \n God help us.",1);

        darkHallway.addItem("note2", " After 5 days we still haven’t been saved, why is it taking them so long? \n Someone has surely alerted the police, right? \n Each day I lose a little bit of hope, if this continues, we won't last long. \n We will not have enough food for another week.", 1);

        diningRoom.addItem("note3", " Today we gave away the last bit of rations. \n All the food is completely gone, all our bread is gone, we only have a little bit of water left. \n I hope we survive this, but I have little hope. ", 1);

        kitchen.addItem("note4" ," Tensions are rising, everyone is accusing one another for being the person behind all of this. \n They have even gone as far to jail one of the stable boys. \n If we don’t get saved soon, I fear it will be an all-out war in here. ", 1);

        basement.addItem("note5" ," Everyone snapped, they all started attacking each other, I am currently hiding. \n I don’t think I will survive this. If someone reads this, please tell my family I love them. \n I fear for my live ", 1);

        prison.addItem("note6" ," The note is covered with dirt and blood, you can barely make out what it says: \n H E L P ", 1);

        
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
     * @author Nick Anbergen, Jesse Kroes
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
            case TELEPORT:
            teleportRoom(command);
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
     *@author Jesse Kroes
     *@version 2020.1.20
     * Try to teleport to another room. If it is possible, enter
     * the new room, otherwise print an error message.
     */
    private void teleportRoom(Command command)
    {
        if(!command.hasSecondWord()){
            System.out.println("Teleport to where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (!roomHistory.contains(nextRoom)) {
            System.out.println("You can't teleport to that location right now!");
        } 
        else{
            roomHistory.push(currentRoom);
            currentRoom = nextRoom;
            System.out.println(currentRoom.getTeleportInfo());
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