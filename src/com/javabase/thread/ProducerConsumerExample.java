package com.javabase.thread;

import java.util.PriorityQueue;

public class ProducerConsumerExample {

	public final static int queueSize = 10;

	public static void main(String[] args) {
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>(queueSize);
		Producer1 producer = new Producer1(queue);
		Consumer1 consumer = new Consumer1(queue);

		producer.start();
		consumer.start();
	}

}

class Consumer1 extends Thread {

	private PriorityQueue<Integer> queue = null;

	public Consumer1(PriorityQueue<Integer> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		consume();
	}

	private void consume() {
		while (true) {
			synchronized (queue) {
				while (queue.size() == 0) {
					try {
						System.out.println("队列空，等待数据");
						queue.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
						queue.notify();
					}
				}
				queue.poll(); // 每次移走队首元素
				queue.notify();
				System.out.println("从队列取走一个元素，队列剩余" + queue.size() + "个元素");
			}
		}
	}
}

class Producer1 extends Thread {

	private PriorityQueue<Integer> queue = null;

	public Producer1(PriorityQueue<Integer> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		produce();
	}

	private void produce() {
		while (true) {
			synchronized (queue) {
				while (queue.size() == ProducerConsumerExample.queueSize) {
					try {
						System.out.println("队列满，等待有空余空间");
						queue.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
						queue.notify();
					}
				}
				queue.offer(1); // 每次插入一个元素
				queue.notify();
				System.out.println("向队列取中插入一个元素，队列剩余空间：" + (ProducerConsumerExample.queueSize - queue.size()));
			}
		}
	}
}