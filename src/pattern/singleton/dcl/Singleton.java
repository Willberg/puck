package pattern.singleton.dcl;


/**
 * @Author John
 * @Description 双检查单例模式（线程安全）
 * @Date 2020/4/2 2:11 PM
 **/
public class Singleton {
	private volatile static Singleton instance;

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
