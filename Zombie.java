import java.util.List;
import java.util.ArrayList;

public class Zombie extends Entity
{
    private static List<Zombie> instances = new ArrayList<>();
    
    private String name;
    private String speak;
    private Location location;
    
    public Zombie(String name, Location location, String speak) {
        this.name = name;
        this.location = location;
        this.speak = speak;
        instances.add(this);
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getSpeak() {
        return this.speak;
    }
    
    public Location getLocation() {
        return this.location;
    }
    
    public static Zombie getZombieInLocation(String name, Location location) {
        for (Zombie zombie : instances) {
            if (zombie.getName().equalsIgnoreCase(name) && zombie.getLocation().equals(location)) {
                return zombie;
            }
        }
        return null;
    }
    
    public static String getZombiesInLocation(Location location) {
        StringBuilder sb = new StringBuilder("NPCs:\n=====\n");
        instances.stream().filter(zombie -> zombie.getLocation().equals(location))
                          .map(zombie -> zombie.getName())
                          .forEach(zombie -> sb.append((String)zombie).append("\n"));
        return sb.toString();
    }
}
