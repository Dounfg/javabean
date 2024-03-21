package com.javabase.thread;

public class InterruptExample extends Thread {
	volatile boolean stop = false;

	public static void main(String args[]) throws Exception {
		InterruptExample thread = new InterruptExample();
		System.out.println("Starting thread...");
		thread.start();
		Thread.sleep(3000);
		System.out.println("Asking thread to stop...");
		// thread.stop = true;
		thread.interrupt();
		Thread.sleep(3000);
		System.out.println("Stopping application...");
		// System.exit( 0 );
	}

	public void run() {
		while (!stop) {
			try {
				System.out.println("Thread running...");
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				System.out.println("***InterruptedException caught: stop flag value: " + stop);
				System.out.println("Thread interrupted...");
			}
		}
		System.out.println("stop flag value: " + stop);
		System.out.println("Thread exiting under request...");
	}
}
