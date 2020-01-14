import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

public class Item
{
    private String itemDescription;
    private int weight;
    
    /**
     * Maak een nieuwe item met een description en weight
     */
    public Item(String itemDescription, int itemWeight)
    {
        this.itemDescription = itemDescription;
        weight = itemWeight;
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
        return "Item: " + itemDescription + " Weight: " + weight;
    }
}
