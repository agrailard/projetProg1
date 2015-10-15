package classes;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Classe Article
 * @author E113155U
 *
 */
public class Article implements Comparable{
	
	//Membres privés
	
	private String reference;
	private String intitule;
	private double prix;
	
	//Constructeur
	
	public Article(){
		
	}

	public Article(String ref, String nom, double prix){
		reference=ref;
		intitule=nom;
		this.prix=prix;
	}
	
	//Getters et setters
	
	public String getReference() {
		return this.reference;
	}
	
	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getIntitule() {
		return this.intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public double getPrix() {
		return this.prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}
	
	public Field[] getFields(){
		return getClass().getDeclaredFields();
	}
	
	//Méthodes
	
	/**
	 * Méthode permettant de renvoyer la description d'un article sous forme de chaîne de caractères
	 * @return la chaîne de caractères
	 */
	public String toString() {
		String chaine;
		chaine ="Reference : " + this.reference + "\n";
		chaine +="Intitulé : \t" + this.intitule + "\n";
		chaine +="Prix : \t\t" + this.prix +"€"+ "\n";
		return chaine;
	}

	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	

}