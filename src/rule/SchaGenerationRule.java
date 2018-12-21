package rule;

import language.Language;

import java.util.List;

public class SchaGenerationRule implements Rule {
    @Override
    public List<String> apply(List<String> phonemStringList) {
        for (int i = 1; i < phonemStringList.size(); i++) {
            String test = phonemStringList.get(i - 1);
            String letter = phonemStringList.get(i);

            if (letter.equals("ч") && (test.equals("с")||test.equals("з")||test.equals("ж"))) {
                phonemStringList.set(i - 1, "щ");
                phonemStringList.remove(i);
            }
        }
        return phonemStringList;
    }
}
