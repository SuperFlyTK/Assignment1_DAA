package org.example;

public class Metrics {
    private int comparisons;
    private int swaps;

    public void reset() {
        comparisons = 0;
        swaps = 0;
    }

    public void incComparisons() {
        comparisons++;
    }

    public void incSwaps() {
        swaps++;
    }

    public int getComparisons() {
        return comparisons;
    }

    public int getSwaps() {
        return swaps;
    }
}
