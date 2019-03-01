package com.example.demo.jdk8;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.demo.entity.User;

public class Java8 {
	public static void main(String[] args) {
		// Java 5 开始就引入了泛型方法
		List<String> testList = Collections.emptyList();
		// 在 Java 7 中，可以在表达式中省略类型参数，只要这些参数能通过上下文确定
		Map<User, List<String>> userChannels = new HashMap<>();
		// 1.Lambda表达式
		Arrays.asList("a", "b", "d").forEach(e -> System.out.println(e));
		int totaAge = 0;
		Arrays.asList(new Persion("t1", 11), new Persion("t2", 12), new Persion("t3", 13)).forEach(e -> {
			System.out.println(e.getName());
			e.getAge();
		});
		System.out.println(Arrays.asList(new Persion("t1", 11), new Persion("t2", 12), new Persion("t3", 13)).stream()
				.findFirst());

		Persion[] persons = (Persion[]) Arrays
				.asList(new Persion("t3", 13), new Persion("t2", 12), new Persion("t1", 11)).toArray();
		Arrays.sort(persons, (a, b) -> {
			return a.getAge().compareTo(b.getAge());
		});
		Arrays.sort(persons, Persion::compareByAge);
		for (int i = 0; i < persons.length; i++)
			System.out.println(persons[i].getName());

		// 2.Option测试
		Optional<String> fullName = Optional.ofNullable(null);
		System.out.println("Full Name is set? " + fullName.isPresent());
		System.out.println("Full Name: " + fullName.orElseGet(() -> "[none]"));
		System.out.println(fullName.map(s -> "Hey " + s + "!").orElse("Hey Stranger!"));

		Persion p = new Persion("xxxx", 17);
		Optional<Persion> op = Optional.ofNullable(p);
		System.out.println("Full Name is set? " + op.isPresent());
		System.out.println("Full Name: " + op.orElseGet(null));
		System.out.println(op.map(Persion::getName).map(String::toUpperCase).orElse("Hey Stranger!"));
	}
}

class Persion {
	private String name;
	private Integer age;

	public Persion(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Persion [name=" + name + ", age=" + age + "]";
	}

	public static int compareByAge(Persion a, Persion b) {
		return b.getAge().compareTo(a.getAge());
	}

}