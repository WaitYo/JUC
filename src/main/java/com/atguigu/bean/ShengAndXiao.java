package com.atguigu.bean;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//生产者与消费者
//实现一个线程对该变量加1，一个线程对该变量减1，实现交替，来5轮，变量初始值为零。
/*
 * 1、线程操作  资源类
 * 2、高内聚低耦合
 * 3、 3step
 *    3.1 判断
 *    3.2 干活
 *    3.3 唤醒
 */
/*class ShareData{
	private int number = 0;
	public synchronized void increment() throws Exception {
		//1、判断
		//if(number!=0) {
		while(number!=0) {
			this.wait();
		}
		//2、干活
		++number;
		System.out.println(Thread.currentThread().getName()+"\t"+number);
		//3、唤醒
		this.notifyAll();
	}
	public synchronized void decrement() throws Exception {
		//1、判断
		//if(number==0) {
		while(number==0) {
			this.wait();
		}
		//2、干活
		--number;
		System.out.println(Thread.currentThread().getName()+"\t"+number);
		//3、唤醒
		this.notifyAll();
	}
}*/
//lock
class ShareData{
	private int number = 0;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	public void increment() throws Exception {
		lock.lock();
		//1、判断
		while(number!=0) {
			condition.await();
		}
		//2、干活
		++number;
		System.out.println(Thread.currentThread().getName()+"\t"+number);
		
		//3、唤醒
		condition.signal();
		
		lock.unlock();
	}
	public void decrement() throws Exception {
		lock.lock();
		//1、判断
		while(number==0) {
			condition.await();
		}
		//2、干活
		--number;
		System.out.println(Thread.currentThread().getName()+"\t"+number);
		//3、唤醒
		condition.signal();
		lock.unlock();
	}
}
public class ShengAndXiao {

	public static void main(String[] args) {
		ShareData shareData = new ShareData();
		new Thread(()->{
			for(int i=1;i<=10;i++) {
				try {
					Thread.sleep(200);
					shareData.increment();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		},"A").start();
		new Thread(()->{
			for(int i=1;i<=10;i++) {
				try {
					Thread.sleep(200);
					shareData.decrement();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		},"B").start();
		new Thread(()->{
			for(int i=1;i<=10;i++) {
				try {
					Thread.sleep(200);
					shareData.increment();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		},"C").start();
		new Thread(()->{
			for(int i=1;i<=10;i++) {
				try {
					Thread.sleep(200);
					shareData.decrement();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		},"D").start();

	}

}
