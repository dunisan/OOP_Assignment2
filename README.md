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


<img width="300" alt="Screen Shot 5783-04-18 at 23 57 02" src="https://user-images.githubusercontent.com/57772699/211926518-73c73757-2e52-4609-8c99-ee486718d031.png">

<img width="474" alt="Screen Shot 5783-04-18 at 23 58 20" src="https://user-images.githubusercontent.com/57772699/211926646-c4c94ad3-afac-42c2-a6e9-ba7f39b5a1a1.png">


<img width="465" alt="Screen Shot 5783-04-18 at 23 59 32" src="https://user-images.githubusercontent.com/57772699/211926862-3d89f047-467e-4c4c-a804-c0ac2e6a03e0.png">

<img width="485" alt="Screen Shot 5783-04-19 at 0 04 59" src="https://user-images.githubusercontent.com/57772699/211927794-a45c15f2-631b-4533-93f4-ad54ddd664c3.png">


<img width="487" alt="Screen Shot 5783-04-19 at 0 04 23" src="https://user-images.githubusercontent.com/57772699/211927707-51a755e6-7e06-4500-a448-82969df7073b.png">

<img width="494" alt="Screen Shot 5783-04-19 at 0 03 38" src="https://user-images.githubusercontent.com/57772699/211927588-4431014f-a40e-4ee5-98e7-1a00d1ba1026.png">


#### summery
In this assigment we needed to show the differnce between non threaded opperation, threaded operation and threadpool operation.
To do that we created 2 additional classes: 
Threadfiles, Threadpoolfiles.
In main we created the files, then we started to count the number of lines without any threads,
after that we counted the same number of line with threading, and right after we did the same with thread pool.

Our conclusion is that using thread is faster than no threads and threadpool is faster than thread.
That is because when we use normal threads in each time that a thread finishes his job the thread get terminated and the threadpool reuse threads untill theres no more task to be done.



<img width="956" alt="Screen Shot 5783-04-18 at 23 49 24" src="https://user-images.githubusercontent.com/57772699/211925164-df530fea-8041-443d-aea0-99e0ef933a79.png">




### part B in detail 

In this assigment we needed to create a threadpool that can relate with priority for Callable tasks.
To do that we used a given enum tasktype and created two new classes: Task, CustomeExecuter.

#### Task class
The Task class extends FutureTask and implements Callable and Comparable.

### Fields and Methods
The class holds two field: Callable task and Tasktype tasktype which represent the task that is callable and the priority that the task have.
The Task type has a class methods such as getters, setters, equals and more for future updates.
We actually use only compareTo().

#### CustomExecuter class
The CustomExecuter class extends ThreadPoolExecuter

### Fields and Methods
The class holds five fields:  int numOfCores, maxPriority. PriorityBlockingQueue priorityBlockingQueue, Task top.
Methods: 
* getCurrentMax() -> returns the max priority of the queue.
* update() -> updates the maxPriority field. used in afterExecute() (explantion in code) and in sumbit(Task task).
* afterExectue() -> execution completed normally and update() maxPriority.
* submit(Task task) -> submitting new task into a thread in the threadpool.
* gracefullyTerminate() -> shut down the threadpool.















         
         

