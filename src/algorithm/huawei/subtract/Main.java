package algorithm.huawei.subtract;

import java.util.Scanner;

public class Main {
	private static boolean canRent(int bookPrice, int days, int due) {
		return calDue(bookPrice, days, due) > 0;
	}

	private static int calDue(int bookPrice, int days, int due) {
		// 待扣金额
		int subDue = 0;
		if (due >= bookPrice) {
			if (days > 15) {
				if (bookPrice >= 100) {
					subDue += (days - 15) * 3 + 15 * 5;
				} else if (bookPrice >= 50) {
					subDue += (days - 15) * 2 + 15 * 3;
				} else if (bookPrice >= 0) {
					subDue += days;
				}
			} else {
				if (bookPrice >= 100) {
					subDue += days * 5;
				} else if (bookPrice >= 50) {
					subDue += days * 3;
				} else if (bookPrice >= 0) {
					subDue += days;
				}
			}
			if (subDue > bookPrice) {
				subDue = bookPrice;
			}
			due -= subDue;
		}
		return due;
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		// 余额
		int due = 300;
		int preDue = 300;
		while (input.hasNextLine()) {
			String inputStr = input.nextLine();
			String[] records = inputStr.split(",");
			int bookPrice = Integer.parseInt(records[0]);
			int rentDays = Integer.parseInt(records[1]);
			int trueRentDays = Integer.parseInt(records[2]);
			preDue = preDue - bookPrice;
			if(canRent(bookPrice, rentDays, preDue)) {
				due = calDue(bookPrice, trueRentDays, due);
				if(trueRentDays > rentDays) {
					due = due - (trueRentDays - rentDays);
				}
			}
		}
		System.out.println(due);
	}
}
