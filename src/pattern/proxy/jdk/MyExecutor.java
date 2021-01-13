package pattern.proxy.jdk;

public class MyExecutor implements Executor {

    @Override
    public void exec(String s) {
        System.out.println("exec: " + s);
    }
}
