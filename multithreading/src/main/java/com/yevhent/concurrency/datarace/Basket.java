package com.yevhent.concurrency.datarace;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

interface Basket {

    /**
     * Thread safe
     */
    void putOneGarlic();

    /**
     * Not Thread safe, just to check amount
     */
    int getTotal();


    class SimpleBasket implements Basket {

        private int garlicCount = 0;

        @Override
        public void putOneGarlic() {
            garlicCount++;
        }

        @Override
        public int getTotal() {
            return garlicCount;
        }
    }

    class ReentrantBasket implements Basket {

        private final Lock lock = new ReentrantLock();

        private int garlicCount = 0;

        @Override
        public void putOneGarlic() {
            lock.lock();
            garlicCount++;
            lock.unlock();
        }

        @Override
        public int getTotal() {
            return garlicCount;
        }
    }

    class MethodSynchronizedBasket implements Basket {

        private int garlicCount = 0;

        @Override
        public synchronized void putOneGarlic() {
            garlicCount++;
        }

        @Override
        public int getTotal() {
            return garlicCount;
        }
    }

    class BlockSynchronizedBasket implements Basket {

        private int garlicCount = 0;

        @Override
        public void putOneGarlic() {
            synchronized (this) {
                garlicCount++;
            }
        }

        @Override
        public int getTotal() {
            return garlicCount;
        }
    }

    class AtomicBasket implements Basket {

        private final AtomicInteger garlicCount = new AtomicInteger(0);

        @Override
        public void putOneGarlic() {
            garlicCount.incrementAndGet();
        }

        @Override
        public int getTotal() {
            return garlicCount.get();
        }
    }
}