package org.example;

public class BinarySearch {
    public static int search(int[] arr, int left, int right, int elem) {
        int mid;
        while (left <= right) {
            mid = left + (right - left)/2;
            if (arr[mid] == elem)
                return mid;
            if (arr[mid] > elem)
                right = mid - 1;
            else
                left = mid + 1;
        }
        return -1;
    }
}
