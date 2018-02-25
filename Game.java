/**
 *  This class is the main class of the "SportyZombies" application. 
 *  "SportyZombies" is a very simple, text based adventure game. Users 
 *  can walk around a track and field area. We are currently adding more
 *  features to it.
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  Locations, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  David Panagiotopulos and Luis Hankel
 * @version 2018.01.15
 */

public class Game 
{
    private Parser parser;
    private Player player;
    
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        parser = new Parser();
        player = new Player();
        createLocations();
    }

    /**
     * Create all the Locations and link their exits together.
     */
    private void createLocations()
    {
        Location trackN, trackW, trackS, trackE, soccer, longJump, shed;
        Item football, soccerball, shotball, throwingball, spear, discus, pylon, flag, pole, hammer;
        FoodItem proteineBar;
        // create the Locations
        trackN = new Location("on the northern part of the tartan track");
        trackW = new Location("on the western part of the tartan track");
        trackS = new Location("on the southern part of the tartan track");
        trackE = new Location("on the eastern part of the tartan track");
        soccer = new Location("on the soccerfield");
        longJump = new Location("in the long jump area with a sandbox:)");
        shed = new Location("at the shed");
        
        soccerball = new Item("soccerball",500,"It looks like there is also a soccerball. How exithing!");
        football = new Item("football",500,"You see a football not far away.");
        hammer = new Item("hammer_throw_hammer",7000,"Last but not least, a beautiful hammer!");
        pole = new Item("pole_vault_pole",5000,"Someone obviously sneak a pole vault pole in there.");
        flag = new Item("checkered_flag",300,"Oh, there is also a checkered flag");
        pylon = new Item("pylons",300,"You see just a buch of pylons");
        discus = new Item("discus",2000,"Next to all of this there is also a discus.");
        spear = new Item("spear",800,"In a pile you also notcie a spear.");
        throwingball = new Item("throwing_ball",200,"And a throwingball.");
        shotball = new Item("shot_put_ball",5000,"You recognise a shot put ball amongst everything else.");
        
        proteineBar = new FoodItem("proteine_bar", 50, "A proteine bar is lying on a table near you.", FoodTypes.STRENGTH_BOOST, 5000);
        
        // initialise Location exits
        trackN.addExit("west", trackW);
        trackN.addExit("east", trackE);
        trackN.addExit("south", soccer);
        
        trackN.addItem(pylon);
        
        new Zombie("Rotten_Zombie", trackN, "OOOOAAARRGGHH!!!");
        
        trackW.addExit("north", trackN);
        trackW.addExit("east", soccer);
        trackW.addExit("south", trackS);
        
        trackW.addItem(flag);
        trackW.addItem(pylon);
        
        trackE.addExit("north", trackN);
        trackE.addExit("west", soccer);
        trackE.addExit("south", trackS);
        
        trackE.addItem(pylon);
        
        trackS.addExit("north", soccer);
        trackS.addExit("east", trackE);
        trackS.addExit("west", trackW);
        trackS.addExit("south", longJump);
        
        trackS.addItem(pylon);
        
        soccer.addExit("north", trackN);
        soccer.addExit("east", trackE);
        soccer.addExit("west", trackW);
        soccer.addExit("south", trackS);
        
        soccer.addItem(football);
        soccer.addItem(soccerball);
        soccer.addItem(spear);
        soccer.addItem(proteineBar);
        
        longJump.addExit("north", trackS);
        longJump.addExit("east", shed);

        shed.addExit("west", longJump);
        
        shed.addItem(shotball);
        shed.addItem(throwingball);
        shed.addItem(discus);
        shed.addItem(pole);
        shed.addItem(hammer);

        player.setLocation(soccer);
    }
    
    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();
        
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
        
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();
        switch (commandWord){
            case HELP:
                printHelp();
                break;
                
            case GO:
                goLocation(command);
                break;
                
            case QUIT:
                wantToQuit = quit(command);
                break;
                
            case LOOK:
                look();
                break;
                
            case OPEN:
                open(command);
                break;
                
            case SLEEP:
                sleep();
                break;
            
            case BACK:
                back();
                break;
            
            case TAKE:
                take(command);
                break;
                 
            case DROP:
                drop(command);
                break;
                
            case ITEMS:
                items();
                break;
                
            case ASK:
                ask(command);
                break;
                
            case LOOKAT:
                lookat(command);
                break;
                
            case EAT:
                eat(command);
                break;
                
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                return false;
        }
        
        return wantToQuit;
    }
    
    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to SportyZombies!");
        System.out.println("SportyZombies is a new, incredible horror adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(player.getLocation().getLongDescription());
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. But you are not alone. A horde of zombies");
        System.out.println("is running towards you.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(parser.getCommands());
    }
    
    /** 
     * Try to go to one direction. If there is an exit, enter
     * the new Location, otherwise print an error message.
     */
    private void goLocation(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current Location.
        Location nextLocation = null;
        
        nextLocation = player.getLocation().getExit(direction);

        if (nextLocation == null) {
            System.out.println("There is no door!");
        }
        else {
            player.addLocationToHistory();
            player.setLocation(nextLocation);
            System.out.println(nextLocation.getLongDescription());
            System.out.println(Zombie.getZombiesInLocation(nextLocation));
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    /**
     * Look around. Prints out the long description of the current location.
     */
    private void look(){
        System.out.println(player.getLocation().getItems());
    }
    
    /**
     * Try to open something.
     * @param command The command that triggered this method call.
     */
    private void open(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("What do you want to open?");
        }
    }
    
    /**
     * Sleep. Just prints out some text.
     */
    private void sleep(){
        System.out.println("You've slept"+"\n"+"You feel soooo good now!");
    }
     
    private void back(){
        if(player.isHistoryEmpty()){
            System.out.println("You are at the start!");
        }else{
            player.getLocationFromHistory();
            System.out.println(player.getLocation().getLongDescription());
        }
      }
      
    private void take(Command command){
        if (!command.hasSecondWord()) {
            System.out.println("What do you want to take, Sir/Ma'am?");
            return;
        }
        
        String itemName = command.getSecondWord();
        if (player.getLocation().containsItem(itemName)) {
            Item item = player.getLocation().removeItem(itemName);
            if (player.addItem(item)) {
                System.out.println("You took: " + command.getSecondWord());
            } else {
                player.getLocation().addItem(item);
                System.out.println("You are carrying too much!");
            }
        } else {
            System.out.println("There is no such item here!");
        }
    }
    
    private void drop(Command command){
        if (!command.hasSecondWord()) {
            System.out.println("What do you want to drop, Sir/Ma'am?");
        }else if(command.getSecondWord().equalsIgnoreCase("all")){
            player.getLocation().addItems(player.dropAll());
        }else{
            if(player.hasItem(command.getSecondWord())){
                player.getLocation().addItem(player.removeItem(command.getSecondWord()));
            }else{
                System.out.println("You don't have that item :(");
            }
        }
    }
    
    private void items() {
        System.out.println(player.listItems());
    }
    
    private void ask(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Ask whom?");
            return;
        }
        
        Zombie zombie = Zombie.getZombieInLocation(command.getSecondWord(), player.getLocation());
        if (zombie == null) {
            System.out.println("There is no " + command.getSecondWord() + " here.");
            return;
        }
        
        System.out.println(zombie.getSpeak());
    }
    
    private void lookat(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Look at what?");
            return;
        }
        
        String itemName = command.getSecondWord();
        
        if (!player.hasItem(itemName) && !player.getLocation().containsItem(itemName)) {
            System.out.println("There is no " + itemName + " to look at!");
            return;
        }
        
        if (player.hasItem(itemName)) {
            System.out.println(player.getItem(itemName).getDescription());
            return;
        } else if (player.getLocation().containsItem(itemName)) {
            System.out.println(player.getLocation().getItem(itemName).getDescription());
            return;
        }
    }
    
    private void eat(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Eat what?");
            return;
        }
        
        String itemName = command.getSecondWord();
        Item itemToEat = player.hasItem(itemName) ? player.getItem(itemName) : 
                            player.getLocation().getItem(itemName);
        
        if (itemToEat == null) {
            System.out.println("There is no such item in your inventory or near you!");
            return;
        }
        
        if (itemToEat instanceof FoodItem) {
            player.removeItem(itemName);
            player.getLocation().removeItem(itemName);
            FoodItem item = (FoodItem)itemToEat;
            item.eat(player);
        } else {
            System.out.println("You can't eat that!");
        }
    }
}
