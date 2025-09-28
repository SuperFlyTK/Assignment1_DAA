package org.example;

import java.util.Random;

public class QuickSort {
    private static final Random rand = new Random();

    public static void quickSort(int[] arr, Metrics metrics) {
        if (metrics != null) metrics.reset();
        quickSort(arr, 0, arr.length - 1, metrics);
    }

    private static void quickSort(int[] arr, int left, int right, Metrics metrics) {
        while (left < right) {
            if (metrics != null) metrics.enterRecursion();

            int pivotIndex = left + rand.nextInt(right - left + 1);
            int pivot = arr[pivotIndex];

            swap(arr, pivotIndex, right, metrics);

            int partitionIndex = partition(arr, left, right, pivot, metrics);

            if (partitionIndex - left < right - partitionIndex) {
                quickSort(arr, left, partitionIndex - 1, metrics);
                left = partitionIndex + 1;
            } else {
                quickSort(arr, partitionIndex + 1, right, metrics);
                right = partitionIndex - 1;
            }

            if (metrics != null) metrics.exitRecursion();
        }
    }

    private static int partition(int[] arr, int left, int right, int pivot, Metrics metrics) {
        int i = left;
        for (int j = left; j < right; j++) {
            if (metrics != null) metrics.incrementComparisons();
            if (arr[j] < pivot) {
                swap(arr, i, j, metrics);
                i++;
            }
        }
        swap(arr, i, right, metrics);
        return i;
    }

    private static void swap(int[] arr, int i, int j, Metrics metrics) {
        if (i != j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            if (metrics != null) metrics.incrementSwaps();
        }
    }
}
