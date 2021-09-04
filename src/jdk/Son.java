package jdk;

public class Son extends Father {
    private static String SON_STATIC = "son static";
    private String sonMethod = getSonMethod();

    static {
        System.out.println("son static block");
    }

    public Son() {
        System.out.println("son construct");
    }

    private String getSonMethod() {
        System.out.println("Son normal method");
        return "Son normal method";
    }

    protected void print() {
        System.out.println("this is son");
    }

    public void onlySon() {
        System.out.println("OnlySon");
    }

    public static void main(String[] args) {

        //初始化顺序: 父类静态变量,父类静态块->子类静态变量,子类静态块->父类成员变量,父类构造方法->子类成员变量,子类构造方法
        Father s = new Son();
//		Father f = new Father();

//		s.print();
//		s.test();
//		s.f();
//		((Son) s).onlySon();
//
//		f.print();
//		f.test();
//		f.f();
        System.out.println(s);
    }
}
