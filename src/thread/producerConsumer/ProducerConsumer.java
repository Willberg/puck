package thread.producerConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 核心思想: 生产消费者模式
 *
 * @author bill
 * @since 2022/11/2 00:15
 */
public class ProducerConsumer {
    private List<Integer> q;
    private volatile int capacity;
    private AtomicInteger ai;

    public ProducerConsumer(int n) {
        capacity = n;
        q = new ArrayList<>(n);
        ai = new AtomicInteger(30);
    }

    public boolean isEmpty() {
        return q.size() == 0;
    }

    public boolean isFull() {
        return q.size() == capacity;
    }

    public void produce(int v) {
        synchronized (this) {
            while (isFull()) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            q.add(v);
            try {
                Thread.sleep(11);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("%s 生产 %d, size:%d\n", Thread.currentThread().getName(), v, q.size());
            this.notifyAll();
        }
    }

    public void consumer() {
        synchronized (this) {
            while (isEmpty()) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("%s 消费 %d, size:%d\n", Thread.currentThread().getName(), q.remove(0), q.size());
            this.notifyAll();
        }
    }

    public static void main(String[] args) {
        ProducerConsumer pc = new ProducerConsumer(10);
        for (int i = 0; i < 20; i++) {
            Thread t = new Thread(() -> {
                for (int j = 1; j <= 100; j++) {
                    int v = j;
                    String[] name = Thread.currentThread().getName().split("-");
                    v += 1000 * (Integer.parseInt(name[1]) + 1);
                    pc.produce(v);
                }
                pc.ai.addAndGet(-1);
            });
            t.start();
        }
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(() -> {
                for (int j = 1; j <= 200; j++) {
                    pc.consumer();
                }
                pc.ai.addAndGet(-1);
            });
            t.start();
        }
        while (pc.ai.get() != 0) {
            Thread.yield();
        }
    }
}
