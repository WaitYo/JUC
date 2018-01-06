package com.atguigu.bean;

import java.util.concurrent.TimeUnit;

class Phone
{
	public static synchronized void getIOS()throws Exception
	{
		TimeUnit.SECONDS.sleep(4);
		System.out.println("-----getIOS");
	}
	
	public synchronized void getAndroid()throws Exception
	{
		System.out.println("-----getAndroid");
	}
	
	public void getHello()throws Exception
	{
		System.out.println("-----getHello");
	}
}
public class Ios {
	public static void main(String[] args) throws InterruptedException
	{
	//phone.class
			Phone phone = new Phone();
			//Phone phone2 = new Phone();
			
			new Thread(() -> {
				try 
				{
					phone.getIOS();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}, "A").start();
			
			Thread.sleep(100);
			
			new Thread(() -> {
				try 
				{
					//phone.getAndroid();
					//phone.getHello();
					phone.getAndroid();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}, "B").start();
			
			
			
			
		}
	}


