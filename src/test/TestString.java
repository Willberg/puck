package test;

public class TestString {
    public static void main(String[] args) {
        String a = "a";
        String b = "a";
        String c = a;
        String d = new String("a");

        System.out.println(a==b);
        System.out.println(a==c);
        System.out.println(a==d);

        String e = a.intern();
        String f = b.intern();
        String g = d.intern();
        System.out.println(e==f);
        System.out.println(e==g);

        System.out.println(a.equals(d));
    }
}
