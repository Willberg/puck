package test;

public class Test {
	private static int test1() {
		try {
			System.out.println(1);
			return 1 + 3;
		} finally {
			System.out.println(2);
		}
	}

	public static void main(String[] args) {
		System.out.println(test1());
	}
}
