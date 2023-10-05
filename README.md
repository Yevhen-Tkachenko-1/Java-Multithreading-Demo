# Java Multithreading Demo

_Learn and play with Concurrent and Parallel programming using Java._

Implemented based on LinkedIn learning courses:
- [**Concurrent Programming with Java**](https://www.linkedin.com/learning/parallel-and-concurrent-programming-with-java-1 'https://www.linkedin.com/learning/parallel-and-concurrent-programming-with-java-1')  
- [**Parallel Programming with Java**](https://www.linkedin.com/learning/parallel-and-concurrent-programming-with-java-2 'https://www.linkedin.com/learning/parallel-and-concurrent-programming-with-java-2')

Covered topics:

- [**Thread vs Process**](#demo-1-thread-vs-process)
- [**Executing Scheduling**](#demo-2-executing-scheduling)
- [**Thread Lifecycle**](#demo-3-thread-lifecycle)
- [**Data Race**](#demo-4-data-race-problem)
- [**Mutual Exclusion**](#demo-5-mutual-exclusion---avoiding-data-race-problem)

### Multithreading bases

#### Demo 1: Thread vs Process

1. On the pc under Windows OS open Task Manager and switch to Performance tab.<br>
   On CPU pane you can see overall CPU utilization which is about 10% in regular state.
2. Start [app](multithreading/src/main/java/com/yevhent/bases/ThreadVsProcess.java) and see how CPU utilization is increased: can be up to 100%.
3. Get Process ID value from console output, e.g. 7428.<br>
   Now in Task Manager use Open Resource Monitor link to see more details.<br>
   In Resource Monitor window on CPU tab in Processes pane you can find our Java app by that Process ID.<br>
   There we can see number of Threads and CPU utilization in percents.
   Number of Threads is usually greater than created by our program.
   Others background Threads are created for util functions like garbage collection and runtime compilations.
4. Stop our app and see overall CPU utilization in Task Manager.<br>
   It should be decreased to regular state about 10%.

#### Demo 2: Executing Scheduling

1. Start [app](multithreading/src/main/java/com/yevhent/bases/ExecutingScheduling.java)
2. Check console output:<br>
    There we have 11 rounds of execution.<br>
    Each time we start 2 Threads with the same order: Baron first, Olivia next.<br> 
    By this, it is expected that Baron wins each time by chopping more vegetables as it goes first.<br> 
    However, actual results is unpredictable and depends on Threads scheduling by system.<br>
    For example, I have 7 times when Baron chopped more vegetables, and Olivia wins 4 times.

#### Demo 3: Thread Lifecycle

1. Start [app](multithreading/src/main/java/com/yevhent/bases/ThreadLifecycle.java)
2. Check console output.<br>
   There we have all possible states:
   - `NEW` - A thread that has not yet started is in this state.
   - `RUNNABLE` - A thread executing in the Java virtual machine is in this state.
   - `BLOCKED` - A thread that is blocked waiting for a monitor lock is in this state.
   - `WAITING` - A thread that is waiting indefinitely for another thread to perform a particular action is in this state.
   - `TIMED_WAITING` - A thread that is waiting for another thread to perform an action for up to a specified waiting time is in this state.
   - `TERMINATED` - A thread that has exited is in this state.

### Concurrency

#### Demo 4: Data Race problem

1. Start [app](multithreading/src/main/java/com/yevhent/concurrency/datarace/DataRace.java)
2. Check console output.<br>
   There we have 2 same Threads that increase counter 10_000_000 times.<br>
   However, since these Threads use the same shared data class, calculation goes wrong way.<br>
   Finally, we have some unexpected value like 11_149_076 instead of 20_000_000.

#### Demo 5: Mutual Exclusion - avoiding Data Race problem

1. Start [app](multithreading/src/main/java/com/yevhent/concurrency/datarace/MutualExclusion.java)
2. Check console output.<br>
   Now we have Thread safe shared data classes implemented based on:
   - ReentrantLook
   - Synchronized method
   - Synchronized block
   - Atomic variable

  