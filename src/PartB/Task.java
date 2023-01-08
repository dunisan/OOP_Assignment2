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
    private  TaskType tasktype;


    private Task(Callable task) {
        tasktype.setPriority(5);
        this.task = task;
    }
    private Task(TaskType tasktype, Callable task) {
        if(tasktype.getPriorityValue()>=0 && tasktype.getPriorityValue()<=10)
            this.tasktype.setPriority(tasktype.getPriorityValue());
        else tasktype.setPriority(5);
        this.task = task;
    }

    public Task createTask(Callable task, TaskType tasktype){
        Task tasks = new Task(tasktype, task);
        return tasks;
    }



    @Override
    public Object call() throws Exception {
        if (this.task!=null)
            return this.task.call();
        else return null;
    }

    public int getTaskType() {return tasktype.getPriorityValue();}

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
        return Integer.compare(this.getTaskType(), o.getTaskType());
    }









    public enum TaskType {
        COMPUTATIONAL(1) {
            @Override
            public String toString() {
                return "Computational Task";
            }
        },

        IO(2) {
            @Override
            public String toString() {
                return "IO-Bound Task";
            }
        },

        OTHER(3) {
            @Override
            public String toString() {
                return "Unknown Task";
            }
        };

        private int typePriority;

        private TaskType(int priority) {
            if (validatePriority(priority)) typePriority = priority;
            else
                throw new IllegalArgumentException("Priority is not an integer");
        }

        public void setPriority(int priority) {
            if (validatePriority(priority)) this.typePriority = priority;
            else
                throw new IllegalArgumentException("Priority is not an integer");
        }

        public int getPriorityValue() {
            return typePriority;
        }

        public TaskType getType() {
            return this;
        }

        /**
         * priority is represented by an integer value, ranging from 1 to 10
         * * @param priority
         *
         * @return whether the priority is valid or not
         */
        private static boolean validatePriority(int priority) {
            if (priority < 1 || priority > 10)
                return false;
            return true;
        }

    }

}
