package com.yevhent.concurrency.locks;

public interface Basket {

    /**
     * Should be Thread safe. Potentially has nested lock
     */
    void putOneGarlicAndOnePotato();

    /**
     * Should be Thread safe. Potentially has nested lock
     */
    void levelPotatoesAndPutOneCarrot();

    /**
     * Not Thread safe, just to check amount
     */
    int getGarlicTotal();

    /**
     * Not Thread safe, just to check amount
     */
    int getPotatoTotal();

    /**
     * Not Thread safe, just to check amount
     */
    int getCarrotTotal();
}