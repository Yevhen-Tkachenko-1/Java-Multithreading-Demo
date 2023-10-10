# Java Multithreading Demo

_Learn and play with Concurrent and Parallel programming using Java._

Implemented based on LinkedIn learning courses:

- [**Concurrent Programming with Java**](https://www.linkedin.com/learning/parallel-and-concurrent-programming-with-java-1 'https://www.linkedin.com/learning/parallel-and-concurrent-programming-with-java-1')
- [**Parallel Programming with Java**](https://www.linkedin.com/learning/parallel-and-concurrent-programming-with-java-2 'https://www.linkedin.com/learning/parallel-and-concurrent-programming-with-java-2')

Covered topics:

[**Multithreading bases**](#multithreading-bases)

- **Thread vs Process**
- **Executing Scheduling**
- **Thread Lifecycle**

[**Concurrency in Java**](#concurrency-in-java)

- **Mutual Exclusion: Data Race problem**
- **Nested Locks**
- **Non-Blocking Locks**
- **Read-Write Locks**
- **Dead Locks**

### Multithreading bases

#### Demo 1: Thread vs Process

1. On the pc under Windows OS open Task Manager and switch to Performance tab.<br>
   On CPU pane you can see overall CPU utilization which is about 10% in regular state.
2. Start [app](multithreading/src/main/java/com/yevhent/bases/ThreadVsProcess.java) and see how CPU utilization is
   increased: can be up to 100%.
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
    - `WAITING` - A thread that is waiting indefinitely for another thread to perform a particular action is in this
      state.
    - `TIMED_WAITING` - A thread that is waiting for another thread to perform an action for up to a specified waiting
      time is in this state.
    - `TERMINATED` - A thread that has exited is in this state.

### Concurrency in Java

#### Demo 4: Mutual Exclusion - resolving Data Race problem

1. Start [app](multithreading/src/main/java/com/yevhent/concurrency/datarace/MutualExclusionDemo.java)
2. Check console output.
3. Review code in this [package](multithreading/src/main/java/com/yevhent/concurrency/datarace).

- **Problem**: There we have 2 same Threads that increase counter 10_000_000 times.<br>
   However, since these Threads use the same shared data class, calculation goes wrong way.<br>
   Finally, we have some unexpected value like 11_149_076 instead of 20_000_000.
- **Solution**: We have Thread safe shared data classes implemented based on:
    - ReentrantLook
    - Synchronized method
    - Synchronized block
    - Atomic variable

#### Demo 5: Nested Locks

1. Start [app](multithreading/src/main/java/com/yevhent/concurrency/locks/nested/NestedReentrantLockDemo.java).
2. Check console output.
3. Review code in this [package](multithreading/src/main/java/com/yevhent/concurrency/locks/nested).

- **Nested Look Problem**: Using locks we can have situation when Thread blocks himself by acquiring same lock
  twice (without releasing after first lock).
  In java we have Reentrant Lock by default which allows specific Thread to acquire same lock several times.
  To release lock completely, Thread should unlock the same number of times as it was acquired.
  ReentrantLock class allows you to see number of holds made by Thread.

#### Demo 6: Non-Blocking Locks

1. Start [app](multithreading/src/main/java/com/yevhent/concurrency/locks/nonblocking/NonBlockingLockDemo.java).
2. Check console output.
3. Review code in this [package](multithreading/src/main/java/com/yevhent/concurrency/locks/nonblocking).

- **Non-Blocking Look**: using locks solves problem of data race, but requires you to wait unit lock is released.<br>
  In case you don't need immediate result and have some other job to do you can use **lock try**.<br>
  If lock is free you will take it, otherwise you will skip locked part and do alternative or just next job.

#### Demo 7: Read-Write Locks

1. Start [app](multithreading/src/main/java/com/yevhent/concurrency/locks/readwrite/ReadWriteLockDemo.java).
2. Check console output.
3. Review code in this [package](multithreading/src/main/java/com/yevhent/concurrency/locks/readwrite).

- Let's imagine situation when only one Thread is changing variable and many Threads reading it.
  With usual approach we lock both reading and writing access.
  That works fine in terms of synchronization, but makes program slow.
  Taking into account that most of the time Data is needed for reading only, we can soften the lock.
  When Data is not blocked by changing, it accessible for reading without blocking, 
  so can be accessible by many Threads at the same time.

#### Demo 8: Dead Locks

1. Start [app](multithreading/src/main/java/com/yevhent/concurrency/locks/deadlock/DeadLockDemo.java).
2. Check console output.
3. Review code in this [package](multithreading/src/main/java/com/yevhent/concurrency/locks/deadlock).

- When several Threads use several shared Locks, it might be situation when Threads blocked by each other and stuck with no progress.
- For example, we have Thread1 and Thread2 which both use Lock1 and Lock2. 
  Dead Lock happens with next steps: Thread1 acquires Lock1 and Thread2 acquires Lock2. 
  Then Thread1 tries to acquire Lock2 and becomes blocked as Lock2 already taken by Thread2.
  Then the same happens with Thread2 which tries to acquire Lock1.
- One of the solutions can be Locks Prioritizing. 
  With this Thread1 and Thread2 should first try to acquire Lock1 and only then Lock2.
  