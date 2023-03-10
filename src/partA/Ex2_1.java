package partA;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;


public class Ex2_1{



    /**
     * THE createTextFiles create n new files, and write a sentence with at least 10 chars.
     *
     * @param n num of text files
     * @param seed
     * @param bound
     * @return array that holds the names of the new files
     */

    public static String[] createTextFiles(int n, int seed, int bound) throws IOException{

        String[] listOfFiles = new String[n];
        for (int i = 0; i < n; i++) {
            Random rand = new Random(seed + i);
            int numLines = rand.nextInt(bound);// Generate a random number of lines

            File file = new File("file_" + (i+1) + ".txt"); // Create a new file with the name "file_1.txt", "file_2.txt", etc.
            FileWriter writer = new FileWriter(file); // Create a FileWriter object to write to the file

            listOfFiles[i] = "file_" + (i+1) + ".txt";

            for (int j = 0; j < numLines; j++) {
                writer.write("This is line number " + (j + 1) + "\n"); // Write a line to the file
            }

            writer.close(); // Close the writer to save the file
        }
        return listOfFiles;
    }


    /**
     * The getNumOfLines() checks how many lines their are in all files and return it
     * @param fileNames array that holds the text files names
     * @return number of all lines in all files.
     */

    public static int getNumOfLines(String[] fileNames) throws IOException{

        int countLines = 0;
        for(int i=0; i<fileNames.length;i++) {
            File file = new File(fileNames[i]);
            Scanner sc = new Scanner(file); // Create a Scanner object to read the file

            while (sc.hasNextLine()) { // Read the file line by line
                String line = sc.nextLine(); // Read the current line
                countLines++;
                //System.out.println(line); // Print the line

            }
            sc.close(); // Close the Scanner object
        }
        return countLines;
    }

    /**
     * The getNumOfLines() checks how many lines their are in all files and return it,
     * but in this time it uses threads to perform the task
     *
     * @param fileNames array that holds the text files names
     * @returnnumber of all lines in all files.
     */

    public static int getNumOfLinesThreads(String[] fileNames){

        List<ThreadFiles> list = new ArrayList<ThreadFiles> ();
        int arrLength = fileNames.length;
        int totalLines = 0;

        for(int i=0; i<arrLength; i++){
            ThreadFiles thread = new ThreadFiles((fileNames[i]));
            thread.start();
            list.add(thread);

        }
        for(ThreadFiles threads : list){
            try {
                threads.join(); // Wait for the thread to complete
                totalLines += threads.getNumOfLines();
            } catch (InterruptedException e) {
                // Handle the exception
            }
        }




        return totalLines;
    }


    /**
     * The getNumOfLines() checks how many lines their are in all files and return it,
     * but this time uses threads pool the performs the task
     * @param fileNames sarray that holds the text files names
     * @return of all lines in all files.
     */

    public static int getNumOfLinesThreadPool(String[] fileNames){

        int arrLength = fileNames.length;
        int totalLines = 0;


        ExecutorService pool = Executors.newFixedThreadPool(10);
        List<Future<Integer>> futureList = new ArrayList<Future<Integer>>();

        for(int i=0; i<arrLength; i++){
          //   Callable <Integer> callable = new ThreadPoolFiles("");
            Future<Integer> future = pool.submit(new ThreadPoolFiles(fileNames[i]));
            futureList.add(future);
        }
        for(Future<Integer> fut : futureList){
            try {
                //print the return value of Future, notice the output delay in console
                // because Future.get() waits for task to get completed
                totalLines += fut.get().intValue();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            pool.shutdown();
        }

        return totalLines;
    }


    public static void main(String[] args) throws IOException {
        String[] fileNames;
        fileNames = createTextFiles(4000, 1 ,10000);

        System.out.println("with creating 4000 files and bound of 10000 lines per file:");
        System.out.println("this time we create thredpool with size 10 instead fileNames.length");


        long start = System.currentTimeMillis();
        System.out.println("Without threads: number of lines: " + getNumOfLines(fileNames));
         long end = System.currentTimeMillis();
        System.out.println("Time: " + ((end - start)/1000.0) + "  Seconds");

        start = System.currentTimeMillis();
        System.out.println("With threads: number of lines: " + getNumOfLinesThreads(fileNames));
        end = System.currentTimeMillis();
        System.out.println("Time: " + ((end - start)/1000.0) + "  Seconds");

        start = System.currentTimeMillis();
        System.out.println("With threadpool: number of lines: " + getNumOfLinesThreadPool(fileNames));
        end = System.currentTimeMillis();
        System.out.println("Time: " + ((end - start)/1000.0) + "  Seconds");




    }

}
