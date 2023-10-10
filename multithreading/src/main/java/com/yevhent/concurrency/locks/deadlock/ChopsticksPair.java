package com.yevhent.concurrency.locks.deadlock;

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

    public void take() throws InterruptedException {
        first.lockInterruptibly();
        second.lockInterruptibly();
    }

    public void release() {
        first.unlock();
        second.unlock();
    }

    @Override
    public String toString() {
        return name;
    }

    public static String toString(int first, int second) {
        return String.format("ChopsticksPair: [%d,%d]", first, second);
    }
}