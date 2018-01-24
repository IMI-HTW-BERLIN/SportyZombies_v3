import java.util.Stack;

/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player extends Entity
{
    private int strength;
    private Stack<Location> history;

    /**
     * Constructor for objects of class Player
     */
    public Player(){
        history = new Stack<>();
        strength = 10000;
    }
    
    public boolean addItem(Item item){
        if(item.getWeight() + getInventory().stream()
            .mapToInt(i -> i.getWeight()).sum() <= strength){
            addItem(item);
            return true;
        }
        return false;
    }
    
    public String listItems() {
        StringBuilder sb = new StringBuilder("Inventory:\n==========\n");
        for (Item item : getInventory()) {
            sb.append(item.getName()).append("\n");
        }
        sb.append("\nTotal weight: ").append(
            getInventory().stream().mapToInt(i -> i.getWeight()).sum()
        );
        return sb.toString();
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
