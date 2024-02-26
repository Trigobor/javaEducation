package exercize.customHashMap;

import exercize.customHashMap.classes.MyHashMap;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        MyHashMap<Integer, String> map = new MyHashMap<>();
        map.put(8, "test");
        map.put(2, "second key");
        map.put(3, "test2");
        map.put(18, "must be written to same bucket as 2");
        System.out.println("----------------------------------------------------------------");
        System.out.println(map.get(8));
        System.out.println(map.get(2));
        System.out.println(map.get(3));
        map.put(8, "lol");
        System.out.println("----------------------------------------------------------------");
        System.out.println(map.get(8));
        System.out.println(map.get(2));
        System.out.println(map.get(3));
        map.remove(18);
        System.out.println("----------------------------------------------------------------");
        System.out.println(map.get(8));
        System.out.println(map.get(2));
        System.out.println(map.get(3));
        System.out.println(map.get(18));
        map.put(18, "18");
        map.remove(3);
        map.remove(2);
        System.out.println("----------------------------------------------------------------");
        System.out.println(map.get(8));
        System.out.println(map.get(2));
        System.out.println(map.get(3));
        System.out.println(map.get(18));
        map.put(2, "second key");
        map.put(34, "34");
        map.remove(2);
        System.out.println("----------------------------------------------------------------");
        System.out.println(map.get(8));
        System.out.println(map.get(2));
        System.out.println(map.get(3));
        System.out.println(map.get(18));
        System.out.println(map.get(34));
        System.out.println("---------------------------recap test---------------------------");
        map.put(19, "19");
        map.put(20, "20");
        map.put(21, "21");
        map.put(22, "22");
        map.put(23, "23");
        map.put(24, "24");
        map.put(25, "25");
        map.put(26, "26");
        map.put(27, "27");
        map.put(28, "28");
        map.put(29, "29");
        map.put(30, "30");
        map.put(31, "31");
        map.put(32, "32");
        map.put(33, "33");
        System.out.println(map.get(8));
        System.out.println(map.get(2));
        System.out.println(map.get(3));
        System.out.println(map.get(18));
        System.out.println(map.get(19));
        System.out.println(map.get(20));
        System.out.println(map.get(21));
        System.out.println(map.get(22));
        System.out.println(map.get(23));
        System.out.println(map.get(24));
        System.out.println(map.get(25));
        System.out.println(map.get(26));
        System.out.println(map.get(27));
        System.out.println(map.get(28));
        System.out.println(map.get(29));
        System.out.println(map.get(30));
        System.out.println(map.get(31));
        System.out.println(map.get(32));
        System.out.println(map.get(33));
        System.out.println(map.get(34));

    }
}
