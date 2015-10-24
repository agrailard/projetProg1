package classes;
import java.util.*;

import org.jdom2.Element;

public class Accessoire extends Article {
	private ArrayList<Telephone> listeTelephones;

	public Accessoire() {
		// TODO - implement Accessoire.Accessoire
		super();
	}
	
	/**
	 * 
	 * @param reference : La reference de l'article concerné
	 * @param libelle : Son libelle/nom
	 * @param prix : Son prix
	 * @param listeTel : Pour un Accessoire : la liste des telephones avec lesquels il est compatible
	 */
	public Accessoire(String reference, String libelle, double prix, ArrayList<Telephone> listeTel) {
		// TODO - implement Accessoire.Accessoire
		super(reference, libelle, prix);
		listeTelephones=listeTel;
	}
	
	/**
	 * 
	 * @return Cette méthode retourne la liste des téléphones compatible avec cet Accessoire
	 */
	public ArrayList<Telephone> getListeTelephones() {
		return listeTelephones;
	}

	public void setListeTelephones(ArrayList<Telephone> listeTelephones) {
		this.listeTelephones = listeTelephones;
	}
	
	/**
	 * Méthode qui renvoit la description d'un accessoire sous forme de chaîne de caractères
	 * @return une chaine de caractères
	 */
	public String toString() {
		String chaine = super.toString();
		chaine += "Liste des téléphones compatibles :\n";
		for (Telephone tel : listeTelephones) {
			chaine += "\t- " + tel.getIntitule() + "\n";
		}
		return chaine;
	}
	
	public Element toXml() {
		Element classe = super.toXml();
		classe.setAttribute("type", "Accessoire");
		Element attribut = new Element("listeTelephone");
		Element telephones= new Element("Telephones");
		Element intitule;
		attribut.addContent(telephones);
		for (Telephone telephone : listeTelephones) {
			intitule = new Element("intitule");
			telephones.addContent(intitule);
			intitule.setText(telephone.getIntitule());
		}
		classe.addContent(attribut);
		return classe;
	}

}