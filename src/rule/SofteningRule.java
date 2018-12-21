package rule;

import language.Language;

import java.util.List;

public class SofteningRule implements Rule {
    @Override
    public List<String> apply(List<String> phonemStringList) {
        for (int i = 1; i < phonemStringList.size(); i++) {
            String test = phonemStringList.get(i - 1);
            String letter = phonemStringList.get(i);
            if ((letter.contains("'")||(letter.contains("й"))) && Language.consolantList.contains(test)&&!Language.unpairedHardConsolant.contains(test)) {
                phonemStringList.set(i - 1, test + "'");
            }

        }
        return phonemStringList;
    }
}
