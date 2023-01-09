package PartB;



import java.util.concurrent.*;


/**
 * The CustomExecutor class provide us a threadPool that holds tasks
 * by priority using a priorityBlockingQueue
 *
 *
 */

public class CustomExecutor extends ThreadPoolExecutor{

    private static int numOfCores = Runtime.getRuntime().availableProcessors(); // num of cores available for deciding
    // how much core to use

    private static PriorityBlockingQueue priorityBlockingQueue; // The priority queue
    private Task top;  // The task that is in the highest priority(top of queue)

    private int maxPriority;                // The max priority at the current time


    /**
     * Constructor
     */
    public CustomExecutor() {
        super(numOfCores/2, numOfCores-1, 300, TimeUnit.MILLISECONDS,
                 priorityBlockingQueue = new PriorityBlockingQueue<Task>());
    }

    /**
     * afterExecute evokes when a threads is finished. we update the current priorityMax
     * @param r the runnable that has completed
     * @param t the exception that caused termination, or null if
     * execution completed normally
     */
    @Override
    protected void afterExecute(Runnable r, Throwable t) {

        super.afterExecute(r, t);
        if(t == null)
            update();   // update the current maxPriority after finished executing a task
        else
            System.out.println(t.getMessage());
    }

    /**
     *
     * @param task the task to submit
     * @return The future object that holds the return from the call
     * @param <T> cast to generic class
     */

    public <T> Future<T> submit(Task<T> task) {

        if (task == null) throw new NullPointerException();
        update();    // update the current when submitted a new task
        execute((RunnableFuture)task); // execute the task
        return task;
    }


    /**
     *
     * @param task - the callable task for creating and submitting task
     * @param type - the type of the task
     * @return A future object
     * @param <T> generic class
     */

    public <T> Future<T> submit(Callable task, TaskType type){
        Task newtask = Task.createTask(task, type);
        return (submit(newtask));
    }


    /**
     *
     * @param task the task to submit
     * @return A future object
     * @param <T> generic class
     */
    public <T> Future<T>  submit(Callable<T> task) {
        Task newtask = Task.createTask(task);
        return (submit(newtask));
    }

    /**
     * gracefully calls the shutdown method to shut the pool.
     * waits for the last task to execute
     *
     */
    public void gracefullyTerminate(){
        this.shutdown();
    }

    /**
     * getCurrentMax return the max priority of the queue
     * @return
     */

    public int getCurrentMax(){
        return maxPriority;
    }


    /**
     * update the maxPriority.
     * @throws NullPointerException if queue is empty
     */
    private void update() throws NullPointerException {
        try {
            top = (Task) priorityBlockingQueue.peek(); // check the head of the queue
            maxPriority = top.getTaskType().getPriorityValue(); // update the value
        }
        catch (NullPointerException e){
            maxPriority = 0;
        }

    }


}

