package com.yevhent.concurrency.locks.livelock;

import java.util.concurrent.locks.Lock;

public class ChopsticksPair {
    private final String name;

    private final Lock first;
    private final Lock second;

    public ChopsticksPair(String name, Lock first, Lock second) {
        this.name = name;
        this.first = first;
        this.second = second;
    }

    public boolean take() throws InterruptedException {
        first.lockInterruptibly();
        processing();
        if (second.tryLock()) {
            return true;
        }
        processing();
        first.unlock();
        return false;
    }

    public void release() {
        second.unlock();
        first.unlock();
    }

    protected void processing() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return name;
    }

    public static String toString(int first, int second) {
        return String.format("ChopsticksPair: [%d,%d]", first, second);
    }
}