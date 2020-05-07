package algorithm.tx.first;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class First {
	public static String solve2(String inputStr) {
		StringBuilder retBuilder = new StringBuilder();
		List<String> inputList = solve(inputStr);
		for (String inputStr1 : inputList) {
			StringBuilder subBuilder = new StringBuilder();
			int lastCharIdx = inputStr1.length();
			for (int i = 0; i < lastCharIdx; i++) {
				if (inputStr1.charAt(i) == '[') {
					int repeatNumToken = findFirstStrIndex(inputStr1.substring(i + 1), '|') + i + 1;
					int repeatNum = Integer.valueOf(inputStr1.substring(i + 1, repeatNumToken));
					int sublastCharIdx = findLastStrIndex(inputStr1.substring(i, lastCharIdx), ']');
					String needRepeatStr = inputStr1.substring(repeatNumToken + 1, sublastCharIdx + i);
					while (needRepeatStr.contains("[")) {
						needRepeatStr = solve2(needRepeatStr);
					}
					for (int j = 0; j < repeatNum; j++) {
						subBuilder.append(needRepeatStr);
					}
					i = sublastCharIdx + i;
				} else {
					subBuilder.append(inputStr1.charAt(i));
				}
			}
			retBuilder.append(subBuilder);
		}


		return retBuilder.toString();
	}

	public static List<String> solve(String inputStr) {
		List<String> strList = new ArrayList<>();
		int flag = 0;
		int flag1 = 0, flag2 = 0;
		int subStringhead = 0;
		for (int i = 0; i < inputStr.length(); i++) {
			if (inputStr.charAt(i) == '[') {
				flag += 1;
				flag2 += 1;
			} else if (inputStr.charAt(i) == ']') {
				flag -= 1;
			} else {
				flag1 += 1;
				flag2 += 1;
			}

			if (flag == 0 && flag1 != flag2) {
				strList.add(inputStr.substring(subStringhead, i + 1));
				subStringhead = i+1;
				flag1 = flag2 = 0;
			}
		}
		if(subStringhead != inputStr.length()) {
			strList.add(inputStr.substring(subStringhead));
		}
		return strList;
	}


	public static int findFirstStrIndex(String inputStr, char needFindChar) {
		int idx = 0;
		for (int i = 0; i < inputStr.length(); i++) {
			if (needFindChar == inputStr.charAt(i)) {
				idx = i;
				break;
			}
		}
		return idx;
	}

	public static int findLastStrIndex(String inputStr, char needFindChar) {
		int idx = 0;
		for (int i = inputStr.length() - 1; i >= 0; i--) {
			if (needFindChar == inputStr.charAt(i)) {
				idx = i;
				break;
			}
		}
		return idx;
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String s = "";
		while (input.hasNextLine()) {
			s = input.nextLine();
			System.out.println(solve2(s));
		}
	}
}
