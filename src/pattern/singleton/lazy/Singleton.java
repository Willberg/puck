package pattern.singleton.lazy;

/**
 * @Author John
 * @Description 懒汉式（非线程安全）
 * @Date 2020/4/2 2:09 PM
 **/
public class Singleton {
    private static Singleton instance;

    private Singleton() {

    }

    /**
     * @return
     * @Author John
     * @Description 初次调用该方法时，实例化
     * @Date 2020/4/2 1:58 PM
     * @Param
     **/
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public static void main(String[] args) {
        Singleton.getInstance();
    }
}
