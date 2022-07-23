import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WordProcessorTest {

    WordProcessor processor = new WordProcessor();

    String fileContent;
    String oneWord;
    String veryLong;
    String numbered;
    String specialChar;
    String whiteSpaceChunks;
    String whiteSpaceOnly;
    Results results;
    int expected = 0;
    int actual = 0;

    Hashtable<Integer,Integer> lengthFreq;


    @BeforeAll
    public void setUp() {
        fileContent = "This is a normal happy easy going file, enjoy calculating me. There will be some sentences. Bye";
        oneWord = "Shenanigans";
        numbered = "12 and 789 and maybe 12.065 and then perhaps 12351 146 2587 5";
        specialChar = "Honestly you $£&% thinking you can ^(£&^ and &£&{}&*^";
        whiteSpaceChunks = "    You got the invitation              you got the right address          ";
        whiteSpaceOnly = "                   ";

        try
        {
            veryLong = Files.readString(Paths.get(System.getProperty("user.dir") + "/src/resources/test.txt"));
        }
        catch(Exception e){
            System.out.println("Error in reading file content from file VeryLong.txt");
        }

    }


    @org.junit.jupiter.api.Test
    void GivenStringFileContent_WhenExpectedContent_ThenCalculateResults() {
        results = processor.compute(fileContent);
        actual = results.getTotalWords();
        expected = 0;
        assertEquals(actual, expected);

        actual = results.getAverageLength();
        expected = 0;
        assertEquals(actual, expected);

        actual = results.getModeLength();
        expected = 0;
        assertEquals(actual, expected);


        actual = results.getLengthFrequency();
        expected = 0;
        assertEquals(actual, expected);

        //total
        //average length
        //mode length
        //frequencies

    }
    @org.junit.jupiter.api.Test
    void GivenStringFileContent_WhenOneWordContent_ThenCalculateResults() {

    }

    @org.junit.jupiter.api.Test
    void GivenStringFileContent_WhenVeryLongContent_ThenCalculateResults() {

    }

    @org.junit.jupiter.api.Test
    void GivenStringFileContent_WhenNumberedContent_ThenCalculateResults() {

    }

    @org.junit.jupiter.api.Test
    void GivenStringFileContent_WhenSpecialCharactersContent_ThenCalculateResults() {

    }

    @org.junit.jupiter.api.Test
    void GivenStringFileContent_WhenWhiteSpaceChunks_ThenCalculateResults() {

    }

    @org.junit.jupiter.api.Test
    void GivenStringFileContent_WhenWhiteSpaceOnlyContent_ThenCalculateResults() {

    }

}