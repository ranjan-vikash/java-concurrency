package com.demo.deadlock;

/**
 * Q: What is a deadlock? 
 * A: A deadlock is a situation where at least 2 threads
 *    T1 & T2 hold locks on 2 or more resources say Resource1 & Resource1
 *    simultaneously, causing themselves to be blocked forever.
 *    
 * 
 * 
 * Q: Write a code to simulate deadlock in java. 
 * A: In the below code :
 *    T1 locks Resource1 and waits for Resource2, which is locked by T2.
 *    T2 locks Resource2 and waits for Resource1, which is locked by T1.
 *    
 *    Since both threads are waiting for each other, they end up in a 
 *    deadlock and cannot proceed further, causing the program to hang
 *    
 *    
 *    
 * Q: How can we detect deadlock in a java program?
 * A: Thread Dump : A thread dump shows the current state of all threads 
 *    in a running java process. Most JDKs/JVMs provide tools like jstack to 
 *    generate thread dumps.
 *    
 *    Command: jstack <pid>
 *    This command gives you info about the threads involved in the given pid.
 *    
 *    To find pid(process id) of your java programs you can run the below utility,
 *    that comes with most JDKs
 *    
 *    Command: jps
 *    This command gives you a list of pids and process/program name.
 *    
 *    Note: Check the Deadlock.txt file for the thread dump for this program.
 *    
 *
 */

public class Deadlock {
	
	private Object resource1 = "Resource1";
	private Object resource2 = "Resource2";

	public static void main(String[] args) {
		
		Deadlock dl = new Deadlock();
		
		Runnable r1 = ()-> dl.method1();
		Runnable r2 = ()-> dl.method2();
		
		Thread t1 = new Thread(r1, "T1");
		Thread t2 = new Thread(r2, "T2");
		
		t1.start();
		t2.start();
		

	}
	
	private void method1() {
		synchronized (this.resource1) {
			System.out.println(Thread.currentThread().getName() + " acquired lock on " + resource1);
			synchronized (resource2) {
				System.out.println(Thread.currentThread().getName() + " acquired lock on " + resource2);
			}

		}
	}
	
	
	private void method2() {
		synchronized (this.resource2) {
			System.out.println(Thread.currentThread().getName() + " acquired lock on " + resource2);
			synchronized (resource1) {
				System.out.println(Thread.currentThread().getName() + " acquired lock on " + resource1);
			}

		}
	}

}
