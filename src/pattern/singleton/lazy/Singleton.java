package pattern.singleton.lazy;

// 懒汉式（非线程安全）
public class Singleton {
    private static Singleton instance;

    //初次调用该方法时，实例化
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public static void main(String[] args) {
        pattern.singleton.hungry.Singleton.getInstance();
    }
}
