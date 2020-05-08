package algorithm.huawei.reverse_str;

import java.util.Scanner;

public class Main {
	private static void solve(String str) {
		for (int i = str.length() - 1; i >= 0; i--) {
			System.out.print(str.charAt(i));
		}

	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String inputStr = input.nextLine();
		solve(inputStr);
	}
}
