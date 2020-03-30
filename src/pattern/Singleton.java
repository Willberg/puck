package pattern;

// 饿汉式，懒汉式，双检查单例模式（线程安全）
public class Singleton {
    private volatile static Singleton instance;

    //双检查单例模式（线程安全）
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        Singleton.getInstance();
    }
}
