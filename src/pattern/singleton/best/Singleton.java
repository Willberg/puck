package pattern.singleton.best;

import java.util.Date;

/**
 * @author john
 * @date 2021/3/1 下午4:31
 * @description 最佳的单例模式（线程安全）,利用类的初始化是线程安全的，内部类会延迟加载（Initialization on Demand Holder）
 * @version v1.0
 */
public class Singleton {
    private Singleton () {

    }

    // 其他功能
    private Date date = new Date();

    public Date getDate() {
        return date;
    }

    private static class Holder {
        public static Singleton instance = new Singleton();
    }

    public static Singleton getInstance() {
        return Holder.instance;
    }
}
