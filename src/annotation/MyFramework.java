package annotation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

//读取解析部分
public class MyFramework {
    public static void main(String[] args) throws Exception {
        // 1.找到测试类字节码
        Class clazz = AnnotationTest.class;
        // 初始化对象
        Object object = clazz.newInstance();

        // 2.获取所有公共方法(java反射)
        Method[] methods = clazz.getMethods();

        // 3.迭代处理每一个Method对象，根据不同注解做不同处理
        List<Method> myBeforeList = new ArrayList<>();
        List<Method> myTestList = new ArrayList<>();
        List<Method> myAfterList = new ArrayList<>();

        for (Method method : methods) {
            if (method.isAnnotationPresent(MyBefore.class)) {
                myBeforeList.add(method);
            } else if (method.isAnnotationPresent(MyTest.class)) {
                myTestList.add(method);
            } else if (method.isAnnotationPresent(MyAfter.class)) {
                myAfterList.add(method);
            }
        }

        // 进行处理
        for (Method method : myTestList) {
            // 执行before方法
            for (Method beforeMethod : myBeforeList) {
                beforeMethod.invoke(object);
            }

            // 执行test方法
            method.invoke(object);

            // 执行after方法
            for (Method afterMethod : myAfterList) {
                afterMethod.invoke(object);
            }
        }
    }
}
