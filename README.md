# Java-Multithreading-Demo

Learn and play with Concurrent and Parallel programming using Java

Implemented based on LinkedIn learning courses:
- [**Concurrent Programming with Java**](https://www.linkedin.com/learning/parallel-and-concurrent-programming-with-java-1 'https://www.linkedin.com/learning/parallel-and-concurrent-programming-with-java-1')  
- [**Parallel Programming with Java**](https://www.linkedin.com/learning/parallel-and-concurrent-programming-with-java-2 'https://www.linkedin.com/learning/parallel-and-concurrent-programming-with-java-2')

### Multithreading bases

#### Demo 1: Thread vs Process

1. On the pc under Windows OS open Task Manager and switch to Performance tab.<br>
   On CPU pane you can see overall CPU utilization which is about 10% in regular state.
2. Start our [app](multithreading/src/main/java/com/yevhent/ThreadVsProcess.java) and see how CPU utilization is increased: can be up to 100%.
3. Get Process ID value from console output, e.g. 7428.<br>
   Now in Task Manager use Open Resource Monitor link to see more details.<br>
   In Resource Monitor window on CPU tab in Processes pane you can find our Java app by that Process ID.<br>
   There we can see number of Threads and CPU utilization in percents.
   Number of Threads is usually greater than created by our program.
   Others background Threads are created for util functions like garbage collection and runtime compilations.
4. Stop our app and see overall CPU utilization in Task Manager.<br>
   It should be decreased to regular state about 10%.

#### Demo 2: Executing Scheduling

1. Start our [app](multithreading/src/main/java/com/yevhent/ExecutingScheduling.java)
2. Check console output:<br>
    There we have 11 rounds of execution. Each time we start 2 Threads with the same order: Baron first, Olivia next. 
    By this, it is expected that Baron wins each time by chopping more vegetables as it goes first. 
    However, actual results is unpredictable and depends on Threads scheduling by system.
    For example, I have 7 times when Baron chopped more vegetables, and Olivia wins 4 times.
