package pattern.singleton.hungry;

// 只在类加载阶段初始化 （线程安全）
public class Singleton {

    private static Singleton instance = new Singleton();

    public static Singleton getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        Singleton.getInstance();
    }
}
