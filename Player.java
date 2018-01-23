import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player
{
    private HashSet<Item> inventory;
    private int strength;

    /**
     * Constructor for objects of class Player
     */
    public Player(){
        inventory = new HashSet<>();
        strength = 10000;
    }
    
    public boolean addItem(Item item){
        if(item.getWeight() + inventory.stream().map(i -> i.getWeight()).
        reduce(0, (item1, item2) -> item1 + item2) <= strength){
            inventory.add(item);
            return true;
        }
        return false;
    }
    
    public boolean hasItem(String itemName){
        for(Item item: inventory){
            if(item.getName().equalsIgnoreCase(itemName)) return true;
        }
        return false;
    }
    
    public Item dropItem(String itemName){
        Item foundItem = null;
        for(Item item: inventory){
            if(item.getName().equalsIgnoreCase(itemName)){
                foundItem = item;
                inventory.remove(item);
                break;
            }
        }
        return foundItem;
    }
    
    public List<Item> dropAll(){
        List<Item> itemList = new ArrayList<> (inventory);
        inventory.removeAll(inventory);
        return itemList;
    }
}
