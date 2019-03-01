package com.example.demo.jdk7;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * JDK7里包含的新特性
 * 
 * 新增了对象Object的一些常规检查操作
 * 
 * @author lijin
 *
 */
public class Java7 {
	public static void main(String[] args) {
		// 1.switch中可以使用字串了
		// 2.泛型实例化类型自动推断
		List<String> lst2 = new ArrayList<>();
		// 3.自定义自动关闭类AutoCloseable(只要实现该接口，在该类对象销毁时自动调用close方法，你可以在close方法关闭你想关闭的资源)
		// 4.JAVA7新增加了Objects类
		System.out.println(Objects.isNull(null));// 判断为空
		System.out.println(Objects.nonNull(BigDecimal.ZERO));// 判断不能为空
		System.out.println(Objects.toString(BigDecimal.ZERO, "xxxx"));// toString，提供为null时的默认值
		System.out.println(Objects.toString(null, "xxxx"));// toString，会有NPE
		Objects.requireNonNull(null, "xx不能为空");// 判断不能为空，否则抛出异常
	}
}
