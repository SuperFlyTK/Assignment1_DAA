package org.example;

public class MergeSort {
    private final Metrics metrics;

    public MergeSort(Metrics metrics) {
        this.metrics = metrics;
    }

    public void sort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        if (metrics != null) metrics.reset();
        mergeSort(arr, 0, arr.length - 1);
    }

    private void mergeSort(int[] arr, int left, int right) {
        if (left >= right) return;
        if (metrics != null) metrics.enterRecursion();

        int mid = (left + right) >>> 1;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);

        if (metrics != null) metrics.exitRecursion();
    }

    private void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        System.arraycopy(arr, left, L, 0, n1);
        System.arraycopy(arr, mid + 1, R, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (metrics != null) metrics.incrementComparisons();
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
            if (metrics != null) metrics.incrementSwaps();
        }
        while (i < n1) {
            arr[k++] = L[i++];
            if (metrics != null) metrics.incrementSwaps();
        }
        while (j < n2) {
            arr[k++] = R[j++];
            if (metrics != null) metrics.incrementSwaps();
        }
    }
}
