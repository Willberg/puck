package annotation;

// 使用部分
public class AnnotationTest {
    @MyBefore
    public void init() {
        System.out.println("初始化......");
    }

    @MyAfter
    public  void destroy() {
        System.out.println("销毁......");
    }

    @MyTest
    public void test1() {
        System.out.println("进行测试1中......");
    }

    @MyTest
    public void test2() {
        System.out.println("进行测试2中......");
    }
}
