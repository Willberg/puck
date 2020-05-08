package algorithm.tx.building;

import java.util.Scanner;
import java.util.Stack;

public class Main {
	private static void solve(int n, int[] arr) {
		int[] rightLook = new int[n];
		Stack<Integer> stack = new Stack<>();
		// 往右看所有能看到的楼
		for (int i = n - 1; i >= 0; i--) {
			rightLook[i] = stack.size();
			while (!stack.isEmpty() && arr[i] >= arr[stack.peek()]) {
				stack.pop();
			}
			stack.push(i);
		}

		stack.clear();
		// 往左看
		for (int i = 0; i < n; i++) {
			int total = 1 + stack.size() + rightLook[i];
			while (!stack.isEmpty() && arr[i] >= arr[stack.peek()]) {
				stack.pop();
			}
			stack.push(i);
			System.out.print(total + " ");
		}
	}


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}

		solve(n, arr);
	}
}
