package com.yevhent.concurrency.locks.starved;

public interface Philosopher {

    void haveDinner(ChopsticksPair pair, SushiDish dish);

    class PotentiallyStarvedPhilosopher extends Thread implements Philosopher {

        private ChopsticksPair chopsticks;
        private SushiDish sushiDish;

        public PotentiallyStarvedPhilosopher(String name) {
            this.setName(name);
        }

        @Override
        public void haveDinner(ChopsticksPair chopsticks, SushiDish sushiDish) {
            this.chopsticks = chopsticks;
            this.sushiDish = sushiDish;
            start();
        }

        public void run() {
            try {
                int eaten = 0;
                while (sushiDish.hasSushi()) {
                    if (chopsticks.take()) {
                        sushiDish.eatSushi();
                        eaten++;
                        chopsticks.release();
                    }
                }
                System.out.println(this.getName() + ": ate " + eaten + " pieces. " + sushiDish.getSatisfaction(eaten));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}