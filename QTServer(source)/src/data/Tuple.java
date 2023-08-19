package data;

import java.io.Serializable;
import java.util.Set;

import Distance.Calcolo;

/**
 * Classe Tuple.
 */
public class Tuple implements Serializable{
	
	/** Vettore di Item di nome tuple */
	Item [] tuple;
	
	/**
	 * Istanzio una nuova Tuple
	 *
	 * @param size Grandezza del vettore
	 */
	public Tuple(int size){
		tuple=new Item [size];
	}
	
	/**
	 * Restituisce la lunghezza della tupla
	 *
	 */
	public int getLength(){
		return tuple.length;
	}
	
	/**
	 * Restituisce l'Item in posizione i del vettore tuple
	 *
	 * @param i Posizione dell'Item
	 */
	public Item get(int i){
		return tuple[i];
	}
	
	/**
	 * Aggiunge l'Item c nella posizione i del vettore tuple
	 *
	 * @param c Item da aggiungere
	 * @param i Posizione dove andare ad inserire l'Item
	 */
	void add(Item c, int i){
		tuple[i]=c;
	}
	
	/**
	 * Calcolo della distanza fra due tuple
	 * 
	 * 
	 *
	 * @param obj1 Tupla 1
	 * @param obj2 Tupla 2
	 * @param typeDistance Tipo di Distanza(Euclidea o Edit)
	 * @return La distanza calcolata
	 */
	public double getDistance(Tuple obj1,Tuple obj2, String typeDistance) throws TypeDistanceException{
		double distanza=0.0;
		
		if(typeDistance.equalsIgnoreCase("euclidea")){
			for(int i=0;i<this.getLength();i++){
				distanza+=Calcolo.distanceE().Euclidea(obj1.get(i),obj2.get(i));
			}	
		}else if(typeDistance.equalsIgnoreCase("edit")){
			for(int j=0;j<this.getLength();j++){
				distanza+=Calcolo.distanceEdit().Edit(obj1.get(j),obj2.get(j));
			}
		}else
			throw new TypeDistanceException();
		return distanza;
		
	}
	
	/**
	 * Calcolo della distanza media
	 *
	 * @param data Prende il Dataset
	 * @param tp1 Prende la tupla
	 * @param clusteredData Set contenente le posizioni delle tuple 
	 * @param typeDistance Tipo di distanza
	 * @return Distanza media
	 */
	public double avgDistance(Data data, Tuple tp1, Set<Integer> clusteredData , String typeDistance)throws TypeDistanceException{
		double p=0.0,sumD=0.0;
		try{
			for(Integer i:clusteredData){
				double d=getDistance(tp1, data.getItemSet(i),typeDistance);
				sumD+=d;
			}
			p=sumD/clusteredData.size();
		}catch(TypeDistanceException e){
			throw new TypeDistanceException();
		}
		
		return p;
	}
}
