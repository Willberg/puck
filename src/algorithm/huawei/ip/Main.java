package algorithm.huawei.ip;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int isA = 0;
		int isB = 0;
		int isC = 0;
		int isD = 0;
		int isE = 0;
		int isError = 0;
		int isPrivate = 0;
		Scanner input = new Scanner(System.in);
		while (input.hasNextLine()) {
			String inputStr = input.nextLine();
			String[] ipAndMask = inputStr.split("~");
			boolean isMask = false;
			String[] masks = ipAndMask[1].split("\\.");
			String maskCode = "";
			for (int i = 0; i < masks.length; i++) {
				String maskStr = Integer.toBinaryString(Integer.parseInt(masks[i]));
				int length = maskStr.length();
				for (int j = 0; j < 8 - length; j++) {
					maskStr = "0".concat(maskStr);
				}
				maskCode = maskCode.concat(maskStr);
			}
			int oneIdx = maskCode.lastIndexOf("1");
			int zeroIdx = maskCode.indexOf("0");
			isMask = oneIdx < zeroIdx;
			if (isMask) {
				int[] ips = new int[4];
				String[] ipStrArr = ipAndMask[0].split("\\.");
				if (ipStrArr.length == 4) {
					for (int i = 0; i < ipStrArr.length; i++) {
						if (ipStrArr[i].equals("")) {
							ips[i] = -1;
						} else {
							ips[i] = Integer.parseInt(ipStrArr[i]);
						}
					}

					if (ips[0] >= 0 && ips[0] <= 255 && ips[1] >= 0 && ips[1] <= 255 && ips[2] >= 0 && ips[2] <= 255 && ips[3] >= 0 && ips[3] <= 255) {
						if (ips[0] >= 1 && ips[0] <= 126) {
							isA++;
							if (ips[0] == 10) {
								isPrivate++;
							}
						} else if (ips[0] >= 128 && ips[0] <= 191) {
							isB++;
							if (ips[0] == 172 && ips[1] >= 16 && ips[1] <= 31) {
								isPrivate++;
							}
						} else if (ips[0] >= 192 && ips[0] <= 223) {
							isC++;
							if (ips[0] == 192 && ips[1] == 168) {
								isPrivate++;
							}
						} else if (ips[0] >= 224 && ips[0] <= 239) {
							isD++;
						} else if (ips[0] >= 240 && ips[0] <= 255) {
							isE++;
						}
					} else {
						isError++;
					}
				} else {
					isError++;
				}
			} else {
				isError++;
			}
		}
		System.out.println(String.format("%d %d %d %d %d %d %d", isA, isB, isC, isD, isE, isError, isPrivate));
		input.close();
	}
}
