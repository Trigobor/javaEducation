package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinarySearchTest {

    @Test
    public void testBinarySearchSuccess() {
        int[] arr = new int[] {12, 3, 69, 42, 74, 0, 8};
        int elem = 42;
        assertEquals(3, BinarySearch.search(arr,  0, arr.length - 1, elem));
    }

    @Test
    public void testBinarySearchFailure() {
        int[] arr = new int[] {12, 3, 69, 42, 74, 0, 8};
        int elem = 99;
        assertEquals(-1, BinarySearch.search(arr,  0, arr.length - 1, elem));
    }
}
