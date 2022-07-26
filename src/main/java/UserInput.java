import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.nio.file.Path;

public class UserInput {

    public static void main (String[] args){
        //Takes user input filename

        String filename = "";

        System.out.println("Hi there! Please enter the filename of the text file (.txt) that you wish to be processed. Please don't include the file type (.txt)");

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            filename = reader.readLine();
            filename = sanitiseInput(filename);
            String content = readFile(filename);
            ProcessingAPI processingAPI = new ProcessingAPI();
            Results results = processingAPI.processingRequest(content);
            displayResults(results);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static String sanitiseInput(String filename) throws Exception{
        //sanitiser user input filename

        if(filename != ""){
            //remove potential file extension
            if (filename.contains(".")){
                String[] characters = filename.split("\\.");
                filename = characters[0];
            }

            filename = String.valueOf(filename);
        }

        else{
            throw new Exception("No filename provided");
        }

        return filename;
    }

    public static String readFile(String filename) throws Exception{
        //read file contents to string variable

        filename = filename + ".txt";
        String fileContent = "";
        String directoryPath = System.getProperty("user.dir");

        Path filepath = Paths.get(directoryPath + "/" + filename);
        boolean exists = Files.exists(filepath);
        if(exists) {
            fileContent = Files.readString(filepath);
            if(fileContent.length() == 0){
                throw new Exception("File has no content");
            }
        }
        else{
            throw new Exception("No file found");
        }
        return fileContent;
    }

    public static void displayResults(Results results) throws Exception{
        //visibly show calculated results

        if(results.getTotalWords() > 0 && results.getAverageLength() > 0 && results.getModeLength() > 0 && !results.getLengthFrequency().isEmpty()){
            System.out.println("Total words: " + results.getTotalWords());
            System.out.println("Average Word Length: " + results.getAverageLength());
            System.out.println("Most Common Word Length: " + results.getModeLength());

            Hashtable<Integer, Integer> lengthFrequencies = results.getLengthFrequency();
            Enumeration<Integer> allKeys = results.getLengthFrequency().keys();

            while(allKeys.hasMoreElements()){
                int key = allKeys.nextElement();
                System.out.println("Words of length " + String.valueOf(key) + " : " + lengthFrequencies.get(key));
            }
        }
        else{
            throw new Exception("Corruption of results");
        }
    }
}
