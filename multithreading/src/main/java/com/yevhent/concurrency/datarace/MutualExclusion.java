package com.yevhent.concurrency.datarace;

public class MutualExclusion {

    public static void main(String[] args) throws InterruptedException {
        System.out.println();
        System.out.println("Guys, let's buy 20_000_000 garlic in total!");
        System.out.println();
        System.out.println("This time, let's have Reentrant basket:");
        DataRace.goShopping(new Basket.ReentrantBasket());
        System.out.println();
        System.out.println("Or you can try Method Synchronized basket:");
        DataRace.goShopping(new Basket.MethodSynchronizedBasket());
        System.out.println();
        System.out.println("Alternatively, we have Block Synchronized basket:");
        DataRace.goShopping(new Basket.BlockSynchronizedBasket());
        System.out.println();
        System.out.println("Finally, try Atomic basket:");
        DataRace.goShopping(new Basket.AtomicBasket());
        System.out.println();
        System.out.println("Great! Now we are good.");
    }
}