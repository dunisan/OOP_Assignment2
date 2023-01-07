package PartB;

// thread pool
// in constructor we will build a task in put it into quqe of prioraty

import java.util.PriorityQueue;
import java.util.concurrent.ThreadPoolExecutor;

public class CustomExecutor{

    private PriorityQueue pq;



    // 1. methods that puts task into a queue
    // 2. method that puts task into queue with typetask
    // 3. method that puts task into queue without typetask
    // 4. 2 and 3 must use 1
    // 5. min number of threads in costomexecuter will be as half as the core that are available to jvm
    // 6. max number of threads in costomexecuter will be as the number of core that are available to jvm -1
    // 7. have a queue that puts task by prioraty
    //         int numOfCores = Runtime.getRuntime().availableProcessors();




    private void addTask(Task task){
        pq.add(task);
    }


}

