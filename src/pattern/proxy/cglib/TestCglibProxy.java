package pattern.proxy.cglib;

public class TestCglibProxy {
    public static void main(String[] args) {
        CglibProxy proxy = new CglibProxy();
        //这里的createProxyObject方法就是正式创建代理类
        MyExecutor mc = (MyExecutor) proxy.createProxyObject(MyExecutor.class);
        //调用代理类的exec方法
        mc.exec("hello");
    }
}
