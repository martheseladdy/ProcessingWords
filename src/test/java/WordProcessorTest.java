import org.junit.jupiter.api.BeforeAll;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
class WordProcessorTest {

    WordProcessor processor = new WordProcessor();
    static String fileContent;
    static String oneWord;
    static String veryLong;
    static String numbered;
    static String specialChar;
    static String whiteSpaceChunks;
    static String whiteSpaceOnly;
    Results results;
    int expected = 0;
    int actual = 0;
    Hashtable<Integer,Integer> actualFrequencies;

    @BeforeAll
    public static void setUp() {

        fileContent = "This is a normal happy easy going file, enjoy calculating me. There will be some sentences. Bye";
        oneWord = "Shenanigans";
        numbered = "12 and 789 and maybe 12.065 and then perhaps 12351 146 2587 5";
        specialChar = "Honestly you $£&% thinking you can ^(£&^ and &£&{}&*^";
        whiteSpaceChunks = "    You got the invitation              you got the right address          ";
        whiteSpaceOnly = "                   ";

        try
        {
            veryLong = Files.readString(Paths.get(System.getProperty("user.dir") + "/src/resources/VeryLong.txt"));
        }
        catch(Exception e){
            System.out.println("Error in reading file content from file VeryLong.txt");
        }

    }

    @org.junit.jupiter.api.Test
    void GivenStringFileContent_WhenExpectedContent_ThenCalculateResults() {

        results = processor.compute(fileContent);

        actual = results.getTotalWords();
        expected = 17;
        assertEquals(expected, actual);

        actual = results.getAverageLength();
        expected = 4;
        assertEquals(expected, actual);

        actual = results.getModeLength();
        expected = 4;
        assertEquals(expected, actual);

        actualFrequencies = results.getLengthFrequency();
        Hashtable<Integer, Integer> expectedFrequencies = new Hashtable<Integer, Integer>() {
            {
                put(1, 1);
                put(2, 3);
                put(3, 1);
                put(4, 5);
                put(5, 4);
                put(6, 1);
                put(9, 1);
                put(11, 1);
            }};

        assertEquals(expectedFrequencies, actualFrequencies);
    }
    @org.junit.jupiter.api.Test
    void GivenStringFileContent_WhenOneWordContent_ThenCalculateResults() {

        results = processor.compute(oneWord);

        actual = results.getTotalWords();
        expected = 1;
        assertEquals(expected, actual);

        actual = results.getAverageLength();
        expected = 11;
        assertEquals(expected, actual);

        actual = results.getModeLength();
        expected = 11;
        assertEquals(expected, actual);

        actualFrequencies = results.getLengthFrequency();

        Hashtable<Integer, Integer> expectedFrequencies = new Hashtable<Integer, Integer>() {
            {
                put(11, 1);
            }};

        assertEquals(expectedFrequencies, actualFrequencies);
    }

    @org.junit.jupiter.api.Test
    void GivenStringFileContent_WhenVeryLongContent_ThenCalculateResults() {

        results = processor.compute(veryLong);

        actual = results.getTotalWords();
        expected = 2726;
        assertEquals(expected, actual);

        actual = results.getAverageLength();
        expected = 5 ;
        assertEquals(expected, actual);

        actual = results.getModeLength();
        expected = 5;
        assertEquals(expected, actual);


        actualFrequencies = results.getLengthFrequency();

        expected = 1;
        assertEquals(expected, actualFrequencies.get(1));

        expected = 1;
        assertEquals(expected, actualFrequencies.get(2));
    }

    @org.junit.jupiter.api.Test
    void GivenStringFileContent_WhenNumberedContent_ThenCalculateResults() {

        results = processor.compute(numbered);

        actual = results.getTotalWords();
        expected = 13;
        assertEquals(expected, actual);

        actual = results.getAverageLength();
        expected = 3;
        assertEquals(expected, actual);

        actual = results.getModeLength();
        expected = 3;
        assertEquals(expected, actual);

        actualFrequencies = results.getLengthFrequency();

        Hashtable<Integer, Integer> expectedFrequencies = new Hashtable<Integer, Integer>() {
            {
                put(1, 1);
                put(2, 1);
                put(3, 5);
                put(4, 2);
                put(5, 2);
                put(6, 1);
                put(7, 1);
            }};

        assertEquals(expectedFrequencies, actualFrequencies);
    }

    @org.junit.jupiter.api.Test
    void GivenStringFileContent_WhenSpecialCharactersContent_ThenCalculateResults() {

        results = processor.compute(specialChar);

        actual = results.getTotalWords();
        expected = 9;
        assertEquals(expected, actual);

        actual = results.getAverageLength();
        expected = 5;
        assertEquals(expected, actual);

        actual = results.getModeLength();
        expected = 3;
        assertEquals(expected, actual);

        actualFrequencies = results.getLengthFrequency();

        Hashtable<Integer, Integer> expectedFrequencies = new Hashtable<Integer, Integer>() {
            {
                put(3, 4);
                put(4, 1);
                put(5, 1);
                put(8, 3);
            }};

        assertEquals(expectedFrequencies, actualFrequencies);
    }

    @org.junit.jupiter.api.Test
    void GivenStringFileContent_WhenWhiteSpaceChunks_ThenCalculateResults() {

        results = processor.compute(whiteSpaceChunks);

        actual = results.getTotalWords();
        expected = 9;
        assertEquals(expected, actual);

        actual = results.getAverageLength();
        expected = 4;
        assertEquals(expected, actual);

        actual = results.getModeLength();
        expected = 3;
        assertEquals(expected, actual);

        actualFrequencies = results.getLengthFrequency();

        Hashtable<Integer, Integer> expectedFrequencies = new Hashtable<Integer, Integer>() {
            {
                put(3, 6);
                put(5, 1);
                put(7, 1);
                put(10, 1);
            }};

        assertEquals(expectedFrequencies, actualFrequencies);
    }

    @org.junit.jupiter.api.Test
    void GivenStringFileContent_WhenWhiteSpaceOnlyContent_ThenCalculateResults() {

        results = processor.compute(whiteSpaceOnly);

        actual = results.getTotalWords();
        expected = 0;
        assertEquals(expected, actual);

        actual = results.getAverageLength();
        expected = 0;
        assertEquals(expected, actual);

        actual = results.getModeLength();
        expected = 0;
        assertEquals(expected, actual);

        actualFrequencies = results.getLengthFrequency();
        Hashtable<Integer, Integer> expectedFrequencies = new Hashtable<Integer, Integer>();

        assertEquals(expectedFrequencies, actualFrequencies);
    }

}