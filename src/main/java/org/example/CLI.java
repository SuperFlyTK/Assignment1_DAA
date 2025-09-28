package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class CLI {
    public static void main(String[] args) {
        int[] sizes = {100, 500, 1000, 2000, 4000, 8000, 16000, 32000};
        Metrics metrics = new Metrics();
        Random rand = new Random();

        try (FileWriter writer = new FileWriter("results.csv")) {
            writer.write("n,algorithm,time_ns,comparisons,swaps,max_depth\n");

            for (int n : sizes) {
                int[] arr = rand.ints(n, 0, 100000).toArray();

                // MergeSort
                int[] copy1 = Arrays.copyOf(arr, arr.length);
                metrics.reset();
                MergeSort ms = new MergeSort(metrics);
                long start = System.nanoTime();
                ms.sort(copy1);
                long end = System.nanoTime();
                writer.write(n + ",MergeSort," + (end - start) + "," + metrics.getComparisons() + "," + metrics.getSwaps() + "," + metrics.getMaxDepth() + "\n");

                // QuickSort
                int[] copy2 = Arrays.copyOf(arr, arr.length);
                metrics.reset();
                start = System.nanoTime();
                QuickSort.quickSort(copy2, metrics);
                end = System.nanoTime();
                writer.write(n + ",QuickSort," + (end - start) + "," + metrics.getComparisons() + "," + metrics.getSwaps() + "," + metrics.getMaxDepth() + "\n");
            }

            System.out.println("Results written to results.csv");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
