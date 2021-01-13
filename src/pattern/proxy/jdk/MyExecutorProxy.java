package pattern.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyExecutorProxy implements InvocationHandler {
    // 被代理的对象
    private Object target;

    public void setTarget(Object target) {
        this.target = target;
    }

    private void prePrint(String s) {
        System.out.println("pre: " + s);
    }

    private void afterPrint(String s) {
        System.out.println("after: " + s);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //参数
        String s = (String) args[0];

        // 增强
        this.prePrint(s);

        Object result = method.invoke(target, args);

        //增强
        this.afterPrint(s);
        return result;
    }

    public Object createProxyObject() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }
}
