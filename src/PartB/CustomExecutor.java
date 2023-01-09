package PartB;



import java.util.concurrent.*;



public class CustomExecutor extends ThreadPoolExecutor{

    private static int numOfCores = Runtime.getRuntime().availableProcessors();;
    private int corePoolSize;
    private int maxPoolSize;

    private static PriorityBlockingQueue priorityBlockingQueue;


    private int maxPriority;


    public CustomExecutor() {
        super(numOfCores/2, numOfCores-1, 300, TimeUnit.MILLISECONDS,
                 priorityBlockingQueue = new PriorityBlockingQueue<>());
    }



    public Future submit(Task task){
        Future future = super.submit(task.getTask());
        // Add here check
        maxPriority = task.getTaskType().getPriorityValue();
        System.out.println(numOfCores);
       // System.out.println(max);
        return future;
    }

    public Future submit(Callable task, TaskType type){
        Task newtask = Task.createTask(task, type);
        return (submit(newtask));
    }

    public Future submit(Callable task) {
        Task newtask = Task.createTask(task);
        priorityBlockingQueue.
        return (submit(newtask));
    }

    public void gracefullyTerminate(){
        this.shutdown();
    }


    public int getCurrentMax(){
        return 0;
    }



}

