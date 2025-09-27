package org.example;

public class Metrics {
    private int comparisons;
    private int swaps;

    public void reset() {
        comparisons = 0;
        swaps = 0;
    }

    // Методы вызывающие MergeSort
    public void incrementComparisons() {
        comparisons++;
    }

    public void incrementSwaps() {
        swaps++;
    }

    public int getComparisons() {
        return comparisons;
    }

    public int getSwaps() {
        return swaps;
    }
}
