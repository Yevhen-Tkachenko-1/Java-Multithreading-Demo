package com.yevhent.concurrency.locks.nested.basket;

import java.util.concurrent.locks.ReentrantLock;

public class NestedReentrantBasket extends SimpleBasket {

    protected final ReentrantLock lock = new ReentrantLock();

    public int nestedLocks = 0;

    @Override
    public void putOneGarlicAndOnePotato() {
        try {
            lock.lock();
            super.putOneGarlicAndOnePotato();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void putOnePotato() {
        try {
            lock.lock();
            super.putOnePotato();
            if (lock.getHoldCount() == 2) {
                nestedLocks++;
            }
        } finally {
            lock.unlock();
        }
    }
}