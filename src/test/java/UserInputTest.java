
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
class UserInputTest {
    UserInput userFacing = new UserInput();
    //if null filename throw error
    @org.junit.jupiter.api.Test
    void GivenUserInputForFilename_WhenFilenameIsEmpty_ThrowError() {
        Exception exception = assertThrows(RuntimeException.class, () -> { userFacing.sanitiseInput(""); });

        String expected = "No filename provided";
        String actual = exception.getMessage();

        assertTrue(actual.contains(expected));

    }
    @org.junit.jupiter.api.Test
    void GivenUserInputForFilename_WhenFilenameIncludesSupportedFileType_ThenRemoveFileType() {
        String actual = userFacing.sanitiseInput("text.txt");
        String expected = "text";
        assertEquals(actual, expected);
    }
    @org.junit.jupiter.api.Test
    void GivenUserInputForFilename_WhenFilenameIncludesUnsupportedFileType_ThenThrowError() {
        Exception exception = assertThrows(RuntimeException.class, () -> { userFacing.sanitiseInput("text.jpg"); });

        String expected = "Unsupported file type";
        String actual = exception.getMessage();

        assertTrue(actual.contains(expected));
    }
    @org.junit.jupiter.api.Test
    void GivenUserInputForFilename_WhenFilenameIncludesFormatIssues_ThenCastToStringType() {
        String actual = userFacing.sanitiseInput("12-3/42.1");
        String expected = String.valueOf("12-3/42.1");

        assertEquals(actual, expected);
    }

    @org.junit.jupiter.api.Test
    void GivenUserInputForFilename_WhenFilenameDoesNotExist_ThenThrowError() {
        Exception exception = assertThrows(RuntimeException.class, () -> { userFacing.sanitiseInput("ImFake"); });

        String expected = "No file found";
        String actual = exception.getMessage();

        assertTrue(actual.contains(expected));
    }

    @org.junit.jupiter.api.Test
    void GivenSanitisedFilename_WhenFileHasExpectedContent_ThenReadFileContent() {
        String actual = userFacing.readFile("text");

        String expected = "";
        try {
            expected = Files.readString(Paths.get("text.txt"));
        }
        catch (Exception e){
            fail("Failed to read file in test class");
        }

        assertEquals(actual, expected);
    }
    @org.junit.jupiter.api.Test
    void GivenSanitisedFilename_WhenFileIsEmpty_ThenThrowError() {
        Exception exception = assertThrows(RuntimeException.class, () -> { userFacing.readFile("ImEmpty"); });

        String expected = "File has no content";
        String actual = exception.getMessage();

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

        String expected = "Corruption of results";
        String actual = exception.getMessage();

        assertTrue(actual.contains(expected));
    }
}