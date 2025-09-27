package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class QuickSortTest {

    @Test
    void testQuickSort() {
        int[] arr = {9, 3, 7, 1, 8, 2};
        int[] expected = {1, 2, 3, 7, 8, 9};

        Metrics metrics = new Metrics();
        QuickSort.quickSort(arr, metrics);

        assertArrayEquals(expected, arr);
        System.out.println("Comparisons: " + metrics.getComparisons());
        System.out.println("Swaps: " + metrics.getSwaps());
    }
}
