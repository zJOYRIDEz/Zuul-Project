import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
/**
 * class Player - geef hier een beschrijving van deze class
 *
 * @author Nick Anbergen
 * @version (versie nummer of datum)
 */
public class Player
{
    // instance variables - vervang deze door jouw variabelen
    private Item item;
    private Room currentRoom;
    private HashMap<String, Item> inventory;
    /**
     * Constructor voor objects van class Player
     */
    public Player()
    {
        inventory = new HashMap<String, Item>();
        currentRoom = new Room("starting room");
    }
    
    public Room getCurrentRoom()
    {
        return currentRoom;
    }
    
    public void setCurrentRoom(Room newRoom)
    {
        currentRoom = newRoom;
    }
    
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
        inventory.put(name, newItem);
    }
    
    public void addInventory(Item item)
    {
        inventory.put(item.getName(), item);
    }
    
    /**
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
     * Drops all the items from the inventory;
     */
    public Item dropInventory()
    {
        Item temp = inventory.entrySet().iterator().next().getValue();
        inventory.clear();
        return temp;
    }
    
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
    
    
    public String getInventoryString()
    {
        String returnString = "Inventory:";
        Set<String> keys = inventory.keySet();
        for(String item : keys)
            returnString += " " + item;
        return returnString;
    }
}
