import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public abstract class Entity
{
    private Location location;
    private Set<Item> inventory;
    
    public Entity() {
        this.inventory = new HashSet<>();
    }
    
    /**
     * Gets the location of the entity.
     * @return The current location
     */
    public Location getLocation() {
        return this.location;
    }
    
    /**
     * Sets the location of the entity to the specified location.
     * @param location The new location
     */
    public void setLocation(Location location) {
        this.location = location;
    }
    
    /**
     * Gets the inventory of the entity.
     * @return The entity's inventory
     */
    public Set<Item> getInventory() {
        return this.inventory;
    }
    
    /**
     * Adds an item to the entity's inventory.
     * @param item The item to add
     * @return True if the operation was successful
     */
    public boolean addItem(Item item) {
        return inventory.add(item);
    }
    
    /**
     * Gets an item from the inventory.
     * @param itemName The name of the item to get
     * @return The item
     */
    public Item getItem(String itemName) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }
    
    /**
     * Checks if the entity has the specified item in its inventory.
     * @param itemName The item to check for
     * @return True if the item was found
     */
    public boolean hasItem(String itemName) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(itemName)) return true;
        }
        return false;
    }
    
    /**
     * Drops an item from the entity's inventory.
     * @param itemName The item to drop
     * @return The dropped item
     */
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
    
    /**
     * Drops all items from the entity's inventory.
     * @return A list containing all dropped items
     */
    public List<Item> dropAll(){
        List<Item> itemList = new ArrayList<> (inventory);
        inventory.removeAll(inventory);
        return itemList;
    }
}
