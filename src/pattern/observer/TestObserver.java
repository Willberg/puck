package pattern.observer;

/**
 * 核心思想: <p>
 *
 * @author bill
 * @since 2022/11/18 02:15
 */
public class TestObserver {
    public static void main(String[] args) {
        Weather w = new Weather(-20D, 40D);
        Observer cel = new CelsiusView();
        w.addObserver(cel);
        Observer wear = new WearView();
        w.addObserver(wear);
        for (int i = 0; i < 10; i++) {
            w.generateTemp();
        }
    }
}


class CelsiusView implements Observer {


    @Override
    public void update(Subject s) {
        if (s instanceof Weather) {
            System.out.printf("%.2f\n", ((Weather) s).getTemp());
        }
    }
}

class WearView implements Observer {


    @Override
    public void update(Subject s) {
        if (s instanceof Weather) {
            double cur = ((Weather) s).getTemp();
            if (cur < 0) {
                System.out.printf("寒冷, temp: %.2f\n", cur);
            } else if (cur < 10D) {
                System.out.printf("凉爽, temp: %.2f\n", cur);
            } else {
                System.out.printf("适宜, temp: %.2f\n", cur);
            }
        }
    }
}
