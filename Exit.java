public class Exit {
    
    private final Location toLocation;
    private final boolean isLocked;
    
    private boolean locked;
    
    public Exit(Location toLocation, boolean isLocked) {
        this.toLocation = toLocation;
        this.isLocked = isLocked;
        
        if (isLocked) {
            locked = true;
        } else {
            locked = false;
        }
    }
    
    public Location getLocation() {
        return toLocation;
    }
    
    public boolean isLocked() {
        return isLocked;
    }
    
    public void unlock() {
        if (isLocked && locked) {
            locked = false;
        } else {
            System.out.println("This exit isn't locked!");
        }
    }
}