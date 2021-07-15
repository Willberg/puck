package jdk;

public class Son extends Father {
	protected void print() {
		System.out.println("this is son");
	}

	public void onlySon() {
		System.out.println("OnlySon");
	}

	public static void main(String[] args) {
		Father f = new Son();
		f.print();
		f.test();
		f.f();
		((Son) f).onlySon();

		f = new Father();
		f.print();
		f.test();
		f.f();
	}
}
