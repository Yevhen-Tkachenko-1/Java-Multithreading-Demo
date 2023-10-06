package com.yevhent.concurrency.locks.nonblocking.basket;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingReentrantBasket extends SimpleBasket {

    protected final Lock lock = new ReentrantLock();

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
    protected void levelPotatoes() {
        try {
            lock.lock();
            super.levelPotatoes();
        } finally {
            lock.unlock();
        }
    }

    @Override
    protected void putOnePotato() {
        try {
            lock.lock();
            super.putOnePotato();
        } finally {
            lock.unlock();
        }
    }
}
