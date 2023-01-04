package partA;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;



public class ThreadFiles extends Thread{
    static AtomicInteger numOfLines = new AtomicInteger();
    private String fileName;
    public ThreadFiles(String fileName){
        this.fileName = fileName;
    }

    @Override
    public void run(){
        countNumOfLines(this.fileName);
    }


    private void countNumOfLines(String fileName){

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
            System.out.println(line); // Print the line
        }

        sc.close(); // Close the Scanner object

        numOfLines.addAndGet(numLines);

    }


}
