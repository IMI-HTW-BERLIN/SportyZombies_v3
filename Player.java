import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

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
    private Location currentLocation;
    private Stack<Location> history;

    /**
     * Constructor for objects of class Player
     */
    public Player(){
        inventory = new HashSet<>();
        history = new Stack<>();
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
    
    public String listItems() {
        StringBuilder sb = new StringBuilder("Inventory:\n==========\n");
        for (Item item : inventory) {
            sb.append(item.getName()).append("\n");
        }
        sb.append("\nTotal weight: ").append(
            inventory.stream().mapToInt(i -> i.getWeight()).sum()
        );
        return sb.toString();
    }
    
    public Location getLocation() {
        return currentLocation;
    }
    
    public void setLocation(Location location) {
        this.currentLocation = location;
    }
    
    public void getLocationFromHistory() {
        setLocation(history.pop());
    }
    
    public void addLocationToHistory() {
        history.push(getLocation());
    }
    
    public boolean isHistoryEmpty() {
        return history.empty();
    }
}
