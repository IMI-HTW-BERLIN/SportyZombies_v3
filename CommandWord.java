import java.util.HashMap;
/**
 * Write a description of class CommandWord here.
 *
 * @author  David Panagiotopulos and Luis Hankel
 * @version 2018.01.15
 */
public enum CommandWord
{
    GO("go"), 
    HELP("help"), 
    QUIT("quit"), 
    LOOK("look"), 
    OPEN("open"), 
    SLEEP("sleep"),
    BACK("back"),
    TAKE("take"),
    DROP("drop"),
    UNKNOWN("");
    
    
    private final String command;
    
    CommandWord(String command) {
        this.command = command;
    }
    
    private String getCommand() {
        return this.command;
    }
    
    public String getCommands(){
        String cmd = "";
        for(CommandWord command : CommandWord.values()){
            cmd += command.getCommand() + ", ";
        }
        cmd = cmd.replaceAll(", $", "");        
        return cmd;
    }
    
    public static CommandWord getEnum(String word) {
        for(CommandWord command : CommandWord.values()){
            if (command.getCommand().equals(word)) {
                return command;
            }
        }
        return CommandWord.UNKNOWN;
    }
}
