import java.util.HashMap;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Nick Anbergen
 * @version 2020.1.22
 */

public class CommandWords
{
    private HashMap<String, CommandWord> validCommands;

    /**
     * @author Nick Anbergen
     * @version 2020.1.22
     * 
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        validCommands = new HashMap<>();

        validCommands.put("help", CommandWord.HELP);

        validCommands.put("go", CommandWord.GO);
        validCommands.put("ga", CommandWord.GO);

        validCommands.put("quit", CommandWord.QUIT);
        validCommands.put("stop", CommandWord.QUIT);

        validCommands.put("look", CommandWord.LOOK);
        validCommands.put("kijk", CommandWord.LOOK);

        validCommands.put("back", CommandWord.BACK);
        validCommands.put("terug", CommandWord.BACK);

        validCommands.put("inventory", CommandWord.INVENTORY);
        validCommands.put("rugzak", CommandWord.INVENTORY);

        validCommands.put("take", CommandWord.TAKE);
        validCommands.put("neem", CommandWord.TAKE);

        validCommands.put("drop", CommandWord.DROP);
        validCommands.put("wegleggen", CommandWord.DROP);

        validCommands.put("?", CommandWord.UNKNOWN);
    }

    /**
     * @author Nick Anbergen
     * @version 2020.1.22
     * 
     * @return the CommandWord from CommandWord
     */
    public CommandWord getCommandWord(String commandWord)
    {
        CommandWord command = validCommands.get(commandWord);
        if (command != null) 
        {
            return command;
        } else {
            return CommandWord.UNKNOWN;
        }
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        return validCommands.containsKey(aString);
    }

    /**
     * @author Nick Anbergen
     * @version 2020.1.18
     * 
     * Prints all the items in the validCommands HashMap, thus displaying an accurate list of
     * all available, valid commands
     */
    public void showAll()
    {
        for(String command : validCommands.keySet())
        {
            System.out.println(command);
        }
        System.out.println();
    }
}
