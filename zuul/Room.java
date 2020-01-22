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
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class Room 
{
    private Item item;
    private String description;
    private HashMap<String, Room> exits;
    //private HashMap<String, Item> items;
    private ArrayList<Item> items;
    //private HashMap<Item, Room> itemLocations;
    /**
     * Create a room
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        //items = new HashMap<String, Item>();
        items = new ArrayList<>();
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
    
    //public void setItem(Item item, Room container)
    //{
    //    itemLocations.put(item, container);
    //}
    
    public void addItem(Item item)
    {
        items.add(item);
    }
    
    /**
     * Get the information about an item.
     */
    public void listItem(int index)
    {
        System.out.println("Item: " + items + ": ");
        Item item = items.get(index);
        System.out.println(item.getDescription());
    }
    
    public String getAllItems()
    {
       Iterator<Item> it = items.iterator();
       String returnString = "Items: ";
       while(it.hasNext())
       {
           Item i = it.next();
           String item = i.getDetails();
           returnString = item + " ";
       }
       return returnString;
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
        return "You are " + description + "\n" + getExitString() + "\n" + getAllItems();
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
    
    public Item delItem(String name)
    {
        Iterator<Item> it = items.iterator();
        while(it.hasNext())
        {
            Item i = it.next();
            String item = i.getName();
            if(item.equals(name))
            {
                it.remove();
            }
        }
        return null;
    }
    
        private boolean indexValid(int index)
        {
        // The return value.
        // Set according to whether the index is valid or not.
        boolean valid;
        
        if(index < 0) {
            System.out.println("Index cannot be negative: " + index);
            valid = false;
        }
        else if(index >= items.size()) {
            System.out.println("Index is too large: " + index);
            valid = false;
        }
        else {
            valid = true;
        }
        return valid;
    }
}
