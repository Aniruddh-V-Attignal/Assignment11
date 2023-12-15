package com.example.demo;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TestRational {

	@Test
	public void testInitialization() {
		Rational r1 = new Rational(3);
		assertEquals("3/1", r1.toString());

		Rational r2 = new Rational(4, 6);
		assertEquals("2/3", r2.toString());
	}

	@Test
	public void testAddition() {
		Rational r1 = new Rational(1, 2);
		Rational r2 = new Rational(1, 4);
		Rational sum = r1.add(r2);
		assertEquals("3/4", sum.toString());
	}

	@Test
	public void testSubtraction() {
		Rational r1 = new Rational(5, 8);
		Rational r2 = new Rational(1, 8);
		Rational result = r1.add(r2.neg());
		assertEquals("1/2", result.toString());
	}

	@Test
	public void testMultiplication() {
		Rational r1 = new Rational(3, 4);
		Rational r2 = new Rational(2, 3);
		Rational result = r1.mul(r2);
		assertEquals("1/2", result.toString());
	}

	@Test
	public void testDivision() {
		Rational r1 = new Rational(3, 4);
		Rational r2 = new Rational(2, 3);
		Rational result = r1.mul(r2.inv());
		assertEquals("9/8", result.toString());
	}

	@Test
	public void testEquality() {
		Rational r1 = new Rational(3, 4);
		Rational r2 = new Rational(6, 8);
		assertEquals(r1, r2);
	}

	@Test
	public void testComparison() {
		Rational r1 = new Rational(3, 5);
		Rational r2 = new Rational(4, 7);
		assertTrue(r1.compareTo(r2) > 0);
	}

	@Test
	public void testNegation() {
		Rational r = new Rational(2, 3);
		Rational neg = r.neg();
		assertEquals("-2/3", neg.toString());
	}

	@Test
	public void testArithmeticExceptions() {
		assertThrows(ArithmeticException.class, () -> {
			Rational r = new Rational(1, 0);
		});

		assertThrows(ArithmeticException.class, () -> {
			Rational r = new Rational(Integer.MIN_VALUE, -1);
		});
	}

}
