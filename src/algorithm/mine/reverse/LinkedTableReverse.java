package algorithm.mine.reverse;

/**
 * @Author John
 * @Description 给定一个链表，将其反转
 * @Date 2020/6/24 7:35 AM
 **/
public class LinkedTableReverse {
	private static class Node {
		int value;
		Node next;
	}

	/**
	 *
	 * @param n
	 * @return 链表头
	 */
	private static Node getLinkedTable(int n) {
		Node head = null;
		Node tail = null;
		for (int i = 0; i < n; i++) {
			Node p = new Node();
			p.value = i + 1;
			p.next = null;
			if (i == 0) {
				head = p;
				tail = head;
			} else {
				tail.next = p;
				tail = p;
			}

		}
		return head;
	}

	/**
	 * 遍历
	 * @param head
	 */
	private static void traversal(Node head) {
		while (head != null) {
			System.out.println(head.value);
			head = head.next;
		}
	}

	/**
	 * 反转链表
	 * @param head
	 * @return head
	 */
	private static Node reverse(Node head) {
		Node p = head.next;
		head.next = null;
		while (p != null) {
			Node tmp = p.next;
			p.next = head;
			head = p;
			p = tmp;
		}
		return head;
	}

	public static void main(String[] args) {
		Node head = getLinkedTable(10);
		traversal(head);
		System.out.println("\n");
		head = reverse(head);
		traversal(head);
	}
}
