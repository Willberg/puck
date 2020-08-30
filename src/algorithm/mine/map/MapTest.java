package algorithm.mine.map;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapTest {
	public static void main(String[] args) {
		Map<Object, Object> map1 =new HashMap<>();
		map1.put(null, "1");
		map1.put(1, null);
		map1.put(null, null);
		System.out.println(map1.get(null));

		Map<Object, Object> map2 = new ConcurrentHashMap<>();
		map2.put(1,null);
		map2.put(null, 2);
		map2.put(null, null);
		System.out.println(map2.get(null));
	}
}
