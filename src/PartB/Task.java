package PartB;

import java.util.Objects;
import java.util.concurrent.Callable;

public class Task implements Callable <Object> , Comparable<Task>{

    // tasktype enum
    // callable
    // method with return value
    // factory that will make  -- static
    // constructor - public class (callable) or public class (callable, task type)
    // the objects of task should be camparable
    // need to decide the חתימה of the class
    // geters, seters, equals , hash code .


    private Callable task;
    private int taskType;


    public Task(Callable task) {
        this.taskType = 5;
        this.task = task;
    }
    public Task(int taskType, Callable task) {
        if(taskType>=0 && taskType<=10)
            this.taskType = taskType;
        else taskType = 5;
        this.task = task;
    }



    @Override
    public Object call() throws Exception {
        if (this.task!=null)
            return this.task.call();
        else return null;
    }

    public int getTaskType() {return taskType;}

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }


    public Callable getTask() {
        return task;
    }

    public void setTask(Callable task) {
        this.task = task;
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskType, task);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task1 = (Task) o;
        return taskType == task1.taskType && Objects.equals(task, task1.task);
    }

    @Override
    public int compareTo(Task o) {
        return Integer.compare(this.getTaskType(), o.getTaskType());
    }
}
