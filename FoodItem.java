public class FoodItem extends Item {
    
    private FoodTypes foodType;
    private int value;
    
    public FoodItem(String name, int weight, String description, FoodTypes foodType, int value) {
        super(name, weight, description);
        this.foodType = foodType;
        this.value = value;
    }
    
    public void eat(Player player) {
        switch (foodType) {
            case STRENGTH_BOOST:
                player.modifyStrength(value);
                System.out.println("Strength modified by " + value);
        }
    }
}