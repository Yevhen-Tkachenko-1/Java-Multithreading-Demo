package com.yevhent.concurrency.locks.starved;

public class SushiDish {

    private final int sushiPortion;
    private int sushiCount;

    public SushiDish(int sushiTotal, int guests) {
        this.sushiCount = sushiTotal;
        this.sushiPortion = sushiTotal / guests;
    }

    public int eatSushi() {
        sushiCount--;
        return sushiCount;
    }

    public String getSatisfaction(int eaten) {
        int diff = eaten - sushiPortion;
        if (Math.abs(diff) <= 15) {
            return "I'm perfectly sated!";
        }
        return diff > 0 ? "Ate too much!" : "I'm still hungry!";
    }

    public boolean hasSushi() {
        return sushiCount > 0;
    }
}