package com.yevhent.concurrency.datarace;

public class Shopper extends Thread {

    private final Basket basket;

    public Shopper(String name, Basket basket) {
        super(name);
        this.basket = basket;
    }

    public void run() {
        System.out.println(this.getName() + ": I'm going to add 10_000_000 garlic ...");

        for (int i = 0; i < 10_000_000; i++)
            basket.putOneGarlic();
    }
}