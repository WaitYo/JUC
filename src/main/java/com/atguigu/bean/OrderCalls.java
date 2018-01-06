package com.atguigu.bean;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareResource {
	private int number = 1;
	private Lock lock = new ReentrantLock();
	private Condition c1 = lock.newCondition();
	private Condition c2 = lock.newCondition();
	private Condition c3 = lock.newCondition();

	public void print5(int totalLoop) {
		lock.lock();

		try {
			// 1、判断
			while (number != 1) {
				c1.await();
			}
			// 2、干活
			for (int i = 1; i <= 5; i++) {
				System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + totalLoop);
			}
			// 3、唤醒
			number = 2;
			c2.signal();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	/*public void print10(int totalLoop) {

		try {
			// 1、判断
			while (number != 2) {
				c2.await();
			}
			// 2、干活
			for (int i = 1; i <= 10; i++) {
				System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + totalLoop);
			}
			// 3、唤醒
			number = 3;
			c3.signal();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}*/
	public void print10(int totalLoop)
	{
		lock.lock();
		try 
		{
			//1 判断
			while(number != 2)
			{
				c2.await();
			}
			//2 干活
			for (int i = 1; i <=10; i++) 
			{
				System.out.println(Thread.currentThread().getName()+"\t"+i+"\t totalLoop"+totalLoop);
			}
			//3 唤醒
			number = 3;
			c3.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}	
	public void print15(int totalLoop)
	{
		lock.lock();
		try 
		{
			//1 判断
			while(number != 3)
			{
				c3.await();
			}
			//2 干活
			for (int i = 1; i <=15; i++) 
			{
				System.out.println(Thread.currentThread().getName()+"\t"+i+"\t totalLoop"+totalLoop);
			}
			//3 唤醒
			number = 1;
			c1.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	/*public void print15(int totalLoop) {

		try {
			// 1、判断
			while (number != 3) {
				c3.await();
			}
			// 2、干活
			for (int i = 1; i <= 15; i++) {
				System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + totalLoop);
			}
			// 3、唤醒
			number = 1;
			c1.signal();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}
*/
}

/*
 * 多线程之间按顺序调用，实现A->B->C 三个线程启动，要求如下：
 * 
 * AA打印5次，BB打印10次，CC打印15次 接着 AA打印5次，BB打印10次，CC打印15次 ......来10轮 1 1 2 1 3 1 4 1 5
 * 1
 * 
 *
 */
public class OrderCalls {

	public static void main(String[] args) {
		ShareResource shareResource = new ShareResource();
		new Thread(() -> {
			for (int i = 1; i <= 5; i++) {
				shareResource.print5(i);
			}

		}, "A").start();
		new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				shareResource.print10(i);
			}
		}, "B").start();
		new Thread(() -> {
			for (int i = 1; i <= 15; i++) {
				shareResource.print15(i);
			}
		}, "C").start();
	}

}
