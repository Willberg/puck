package thread.bank;

public class Bank {
	private int money;
	private String name;

	public Bank(String name, int money) {
		this.money = money;
		this.name = name;
	}

	public synchronized void deposit(int m) {
		money += m;
		System.out.println("plus, thread name:" + Thread.currentThread().getName() + ", value: " + money);
	}

	public synchronized boolean withdraw(int m) {
		if (money >= m) {
			money -= m;
			System.out.println("sub, thread name:" + Thread.currentThread().getName() + ", value: " + money);
			return true;
		} else {
			return false;
		}
	}

	public String getName() {
		return name;
	}
}
