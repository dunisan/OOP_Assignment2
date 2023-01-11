package partA;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static partA.Ex2_1.*;

class Ex2_1Test {


    @Test
    void createTextFilesTest() throws IOException {
        String[] arr = {"file_1.txt","file_2.txt"};
        String[] toTest;
        toTest = createTextFiles(2,1,100);
        assertArrayEquals(arr,toTest);
    }
    @Test
    void createTextFilesEmptyTest() throws IOException {
        String[] arr = {};
        String[] toTest;
        toTest = createTextFiles(0,1,100);
        assertArrayEquals(arr,toTest);
    }
    @Test
    void createTextFilesSeedTest() throws IOException {
        String[] arr = {"file_1.txt"};
        String[] toTest;
        toTest = createTextFiles(1,0,1);
        assertArrayEquals(arr,toTest);
    }

    @Test
    void getNumOfLinesTest() throws IOException {
        String[] testy = createTextFiles(100,5,5);
        int threaded = getNumOfLinesThreads(testy);
        int pooled = getNumOfLinesThreadPool(testy);
        int regular = getNumOfLines(testy);
        assertEquals(threaded,pooled);
        assertEquals(regular,pooled);
    }




}