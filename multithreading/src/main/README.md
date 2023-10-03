## Multithreading bases

#### Demo 1: Thread vs Process 

1. On the pc under Windows OS open Task Manager and switch to Performance tab.<br>
   On CPU pane you can see overall CPU utilization which is about 10% in regular state. 
2. Start our [app](java/com/yevhent/ThreadVsProcess.java) and see how CPU utilization is increased: can be up to 100%.
3. Get Process ID value from console output, e.g. 7428.<br>
   Now in Task Manager use Open Resource Monitor link to see more details.<br>
   In Resource Monitor window on CPU tab in Processes pane you can find our Java app by that Process ID.<br>
   There we can see number of Threads and CPU utilization in percents.
   Number of Threads is usually greater than created by our program.
   Others background Threads are created for util functions like garbage collection and runtime compilations.
4. Stop our app and see overall CPU utilization in Task Manager.<br>
   It should be decreased to regular state about 10%.