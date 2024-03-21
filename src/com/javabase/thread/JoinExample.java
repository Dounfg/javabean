package com.javabase.thread;

public class JoinExample {
	public static void main(String[] args) {
		String threadName = Thread.currentThread().getName();
		System.out.println(threadName + " start.");
		CustomThread1 t1 = new CustomThread1();
		CustomThread2 t2 = new CustomThread2(t1);
		try {
			t1.start();
			Thread.sleep(1000);
			t2.start();
			t2.join();
		} catch (Exception e) {
			System.out.println("Exception from main");
		}
		System.out.println(threadName + " end!");
	}
}

class CustomThread1 extends Thread {
	public CustomThread1() {
		super("[CustomThread1] Thread");
	};

	public void run() {
		String threadName = Thread.currentThread().getName();
		System.out.println(threadName + " start.");
		try {
			for (int i = 0; i < 5; i++) {
				System.out.println(threadName + " loop at " + i);
				Thread.sleep(1000);
			}
			System.out.println(threadName + " end.");
		} catch (Exception e) {
			System.out.println("Exception from " + threadName + ".run");
		}
	}
}

class CustomThread2 extends Thread {
	CustomThread1 t1;

	public CustomThread2(CustomThread1 t1) {
		super("[CustomThread2] Thread");
		this.t1 = t1;
	}

	public void run() {
		String threadName = getName();
		System.out.println(threadName + " start.");
		try {
			for (int i = 0; i < 10; i++) {
				System.out.println(threadName + " is running...");
			}
			System.out.println(threadName + " end.");
		} catch (Exception e) {
			System.out.println("Exception from " + threadName + ".run");
		}
	}
}