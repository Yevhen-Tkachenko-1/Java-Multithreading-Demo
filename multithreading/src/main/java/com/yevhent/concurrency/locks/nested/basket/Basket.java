package com.yevhent.concurrency.locks.nested.basket;

public interface Basket {

    /**
     * Should be Thread safe. Potentially has nested lock
     */
    void putOneGarlicAndOnePotato();

    /**
     * Should be Thread safe. Potentially has nested lock
     */
    void putOnePotato();

    /**
     * Not Thread safe, just to check amount
     */
    int getGarlicTotal();

    /**
     * Not Thread safe, just to check amount
     */
    int getPotatoTotal();
}