package com.javabase.thread;

public class WaitNotifyExample1 {

	public static void main(String[] args) {
		WaitNotifyExample1 e1 = new WaitNotifyExample1();
		e1.testWait();

		e1.noWait();
	}

	private void noWait() {
		ThreadB b = new ThreadB();
		b.start();

		System.out.println("Total is: " + b.total);
	}

	private void testWait() {
		ThreadB b = new ThreadB();
		b.start();

		synchronized (b) {
			try {
				System.out.println("Waiting for b to complete...");
				b.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("Total is: " + b.total);
		}
	}
}

class ThreadB extends Thread {
	int total;

	@Override
	public void run() {
		synchronized (this) {
			for (int i = 0; i < 10; i++) {
				total += i;
			}
			notify();
		}
	}
}
