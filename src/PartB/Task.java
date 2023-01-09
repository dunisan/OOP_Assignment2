package PartB;

import java.util.Objects;
import java.util.concurrent.Callable;

public class Task<T> implements Callable<Object>, Comparable<Task>{

    // tasktype enum
    // callable
    // method with return value
    // factory that will make  -- static
    // constructor - public class (callable) or public class (callable, task type)
    // the objects of task should be camparable
    // need to decide the חתימה of the class
    // geters, seters, equals , hash code .


    private Callable task;
    private  TaskType tasktype;





    private Task(Callable task) {
        this(TaskType.OTHER, task);
    }
    private Task(TaskType tasktype, Callable task) {
        this.task = task;
        this.tasktype = tasktype;
    }


    public static Task createTask(Callable task, TaskType tasktype){
        Task tasks = new Task(tasktype, task);
        return tasks;
    }

    public static Task createTask(Callable task){
        Task newtask = new Task(task);
        return newtask;
    }


    @Override
    public T  call() throws Exception {
        if (this.task!=null)
            return (T) task.call();
        else return null;
    }

    public TaskType getTaskType() {return tasktype.getType();}

    public void setTaskType(int taskType) {
        this.tasktype.setPriority(taskType);
    }


    public Callable getTask() {
        return task;
    }

    public void setTask(Callable task) {
        this.task = task;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tasktype, task);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task1 = (Task) o;
        return tasktype.getPriorityValue() == task1.tasktype.getPriorityValue() && Objects.equals(task, task1.task);
    }

    @Override
    public int compareTo(Task o) {
        return Integer.compare(this.tasktype.getPriorityValue(), o.tasktype.getPriorityValue());
    }








}
