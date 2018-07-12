package com.example.demo.jdk8;

import java.util.Arrays;
import java.util.Optional;

public class Java8 {
	public static void main(String[] args) {
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