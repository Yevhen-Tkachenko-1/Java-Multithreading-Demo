package com.yevhent.concurrency.locks.nested.basket;

public class SimpleBasket implements Basket {

    protected int garlicCount = 0;
    protected int potatoCount = 0;

    @Override
    public void putOneGarlicAndOnePotato() {
        garlicCount++;
        putOnePotato();
    }

    @Override
    public void putOnePotato() {
        potatoCount++;
    }

    @Override
    public int getGarlicTotal() {
        return garlicCount;
    }

    @Override
    public int getPotatoTotal() {
        return potatoCount;
    }
}