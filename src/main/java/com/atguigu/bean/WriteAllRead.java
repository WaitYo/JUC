package com.atguigu.bean;

import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyQeueu{
	private Object obj=null;
	private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	public void write(Object obj) {
		rwl.writeLock().lock();
		try {
			this.obj=obj;
			System.out.println(Thread.currentThread().getName()+"\t"+obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		
		rwl.writeLock().unlock();
		}
	}
	public void read() {
		rwl.readLock().lock();
		try {
			
			System.out.println(Thread.currentThread().getName()+"\t"+obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		
		rwl.readLock().unlock();
		}
	}
}
/**
 * 读写锁
 *一个线程写，100个线程读取
 * @author 季跃旺
 *
 */
public class WriteAllRead {
public static void main(String[] args) {
	MyQeueu q = new MyQeueu();
	new Thread(()->{
		q.write("0725");
	}
	, "A").start();
	for(int i=1;i<=100;i++) {
		new Thread(()->{
			q.read();
		}
		, String.valueOf(i)).start();
	}
}
}
