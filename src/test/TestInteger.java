package test;

import java.util.ArrayList;
import java.util.List;

public class TestInteger {
    public static void main(String[] args) {
        Integer a = -128;
        Integer b = -128;
        System.out.println(a == b);

        Integer c = 128;
        Integer d = 128;
        System.out.println(c == d);

        Integer e = Integer.valueOf(-128);
        Integer f = Integer.valueOf(-128);
        System.out.println(e == f);

        List<Integer> list = new ArrayList<>();
        list.add(1, 1);
    }
}
