package com.yevhent.concurrency.locks.starved;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ChopsticksPair {

    private final Lock first;
    private final Lock second;

    public ChopsticksPair(Lock first, Lock second) {
        this.first = first;
        this.second = second;
    }

    public boolean take() throws InterruptedException {
        first.lockInterruptibly();
        if (second.tryLock()) {
            return true;
        }
        first.unlock();
        return false;
    }

    public void release() {
        second.unlock();
        first.unlock();
    }

    public static ChopsticksPair getSimple() {
        return new ChopsticksPair(new ReentrantLock(), new ReentrantLock());
    }
}