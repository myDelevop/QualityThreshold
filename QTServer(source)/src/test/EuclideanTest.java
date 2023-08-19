package test;

import static org.junit.Assert.*;

import java.util.TreeSet;

import org.junit.Test;

import data.*;
import Distance.Calcolo;

public class EuclideanTest {
	@Test
	public void test() {

		DiscreteAttribute attributeD = utility.ValuesInit();
		ContinuousAttribute attributeC = new ContinuousAttribute("attr", 0,
				2.1, 35.6);

		DiscreteItem o1 = new DiscreteItem(attributeD, "test");
		DiscreteItem o2 = new DiscreteItem(attributeD, "unValore");

		double result = Calcolo.distanceE().Euclidea(o1, o2);
		assertEquals(result, 1.0, 0.0);
		o1 = new DiscreteItem(attributeD, "prova");
		o2 = new DiscreteItem(attributeD, "prova2");
		result = Calcolo.distanceE().Euclidea(o1, o2);
		assertEquals(result, 1.0, 0.0);

		o1 = new DiscreteItem(attributeD, "test");
		o2 = new DiscreteItem(attributeD, "test");
		result = Calcolo.distanceE().Euclidea(o1, o2);
		assertEquals(result, 0.0, 0.0);

		ContinuousItem o11 = new ContinuousItem(attributeC, 22.0);
		ContinuousItem o22 = new ContinuousItem(attributeC, 22.0);
		result = Calcolo.distanceE().Euclidea(o11, o22);
		assertEquals(result, 0.0, 0.0);

		o11 = new ContinuousItem(attributeC, 2.1);
		o22 = new ContinuousItem(attributeC, 35.6);
		result = Calcolo.distanceE().Euclidea(o11, o22);
		assertEquals(result, 1.0, 0.0);

		o11 = new ContinuousItem(attributeC, 16.7);
		o22 = new ContinuousItem(attributeC, 34.1);
		result = Calcolo.distanceE().Euclidea(o11, o22);
		assertEquals(result, 0.519402985, 0.000000001);

		o11 = new ContinuousItem(attributeC, 21.24);
		o22 = new ContinuousItem(attributeC, 2.13);
		result = Calcolo.distanceE().Euclidea(o11, o22);
		assertEquals(result, 0.570447761, 0.000000001);

	}

}

class utility {
	static DiscreteAttribute ValuesInit() {
		TreeSet<String> values = new TreeSet<String>();
		values.add("test");
		values.add("prova");
		values.add("prova2");
		values.add("unValore");
		DiscreteAttribute attribute = new DiscreteAttribute("attr", 0, values);
		return attribute;
	}

	static void scaledTest() {
		GetScaledValueTest g = new GetScaledValueTest();

	}
}
