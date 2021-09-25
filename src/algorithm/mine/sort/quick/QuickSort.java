package algorithm.mine.sort.quick;

import java.util.ArrayList;
import java.util.List;

public class QuickSort {
	private static void qSort(List<Integer> list, int low, int high) {
		int i = low;
		int j = high;
		if (i >= j) {
			return;
		}

		int pivot = list.get(i);
		while (i < j) {
			while (i < j && list.get(j) >= pivot) {
				j--;
			}
			list.set(i, list.get(j));

			while (i < j && list.get(i) <= pivot) {
				i++;
			}
			list.set(j, list.get(i));
		}

		// 交换完成之后，将指标数据放回原列表，不一定是原位置，i已经变化
		list.set(i, pivot);
		qSort(list, low, i - 1);
		qSort(list, i + 1, high);

	}

	public static void main(String[] args) {
		int n = 30;
		List<Integer> list = new ArrayList<>(n);
		for (int i = 0; i < n; i++) {
			int v = Double.valueOf(Math.random() * 100).intValue();
			list.add(v);
			System.out.print(v + " ");
		}


		qSort(list, 0, list.size() - 1);
		System.out.println("");
		list.forEach(v -> System.out.print(v + " "));
	}
}
