import PartB.CustomExecutor;
import PartB.Task;
import PartB.TaskType;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        Callable<Integer> callable1 = (() -> 10 * 5);

        CustomExecutor c = new CustomExecutor();

        for(int i=0;i<3000;i++) {
            if(i%2==0){
                Task<Integer> task2 = Task.createTask(callable1,TaskType.COMPUTATIONAL);
                Future<Integer> result2 = c.submit(task2);

            }
            else if(i%3==0) {
                Task<Integer> task1 = Task.createTask(callable1, TaskType.OTHER);
                Future<Integer> result1 = c.submit(task1);
            }
            else {
                Task<Integer> task3 = Task.createTask(callable1, TaskType.IO);
                Future<Integer> result3 = c.submit(task3);
            }


            System.out.println(c.getCurrentMax());
            System.out.println(c);
        }

        
        c.shutdown();





    }
}