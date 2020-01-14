import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;
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
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class Room 
{
    private String description;
    private HashMap<String, Room> exits;
    private HashMap<Item, Room> items;
    /**
     * Create a room
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        items = new HashMap<>();
    }
    
    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }
    
    /**
     * Define the item and where it is on the map
     */
    public void setItem(Item item, Room container)
    {
        items.put(item, container);
    }
    
    /**
     * Get the information about an item.
     */
    public String getItem()
    {
        String itemString = null;
        Set<Item> keys = items.keySet();
        for(Item items : keys)
        {
            itemString =items + " ";
        }
        return itemString;
    }
   
    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
    
    public String getLongDescription()
    {
        return "You are " + description + "\n" + getExitString();
    }

    public Room getExit(String direction)
    {
        return exits.get(direction);
    }
    
    /**
     * Retourneer een string met daarin de uitgangen van de ruimte.
     * Bijvoorbeeld: "Exits: north west"
     * @return Een omschrijving van de aanwezige uitgangen in de ruimte
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
}
