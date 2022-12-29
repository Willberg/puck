package spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 核心思想: 测试spi
 *
 * @author bill
 * @since 2022/12/29 15:02
 */
public class TestSpi {
    public static void main(String[] args) {
        ServiceLoader<Search> s = ServiceLoader.load(Search.class);
        Iterator<Search> iterator = s.iterator();
        while (iterator.hasNext()) {
            Search search = iterator.next();
            search.searchDoc("hello, world!");
        }
    }
}
