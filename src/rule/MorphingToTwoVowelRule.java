package rule;
/*
  Превращение гласной буквы Ю, Ё, Я или Е в пару фонем [Й] и [У], [О], [А] или [Э]
  соответственно происходит в тех случаях, когда эти буквы в слове стоят в одном из  следующих положений:
  в начале транскрибируемого слова;
  после мягкого знака;
  после твердого знака; после гласной буквы».
  Например:
  вьюга  ->  [в й у г а],	объём  ->  [а б й о м],	яма  -> [й а м а],	поёт-> [п а й о т].
  **/
import language.Language;

import java.util.List;

public class MorphingToTwoVowelRule implements Rule {
    @Override
    public List<String> apply(List<String> phonemStringList) {
        if (phonemStringList.get(0).equals("e")) {
            phonemStringList.set(0, "э");
            phonemStringList.add(0, "й");
        }
        if (phonemStringList.get(0).equals("ё")) {
            phonemStringList.set(0, "о");
            phonemStringList.add(0, "й");
        }
        if (phonemStringList.get(0).equals("ю")) {
            phonemStringList.set(0, "у");
            phonemStringList.add(0, "й");
        }
        if (phonemStringList.get(0).equals("я")) {
            phonemStringList.set(0, "а");
            phonemStringList.add(0, "й");
        }
        for (int i = 1; i < phonemStringList.size(); i++) {
            String test = phonemStringList.get(i - 1);
            String letter = phonemStringList.get(i);

            if (letter.equals("е") && test.equals("ь")) {
                phonemStringList.set(i - 1, "й");
                phonemStringList.set(i, "э");
            }
            if (letter.equals("ё") && test.equals("ь")) {
                phonemStringList.set(i - 1, "й");
                phonemStringList.set(i, "о");
            }
            if (letter.equals("ю") && test.equals("ь")) {
                phonemStringList.set(i - 1, "й");
                phonemStringList.set(i, "у");
            }
            if (letter.equals("я") && test.equals("ь")) {
                phonemStringList.set(i - 1, "й");
                phonemStringList.set(i, "а");
            }

            if (letter.equals("е") && test.equals("ъ")) {
                phonemStringList.set(i - 1, "й");
                phonemStringList.set(i, "э");
            }
            if (letter.equals("ё") && test.equals("ъ")) {
                phonemStringList.set(i - 1, "й");
                phonemStringList.set(i, "о");
            }
            if (letter.equals("ю") && test.equals("ъ")) {
                phonemStringList.set(i - 1, "й");
                phonemStringList.set(i, "у");
            }
            if (letter.equals("я") && test.equals("ъ")) {
                phonemStringList.set(i - 1, "й");
                phonemStringList.set(i, "а");
            }

            if (letter.equals("е") && test.equals(" ")) {
                phonemStringList.set(i, "э");
                phonemStringList.add(i, "й");
            }
            if (letter.equals("ё") && test.equals(" ")) {
                phonemStringList.set(i, "о");
                phonemStringList.add(i, "й");
            }
            if (letter.equals("ю") && test.equals(" ")) {
                phonemStringList.set(i, "у");
                phonemStringList.add(i, "й");
            }
            if (letter.equals("я") && test.equals(" ")) {
                phonemStringList.set(i, "а");
                phonemStringList.add(i, "й");
            }

            if (letter.equals("е") && Language.vowels.contains(test)) {
                phonemStringList.set(i, "э");
                phonemStringList.add(i, "й");
            }
            if (letter.equals("ё") && Language.vowels.contains(test)) {
                phonemStringList.set(i, "о");
                phonemStringList.add(i, "й");
            }
            if (letter.equals("ю") && Language.vowels.contains(test)) {
                phonemStringList.set(i, "у");
                phonemStringList.add(i, "й");
            }
            if (letter.equals("я") && Language.vowels.contains(test)) {
                phonemStringList.set(i, "а");
                phonemStringList.add(i, "й");
            }
        }

        return phonemStringList;
    }
}
