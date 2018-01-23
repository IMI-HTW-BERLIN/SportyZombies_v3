import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
/**
 * Class Location - a Location in an adventure game.
 *
 * This class is part of the "SportyZombies" application. 
 * "SportyZombies" is a very simple, text based adventure game.  
 *
 * A "Location" represents one location in the scenery of the game.  It is 
 * connected to other Locations via exits.  The exits are stored in a Map.
 * Each map entry consists of an exit name and the Location the exit leads to.
 * 
 * @author  David Panagiotopulos and Luis Hankel
 * @version 2018.01.15
 */
public class Location 
{
    private HashMap<String, Location> exits;
    private String description;
    private ArrayList <Item> items;

    /**
     * Create a Location described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The Location's description.
     */
    public Location(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        items = new ArrayList <>();
    }
    
    public void addItem (Item item) 
    {
        items.add(item);
    }
    
    public void addItems (List<Item> item) 
    {
        items.addAll(item);
    }
    
    /**
     * Add an exit to the Location.
     */
    public void addExit(String name, Location exit) 
    {
        exits.put(name, exit);
    }

    /**
     * @return The description of the Location.
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * Gets the long description of the Location. It consists of the description 
     * plus a list of all exits.
     * @return The long description of the Location.
     */
    public String getLongDescription()
    {
        return "You are " + getDescription() + "\n" + getExits(); 
    }
    
    /**
     * Gets a list of all exits available for this Location.
     * @return The exits available.
     */
    public String getExits(){
        String allExits = "Exits: ";
        for(String name : exits.keySet()){
            allExits += name + ", ";
        }
        allExits = allExits.replaceAll(", $","");
        
        return allExits;
    }
    public String getItems(){
        String labelItems = "Items: ";
        for(Item item: items){
            labelItems += item.getName()+ "|";
        }
        for(Item item: items){
            labelItems += "\n"+item.getDescription() ;
        }
        
        return labelItems;
    }
    
    public boolean containsItem(String itemName){
        for(Item item: items){
            if(item.getName().equalsIgnoreCase(itemName)) return true;
        }
        return false;
    }
    
    public Item takeItem(String itemName){
        Item foundItem = null;
        for(Item item: items){
            if(item.getName().equalsIgnoreCase(itemName)){
                foundItem = item;
                items.remove(item);
                break;
            }
        }
        return foundItem;
    }
    
    /**
     * Gets the Location an exit leads to.
     * @return The Location the exit leads to.
     */
    public Location getExit(String exit){
        return exits.get(exit);
    }
}
