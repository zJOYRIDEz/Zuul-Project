public class Item
{
    private String description;
    private String name;
    private int weight;
    /**
     * Constructor for objects of class Item
     */
    public Item(String description, String name, int weight)
    {
        // initialise instance variables
        this.description = description;
        this.name = name;
        this.weight = weight;
    }


    public String getDescription()
    {
        return description;
    }
    
    public String getName()
    {
        return name;
    }
    
    public int getWeight()
    {
       return weight;
    }
}
