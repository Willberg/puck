package pattern.singleton.hungry;

/**
 * @Author John
 * @Description 饿汉式，只在类加载阶段初始化 （线程安全）
 * @Date 2020/4/2 2:11 PM
 **/
public class Singleton {

	private static Singleton instance = new Singleton();

	public static Singleton getInstance() {
		return instance;
	}

	public static void main(String[] args) {
		Singleton.getInstance();
	}
}
