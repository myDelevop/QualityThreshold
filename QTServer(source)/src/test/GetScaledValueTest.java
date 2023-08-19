package test;

import static org.junit.Assert.*;
import org.junit.Test;
import data.ContinuousAttribute;

/**
 * 
 * Classe che testa il getScaledValue con valori differenti.
 *
 */
public class GetScaledValueTest { // min max
	@Test
	public void test1() {

		ContinuousAttribute first = new ContinuousAttribute("First", 1, 2.1,
				5.3);

		double result = first.getScaledValue(2.1); // min
		assertEquals(result, 0.0, 0.0);
		result = first.getScaledValue(5.3); // max
		assertEquals(result, 1.0, 0.0);
		result = first.getScaledValue(5.0);
		assertEquals(result, 0.90625, 0.0);

		ContinuousAttribute second = new ContinuousAttribute("Second", 1, 0.1,
				35.13);

		result = second.getScaledValue(0.1); // min
		assertEquals(result, 0.0, 0.0);
		result = second.getScaledValue(35.13); // max
		assertEquals(result, 1.0, 0.0);
		result = second.getScaledValue(15.7);
		assertEquals(result, 0.4453, 0.0001);
		// non ci sono errori macchina di calcolo quindi il delta posso
		// prenderlo = 0

		ContinuousAttribute last = new ContinuousAttribute("Last", 2, 132.2,
				1135.13);

		result = last.getScaledValue(132.2); // min
		assertEquals(result, 0.0, 0.0);
		// non ci sono errori macchina di calcolo quindi il delta posso
		// prenderlo = 0
		result = last.getScaledValue(1135.13); // max
		assertEquals(result, 1.0, 0.0);
		// non ci sono errori macchina di calcolo quindi il delta posso
		// prenderlo = 0
		result = last.getScaledValue(762.64);
		assertEquals(result, 0.62867, 0.0001);
		// non ci sono errori macchina di calcolo quindi il delta posso
		// prenderlo = 0
	}

}
