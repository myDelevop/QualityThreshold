package data;

/**
 * Classe ContinuousItem.
 */
public class ContinuousItem extends Item {

	/**
	 * 
	 * Costruttore ContinuosItem Instanzio un nuovo ContinuosItem
	 *
	 * @param Attributo
	 * @param Valore
	 *            continuo
	 */
	public ContinuousItem(Attribute attribute, Double value) {
		super(attribute, value);
		// TODO Auto-generated constructor stub
	}

	/*
	 * @Override public double distance(Object a) { // TODO Auto-generated
	 * method stub double Valore1= (double)this.getValue(); double Valore2=
	 * (double)((ContinuousItem)a).getValue();
	 * 
	 * double
	 * ris1=((ContinuousAttribute)this.getAttribute()).getScaledValue(Valore1);
	 * double
	 * ris2=((ContinuousAttribute)((ContinuousItem)a).getAttribute()).getScaledValue
	 * (Valore2);
	 * 
	 * return Math.abs(ris1-ris2); }
	 */

}
