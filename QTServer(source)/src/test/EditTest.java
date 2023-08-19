package test;

import static org.junit.Assert.*;

import java.util.TreeSet;

import org.junit.Test;

import data.*;
import Distance.Calcolo;

/**
 *
 * Classe che effettua dei test sul funzionamento della Edit
 *
 */
public class EditTest {

	@Test
	public void test() {

		DiscreteAttribute attributeD = utilities.ValuesInit();
		ContinuousAttribute attributeC = new ContinuousAttribute("attr", 0,
				2.1, 35.6);
		DiscreteItem o1 = new DiscreteItem(attributeD, "rain");
		DiscreteItem o2 = new DiscreteItem(attributeD, "sunny");

		double result = Calcolo.distanceEdit().Edit(o1, o2);
		assertEquals(result, 0.5, 0.0);
		o1 = new DiscreteItem(attributeD, "overcast");
		o2 = new DiscreteItem(attributeD, "rain");
		result = Calcolo.distanceEdit().Edit(o1, o2);
		assertEquals(result, 0.5, 0.0);

		o1 = new DiscreteItem(attributeD, "sunny");
		o2 = new DiscreteItem(attributeD, "sunny");
		result = Calcolo.distanceEdit().Edit(o1, o2);
		assertEquals(result, 0.0, 0.0);

		utilities.scaledTest();

		ContinuousItem o11 = new ContinuousItem(attributeC, 22.0);
		ContinuousItem o22 = new ContinuousItem(attributeC, 22.0);
		result = Calcolo.distanceEdit().Edit(o11, o22);
		assertEquals(result, 0.0, 0.0);

		o11 = new ContinuousItem(attributeC, 2.1);
		o22 = new ContinuousItem(attributeC, 35.6);
		result = Calcolo.distanceEdit().Edit(o11, o22);
		assertEquals(result, 1.0, 0.0);

		o11 = new ContinuousItem(attributeC, 16.7);
		o22 = new ContinuousItem(attributeC, 34.1);
		result = Calcolo.distanceEdit().Edit(o11, o22);
		assertEquals(result, 0.519402985, 0.000000001);

		o11 = new ContinuousItem(attributeC, 21.24);
		o22 = new ContinuousItem(attributeC, 2.13);
		result = Calcolo.distanceEdit().Edit(o11, o22);
		assertEquals(result, 0.570447761, 0.000000001);

	}

}

class utilities {
	static DiscreteAttribute ValuesInit() {
		TreeSet<String> values = new TreeSet<String>();
		values.add("overcast");
		values.add("rain");
		values.add("sunny");
		DiscreteAttribute attribute = new DiscreteAttribute("outlook", 0,
				values);
		return attribute;
	}

	static void scaledTest() {
		GetScaledValueTest g = new GetScaledValueTest();

	}

}
