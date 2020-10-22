package thread.bank;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.IntStream;

public class Main {
	private static Bank bank = new Bank("bank", 10000);

	private static class Thread1 implements Runnable {

		@Override
		public void run() {
			IntStream.range(0, 10000).forEach(i -> {
				bank.deposit(5);
			});
		}
	}

	private static class Thread2 implements Runnable {

		@Override
		public void run() {
			IntStream.range(0, 15000).forEach(i -> {
				bank.withdraw(2);
			});
		}
	}


	public static void main(String[] args) {
		Thread1 r1 = new Thread1();
		ThreadFactory threadFactory = Executors.defaultThreadFactory();
		IntStream.range(0, 5).forEach(i -> {
			threadFactory.newThread(r1).start();
		});

		Thread2 r2 = new Thread2();
		IntStream.range(0, 6).forEach(i -> {
			threadFactory.newThread(r2).start();
		});


	}
}
