import java.io.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.nio.*;
import java.nio.file.Path;
import java.nio.file.*;


public class UserInput {

    //take text file
    //assume .txt
    //assume in same directory
    //pass text file to api
    //recieve data from api
    //display data

    public static void main (String[] args){
        System.out.println("Hi there! Please enter the filename of the text file (.txt) that you wish to be processed. Please don't include the file type (.txt)");
        String filename = "";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            filename = reader.readLine();
        }
        catch(Exception e){
            System.out.println("Sorry, something went wrong!");
        }

        try{
            filename = sanitiseInput(filename);
            String content = readFile(filename);
            //ask api to process the content
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
    public static String sanitiseInput(String filename) throws Exception{

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

        filename = filename + ".txt";
        String fileContent = "";

        String directoryPath = System.getProperty("user.dir");
        Path filepath = Paths.get(directoryPath + "/src/resources/" + filename);
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

    public void displayResults(Results results) throws Exception{
        if(results.totalWords > 0 && results.averageLength > 0 && results.medianLength > 0 && !results.lengthFrequency.isEmpty()){
            System.out.println("Total words: " + results.totalWords);
            System.out.println("Average Word Length: " + results.averageLength);
            System.out.println("Most Common Word Length: " + results.medianLength);

            Enumeration<Integer> enumerateFrequencies = results.lengthFrequency.keys();

            while(enumerateFrequencies.hasMoreElements()){
                int key = enumerateFrequencies.nextElement();
                System.out.println("Words of length " + results.lengthFrequency.get(key) + " : " + String.valueOf(key) + "/n");
            }
        }
        else{
            throw new Exception("Corruption of results");
        }

    }
}
