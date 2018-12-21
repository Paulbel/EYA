package rule;

import language.Language;

import java.util.List;

public class AfterUnpairConsonantRule implements Rule {
    @Override
    public List<String> apply(List<String> phonemStringList) {
        for (int i = 1; i < phonemStringList.size(); i++) {
            String test = phonemStringList.get(i - 1);
            String letter = phonemStringList.get(i);

            if (letter.equals("и") && Language.unpairedHardConsolant.contains(test)) {
                phonemStringList.set(i, "ы");
            }
        }
        return phonemStringList;
    }
}
