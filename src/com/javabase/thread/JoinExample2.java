package com.javabase.thread;

public class JoinExample2 {
	public static void main(String args[]) throws Exception {
		ThreadTest t3 = new ThreadTest("c", null);
		ThreadTest t2 = new ThreadTest("b", t3);
		ThreadTest t1 = new ThreadTest("a", t2);
		t1.start();
		t2.start();
		t3.start();
	}
}

class ThreadTest extends Thread {

	private String name = null;
	private Thread pThread = null;

	public ThreadTest(String name, Thread preceedingThread) {
		this.name = name;
		this.pThread = preceedingThread;
	}

	public void run() {
		try {
			if (pThread != null) {
				pThread.join();
			}
			for (int i = 0; i < 2; i++) {
				System.out.println("Thread running: " + name);
				Thread.sleep(500);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
