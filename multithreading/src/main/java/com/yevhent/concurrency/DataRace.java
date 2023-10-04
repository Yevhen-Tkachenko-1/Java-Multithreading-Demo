package com.yevhent.concurrency;

public class DataRace {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Guys, let's buy 20_000_000 garlic in total!");
        Thread barron = new Shopper("Barron");
        Thread olivia = new Shopper("Olivia");
        barron.start();
        olivia.start();
        barron.join();
        olivia.join();
        System.out.println("We bought " + Shopper.garlicCount + " garlic.");
    }
}

class Shopper extends Thread {

    static int garlicCount = 0;

    public Shopper(String name) {
        super(name);
    }

    public void run() {
        System.out.println(this.getName() + ": I'm going to add 10_000_000 garlic ...");

        for (int i = 0; i < 10_000_000; i++)
            garlicCount++;
    }
}