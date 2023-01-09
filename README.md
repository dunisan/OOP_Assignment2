# OOP_Assignment2

This project is assignment num 2 in OOP class. 
The project is built from 2 part. 
 
## part A 
In the first part, we perform a task in 3 different ways.  
First time we perform without threads, second time with threads, and in the last time using threadpool. 

## part B 

In this part we are building in new threadpool that can dicide which thread, 
will run ecording to it's prioraty. 


### part A in detail

In this part we built a class that has 4 function. 
* First function - create n files with random lines in each file(up to bound)

And 3 more function that count the number of lines that are in all of the created files in 3 ways: 
* In a regular way with the main process performing all
* With creating n threads that every thread will open a different file
* With threadpool. 

#### differences between threads and threadPool
The differnce between a group of threads and threadpool is simple:
in a group of threads after we will finish the useage of a single thread the thread will close.
in a threadpool the thread will get recycled for the next task.
#### various tests on 500 files, 1500 files 5000 files 
//TODO





#### summery
In this assigment we needed to show the differnce between non threaded opperation, threaded operation and threadpool operation.
To do that we created 2 additional classes: 
Threadfiles, Threadpoolfiles.
In main we created the files, then we started to count the number of lines without any threads,
after that we counted the same number of line with threading, and right after we did the same with thread pool.

Our conclusion is that using thread is faster than no threads and threadpool is faster than thread.
That is because when we use normal threads in each time that a thread finishes his job the thread get terminated and the threadpool reuse threads untill theres no more task to be done.


### part B in detail 
In this assigment we needed to create a threadpool that can relate with priority for Callable tasks.
To do that we used a given enum tasktype and created two new classes: Task, CustomeExecuter.

#### Task class
The Task class extends FutureTask and implements Callable and Comparable.

### Fields and Methods
The class holds two field: Callable task and Tasktype tasktype which represent the task that is callable and the priority that the task have.
The Task type has a call() method for the callable task, and more class methods such as getters and setters and equals.

#### CustomExecuter class
The CustomExecuter class extends ThreadPoolExecuter

### Fields and Methods
The class holds five fields:  int numOfCores, maxPriority. PriorityBlockingQueue priorityBlockingQueue, Task top.
Methods: getCurrentMax() returns the max priority of the queue.
         update() updates the maxPriority field. used in afterExecute() (explantion in code) and in sumbit(Task task).
         afterExectue() execution completed normally and update() maxPriority.
         submit(Task task) submitting new task into a thread in the threadpool.
         gracefullyTerminate() shut down the threadpool.
         
         

