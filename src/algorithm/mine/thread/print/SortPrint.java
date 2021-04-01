package algorithm.mine.thread.print;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 我们提供了一个类：
 * <p>
 * public class Foo {
 *   public void first() { print("first"); }
 *   public void second() { print("second"); }
 *   public void third() { print("third"); }
 * }
 * 三个不同的线程 A、B、C 将会共用一个 Foo 实例。
 * <p>
 * 一个将会调用 first() 方法
 * 一个将会调用 second() 方法
 * 还有一个将会调用 third() 方法
 * 请设计修改程序，以确保 second() 方法在 first() 方法之后被执行，third() 方法在 second() 方法之后被执行。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3]
 * 输出: "firstsecondthird"
 * 解释:
 * 有三个线程会被异步启动。
 * 输入 [1,2,3] 表示线程 A 将会调用 first() 方法，线程 B 将会调用 second() 方法，线程 C 将会调用 third() 方法。
 * 正确的输出是 "firstsecondthird"。
 * 示例 2:
 * <p>
 * 输入: [1,3,2]
 * 输出: "firstsecondthird"
 * 解释:
 * 输入 [1,3,2] 表示线程 A 将会调用 first() 方法，线程 B 将会调用 third() 方法，线程 C 将会调用 second() 方法。
 * 正确的输出是 "firstsecondthird"。
 *  
 * <p>
 * 提示：
 * <p>
 * 尽管输入中的数字似乎暗示了顺序，但是我们并不保证线程在操作系统中的调度顺序。
 * 你看到的输入格式主要是为了确保测试的全面性。
 */
class Foo {
    ReentrantLock lock = new ReentrantLock();
    volatile int k = 0;
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        lock.lock();
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        k++;
        // 唤醒相应的线程，并且相关线程获取await时释放的锁
        condition1.signal();
        lock.unlock();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        lock.lock();
        while (k != 1) {
            // await使当前线程进入等待队列，并释放与当前线程相关的锁
            condition1.await();
        }
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        k++;
        condition2.signal();
        lock.unlock();
    }

    public void third(Runnable printThird) throws InterruptedException {
        lock.lock();
        while (k != 2) {
            condition2.await();
        }
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
        lock.unlock();
    }
}

public class SortPrint {
    public static void main(String[] args) {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("first");
            }
        };

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("second");
            }
        };

        Runnable r3 = new Runnable() {
            @Override
            public void run() {
                System.out.println("third");
            }
        };


        Foo f = new Foo();
        Thread t1 = new Thread(() -> {
            try {
                f.first(r1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                f.second(r2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t3 = new Thread(() -> {
            try {
                f.third(r3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t2.start();
        t1.start();
        t3.start();
    }


}
