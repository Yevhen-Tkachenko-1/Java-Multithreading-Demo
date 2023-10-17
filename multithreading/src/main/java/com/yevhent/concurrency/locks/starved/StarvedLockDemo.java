package com.yevhent.concurrency.locks.starved;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class StarvedLockDemo {

    public static void main(String[] args) throws InterruptedException {

        System.out.println();
        System.out.println("Yevhen: Dear Philosophers,");
        System.out.println("        For you 3 people we have only 2 chopsticks passing in the same priority.");
        System.out.println("        So, you are going to have the same rights to food.");
        System.out.println("        Let's have a dinner!");
        System.out.println();
        eatSushi(Philosopher.PotentiallyStarvedPhilosopher::new);
        Thread.sleep(2_000);
        System.out.println();
        System.out.println("Yevhen: well, that's definitely not the same rights to food.");
        System.out.println("        Some of you got too much, and others not enough.");
        System.out.println("        I don't have solution for now! TODO: fix later"); // TODO
        System.out.println();
    }

    static void eatSushi(Function<String, Philosopher> factory) {
        ChopsticksPair pair = ChopsticksPair.getSimple();
        int philosopherCount = 20;
        SushiDish sushiDish = new SushiDish(1000, philosopherCount);

        List<Philosopher> philosophers = new ArrayList<>();
        for (int i = 0; i < philosopherCount; i++) {
            Philosopher philosopher = factory.apply("Barron-" + i);
            philosophers.add(philosopher);
        }
        for (Philosopher philosopher : philosophers) {
            philosopher.haveDinner(pair, sushiDish);
        }
    }
}