/**
 * The class Item has a description, name and weight for a new item.
 * 
 * @author Nick Anbergen
 * @version 2020.1.21
 */
public class Item
{
    private String description;
    private String name;
    private int weight;
    /**
     * @author Nick Anbergen
     * @version 2020.1.21
     * 
     * Constructor for objects of class Item
     */
    public Item(String description, String name, int weight)
    {
        // initialise instance variables
        this.description = description;
        this.name = name;
        this.weight = weight;
    }

    /**
     * @author Nick Anbergen
     * @version 2020.1.21
     * 
     * @return the description of the an item
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @author Nick Anbergen
     * @version 2020.1.21
     * 
     * @return the name of an item
     */
    public String getName()
    {
        return name;
    }

    /**
     * @author Nick Anbergen
     * @version 2020.1.21
     * 
     * @return the weight of an item
     */
    public int getWeight()
    {
        return weight;
    }
}
