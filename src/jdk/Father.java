package jdk;

public class Father {
    private static String FATHER_STATIC = "father static";
    private String sonMethod = getFatherMethod();

    static {
        System.out.println("father static block");
    }

    public Father() {
        System.out.println("father construct");
    }

    private String getFatherMethod() {
        System.out.println("Father normal method");
        return "Father normal method";
    }

    protected void print() {
        System.out.println("This is Father");
    }

    protected void f() {
        System.out.println("father method");
    }

    protected void test() {
        print();
    }
}
