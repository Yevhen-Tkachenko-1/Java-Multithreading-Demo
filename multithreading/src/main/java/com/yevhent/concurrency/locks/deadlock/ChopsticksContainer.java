package com.yevhent.concurrency.locks.deadlock;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public interface ChopsticksContainer {

    List<ChopsticksPair> getPairs();

    static List<Lock> getAllChopsticks() {
        return List.of(new ReentrantLock(), new ReentrantLock(), new ReentrantLock());
    }

    class CircularChopsticks implements ChopsticksContainer {

        @Override
        public List<ChopsticksPair> getPairs() {

            List<Lock> allChopsticks = getAllChopsticks();
            return List.of(
                    new ChopsticksPair(ChopsticksPair.toString(0, 1), allChopsticks.get(0), allChopsticks.get(1)),
                    new ChopsticksPair(ChopsticksPair.toString(1, 2), allChopsticks.get(1), allChopsticks.get(2)),
                    new ChopsticksPair(ChopsticksPair.toString(2, 0), allChopsticks.get(2), allChopsticks.get(0))
            );
        }
    }

    class PrioritizedChopsticks implements ChopsticksContainer {

        @Override
        public List<ChopsticksPair> getPairs() {
            List<Lock> allChopsticks = getAllChopsticks();
            return List.of(
                    new ChopsticksPair(ChopsticksPair.toString(0, 1), allChopsticks.get(0), allChopsticks.get(1)),
                    new ChopsticksPair(ChopsticksPair.toString(1, 2), allChopsticks.get(1), allChopsticks.get(2)),
                    new ChopsticksPair(ChopsticksPair.toString(0, 2), allChopsticks.get(0), allChopsticks.get(2))
            );
        }
    }
}