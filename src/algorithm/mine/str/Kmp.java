package algorithm.mine.str;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author John
 * @Description 给定一个主串（以 S 代替）和模式串（以 P 代替），要求找出 P 在 S 中出现的位置，此即串的模式匹配问题。
 * @Date 2020/9/23 8:14 PM
 **/
public class Kmp {
	private static int[] calNext(String p) {
		int[] next = new int[p.length()];
		int k = -1;
		int i = 0;
		next[0] = -1;
		while (i < p.length() - 1) {
			if (k == -1 || p.charAt(i) == p.charAt(k)) {
				i++;
				k++;
				next[i] = k;
			} else {
				k = next[i];
				i++;
			}
		}
		return next;
	}

	/**
	 * KMP时间复杂度：O(m+n)
	 * @param s
	 * @param p
	 * @return list
	 */
	private static List<Integer> kmp(String s, String p) {
		List<Integer> list = new ArrayList<>();
		int[] next = calNext(p);
		int i = 0;
		int j = 0;
		while (i < s.length() && j < p.length()) {
			if (j == -1 || s.charAt(i) == p.charAt(j)) {
				i++;
				j++;
			} else {
				j = next[j];
			}

			if (j == p.length()) {
				// 匹配成功
				list.add(i - j);
				// 重置
				j = -1;
			}
		}
		return list;
	}


	public static void main(String[] args) {
		String s = "BBC ABCDAB ABCDABCDABDEABCDABD";
		String p = "ABCDABD";
//		for(int k: calNext(s, p)) {
//			System.out.print(k + "\t");
//		}
		kmp(s, p).forEach(v -> System.out.print(v + "\t"));
	}
}
