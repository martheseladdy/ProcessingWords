
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
class UserInputTest {
    UserInput userFacing = new UserInput();
    String actual = "";
    String expected = "";
    @org.junit.jupiter.api.Test
    void GivenUserInputForFilename_WhenFilenameIsEmpty_ThrowError() {
        Exception exception = assertThrows(RuntimeException.class, () -> { userFacing.sanitiseInput(""); });

        expected = "No filename provided";
        actual = exception.getMessage();

        assertTrue(actual.contains(expected));

    }
    @org.junit.jupiter.api.Test
    void GivenUserInputForFilename_WhenFilenameIncludesSupportedFileType_ThenRemoveFileType() {

        expected = "text";

        try{
            actual = userFacing.sanitiseInput("text.txt");

        }
        catch(Exception e){
            fail("Failed to sanitise input");
        }
        assertEquals(actual, expected);

        try {
            actual = userFacing.sanitiseInput("text.jpg");
        }
        catch(Exception e){
            fail("Failed to sanitise input");
        }
        assertEquals(actual, expected);
    }
    @org.junit.jupiter.api.Test
    void GivenUserInputForFilename_WhenFilenameIncludesUnsupportedFileType_ThenThrowError() { //remove and change to txt
        actual = "";
        try{
            actual = userFacing.sanitiseInput("text.jpg");
        }
        catch(Exception e){
            fail("Failed to sanitise input");
        }

        expected = "text";
        assertEquals(actual, expected);
    }
    @org.junit.jupiter.api.Test
    void GivenUserInputForFilename_WhenFilenameIncludesFormatIssues_ThenCastToStringType() {
        actual = "";
        try{
            actual = userFacing.sanitiseInput("12-3/42.1");
        }
        catch(Exception e){
            fail("Failed to sanitise input");
        }

        expected = String.valueOf("12-3/42");

        assertEquals(actual, expected);
    }

    @org.junit.jupiter.api.Test
    void GivenSanitisedFilename_WhenFilenameDoesNotExist_ThenThrowError() {
        Exception exception = assertThrows(RuntimeException.class, () -> { userFacing.readFile("ImFake"); });

        expected = "No file found";
        actual = exception.getMessage();

        assertTrue(actual.contains(expected));
    }

    @org.junit.jupiter.api.Test
    void GivenSanitisedFilename_WhenFileHasExpectedContent_ThenReadFileContent() {
        expected = "";
        actual = "";

        try {
            actual = userFacing.readFile("text");
            expected = Files.readString(Paths.get("text.txt"));
        }
        catch (Exception e){
            fail("Failed to read file");
        }

        assertEquals(actual, expected);
    }
    @org.junit.jupiter.api.Test
    void GivenSanitisedFilename_WhenFileIsEmpty_ThenThrowError() {
        Exception exception = assertThrows(RuntimeException.class, () -> { userFacing.readFile("ImEmpty"); });

        expected = "File has no content";
        actual = exception.getMessage();

        assertTrue(actual.contains(expected));
    }

    @org.junit.jupiter.api.Test
    void GivenSanitisedFilename_WhenFileIsVeryLong_ThenReadFileContent() {
      assertDoesNotThrow(() -> userFacing.readFile("VeryLong"));
    }

    @org.junit.jupiter.api.Test
    void GivenSanitisedFilename_WhenFileIsOneWord_ThenReadFileContent() {
        assertDoesNotThrow(() -> userFacing.readFile("JustOneWord"));
    }

    @org.junit.jupiter.api.Test
    void GivenProcessingResults_WhenExpectedDataTypes_ThenDisplayResults() {
        Hashtable<Integer, Integer> frequencies = new Hashtable<Integer, Integer>();
        frequencies.put(10, 5);

        Results results = new Results(1,1,1,frequencies);

        assertDoesNotThrow(() -> userFacing.displayResults(results));

    }
    @org.junit.jupiter.api.Test
    void GivenProcessingResults_WhenUnexpectedValues_ThenThrowError() {
        Hashtable<Integer, Integer> emptyFrequencies = new Hashtable<Integer, Integer>();


        Results results = new Results(-52,-1,-10,emptyFrequencies);

        Exception exception = assertThrows(RuntimeException.class, () -> { userFacing.displayResults(results); });

        expected = "Corruption of results";
        actual = exception.getMessage();

        assertTrue(actual.contains(expected));
    }
}