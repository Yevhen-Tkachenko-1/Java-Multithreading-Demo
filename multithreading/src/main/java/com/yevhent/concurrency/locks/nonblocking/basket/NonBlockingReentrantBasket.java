package com.yevhent.concurrency.locks.nonblocking.basket;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NonBlockingReentrantBasket extends SimpleBasket {

    private final Lock lock = new ReentrantLock();

    @Override
    public void putOneGarlicAndOnePotato() {
        try {
            lock.lock();
            standInLine();
            garlicCount++;
            isPotatoEnough = false;
            putOnePotato();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void levelPotatoesAndPutOneCarrot() {
        if (lock.tryLock()) {
            try {
                standInLine();
                if (!isPotatoEnough) {
                    putOnePotato();
                }
            } finally {
                lock.unlock();
            }
        }
        putOneCarrot();
    }

    @Override
    protected void putOnePotato() {
        try {
            lock.lock();
            standInLine();
            potatoCount++;
            isPotatoEnough = true;
        } finally {
            lock.unlock();
        }
    }
}
