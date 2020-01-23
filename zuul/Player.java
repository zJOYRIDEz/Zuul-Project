import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
/**
 * class Player
 * Contains the inventory and the information about the weight of the player.
 *
 * @author Nick Anbergen
 * @version 2020.1.21
 */
public class Player
{
    // instance variables - vervang deze door jouw variabelen
    private Item item;
    private Room currentRoom;
    private HashMap<String, Item> inventory;
    private int maxWeight;
    private int currentWeight;
    /**
     * @author Nick Anbergen
     * @version 2020.1.18
     * 
     * Constructor for object of class Player
     */
    public Player(int mWeight, int cWeight)
    {
        maxWeight = 150;
        currentWeight = 0;
        inventory = new HashMap<String, Item>();
        currentRoom = new Room("Room");
    }

    /**
     * @author Nick Anbergen
     * @version 2020.1.18
     * 
     * Returns the current room of the player;
     */
    public Room getCurrentRoom()
    {
        return currentRoom;
    }

    /**
     * @authorNick Anbergen
     * @version 2020.1.18
     * 
     * Sets the current room of the player
     */
    public void setCurrentRoom(Room newRoom)
    {
        currentRoom = newRoom;
    }

    /**
     * @author Nick Anbergen
     * @version 2020.1.18
     * 
     * Take the information about the item specified in game.takeItem(),
     * firstly it looks through the inventory to check if the player already has one
     * if it passes the check then it will add the item to the inventory if the player has
     * enough available weight, if this is not the case it will display an error.
     * 
     */
    public void addInventory(String name, String description, int weight)
    {
        Set<String> keys = inventory.keySet();
        for(String item : keys) {
            if (item.equals(name))
            {
                System.out.println("We've already got one!");
                return;
            }   
        }
        Item newItem = new Item(description, name, weight);

        currentWeight += newItem.getWeight();
        if(currentWeight < maxWeight)
        {
            inventory.put(name, newItem);
        }
        else 
        {
            System.out.println("You are too heavy too pick up this item.");
        }
    }

    /**
     * @author Nick Anbergen
     * @version 2020.1.18
     * 
     * Adds an item directly into the inventory
     */
    public void addInventory(Item item)
    {
        inventory.put(item.getName(), item);
    }

    /**
     * @author Nick Anbergen
     * @version 2020.1.21
     * 
     * drops a specific item from the inventory
     */
    public Item dropInventory(String name)
    {
        Set<String> keys = inventory.keySet();
        for(String item : keys) {
            if (item.equals(name))
            {
                Item temp = inventory.get(name);
                inventory.remove(name);
                return temp;

            }   
        }
        System.out.println("We've haven't got one!");
        return null;

    }

    /**
     * @author Nick Anbergen
     * @version 2020.1.21
     * 
     * Drops all the items from the inventory;
     */
    public Item dropInventory()
    {
        Item temp = inventory.entrySet().iterator().next().getValue();
        inventory.clear();
        return temp;
    }

    /**
     * @author Nick Anbergen
     * @version 2020.1.21
     * 
     * @return the description of a specified item from the description
     * If the player attempts to examine an item not in his/her inventory an error will be returned
     */
    public String getExamineString(String name)
    {
        String returnString = "You examine the " + name + "\n";
        Item temp = inventory.get(name);
        if (temp != null) {
            returnString += "It's " + temp.getDescription() + ".";
            return returnString;
        }
        return "You can only examine items in your inventory.";
    }

    /**
     * @author Nick Anbergen
     * @version 2020.1.21
     * 
     * @return all the items from the inventory of the player
     */
    public String getInventoryString()
    {
        String returnString = "Inventory:";
        Set<String> keys = inventory.keySet();
        for(String item : keys)
            returnString += " " + item;
        return returnString;
    }
}
