package exercize.binarysearch;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arr = new int[] {42, 12, 3, 69, 74, 0, 8};
        int elem = 42;
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println("Ищем " + elem);
        int rez = search(arr,  0, arr.length - 1, elem);
        if(rez == -1)
            System.out.println("Элемент не найден");
        else
            System.out.println("Индекс искомого элемента: " + rez);
    }

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
