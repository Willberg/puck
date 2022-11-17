package pattern.observer;

import java.util.LinkedList;
import java.util.List;

/**
 * 核心思想: 目标对象，被观察者
 *
 * @author bill
 * @since 2022/11/18 02:08
 */
public abstract class Subject {
    private List<Observer> list;

    public Subject() {
        list = new LinkedList<>();
    }

    public void addObserver(Observer o) {
        list.add(o);
    }

    public void delObserver(Observer o) {
        list.remove(o);
    }

    public void notifyObservers() {
        for (Observer o : list) {
            o.update(this);
        }
    }
}
