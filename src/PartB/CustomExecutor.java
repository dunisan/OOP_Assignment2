package PartB;

// thread pool
// in constructor we will build a task in put it into quqe of prioraty

import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.*;

public class CustomExecutor extends ThreadPoolExecutor{

    private int numOfCores;
    private int corePoolSize;
    private int maxPoolSize;

    public CustomExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public ExecutorService newFixedThreadPool(){
        numOfCores = Runtime.getRuntime().availableProcessors();
        corePoolSize = numOfCores/2;
        maxPoolSize = numOfCores-1;
        return new CustomExecutor(this.corePoolSize, this.maxPoolSize, 300, TimeUnit.MILLISECONDS, new PriorityBlockingQueue<>());
    }

    public Future<Object> submit(Callable task){
        return super.submit(task);
    }

   // public Future<Object> submit(Callable task, Task.TaskType){

    //}


}

