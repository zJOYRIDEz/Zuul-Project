import java.util.*;
import java.util.Iterator;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Nick Anbergen
 * @version 2020.1.23
 */
public class Room 
{
    private Item item;
    private String description;
    private HashMap<String, Room> exits;
    private HashMap<String, Item> items;
    /**
     * @author Nick Anbergen
     * @version 2020.1.18
     * 
     * Create a room
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        items = new HashMap<String, Item>();
    }

    /**
     * @author Nick Anbergen
     * @version 2020.1.18
     * 
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @author Nick Anbergen
     * @version 2020.18.1
     * 
     * Adds an item to the specified room, this item has a name, description and weight
     * in unspecified units.
     */
    public void addItem(String itemName, String description, int itemWeight)
    {
        Set<String> keys = items.keySet();
        for(String item : keys)
            if(item.equals(itemName))
                return;

        Item newItem = new Item(description, itemName, itemWeight);
        items.put(itemName, newItem);
    }

    /**
     * @author Nick Anbergen
     * @version 2020.1.18
     * 
     * @return the string for an item.
     */
    public String getItemString()
    {
        String returnString = "Items:";
        Set<String> keys = items.keySet();
        for(String item : keys)
            returnString += " " + item;
        return returnString;
    }

    /**
     * @author Nick Anbergen
     * @version 2020.1.18
     * 
     * @return an item from the HashMap which equals the specified name
     */
    public Item getItem(String name)
    {
        return items.get(name);
    }

    /**
     * @author Nick Anbergen
     * @version 2020.1.18
     * 
     * @return the description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @author Nick Anbergen
     * @version 2020.1.18
     * 
     * @return the description of a room, together with its exits and items
     */
    public String getLongDescription()
    {
        return "You are " + description + "\n" + getExitString() + "\n" + getItemString();
    }

    /**
     * @author Nick Anbergen
     * @version 2020.1.18
     * 
     * @return the exit directions of a room
     */
    public Room getExit(String direction)
    {
        return exits.get(direction);
    }

    /**
     * @author Nick Anbergen
     * @version 2020.1.18
     * returns a String containing the exits of a room
     * For Example: "Exits: north west"
     * @return A description of the existing exits of a room
     */
    public String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys)
        {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * @author Nick Anbergen
     * @version 2020.1.23
     * 
     * Deletes the item that corresponds to the entered String at Game.takeItem()
     */
    public void delItem(String name)
    {
        items.remove(name);
    }
}
