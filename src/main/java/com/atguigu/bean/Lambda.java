package com.atguigu.bean;
/**
 * 如何使用Lambda表达式写？（形参列表）->{方法体的落地实现}
 * 2、有且只有一个方法，新的注解标识要求@FunctionInterface
 * 3、default方法声明使用
 * 4、static方法声明使用
 * @author 季跃旺
 *
 */
public class Lambda {

	public static void main(String[] args) {
      Foo foo = (x,y)->{return x+y;};
      int result = foo.add(2, 5);
      System.out.println(result);
     result = foo.div(10, 5);
     System.out.println(result);
    int i = Foo.div3(15, 3);
    System.out.println(i);
}
}
@FunctionalInterface
interface Foo{
	public int add(int x,int y);
	default int div(int x,int y) {
		return x/y;
	}
	public static int div3(int x,int y){
		return x / y;
	}
}