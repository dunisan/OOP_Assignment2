package PartB;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Task<T> extends FutureTask<T> implements Callable<T>, Comparable<Task<T>>{


    private Callable<T> task;
    private  TaskType tasktype;





    private Task(Callable<T> task) {
        this(TaskType.OTHER, task);
    }
    private Task(TaskType tasktype, Callable<T> task) {
        super(task);
        this.task = task;
        this.tasktype = tasktype;
    }


    public static <T> Task<T> createTask(Callable<T> task, TaskType tasktype){
        return new Task<T>(tasktype, task);
    }

    public  static<T> Task<T> createTask(Callable<T> task){
        return new Task<T>(task);
    }


    @Override
    public T  call() throws Exception {
        if (this.task!=null)
            return task.call();
        else return null;
    }

    public TaskType getTaskType() {return tasktype.getType();}

    public void setTaskType(int taskType) {
        this.tasktype.setPriority(taskType);
    }


    public Callable<T> getTask() {
        return task;
    }

    public void setTask(Callable task) {
        this.task = task;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tasktype, task);
    }


    public boolean equals(Task<T> task) {
        if (this.compareTo(task)==0) return true;
        else
            return false;
    }

    @Override
    public int compareTo(Task<T> o) {
        int diff = o.tasktype.getPriorityValue() - this.tasktype.getPriorityValue();
        return Integer.signum(diff);
    }








}
