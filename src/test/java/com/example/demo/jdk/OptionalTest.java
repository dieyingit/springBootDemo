package com.example.demo.jdk;

import java.util.Optional;

public class OptionalTest {
	public static void main(String[] args) {
		Optional<Object> o = Optional.empty();

		Double d = Double.valueOf(212.2);
		Optional<Double> doubleOp = Optional.ofNullable(d);
		System.out.println(doubleOp.orElse(0.0));
		
		Optional<Double> doubleOpEmp = Optional.empty();
		System.out.println(doubleOpEmp.orElse(0.0));

//		System.out.println(doubleOp.orElseThrow(new NumberFormatException()));

	}
}
