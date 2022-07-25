import java.util.*;
import java.lang.*;
public class WordProcessor {

    public Results compute(String fileContent){
        //read through file content and produce results

        int total = 0;
        int mode = 0;
        int average = 0;
        int length = 0;
        int currentValue = 0;
        Hashtable<Integer, Integer> frequencies = new Hashtable<Integer, Integer>();

        String[] content = fileContent.split(" ");


        for (String word : content) {

            if (!word.isBlank()) {
                word = cleanWords(word);
                String finalChar = String.valueOf(word.charAt(word.length() - 1));
                //don't include punctuation in word length


                length = word.length();


                System.out.println("word: " + word + " Length:" + length);
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
            float av = average;
            float t = total;
            float avg = av/t;
            average = Math.round(avg);
        }

        mode = findMode(frequencies);

        Results results = new Results(total, mode, average, frequencies);

        return results;
    }

    public int findMode(Hashtable<Integer, Integer> frequencies){
        //iterate through dictionary to find the most common word length

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

    public String cleanWords(String word){
        String finalChar = String.valueOf(word.charAt(word.length() - 1));
        if (finalChar.equals(".")
                || finalChar.equals(",")
                || finalChar.equals("?")
                || finalChar.equals("!")
                || finalChar.equals(":")
                || finalChar.equals(";")
                || finalChar.equals("-")) {
            word = word.substring(0, word.length()-1);
        }

        word = word.replaceAll("\\r", "");
        word = word.replaceAll("\\n", "");


        return word;
    }

}
