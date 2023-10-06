package com.yevhent.concurrency.locks.nonblocking;

import com.yevhent.concurrency.locks.nonblocking.basket.Basket;
import com.yevhent.concurrency.locks.nonblocking.basket.BlockingReentrantBasket;
import com.yevhent.concurrency.locks.nonblocking.basket.NonBlockingReentrantBasket;
import com.yevhent.concurrency.locks.nonblocking.basket.SimpleBasket;

public class NestedAndNonBlockingLocks {

    private static final int number = 15;

    public static void main(String[] args) throws InterruptedException {
        System.out.println();
        System.out.println("Yevhen: Hi guys!");
        System.out.println("        Olivia, please buy " + number + " potatoes and " + number + " garlic in total.");
        System.out.println("        Barron, please keep track of potatoes and buy " + number + " carrots.");
        System.out.println("        So, in total we should have " + number + " potatoes, " + number + " garlic, " + number + " carrots.");
        System.out.println();
        goShopping(new SimpleBasket());
        System.out.println();
        System.out.println("Yevhen: Sorry guys, but we have more potatoes than needed!");
        System.out.println("        This time, let's have Blocking Reentrant basket:");
        System.out.println();
        goShopping(new BlockingReentrantBasket());
        System.out.println();
        System.out.println("Yevhen: That's better! We have right amount of potatoes now!");
        System.out.println("        However,it took more time now :(");
        System.out.println("        Barron, you can ignore potatoes if there are a lot of people in the line.");
        System.out.println("        This time, let's have Non-Blocking Reentrant basket:");
        System.out.println();
        goShopping(new NonBlockingReentrantBasket());
        System.out.println();
        System.out.println("Yevhen: Great job Barron! You've done it faster now!");
    }

    static void goShopping(Basket basket) throws InterruptedException {
        Thread barron = new Barron(basket);
        Thread olivia = new Olivia(basket);
        barron.start();
        olivia.start();
        barron.join();
        olivia.join();
        System.out.println("Barron and Olivia: We bought "
                + basket.getPotatoTotal() + " potatoes, "
                + basket.getGarlicTotal() + " garlic, "
                + basket.getCarrotTotal() + " carrots.");
    }

    static class Barron extends Thread {

        private final Basket basket;

        public Barron(Basket basket) {
            this.basket = basket;
        }

        public void run() {
            long start = System.currentTimeMillis();
            System.out.println("Barron: I'm going to check potatoes and add " + number + " carrots ...");
            for (int i = 0; i < number; i++) {
                basket.levelPotatoesAndPutOneCarrot();
            }
            System.out.println("Barron: I'm done, it took me " + (System.currentTimeMillis() - start));
        }
    }

    static class Olivia extends Thread {

        private final Basket basket;

        public Olivia(Basket basket) {
            this.basket = basket;
        }

        public void run() {
            System.out.println("Olivia: I'm going to add " + number + " potatoes and " + number + " garlic ...");
            for (int i = 0; i < number; i++) {
                basket.putOneGarlicAndOnePotato();
            }
        }
    }
}