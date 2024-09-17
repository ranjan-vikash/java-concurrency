package com.demo.threadsync;

/**
 * 
 * 
 * 
 * Q: Write a code to print 1 to 10 using 2 threads. 
 * A: In the below code : 
 * 		Any one of the threads enters the synchronized block and acquires a lock on Printer object. 
 * 		While having the lock, it prints the value, signals all other thread to wake up, 
 * 		goes into waiting state and releases the lock. 
 * 		Since there are 2 threads only, the above keeps on happening alternatively.
 *      In finally block, signal any waiting thread to wake up.
 * 
 *
 */

public class ThreadSync {

	public static void main(String[] args) {
		Printer printer = new Printer(10, "odd-thread", "even-thread");

		Thread odd = new Thread(printer, "odd-thread");
		odd.start();

		Thread even = new Thread(printer, "even-thread");
		even.start();

	}

}

class Printer implements Runnable {

	private final int range;
	private final String oddThread;
	private final String evenThread;

	public Printer(int range, String oddThread, String evenThread) {
		super();
		this.range = range;
		this.oddThread = oddThread;
		this.evenThread = evenThread;

	}

	@Override
	public void run() {
		for (int i = 1; i <= this.range; i++) {
			synchronized (this) {
				try {
					if (i % 2 == 1 && this.oddThread.equals(Thread.currentThread().getName())) {
						System.out.println(this.oddThread + ":" + i);
						this.notifyAll();
						this.wait();
					}
					if (i % 2 == 0 && this.evenThread.equals(Thread.currentThread().getName())) {
						System.out.println(this.evenThread + ":" + i);
						this.notifyAll();
						this.wait();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
					Thread.currentThread().interrupt();
				}finally {
					this.notifyAll();
				}
			}
		}

	}

}
