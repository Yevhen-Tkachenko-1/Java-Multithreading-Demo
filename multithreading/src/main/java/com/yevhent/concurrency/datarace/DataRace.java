package com.yevhent.concurrency.datarace;

public class DataRace {

    public static void main(String[] args) throws InterruptedException {
        System.out.println();
        System.out.println("Guys, let's buy 20_000_000 garlic in total!");
        System.out.println();
        goShopping(new Basket.SimpleBasket());
        System.out.println();
        System.out.println("Sorry guys, but this is not what was expected!");
        System.out.println();
    }

    static void goShopping(Basket basket) throws InterruptedException {
        Thread barron = new Shopper("Barron", basket);
        Thread olivia = new Shopper("Olivia", basket);
        barron.start();
        olivia.start();
        barron.join();
        olivia.join();
        System.out.println("We bought " + basket.getTotal() + " garlic.");
    }
}
