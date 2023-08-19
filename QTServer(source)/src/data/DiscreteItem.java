package data;

/**
 * Classe DiscreteItem.
 */
public class DiscreteItem extends Item {

	/**
	 * Istanzia un nuovo DiscreteItem
	 *
	 * @param attribute
	 *            prende l'Attributo
	 * @param value
	 *            prende la stringa del valore discreto
	 */
	public DiscreteItem(Attribute attribute, String value) {
		super(attribute, value);
	}
	/*
	 * public double distance(Object a){
	 * if((getValue().equals(((Item)a).getValue()))) return 0; else return 1; }
	 */
}
