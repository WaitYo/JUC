package com.atguigu.bean;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * \ @author zhouyang 线程池，近似的理解java里面第4种获得多线程的方式
 * 
 * Collections Arrays Executors
 * 
 * @author 季跃旺
 *
 */
public class ThreadPool {

	public static void main(String[] args) {

		// test();
		test1();
	}

	/**
	 * 
	 */
	public static void test1() {
		//定时事件指定任务
		ScheduledExecutorService service = Executors.newScheduledThreadPool(5);
		ScheduledFuture<Integer> result = null;
		try {
			for(int i = 1;i<=15;i++) {
				result=service.schedule(()->
				{
					System.out.print(Thread.currentThread().getName());
					return new Random().nextInt(30);
				}, 2, TimeUnit.SECONDS);
				System.out.println("\t"+result.get());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			service.shutdown();
		}
	}

	/**
	 * 
	 */
	public static void test() {
		// ExecutorService service = Executors.newFixedThreadPool(5);//一池5线程
		// ExecutorService service = Executors.newSingleThreadExecutor();//一个线程
		ExecutorService service = Executors.newCachedThreadPool();
		Future<Integer> result = null;
		try {
			for (int i = 1; i <= 100; i++) {
				result = service.submit(() -> {
					System.out.print(Thread.currentThread().getName());
					return new Random().nextInt(30);
				});
				System.out.println("\t" + result.get());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			service.shutdown();
		}
	}
}
