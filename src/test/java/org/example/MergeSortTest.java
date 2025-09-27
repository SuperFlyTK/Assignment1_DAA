package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MergeSortTest {

    @Test
    public void testMergeSort() {
        int[] arr = {5, 2, 9, 1, 5, 6};
        Metrics metrics = new Metrics();
        MergeSort mergeSort = new MergeSort(metrics);

        mergeSort.sort(arr);

        assertArrayEquals(new int[]{1, 2, 5, 5, 6, 9}, arr);
        System.out.println("Comparisons: " + metrics.getComparisons());
        System.out.println("Swaps: " + metrics.getSwaps());
    }
}
