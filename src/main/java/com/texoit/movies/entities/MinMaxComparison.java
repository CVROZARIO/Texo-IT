package com.texoit.movies.entities;

public class MinMaxComparison {

    private ComparisonItem min;
    private ComparisonItem max;

    public MinMaxComparison() {
        super();
    }

    public MinMaxComparison(ComparisonItem min, ComparisonItem max) {
        super();
        this.min = min;
        this.max = max;
    }

    public ComparisonItem getMin() {
        return min;
    }

    public void setMin(ComparisonItem min) {
        this.min = min;
    }

    public ComparisonItem getMax() {
        return max;
    }

    public void setMax(ComparisonItem max) {
        this.max = max;
    }

    public static class ComparisonItem {

        public String producer;
        public int interval;
        public int previousWin;
        public int followingWin;

        public ComparisonItem() {
            super();
        }

        public ComparisonItem(String producer, int previousWin, int followingWin) {
            super();
            this.producer = producer;
            this.previousWin = previousWin;
            this.followingWin = followingWin;
            this.interval = Math.abs(followingWin - previousWin);
        }

        public String getProducer() {
            return producer;
        }

        public void setProducer(String producer) {
            this.producer = producer;
        }

        public int getInterval() {
            return interval;
        }

        public void setInterval(int interval) {
            this.interval = interval;
        }

        public int getPreviousWin() {
            return previousWin;
        }

        public void setPreviousWin(int previousWin) {
            this.previousWin = previousWin;
        }

        public int getFollowingWin() {
            return followingWin;
        }

        public void getFollowingWin(int followingWin) {
            this.followingWin = followingWin;
        }

    }

}
