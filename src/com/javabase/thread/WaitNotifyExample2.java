package com.javabase.thread;

import java.util.Vector;

public class WaitNotifyExample2 {
	public static void main(String[] args) throws Exception {
		Producer producer = new Producer();
		Consumer consumer = new Consumer(producer);
		// producer.setDaemon(true);
		// consumer.setDaemon(true);
		producer.start();
		consumer.start();

		Thread.sleep(5000);
	}
}

class Producer extends Thread {

	static final int MAXQUEUE = 5;
	private Vector<String> messages = new Vector();

	@Override
	public void run() {
		try {
			while (true) {
				putMessage();
				// Thread.sleep(2000);
			}
		} catch (InterruptedException e) {
		}
	}

	private synchronized void putMessage() throws InterruptedException {
		while (messages.size() == MAXQUEUE) {
			System.out.println("MAX size reached. Producer wait.");
			wait();
		}
		messages.addElement(new java.util.Date().toString());
		System.out.println("put message");
		notify();
		// Later, when the necessary event happens, the thread that is running
		// it calls notify() from a block synchronized on the same object.
	}

	// Called by Consumer
	public synchronized String getMessage() throws InterruptedException {
		notify();
		while (messages.size() == 0) {
			System.out.println("No messages. Consumer wait.");
			wait();
			// By executing wait() from a synchronized block, a thread
			// gives up its hold on the lock and goes to sleep.
		}
		String message = (String) messages.firstElement();
		messages.removeElement(message);
		return message;
	}
}

class Consumer extends Thread {

	Producer producer;

	Consumer(Producer p) {
		producer = p;
	}

	@Override
	public void run() {
		try {
			while (true) {
				String message = producer.getMessage();
				System.out.println("Got message: " + message);
				// sleep(200);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}