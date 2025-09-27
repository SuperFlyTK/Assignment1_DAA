package org.example;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeterministicSelectTest {

    @Test
    void testSmallArray() {
        int[] arr = {9, 3, 7, 1, 8, 2};
        Metrics metrics = new Metrics();
        int result = DeterministicSelect.select(arr.clone(), 2, metrics);

        Arrays.sort(arr);
        assertEquals(arr[2], result);
    }

    @Test
    void testRandomArrays() {
        Random rand = new Random();
        for (int t = 0; t < 20; t++) {
            int[] arr = rand.ints(20, 0, 100).toArray();
            int k = rand.nextInt(arr.length);
            Metrics metrics = new Metrics();

            int result = DeterministicSelect.select(arr.clone(), k, metrics);

            Arrays.sort(arr);
            assertEquals(arr[k], result);
        }
    }
}
