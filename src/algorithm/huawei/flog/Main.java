package algorithm.huawei.flog;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
	private static int calCnt(int l, int s, int t, int[] stones) {
		int cnt = 0;
		StringBuilder builder = new StringBuilder();
		for(int i =0, j=0;i<l;i++) {
			if(j<stones.length) {
				if(i == stones[j]) {
					builder.append("0");
					j++;
				} else {
					builder.append("1");
				}
			} else {
				builder.append("1");
			}
		}

		String binStr = builder.toString();



		System.out.println(binStr);
		return 0;
	}

	private static String toBin(int[] arr) {
		StringBuilder builder = new StringBuilder();
		return builder.toString();
	}

	private static int calAndCnt(String s1, String s2) {
		int cnt = 0;
		return cnt;
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int l = Integer.parseInt(input.nextLine());
		List<Integer> a = Arrays.stream(input.nextLine().split(" ")).map(v -> Integer.parseInt(v)).collect(Collectors.toList());
		int s = a.get(0);
		int t = a.get(1);
		int n = a.get(2);
		int[] stones = new int[n];
		for (int i = 0; i < n; i++) {
			stones[i] = input.nextInt();
		}
		int cnt = calCnt(l, s, t, stones);
		System.out.println(cnt);
	}
}
