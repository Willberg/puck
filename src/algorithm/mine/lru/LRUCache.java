package algorithm.mine.lru;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
	private int count;
	private int capacity;
	private Map<String, Node> map;
	private Node head, tail;

	class Node {
		String key;
		String value;
		Node pre;
		Node post;
	}

	public LRUCache(int capacity) {
		this.count = 0;
		this.capacity = capacity;
		this.map = new HashMap<>(capacity);

		head = new Node();
		head.pre = null;

		tail = new Node();
		tail.post = null;

		head.post = tail;
		tail.pre = head;
	}

	public String get(String key) {
		Node n = map.get(key);
		if (n == null) {
			return "-1";
		}

		this.moveToHead(n);
		return n.value;
	}


	public synchronized void set(String key, String value) {
		Node n = map.get(key);
		if (n == null) {
			n = new Node();
			n.key = key;
			n.value = value;

			count++;
			if (count > capacity) {
				// 删除最久未使用的node
				Node tailNode = popTailNode();
				map.remove(tailNode.key);
				count--;
			}

			map.put(key, n);
			addNode(n);
		} else {
			n.value = value;
			moveToHead(n);
		}
	}

	private void addNode(Node n) {
		n.pre = head;
		n.post = head.post;

		head.post.pre = n;
		head.post = n;
	}

	private void removeNode(Node n) {
		n.pre.post = n.post;
		n.post.pre = n.pre;
		n.pre = null;
		n.post = null;
	}

	private void moveToHead(Node n) {
		removeNode(n);
		addNode(n);
	}

	private Node popTailNode() {
		Node n = tail.pre;
		removeNode(n);
		return n;
	}
}
