package test;

public class TestBits {
    static int hash(Object key) {
        int h;
        System.out.println(h = key.hashCode());
        System.out.println(h >>> 16);
        return (key == null) ? 0 : h ^ (h >>> 16);
    }

    public static void main(String[] args) {
        int h = -1 << 31;
        System.out.println(h);
        int h1 = h >> 31;
        System.out.println(h1);
        int h2 = h >>> 31;
        System.out.println(h2);

        System.out.println(hash("342"));
    }
}
