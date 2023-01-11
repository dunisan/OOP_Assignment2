package PartB;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;


/**
 * Task create a new task that add a priority key to the task
 * @param <T>
 */
public class Task<T> extends FutureTask<T> implements Callable<T>, Comparable<Task<T>>{

    private Callable<T> task; // The callable task
    private  TaskType tasktype; // the priorityType of the task


    /**
     * C'tor
     *
     * @param task
     */

    public Task(Callable<T> task) {
        this(TaskType.OTHER, task);
    }

    /**
     * C'tor
     * @param tasktype
     * @param task
     */
    public Task(TaskType tasktype, Callable<T> task) {
        super(task);
        this.task = task;
        this.tasktype = tasktype;
    }

    /**
     * factory c'tor
     * @param task
     * @param tasktype
     * @return
     * @param <T>
     */

    public static <T> Task<T> createTask(Callable<T> task, TaskType tasktype){
        return new Task<T>(tasktype, task);
    }

    /**
     * factory c'tor
     *
     * @param task
     * @return
     * @param <T>
     */
    public  static<T> Task<T> createTask(Callable<T> task){
        return new Task<T>(task);
    }


    /**
     * Call method (Callable)
     * @return
     * @throws Exception
     */
    @Override
    public T  call() throws Exception {
        if (this.task!=null)
            return task.call();
        else return null;
    }

    /**
     * Getter
     *
     * @return Tasktype
     */
    public TaskType getTaskType() {return tasktype.getType();}

    public void setTaskType(int taskType) {
        this.tasktype.setPriority(taskType);
    }

    /**
     * Getter
     * @return the task
     */
    public Callable<T> getTask() {
        return task;
    }

    /**
     * Setter
     * @param task the new task
     */
    public void setTask(Callable task) {
        this.task = task;
    }


    /**
     *
     * @param o the object to be compared.
     * @return -1, 0 or 1
     */
    @Override
    public int compareTo(Task<T> o) {
        return Integer.compare(this.getTaskType().getPriorityValue(), o.getTaskType().getPriorityValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task<?> task1 = (Task<?>) o;
        return Objects.equals(task, task1.task) && tasktype == task1.tasktype;
    }

    @Override
    public int hashCode() {
        return Objects.hash(task, tasktype);
    }
}
