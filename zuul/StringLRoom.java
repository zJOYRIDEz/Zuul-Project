import java.util.HashMap;
/**
 * class StringLRoom - geef hier een beschrijving van deze class
 *
 * @author Nick Anbergen
 * @version 2020.1.23
 */
public class StringLRoom
{
    private HashMap<String, String> list;
    
    public StringLRoom()
    {
        list = new HashMap<>();
        
        list.put("darkRoom", "in a dark room, the air heavy with the stink of sewage.in een donkere kamer, the lucht is zwaar met de meur van riool");
        list.put("darkHallway", "in a dark hallway, dimly lit by torches.in een donkere gang, dat zwak wordt belicht door vakkels");
        list.put("diningRoom", "in what appears to be some sort of dining room.in wat een eetkamer blijkt te zijn");
    }
        
    public String getInfo(String name, Language language)
    {
        String[] parts = getString(name).split(".");
        String english = parts[0];
        String nederlands = parts[1];
        String fout = ("Er is iets mis gegaan");
        
        if(language == Language.ENGLISH)
        {
            return english;
        } else if(language == Language.DUTCH){
            return nederlands;
        }
        return fout;
    }
    
    public String getString(String name)
    {
        return list.get(name);
    }
}
