package com.yevhent.concurrency.locks.basket;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingReentrantBasket<T extends Lock> extends SimpleBasket {

    private final T lock;

    public BlockingReentrantBasket() {
        this((T) new ReentrantLock());
    }

    public BlockingReentrantBasket(T lock) {
        this.lock = lock;
    }

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
        standInLine();
        try {
            lock.lock();
            if (!isPotatoEnough) {
                putOnePotato();
            }
        } finally {
            lock.unlock();
        }

        putOneCarrot();
    }

    /**
     * Should be Thread safe. Is nested lock
     */
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
