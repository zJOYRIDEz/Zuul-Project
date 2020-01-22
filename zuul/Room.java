import java.util.*;
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
    private HashMap<String, Item> items;
    //private HashMap<Item, Room> itemLocations;
    /**
     * Create a room
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        items = new HashMap<String, Item>();
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
     * Get the information about an item.
     */
    public String getItemString()
    {
        String returnString = "Items:";
        Set<String> keys = items.keySet();
        for(String item : keys)
            returnString += " " + item;
        return returnString;
    }
    
    public Item getItem(String name)
    {
        return items.get(name);
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
        return "You are " + description + "\n" + getExitString() + "\n" + getItemString();
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
        Set<String> keys = items.keySet();
        for(String item : keys) {
            if (item.equals(name))
            {
                Item temp = items.get(name);
                items.remove(name);
                return temp;
            }            
        }
        System.out.println("That isn't here.");
        return null;
    }
}
