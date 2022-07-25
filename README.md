# ProcessingWords
Word processing task in Java

Java API to process total words, most common word length, average word length, and frequencies of each word length, from a plaintext .txt file.

A word is defined as characters (incluidng numbers and special characters) of length one or more, seperated by spaces.
Words ending in punctuation are the word without the final punctuation; for example "cat," would produce word "cat".

Other assumptions are the files are of the .txt file type, and that decimal numbers will be rounded to whole numbers due to context (i.e. words cannot be 3.5 characters long).

.txt files are to be placed in the directory path from the parent/where the JAR file is.

To run the jar from the project directory, type in a terminal `java -jar ProcessingWords-1.0-SNAPSHOT.jar`
 
