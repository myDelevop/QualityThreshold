package data;

/**
 * Classe ContinuousAttribute.
 */
public class ContinuousAttribute extends Attribute {

	/**
	 * Dichiarazione di massimo e minimo
	 * 
	 * Questi rappresentano gli estremi dell'intervallo di valori(dominio) che
	 * l'attributo può realmente assumere
	 */
	private double max; // rappresentano gli estremi dell'intervallo di valori
						// (dominio)
	private double min; // che l'attributo può realmente assumere

	/**
	 * Istanzio un nuovo Attributo Continuo
	 *
	 * @param Nome
	 *            attributo
	 * @param Identificativo
	 *            numerico attributo
	 * @param Minimo
	 * @param Massimo
	 * 
	 */
	public ContinuousAttribute(String name, int index, double min, double max) {
		super(name, index);
		this.min = min;
		this.max = max;
	}

	/**
	 * Metodo getScaledValue
	 * 
	 * Calcolo del valore scalato secondo la formula (v-min)/(max-min)
	 *
	 * @param valore
	 *            double
	 * @return valore scalore
	 */
	public double getScaledValue(double v) {
		return (v - min) / (max - min);
	}
}
