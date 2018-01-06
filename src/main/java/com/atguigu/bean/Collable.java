package com.atguigu.bean;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

//资源类
class MyTread implements Callable<Integer>{

	@Override
	public Integer call() throws Exception {
		System.out.println("***come in Callable");
		Thread.sleep(4000);
		return 345;
	}
	
}
/**
 * 在主线程中需要执行比较耗时的操作时，但又不想堵塞主线程时，
 * 可以吧这些作业交给Future对象在后台完成，就不能重新开始或取消计算。
 * 就可以通过Future对象获得后台作业的计算结果或者执行状态
 * 
 * 一般FutureTask多用于耗时的计算，主线程可以在完成自己的任务后，再去获取结果
 * 
 * 仅在计算完成时才能检索结果：如果计算尚未完成，则堵塞get方法。一旦计算完成，就不能再重新开始或者取消计算
 * get方法而获取结果只有在计算完成时获取，否则会一直堵塞到任务转入完成状态，然后会返回结果或者抛出异常
 * 
 * 只计算一次
 * get方法放到最后
 * @author 季跃旺
 *
 */

public class Collable {

	public static void main(String[] args) throws Exception, Exception {
 FutureTask<Integer> ft = new FutureTask<Integer>(new MyTread());
 new Thread(ft,"A").start();
System.out.println("*****main task");
int result = (int) ft.get();
System.out.println("****finally result :"+result);
	}

}
