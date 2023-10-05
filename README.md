# Java Multithreading Demo

_Learn and play with Concurrent and Parallel programming using Java._

Implemented based on LinkedIn learning courses:
- [**Concurrent Programming with Java**](https://www.linkedin.com/learning/parallel-and-concurrent-programming-with-java-1 'https://www.linkedin.com/learning/parallel-and-concurrent-programming-with-java-1')  
- [**Parallel Programming with Java**](https://www.linkedin.com/learning/parallel-and-concurrent-programming-with-java-2 'https://www.linkedin.com/learning/parallel-and-concurrent-programming-with-java-2')

Covered topics:

#### Multithreading bases
- [**Thread vs Process**](#demo-1-thread-vs-process)
- [**Executing Scheduling**](#demo-2-executing-scheduling)
- [**Thread Lifecycle**](#demo-3-thread-lifecycle)
#### Concurrency in Java
- [**Mutual Exclusion: Data Race problem**](#demo-4-mutual-exclusion---resolving-data-race-problem)
- [**Java Lock Interface**](#demo-5-java-lock-interface)

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
2. Check console output.
3. There we have 11 rounds of execution.<br>
   Each time we start 2 Threads with the same order: Baron first, Olivia next.<br> 
   By this, it is expected that Baron wins each time by chopping more vegetables as it goes first.<br> 
   However, actual results is unpredictable and depends on Threads scheduling by system.<br>
   For example, I have 7 times when Baron chopped more vegetables, and Olivia wins 4 times.

#### Demo 3: Thread Lifecycle

1. Start [app](multithreading/src/main/java/com/yevhent/bases/ThreadLifecycle.java)
2. Check console output.
3. There we have all possible states:
   - `NEW` - A thread that has not yet started is in this state.
   - `RUNNABLE` - A thread executing in the Java virtual machine is in this state.
   - `BLOCKED` - A thread that is blocked waiting for a monitor lock is in this state.
   - `WAITING` - A thread that is waiting indefinitely for another thread to perform a particular action is in this state.
   - `TIMED_WAITING` - A thread that is waiting for another thread to perform an action for up to a specified waiting time is in this state.
   - `TERMINATED` - A thread that has exited is in this state.

### Concurrency

#### Demo 4: Mutual Exclusion - resolving Data Race problem

1. Start [app](multithreading/src/main/java/com/yevhent/concurrency/datarace/MutualExclusion.java)
2. Check console output.
3. Review code in this [package](multithreading/src/main/java/com/yevhent/concurrency/datarace).
4. Problem: There we have 2 same Threads that increase counter 10_000_000 times.<br>
   However, since these Threads use the same shared data class, calculation goes wrong way.<br>
   Finally, we have some unexpected value like 11_149_076 instead of 20_000_000.
5. Solution: We have Thread safe shared data classes implemented based on:
   - ReentrantLook
   - Synchronized method
   - Synchronized block
   - Atomic variable

#### Demo 5: Java Lock Interface

1. Start [app](multithreading/src/main/java/com/yevhent/concurrency/locks/JavaLocks.java).
2. Check console output.
3. Review code in this [package](multithreading/src/main/java/com/yevhent/concurrency/locks).
4. Nested Look Problem: Using locks we can have situation when Thread blocks himself by acquire same lock twice (without releasing after first lock).<br>
   In java we have Reentrant Lock by default which allows specific Thread to acquire same lock several times.<br>
   To release lock completely, Thread should unlock the same number of times as it was acquired.<br>
   ReentrantLock class allows you to see number of holds made by Thread.



  