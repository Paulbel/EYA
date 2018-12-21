package rule;
/*
 * после согласных гласные Ю, Ё, Я или Е преобразуются в фонемы [У], [О], [А] или [Э]
 * соответственно и при этом смягчают стоящие перед ними согласные».
 * Например: тюк -> [т' у к], лёд -> [л' о т], пять -> [п' а т'], семь -> [с' э м'].
 * */

import language.Language;

import java.util.List;

public class MorphingVowelRule implements Rule {
    @Override
    public List<String> apply(List<String> phonemStringList) {
        for (int i = 1; i < phonemStringList.size(); i++) {
            String test = phonemStringList.get(i - 1);
            String letter = phonemStringList.get(i);

            if (letter.equalsIgnoreCase("е") && (Language.consolantList.contains(test) || Language.unpairedHardConsolant.contains(test))) {
                if (!Language.unpairedHardConsolant.contains(test)) {
                    phonemStringList.set(i - 1, test + "'");
                }
                phonemStringList.set(i, "э");
            }
            if (letter.equalsIgnoreCase("ё") && (Language.consolantList.contains(test) || Language.unpairedHardConsolant.contains(test))) {
                if (!Language.unpairedHardConsolant.contains(test)) {
                    phonemStringList.set(i - 1, test + "'");
                }
                phonemStringList.set(i, "о");
            }
            if (letter.equalsIgnoreCase("ю") && (Language.consolantList.contains(test) || Language.unpairedHardConsolant.contains(test))) {
                if (!Language.unpairedHardConsolant.contains(test)) {
                    phonemStringList.set(i - 1, test + "'");
                }
                phonemStringList.set(i, "у");
            }
            if (letter.equalsIgnoreCase("я") && (Language.consolantList.contains(test) || Language.unpairedHardConsolant.contains(test))) {
                if (!Language.unpairedHardConsolant.contains(test)) {
                    phonemStringList.set(i - 1, test + "'");
                }
                phonemStringList.set(i, "а");
            }

        }
        return phonemStringList;
    }
}
