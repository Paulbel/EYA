package language;

import java.util.ArrayList;
import java.util.List;

public class Language {
    public static List<String> consolantList = new ArrayList<>();
    public static List<String> vowels = new ArrayList<>();
    public static List<String> unpairedHardConsolant = new ArrayList<>();

    static {
        vowels.add("а");
        vowels.add("о");
        vowels.add("у");
        vowels.add("э");
        vowels.add("ы");
        vowels.add("и");
        vowels.add("е");
        vowels.add("ё");
        vowels.add("ю");
        vowels.add("я");

        consolantList.add("б");
        consolantList.add("в");
        consolantList.add("г");
        consolantList.add("д");
        consolantList.add("л");
        consolantList.add("м");
        consolantList.add("н");
        consolantList.add("р");
        consolantList.add("п");
        consolantList.add("к");
        consolantList.add("т");
        consolantList.add("з");
        consolantList.add("ф");
        consolantList.add("с");
        consolantList.add("щ");
        consolantList.add("х");
        consolantList.add("ч");

        unpairedHardConsolant.add("ж");
        unpairedHardConsolant.add("ш");
        unpairedHardConsolant.add("ц");
    }
}
