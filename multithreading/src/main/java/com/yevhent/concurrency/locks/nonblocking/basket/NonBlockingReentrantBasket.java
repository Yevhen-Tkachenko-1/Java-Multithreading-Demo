package com.yevhent.concurrency.locks.nonblocking.basket;

public class NonBlockingReentrantBasket extends BlockingReentrantBasket {

    @Override
    protected void levelPotatoes() {
        if (lock.tryLock()) {
            try {
                super.levelPotatoes();
            } finally {
                lock.unlock();
            }
        }
    }
}
