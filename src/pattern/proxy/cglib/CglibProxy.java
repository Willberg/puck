package pattern.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {
    private void prePrint(String s) {
        System.out.println("pre: " + s);
    }

    private void afterPrint(String s) {
        System.out.println("after: " + s);
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        //参数
        String s = (String) objects[0];

        // 增强
        this.prePrint(s);

        Object result = methodProxy.invokeSuper(o, objects);

        //增强
        this.afterPrint(s);

        return result;
    }

    // 根据类型产生代理类
    public Object createProxyObject(Class<?> clazz) {
        //创建Enhancer对象，类似于JDK动态代理的Proxy类，下一步就是设置几个参数
        Enhancer enhancer =  new Enhancer();
        //设置目标类的字节码文件
        enhancer.setSuperclass(clazz);
        //设置回调函数
        enhancer.setCallback(this);
        return enhancer.create();
    }
}
