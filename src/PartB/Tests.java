package PartB;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.util.concurrent.*;

import static java.lang.Thread.sleep;

public class Tests {

    public static final Logger logger = LoggerFactory.getLogger(Tests.class);

    @Test
    public void partialTest() {

        CustomExecutor customExecutor = new CustomExecutor();

        var task = Task.createTask(() -> {
            int sum = 0;
            for (int i = 1; i <= 10; i++) {
                sum += i;
            }
            return sum;
        }, TaskType.COMPUTATIONAL);

        var sumTask = customExecutor.submit(task);


        final int sum;
        try {
            sum = (int) sumTask.get(1, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new RuntimeException(e);
        }
        logger.info(() -> "Sum of 1 through 10 = " + sum);

        Callable<Double> callable1 = ()-> {
          return 1000 * Math.pow(1.02, 5);
        };


        Callable<String> callable2 = ()-> {
            StringBuilder sb = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
            return sb.reverse().toString();
        };



        // var is used to infer the declared type automatically
        var priceTask = customExecutor.submit(()-> { return 1000 * Math.pow(1.02, 5);
              }, TaskType.COMPUTATIONAL);
        var reverseTask = customExecutor.submit(callable2, TaskType.IO);
        final Double totalPrice;
        final String reversed;
        try {
            totalPrice = (Double) priceTask.get();
            reversed = (String) reverseTask.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        logger.info(()-> "Reversed String = " + reversed);
        logger.info(()->String.valueOf("Total Price = " + totalPrice));
        System.out.println("LAST" + customExecutor);
        logger.info(()-> "Current maximum priority = " + customExecutor.getCurrentMax());
        customExecutor.gracefullyTerminate();


    }


    // this test create 500 tasks, every task gets a different priority value.
    // in the console we can see the max priority in any moment.
    @Test
    public void getCurrentMaxTest(){
        Callable<Integer> callable1 = (() -> 10 * 5);

        CustomExecutor c = new CustomExecutor();

        for(int i=0;i<500;i++) {
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




        c.gracefullyTerminate();

    }





    // This test checks 2 thing. 1 that after shutting down the pool - no thread can be submitted.
    //                           2 that the remaining threads in the queue will execute before terminated

    @Test
    public void shutdown() throws InterruptedException {
        CustomExecutor customExecutor = new CustomExecutor();
        Task<Integer> task1 = Task.createTask((() -> {
            sleep(500);
            return 1;
        }), TaskType.OTHER);

        customExecutor.submit(task1);
        customExecutor.gracefullyTerminate();


        Assertions.assertThrows(RuntimeException.class, () -> {
            customExecutor.submit(task1); // This submit throws exception.
        });

        //finish all task in threads
        logger.info(customExecutor::toString); // before task is finished
        sleep(2000);
        logger.info(customExecutor::toString); // after task has finished

        // The second test
        CustomExecutor customExecutor1 = new CustomExecutor();
        for (int i = 0; i < 10; i++) {
            customExecutor1.submit(Task.createTask((() -> {
                sleep(1000);
                return 1;
            }), TaskType.OTHER));
        }
        customExecutor1.gracefullyTerminate();
        logger.info(customExecutor1::toString); // before termination
        sleep(10000); // we created 10 task that every task waits for one second. so the termination will be after 10.
        logger.info(customExecutor1::toString); // after termination
    }

}
