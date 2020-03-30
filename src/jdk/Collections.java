package jdk;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Collections {
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new LinkedList<>();

        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new LinkedHashSet<>();

        Map<String, List<String>> map1 = new HashMap<>();
        Map<String, String> map2 = new Hashtable<>();
        Map<String, String> map3 = new ConcurrentHashMap<>();

        System.out.println("hello");
    }
}
