/**
 * An enum of all valid commands
 * 
 * 
 * @author  Nick Anbergen
 * @version 2020.1.22
 */
public enum CommandWord
{
    // A value for each command word along with its
    // corresponding user interface string.
    GO("go"), 

    BACK("back"), 

    LOOK("look"), 

    TAKE("take"), 

    DROP("drop"), 

    INVENTORY("inventory"), 

    QUIT("quit"), 

    HELP("help"), 

    UNKNOWN("?");
    // The command string.
    private String commandString;

    /**
     * @author Nick Anbergen
     * @version 2020.1.22
     * 
     * Initialise with the corresponding command string.
     * @param commandString The command string.
     */
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }

    /**
     * @author Nick Anbergen
     * @version 2020.1.22
     * 
     * @return The command word as a string.
     */
    public String toString()
    {
        return commandString;
    }
}