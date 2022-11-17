package pattern.observer;

/**
 * 核心思想: <p>
 *
 * @author bill
 * @since 2022/11/18 02:13
 */
public class Weather extends Subject {
    private final double min;
    private final double max;
    private double temp;

    public double getTemp() {
        return temp;
    }

    public Weather(double min, double max) {
        this.min = min;
        this.max = max;
        this.temp = 0D;
    }

    public void generateTemp() {
        double nextTemp = Math.random() * (max - min) + min;
        if (temp != nextTemp) {
            temp = nextTemp;
            this.notifyObservers();
        }
    }
}
