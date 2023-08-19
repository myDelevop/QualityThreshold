package Distance;

import data.*;

// TODO: Auto-generated Javadoc
/**
 * Classe ImplDistance.
 */
public class ImplDistance implements IntDistanceEuclidea, IntDistanceEdit {

	/**
	 * Calcola la distanza Euclidea. Se i valori sono instanze di
	 * ContinuuousItem, il risultato della distanza è la differenza tra i due
	 * valori scalati altrimenti 0 se sono istanze di DiscreteItem 1 altrimenti
	 */
	@Override
	public Double Euclidea(Item o1, Item o2) {
		Double result = 0.0;
		if (o1 instanceof ContinuousItem && o2 instanceof ContinuousItem) {
			double Valore1 = (double) o1.getValue();
			double Valore2 = (double) ((ContinuousItem) o2).getValue();

			double ris1 = ((ContinuousAttribute) o1.getAttribute())
					.getScaledValue(Valore1);
			double ris2 = ((ContinuousAttribute) ((ContinuousItem) o2)
					.getAttribute()).getScaledValue(Valore2);

			result = Math.abs(ris1 - ris2);
		} else if (o1 instanceof DiscreteItem && o2 instanceof DiscreteItem
				&& o1.getValue().equals(o2.getValue())) {
			result = 0.0;
		} else
			result = 1.0;

		return result;

	}

	/**
	 * Calcola la distanza Edit. Se i valori sono instanze di ContinuuousItem,
	 * il risultato della distanza è la differenza tra i due valori scalati
	 * altrimenti calcola la differenza delle posizioni dei valori nel TreeSet
	 * dividendolo per la grandezza dei valori nel Treeset.
	 */
	@Override
	public Double Edit(Item o1, Item o2) {
		double result = 0.0;
		if (o1 instanceof ContinuousItem && o2 instanceof ContinuousItem) {
			double Valore1 = (double) o1.getValue();
			double Valore2 = (double) ((ContinuousItem) o2).getValue();

			double ris1 = ((ContinuousAttribute) o1.getAttribute())
					.getScaledValue(Valore1);
			double ris2 = ((ContinuousAttribute) ((ContinuousItem) o2)
					.getAttribute()).getScaledValue(Valore2);

			result = Math.abs(ris1 - ris2);

		} else if (o1 instanceof DiscreteItem && o2 instanceof DiscreteItem) {
			int n = ((DiscreteAttribute) ((DiscreteItem) o1).getAttribute())
					.getNumberOfDistinctValues();
			int k = ((DiscreteAttribute) ((DiscreteItem) o1).getAttribute())
					.getValues().headSet(o1.toString()).size();
			int h = ((DiscreteAttribute) ((DiscreteItem) o1).getAttribute())
					.getValues().headSet(o2.toString()).size();
			int i = Math.abs(k - h);
			result = ((double) (i)) / ((double) (n - 1));

		}
		return result;
	}
}
