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

        Exception exception = assertThrows(Exception.class, () -> { userFacing.sanitiseInput(""); });

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
        assertEquals(expected, actual);

        try {
            actual = userFacing.sanitiseInput("text.jpg");
        }
        catch(Exception e){
            fail("Failed to sanitise input");
        }
        assertEquals(expected, actual);
    }
    @org.junit.jupiter.api.Test
    void GivenUserInputForFilename_WhenFilenameIncludesUnsupportedFileType_ThenThrowError() {

        try{
            actual = userFacing.sanitiseInput("text.jpg");
        }
        catch(Exception e){
            fail("Failed to sanitise input");
        }
        expected = "text";
        assertEquals(expected, actual);
    }
    @org.junit.jupiter.api.Test
    void GivenUserInputForFilename_WhenFilenameIncludesFormatIssues_ThenCastToStringType() {

        expected = String.valueOf("12-3/42");
        try{
            actual = userFacing.sanitiseInput("12-3/42.1");
        }
        catch(Exception e){
            fail("Failed to sanitise input");
        }
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void GivenSanitisedFilename_WhenFilenameDoesNotExist_ThenThrowError() {

        Exception exception = assertThrows(Exception.class, () -> { userFacing.readFile("ImFake"); });

        expected = "No file found";
        actual = exception.getMessage();
        assertTrue(actual.contains(expected));
    }

    @org.junit.jupiter.api.Test
    void GivenSanitisedFilename_WhenFileHasExpectedContent_ThenReadFileContent() {

        try {
            actual = userFacing.readFile("text");
            String directoryPath = System.getProperty("user.dir");
            expected = Files.readString(Paths.get(directoryPath + "/src/resources/text.txt"));
        }
        catch (Exception e){
            fail("Failed to read file 2");
        }

        assertEquals(expected, actual);
    }
    @org.junit.jupiter.api.Test
    void GivenSanitisedFilename_WhenFileIsEmpty_ThenThrowError() {

        Exception exception = assertThrows(Exception.class, () -> { userFacing.readFile("ImEmpty"); });

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

        Exception exception = assertThrows(Exception.class, () -> { userFacing.displayResults(results); });

        expected = "Corruption of results";
        actual = exception.getMessage();
        assertTrue(actual.contains(expected));
    }
}