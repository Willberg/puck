package algorithm.huawei.ip;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
	private static void solve(List<String> inputList) {
		int[] ret = new int[7];
		for (String str : inputList) {
			List<Integer> ipList = Arrays.stream(str.split(".|~"))
					.filter(v -> {
						if (!v.matches("^[0-9]+$")) {
							ret[5]++;
							return false;
						} else {
							Integer val = Integer.valueOf(v);
							if (val < 0 || val > 255) {
								ret[5]++;
								return false;
							}
							return true;
						}
					})
					.map(v -> Integer.valueOf(v))
					.collect(Collectors.toList());

		}

		for (int i : ret) {
			System.out.print(i + " ");
		}
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		List<String> inputList = new ArrayList<>();
		String inputStr = input.nextLine();
		while (inputStr != null && !inputStr.equals("")) {
//			inputList.add(inputStr);
			System.out.println(inputStr);
			inputStr = input.nextLine();
			System.out.println(inputStr);
		}
		System.out.println(inputStr);
		solve(inputList);
	}
}
