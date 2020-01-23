
/**
 * class Language - geef hier een beschrijving van deze class
 *
 * @author Nick Anbergen
 * @version 2020.1.23
 */
public enum Language
{
   ENGLISH("English"),
   DUTCH("Nederlands");
   

   private String language;
   
   Language(String language)
   {
       this.language = language;
   }
   
   public String toString()
   {
       return language;
   }
}
