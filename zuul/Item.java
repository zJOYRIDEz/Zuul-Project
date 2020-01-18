import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

public class Item
{
    private String itemName;
    private String itemDescription;
    private int weight;
    private int itemDamage;
    
    /**
     * Maak een nieuwe item met een description en weight
     */
    public Item(String itemName, String itemDescription, int itemWeight, int itemDamage)
    {   
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.weight = itemWeight;
        this.itemDamage = itemDamage;
    }
    
    public String getShortItemDescription()
    {
        return itemDescription;
    }
    
    public int weight()
    {
        return weight;
    }
    
    public String toString()
    {
        return "Item: " + itemName + "\n" + "Description: " + itemDescription + "\n" + "Weight: " + weight + "\n" + "Damage: "+ itemDamage;
    }
}
