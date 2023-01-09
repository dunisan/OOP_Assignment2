package PartB;



import java.util.concurrent.*;



public class CustomExecutor extends ThreadPoolExecutor{

    private static int numOfCores = Runtime.getRuntime().availableProcessors();;
    private int corePoolSize;
    private int maxPoolSize;

    private static PriorityBlockingQueue priorityBlockingQueue;
    private Task top;

    private int maxPriority;


    public CustomExecutor() {
        super(numOfCores/2, numOfCores-1, 300, TimeUnit.MILLISECONDS,
                 priorityBlockingQueue = new PriorityBlockingQueue<>());
    }

//    @Override
//    protected void beforeExecute(Thread t, Runnable r) {
//        super.beforeExecute(t, r);
//        if(t ==  null)
//            update();
//    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {

        super.afterExecute(r, t);
        if(t == null)
            update();
        else
            System.out.println(t.getMessage());
    }



    public <T> Future<T> submit(Task<T> task) {

        if (task == null) throw new NullPointerException();
        update();
        execute((RunnableFuture)task);
        return task;
    }





    public <T> Future<T> submit(Callable task, TaskType type){
        Task newtask = Task.createTask(task, type);
        return (submit(newtask));
    }

    public <T> Future<T>  submit(Callable<T> task) {
        Task newtask = Task.createTask(task);
        return (submit(newtask));
    }

    public void gracefullyTerminate(){
        this.shutdown();
    }


    public int getCurrentMax(){
        return maxPriority;
    }

    private void update() throws NullPointerException {
        try {
            top = (Task) priorityBlockingQueue.peek();
            maxPriority = top.getTaskType().getPriorityValue();
        }
        catch (NullPointerException e){
        }

    }


}

