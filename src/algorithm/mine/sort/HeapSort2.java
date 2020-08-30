package algorithm.mine.sort;

import java.util.Arrays;

public class HeapSort2 {
	private int[] arr;

	public HeapSort2(int[] arr) {
		this.arr = arr;
	}

	// 排序，先小顶堆化，然后每次将堆顶放到数组尾部，最后排序成递减的数列
	public void sort() {
		int len = arr.length;
		minHeapify(len);
		while (len > 0) {
			swap(0, len - 1);
			len--;
			minHeapify(len);
		}
	}

	// 小顶堆化
	public void minHeapify(int len) {
		int i = len / 2 - 1;
		while (i >= 0) {
			int j = 2 * i + 1;
			if (j + 1 < len && arr[j] > arr[j + 1]) {
				j = j + 1;
			}
			if (arr[i] > arr[j]) {
				swap(i, j);
			}
			i--;
		}
	}

	private void swap(int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}


	/**
	 * 测试用例
	 * [3, 5, 3, 0, 8, 6, 1, 5, 8, 6, 2, 4, 9, 4, 7, 0, 1, 8, 9, 7, 3, 1, 2, 5, 9, 7, 4, 0, 2, 6]
	 * 输出：
	 * [9, 9, 9, 8, 8, 8, 7, 7, 7, 6, 6, 6, 5, 5, 5, 4, 4, 4, 3, 3, 3, 2, 2, 2, 1, 1, 1, 0, 0, 0]
	 */
	public static void main(String[] args) {
		int[] arr = new int[]{3, 5, 3, 0, 8, 6, 1, 5, 8, 6, 2, 4, 9, 4, 7, 0, 1, 8, 9, 7, 3, 1, 2, 5, 9, 7, 4, 0, 2, 6};
		new HeapSort2(arr).sort();
		System.out.println(Arrays.toString(arr));
	}
}
