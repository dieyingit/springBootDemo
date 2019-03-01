package com.example.demo.jdk8;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;

/**
 * Java8里Time的新用法
 * 
 * @author lijin
 *
 */
public class TimeTest {
	public static void main(String[] args) {
		// 计算日期时间差异：1.Period
		LocalDate ld = LocalDate.now();
		System.out.println("Today: " + ld);
		LocalDate birthDate = LocalDate.of(1993, Month.MAY, 19);
		System.out.println("End day: " + birthDate);
		Period p = Period.between(birthDate, ld);
		System.out.printf("年龄 : %d 年 %d 月 %d 日\n", p.getYears(), p.getMonths(), p.getDays());

		// 计算日期时间差异：2.Duration
		// Duration 是在 Java 8中加入的，主要是用来计算日期，差值之类的。Duration
		// 被声明final（immutable），并且线程安全。
		Instant inst1 = Instant.now();
		System.out.println("Inst1 : " + inst1);
		Instant inst2 = inst1.plus(Duration.ofSeconds(10));
		System.out.println("Inst2 : " + inst2);
		System.out.println("Difference in milliseconds : " + Duration.between(inst1, inst2).toMillis());
		System.out.println("Difference in seconds : " + Duration.between(inst1, inst2).getSeconds());

		// 计算日期时间差异：3.ChronoUnit
		// ChronoUnit类可用于在单个时间单位内测量一段时间，例如天数或秒。
		LocalDate startDate = LocalDate.of(1993, Month.OCTOBER, 19);
		System.out.println("开始时间  : " + startDate);
		LocalDate endDate = LocalDate.of(2017, Month.JUNE, 16);
		System.out.println("结束时间 : " + endDate);
		long daysDiff = ChronoUnit.DAYS.between(startDate, endDate);
		System.out.println("两天之间的差在天数   : " + daysDiff);
	}
}
