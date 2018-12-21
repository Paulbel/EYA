package rule;

import language.Language;

import java.util.List;

public class MainSimpleRule implements Rule{
    @Override
    public List<String> apply(List<String> phonemStringList) {
        for (int i = 1; i < phonemStringList.size(); i++) {
            String test = phonemStringList.get(i - 1);
            String letter = phonemStringList.get(i);
            if (letter.equals("ь") && Language.consolantList.contains(test)) {
                phonemStringList.set(i - 1, test + "'");
                phonemStringList.remove(i);
            }
            if (letter.equals("ь") && Language.unpairedHardConsolant.contains(test)) {
                phonemStringList.remove(i);
            }

        }
        return phonemStringList;
    }
}
