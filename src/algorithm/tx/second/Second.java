package algorithm.tx.second;

import java.util.*;

public class Second {
	private static String solve(int n, String wi) {
		List<Integer> wiList = new ArrayList<>(n);
		Arrays.stream(wi.split(" ")).forEach(w -> wiList.add(Integer.valueOf(w)));
		StringBuilder answer = new StringBuilder();
		int preIdx = 0, lastIdx = 0;
		for (int i = 0; i < n; i++) {
			int canSeeCount = 1;
			if (i > 0) {
				preIdx = i - 1;
				int preFlag = wiList.get(preIdx);
				for (int j = preIdx; j >= 0; j--) {
					if (j == preIdx) {
						canSeeCount += 1;
					} else {
						if (wiList.get(j) > preFlag) {
							canSeeCount += 1;
							preFlag = wiList.get(j);
						}
					}
				}
			}

			lastIdx = i + 1;
			if (lastIdx < wiList.size()) {
				int lastFlag = wiList.get(lastIdx);
				for (int j = lastIdx; j < wiList.size(); j++) {
					if (j == lastIdx) {
						canSeeCount += 1;
					} else {
						if (wiList.get(j) > lastFlag) {
							canSeeCount += 1;
							lastFlag = wiList.get(j);
						}
					}
				}
			}

			answer.append(canSeeCount).append(" ");
		}

		String answerStr = answer.toString();
		return answerStr.substring(0, answerStr.length() - 1);
	}

	private static String solve2(int n, String wi) {
		List<Integer> wiList = new ArrayList<>(n);
		Arrays.stream(wi.split(" ")).forEach(w -> wiList.add(Integer.valueOf(w)));
		StringBuilder answer = new StringBuilder();
		Stack<Integer> stack1 = new Stack<>();
		Stack<Integer> stack2 = new Stack<>();
		for (int i = 0; i < n; i++) {
			for (int j = i - 1; j >= 0; j--) {
				if (stack1.empty() || stack1.peek() < wiList.get(j)) {
					stack1.push(wiList.get(j));
				}
			}

			for (int j = i + 1; j < n; j++) {
				if (stack2.empty() || stack2.peek() < wiList.get(j)) {
					stack2.push(wiList.get(j));
				}
			}

			answer.append(String.valueOf(1 + stack1.size() + stack2.size())).append(" ");
			stack1.removeAllElements();
			stack2.removeAllElements();
		}

		String answerStr = answer.toString();
		return answerStr.substring(0, answerStr.length() - 1);
	}


	public static void main(String[] args) {
		int n = 0;
		String wi = "";
		Scanner input = new Scanner(System.in);
		while (input.hasNextLine()) {
			n = Integer.valueOf(input.nextLine());
			wi = input.nextLine();
			System.out.println(solve2(n, wi));
		}
	}
}
