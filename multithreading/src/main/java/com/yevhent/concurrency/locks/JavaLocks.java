package com.yevhent.concurrency.locks;

import com.yevhent.concurrency.locks.basket.BlockingReentrantBasket;
import com.yevhent.concurrency.locks.basket.NonBlockingReentrantBasket;
import com.yevhent.concurrency.locks.basket.SimpleBasket;

public class JavaLocks {

    private static final int number = 15;

    public static void main(String[] args) throws InterruptedException {
        System.out.println();
        System.out.println("Hi guys!");
        System.out.println("Olivia, please buy " + number + " potatoes and " + number + " garlic in total.");
        System.out.println("Barron, please keep track of potatoes and buy " + number + " carrots.");
        System.out.println("So, in total we should have \n" + number + " potatoes, \n" + number + " garlic, \n" + number + " carrots.");
        System.out.println();
        goShopping(new SimpleBasket());
        System.out.println();
        System.out.println("Sorry guys, but we have more potatoes than needed!");
        System.out.println("This time, let's have Blocking Reentrant basket:");
        System.out.println();
        goShopping(new BlockingReentrantBasket());
        System.out.println();
        System.out.println("That's better! We have right amount of potatoes now!");
//        System.out.println("But you shopping takes so much time :(");
//        System.out.println("This time, let's have Non-Blocking Reentrant basket:");
//        System.out.println();
//        goShopping(new NonBlockingReentrantBasket());
//        System.out.println();
//        System.out.println("That's better! We have right amount of potatoes now!");
    }

    static void goShopping(Basket basket) throws InterruptedException {
        Thread barron = new Barron(basket);
        Thread olivia = new Olivia(basket);
        barron.start();
        olivia.start();
        barron.join();
        olivia.join();
        System.out.println("We bought " + basket.getPotatoTotal() + " potatoes.");
        System.out.println("We bought " + basket.getGarlicTotal() + " garlic.");
        System.out.println("We bought " + basket.getCarrotTotal() + " carrot.");
    }

    static class Barron extends Thread {

        private final Basket basket;

        public Barron(Basket basket) {
            this.basket = basket;
        }

        public void run() {
            System.out.println("Barron: I'm going to check potatoes and add " + number + " carrots ...");
            for (int i = 0; i < number; i++) {
                basket.levelPotatoesAndPutOneCarrot();
            }
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