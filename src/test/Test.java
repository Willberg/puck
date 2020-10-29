package test;

import algorithm.mine.lru.LRUCache;

import java.util.stream.IntStream;

public class Test {
	public static void main(String[] args) {
		LRUCache lruCache = new LRUCache(16);
		IntStream.range(0, 10).forEach(v -> {
			new Thread(() -> {
				int i = 0;
				while (i < 1000) {
					lruCache.set(String.valueOf(i), String.valueOf(i));
					i++;
				}
			}).start();
		});

		IntStream.range(0, 20).forEach(v -> {
			new Thread(() -> {
				int i = 0;
				while (i < 1000) {
					String value = lruCache.get(String.valueOf(i));
					if (value.equals("-1")) {
						System.out.println(Thread.currentThread().getName() + ":" + i);
					} else {
						System.out.println(Thread.currentThread().getName() + ":" + value);
					}

					i++;
				}
			}).start();
		});
	}
}
