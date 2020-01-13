import java.util.ArrayList;
import java.util.HashMap;

public class Item
{
    private String itemDescription;
    private int itemWeight;
    private HashMap items;
    /**
     * Maak een nieuwe item met een description en weight
     */
    public Item(String itemDescription, int itemWeight)
    {
        this.itemDescription = itemDescription;
        this.itemWeight = itemWeight;
        items = new HashMap();
    }
}
