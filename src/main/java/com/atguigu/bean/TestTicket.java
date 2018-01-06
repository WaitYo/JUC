package com.atguigu.bean;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//三个售票员售10张票

class Ticket{
	private int ticket = 100;
	private Lock lock = new ReentrantLock();
	public void sale() {
		lock.lock();
		try {
			if(ticket>0) {
				System.out.println(Thread.currentThread().getName()+"卖出第"+(ticket--)+"还剩"+ticket);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			lock.unlock();
		}
	}
}


public class TestTicket {

	public static void main(String[] args) {
		Ticket t = new Ticket();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i =1;i<=100;i++) {
					t.sale();
				}
				
			}
		}, "A").start();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i =1;i<=100;i++) {
					t.sale();
				}
				
			}
		}, "B").start();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i =1;i<=100;i++) {
					t.sale();
				}
				
			}
		}, "C").start();

	}

}
