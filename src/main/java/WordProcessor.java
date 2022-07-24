import java.util.*;
import java.lang.*;
public class WordProcessor {

    public Results compute(String fileContent){

        int total = 0;
        int mode = 0;
        int average = 0;
        int length = 0;
        int currentValue = 0;
        Hashtable<Integer, Integer> frequencies = new Hashtable<Integer, Integer>();

        String[] content = fileContent.split(" ");

        for (String word : content) {
            if (!word.isBlank()) {
                //don't include punctuation in word length
                if (String.valueOf(word.charAt(word.length() - 1)).equals(".")
                        || String.valueOf(word.charAt(word.length() - 1)).equals(",")
                        || String.valueOf(word.charAt(word.length() - 1)).equals("?")
                        || String.valueOf(word.charAt(word.length() - 1)).equals("!")
                        || String.valueOf(word.charAt(word.length() - 1)).equals(":")
                        || String.valueOf(word.charAt(word.length() - 1)).equals(";")
                        || String.valueOf(word.charAt(word.length() - 1)).equals("-")) {
                    length = word.length() - 1;
                }
                else {
                    length = word.length();
                }

                total = total + 1;
                average = average + length;

                if (frequencies.containsKey(length)) {
                    currentValue = frequencies.get(length);
                    frequencies.replace(length, currentValue + 1);
                }
                else {
                    frequencies.put(length, 1);
                }
            }
        }

        if(average > 0){
            average = average / total;
        }

        mode = findMode(frequencies);

        Results results = new Results(total, mode, average, frequencies);

        return results;
    }

    public int findMode(Hashtable<Integer, Integer> frequencies){

        int highestFrequency = 0;
        int wordLength = 0;
        Enumeration<Integer> allKeys = frequencies.keys();

        while(allKeys.hasMoreElements()){
            int key = allKeys.nextElement();
            if(frequencies.get(key) > highestFrequency){
                highestFrequency = frequencies.get(key);
                wordLength = key;

            }
        }
        return wordLength;
    }

}
