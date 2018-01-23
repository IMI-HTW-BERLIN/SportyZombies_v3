
/**
 * The Items that are stored in Room
 *
 * @author Ema Stoyanova and Jacob-Benedikt Haut
 * @version 20.01.2018
 */
public class Item
{
    
    private String name;
    private int weight;
    private String description;

    /**
     * Constructor for objects of class Items
     */
    public Item(String name,int weight,String description)
    {
        this.weight = weight;
        this.name = name;
        this.description =description;
    }
    
    public String getDescription ()
    {
        return description;
     
    }
    
    public String getName ()
    {
        return name;
     
    }
    public int getWeight ()
    {
        return weight;
    }
  
}
