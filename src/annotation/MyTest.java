package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解的实现分为：定义，使用，读取处理，一般我们只会有使用这一层
 * 第三方框架会完成定义和读取处理
 * 下面简单实现三个自定义注解
 * 定义部分
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MyTest {
}
