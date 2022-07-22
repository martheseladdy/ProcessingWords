import java.util.*;

public class Results {
    int totalWords = 0;
    int medianLength = 0;
    int averageLength = 0;
    Hashtable<Integer, Integer> lengthFrequency = new Hashtable<Integer, Integer>();
    public Results(int total, int median, int average, Hashtable<Integer, Integer> frequency){
        this.totalWords = total;
        this.medianLength = median;
        this.averageLength = average;
        this.lengthFrequency = frequency;
    }
}
