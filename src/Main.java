import language.Language;
import rule.*;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.SequenceInputStream;
import java.util.*;

public class Main {

    private static List<Double> listLengths = new ArrayList<>();
    private static List<String> phonemStringList;


    public static void main(String[] args) throws Exception {
        while (true) {
            List<Character> lettersList = new ArrayList<>();
            List<Character> phoneticList = new ArrayList<>();
            List<String> soundsList = new ArrayList<>();
            phonemStringList = new ArrayList<>();
            Map<String, String> soundsMap = new HashMap<>();


            Map<String, String> voiceToDeafConsonants = new HashMap<>();
            voiceToDeafConsonants.put("б", "п");
            voiceToDeafConsonants.put("в", "ф");
            voiceToDeafConsonants.put("г", "к");
            voiceToDeafConsonants.put("д", "т");
            voiceToDeafConsonants.put("ж", "ш");
            voiceToDeafConsonants.put("з", "с");
            voiceToDeafConsonants.put("б'", "п'");
            voiceToDeafConsonants.put("в'", "ф'");
            voiceToDeafConsonants.put("г'", "к'");
            voiceToDeafConsonants.put("д'", "т'");
            voiceToDeafConsonants.put("з'", "с'");

            Map<String, String> deafToVoiceConsonants = new HashMap<>();
            deafToVoiceConsonants.put("п", "б");
            deafToVoiceConsonants.put("ф", "в");
            deafToVoiceConsonants.put("к", "г");
            deafToVoiceConsonants.put("т", "д");
            deafToVoiceConsonants.put("ш", "ж");
            deafToVoiceConsonants.put("с", "з");
            deafToVoiceConsonants.put("п'", "б'");
            deafToVoiceConsonants.put("ф'", "в'");
            deafToVoiceConsonants.put("к'", "г'");
            deafToVoiceConsonants.put("т'", "д'");
            deafToVoiceConsonants.put("с'", "з'");


            soundsMap.put("а", "src/sounds/а.wav");
            soundsMap.put("б", "src/sounds/б.wav");
            soundsMap.put("в", "src/sounds/в.wav");
            soundsMap.put("г", "src/sounds/г.wav");
            soundsMap.put("д", "src/sounds/д.wav");
            soundsMap.put("ж", "src/sounds/ж.wav");
            soundsMap.put("з", "src/sounds/з.wav");
            soundsMap.put("и", "src/sounds/и.wav");
            soundsMap.put("к", "src/sounds/к.wav");
            soundsMap.put("л", "src/sounds/л.wav");
            soundsMap.put("м", "src/sounds/м.wav");
            soundsMap.put("н", "src/sounds/н.wav");
            soundsMap.put("о", "src/sounds/о.wav");
            soundsMap.put("п", "src/sounds/п.wav");
            soundsMap.put("р", "src/sounds/р.wav");
            soundsMap.put("с", "src/sounds/с.wav");
            soundsMap.put("т", "src/sounds/т.wav");
            soundsMap.put("у", "src/sounds/у.wav");
            soundsMap.put("ф", "src/sounds/ф.wav");
            soundsMap.put("х", "src/sounds/х.wav");
            soundsMap.put("ц", "src/sounds/ц.wav");
            soundsMap.put("ч", "src/sounds/ч.wav");
            soundsMap.put("щ", "src/sounds/щ.wav");
            soundsMap.put("ш", "src/sounds/ш.wav");
            soundsMap.put("э", "src/sounds/э.wav");
            soundsMap.put("ы", "src/sounds/ы.wav");
            soundsMap.put(" ", "src/sounds/silence.wav");
            soundsMap.put("й", "src/sounds/й.wav");
            soundsMap.put("л'", "src/sounds/ль.wav");
            soundsMap.put("м'", "src/sounds/мь.wav");
            soundsMap.put("н'", "src/sounds/нь.wav");
            soundsMap.put("р'", "src/sounds/рь.wav");
            soundsMap.put("п'", "src/sounds/пь.wav");
            soundsMap.put("к'", "src/sounds/кь.wav");
            soundsMap.put("т'", "src/sounds/ть.wav");
            soundsMap.put("в'", "src/sounds/вь.wav");
            soundsMap.put("з'", "src/sounds/зь.wav");
            soundsMap.put("б'", "src/sounds/бь.wav");
            soundsMap.put("д'", "src/sounds/дь.wav");
            soundsMap.put("г'", "src/sounds/гь.wav");
            soundsMap.put("ф'", "src/sounds/фь.wav");
            soundsMap.put("с'", "src/sounds/сь.wav");
            soundsMap.put("ч'", "src/sounds/ч.wav");

            Scanner scanner = new Scanner(System.in);

            System.out.println("Введите текст:");
            String input = scanner.nextLine().toLowerCase();

            String phoneticString = input.replaceAll(",", " ").replaceAll("-", " ")
                    .replaceAll(":", " ").replaceAll("\\.", "  ").replaceAll("\\?", " ");


            for (int i = 0; i < input.length(); i++) {
                lettersList.add(input.charAt(i));
            }

            for (int i = 0; i < phoneticString.length(); i++) {
                phoneticList.add((phoneticString.charAt(i)));
            }

            for (Character aPhoneticList : phoneticList) {
                phonemStringList.add(aPhoneticList.toString());
            }

            Rule rule1 = new MorphingToTwoVowelRule();
            phonemStringList = rule1.apply(phonemStringList);
            Rule rule3 = new AfterUnpairConsonantRule();
            phonemStringList = rule3.apply(phonemStringList);
            Rule rule4 = new SchaGenerationRule();
            phonemStringList = rule4.apply(phonemStringList);

            for (int i = 1; i < phonemStringList.size(); i++) {
                String test = phonemStringList.get(i - 1);
                String letter = phonemStringList.get(i);

                if (letter.equals("й") && Language.unpairedHardConsolant.contains(test)) {
                    phonemStringList.remove(i);
                }
                if (letter.equals("и") && Language.consolantList.contains(test)&&!Language.unpairedHardConsolant.contains(test)) {
                    phonemStringList.set(i - 1, test + "'");
                }

                if (letter.equals("й") && test.equals("ъ")) {
                    phonemStringList.remove(i - 1);
                }


                if (deafToVoiceConsonants.containsKey(letter) && voiceToDeafConsonants.containsKey(test)) {
                    phonemStringList.set(i - 1, voiceToDeafConsonants.get(test));
                }
                if (deafToVoiceConsonants.containsKey(letter) && test.equals(" ") &&
                        i - 2 >= 0 && voiceToDeafConsonants.containsKey(phonemStringList.get(i - 2))) {
                    phonemStringList.set(i - 1, voiceToDeafConsonants.get(test));
                }

                if ((letter.equals(" ") || i == phonemStringList.size() - 1) && voiceToDeafConsonants.containsKey(test)) {
                    phonemStringList.set(i - 1, voiceToDeafConsonants.get(test));
                }
            }

            Rule rule2 = new MorphingVowelRule();
            phonemStringList = rule2.apply(phonemStringList);


            Rule rule5 = new MainSimpleRule();
            phonemStringList = rule5.apply(phonemStringList);

            Rule rule6 = new SofteningRule();
            phonemStringList = rule6.apply(phonemStringList);

            System.out.println("Транскрипция:\n" + phonemStringList);

            for (String phonem : phonemStringList) {
                String sound = soundsMap.get(phonem);
                soundsList.add(sound);
            }

            AudioInputStream audioInputStream = null;
            List<AudioInputStream> audioInputStreamList = null;
            AudioFormat audioFormat = null;
            Long frameLength = null;

            try {
                // loop through our files first and load them up
                for (String sourceFile : soundsList) {
                    audioInputStream = AudioSystem.getAudioInputStream(new File(sourceFile));

                    // get the format of first file
                    if (audioFormat == null) {
                        audioFormat = audioInputStream.getFormat();
                    }

                    // add it to our stream list
                    if (audioInputStreamList == null) {
                        audioInputStreamList = new ArrayList<>();
                    }
                    audioInputStreamList.add(audioInputStream);

                    // keep calculating frame length
                    if (frameLength == null) {
                        frameLength = audioInputStream.getFrameLength();
                    } else {
                        frameLength += audioInputStream.getFrameLength();
                    }

                    listLengths.add(((audioInputStream.getFrameLength() + 0.0) / audioFormat.getFrameRate()));
                }

                //assert audioFormat != null;
                double duration = (frameLength + 0.0) / audioFormat.getFrameRate();

                //System.out.println("duration in seconds: " + duration);

                AudioInputStream appendedFiles = new AudioInputStream(
                        new SequenceInputStream(
                                Collections.enumeration(audioInputStreamList)), audioFormat, frameLength);

                //AudioSystem.write(appendedFiles, AudioFileFormat.Type.WAVE, out);

                Clip clip = AudioSystem.getClip();
                clip.open(appendedFiles);
                clip.start();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (audioInputStream != null) {
                    audioInputStream.close();
                }
            }
        }
    }
}
