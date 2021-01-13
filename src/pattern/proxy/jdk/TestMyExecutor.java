package pattern.proxy.jdk;

public class TestMyExecutor {
    public static void main(String[] args) {
        Executor myExecutor = new MyExecutor();
        MyExecutorProxy proxy = new MyExecutorProxy();
        proxy.setTarget(myExecutor);
        Executor mc = (Executor) proxy.createProxyObject();
        mc.exec("hello");
    }
}
