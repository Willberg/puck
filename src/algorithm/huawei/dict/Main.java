package algorithm.huawei.dict;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	private static void solve(int n, List<String> list, String word, int pre) {


		int cnt = 0;
		for (int i = pre - 1; i < list.size(); i++) {
			if (isBrother(list.get(i), word)) {
				cnt++;
			}
		}
		System.out.println(cnt);
	}

	private static boolean isBrother(String newWord, String old) {
		if (newWord.equals(old) || newWord.length() != old.length()) {
			return false;
		}
		return sortWord(newWord).equals(sortWord(old));
	}

	private static String sortWord(String old) {
		List<Character> charList = new ArrayList<>(old.length());
		for (int i = 0; i < old.length(); i++) {
			charList.add(old.charAt(i));
		}

		charList.sort((o1, o2) -> {
			if (o1 < o2) {
				return -1;
			} else {
				return 1;
			}
		});

		StringBuilder builder = new StringBuilder();
		for (Character c : charList) {
			builder.append(c);
		}
		return builder.toString();
	}


	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		while (input.hasNextLine()) {
			String line = input.nextLine();
			String[] strArr = line.split(" ");
			int n = Integer.valueOf(strArr[0]);
			List<String> list = new ArrayList<>(n);
			for (int i = 1; i < n + 1; i++) {
				list.add(strArr[i]);
			}
			String word = strArr[n + 1];
			int pre = Integer.valueOf(strArr[strArr.length - 1]);
			solve(n, list, word, pre);
		}


	}
}




