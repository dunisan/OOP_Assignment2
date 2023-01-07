package partA;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class ThreadPoolFiles implements Callable<Integer> {

    private String fileName;

    public ThreadPoolFiles(String filename) {
        this.fileName = filename;
    }

    @Override
    public Integer call() throws Exception {
        int numLines = 0; // Counter for the number of lines in the file

        File file = new File(this.fileName);
        Scanner sc = null; // Create a Scanner object to read the file

        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (sc.hasNextLine()) { // Read the file line by line
            String line = sc.nextLine(); // Read the current line
            numLines++; // Increment the line counter
            // System.out.println(line); // Print the line
        }

        sc.close(); // Close the Scanner object
        
        return numLines;
    }
}
