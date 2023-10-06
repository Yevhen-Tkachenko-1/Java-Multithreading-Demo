package com.yevhent.concurrency.locks.nonblocking.basket;

public class SimpleBasket implements Basket {

    protected int garlicCount = 0;
    protected int potatoCount = 0;
    protected int carrotCount = 0;

    protected boolean isPotatoEnough = true;

    @Override
    public void putOneGarlicAndOnePotato() {
        standInLine();
        garlicCount++;
        isPotatoEnough = false;
        putOnePotato();
    }

    @Override
    public void levelPotatoesAndPutOneCarrot() {
        levelPotatoes();
        putOneCarrot();
    }

    @Override
    public int getGarlicTotal() {
        return garlicCount;
    }

    @Override
    public int getPotatoTotal() {
        return potatoCount;
    }

    @Override
    public int getCarrotTotal() {
        return carrotCount;
    }

    protected void levelPotatoes() {
        standInLine();
        if (!isPotatoEnough) {
            putOnePotato();
        }
    }

    protected void putOnePotato() {
        standInLine();
        potatoCount++;
        isPotatoEnough = true;
    }

    protected void putOneCarrot() {
        standInLine();
        carrotCount++;
    }

    protected void standInLine() {
        try {
            Thread.sleep((long) (Math.random() * 100));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
