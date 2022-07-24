import java.util.*;

public class Results {
    //data type to store results from processing

    private int totalWords = 0;
    private int modeLength = 0;
    private int averageLength = 0;
    Hashtable<Integer, Integer> lengthFrequency = new Hashtable<Integer, Integer>();

    public Results(int total, int mode, int average, Hashtable<Integer, Integer> frequency){

        this.totalWords = total;
        this.modeLength = mode;
        this.averageLength = average;
        this.lengthFrequency = frequency;

    }
    public int getTotalWords(){

        return this.totalWords;
    }

    public int getModeLength(){

        return this.modeLength;
    }

    public int getAverageLength(){

        return this.averageLength;
    }

    public Hashtable<Integer,Integer> getLengthFrequency(){

        return this.lengthFrequency;
    }
}
