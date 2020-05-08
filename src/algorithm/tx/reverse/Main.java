package algorithm.tx.reverse;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
	private static void solve(int n, Integer[] nArr, int m, Integer[] mArr) {
		List<Integer> l = new LinkedList<>();

		for (int i = 0; i < m; i++) {
			// 每个分组数量
			int q = 1 << mArr[i];

			if (q == 1) {
				printReverseGroup(nArr);
				continue;
			}
			// 分组次数
			int k = 0;
			Integer[] newNArr = new Integer[nArr.length];
			int[] qArr = new int[q];
			for (int j = q * k; j < nArr.length; j++) {
				qArr[j % q] = nArr[j];

				if (j != 0 && (j + 1) % q == 0) {
					reverseArr(qArr);
					for (int p = 0; p < q; p++) {
						newNArr[q * k + p] = qArr[p];
					}
					k++;
				}
			}

			nArr = newNArr;
			printReverseGroup(newNArr);
		}
	}

	private static void printReverseGroup(Integer[] arr) {
		int total = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] > arr[j]) {
					total++;
				}
			}
		}
		System.out.println(total + " ");
	}

	private static void reverseArr(int[] arr) {
		int low = 0;
		int high = arr.length - 1;
		while (low < high) {
			int tmp = arr[low];
			arr[low] = arr[high];
			arr[high] = tmp;
			low++;
			high--;
		}
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		while (input.hasNextLine()) {
			int n = Integer.valueOf(input.nextLine());
			String inputLine = input.nextLine();
			List<Integer> list = Arrays.stream(inputLine.split(" ")).map(v -> Integer.valueOf(v)).collect(Collectors.toList());
			Integer[] nArr = list.toArray(new Integer[list.size()]);

			int m = Integer.valueOf(input.nextLine());
			inputLine = input.nextLine();
			list = Arrays.stream(inputLine.split(" ")).map(v -> Integer.valueOf(v)).collect(Collectors.toList());
			Integer[] mArr = list.toArray(new Integer[list.size()]);
			solve(n, nArr, m, mArr);
		}


	}
}
