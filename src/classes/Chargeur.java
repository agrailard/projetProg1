package classes;

import org.jdom2.Element;

public class Chargeur extends Accessoire {

	private String type;

	public Chargeur() {
		// TODO - implement Chargeurs.Chargeurs
	}
	
	/**
	 * 
	 * @param unAcces : Un chargeur étant un accessoire, on lui passe un objet de type accessoire auquel il sera lié
	 * @param type : Type du chargeur : USB, secteur, ...
	 */
	public Chargeur(Accessoire unAcces, String type) {
		super(unAcces.getReference(),unAcces.getIntitule(),unAcces.getPrix(),unAcces.getListeTelephones());
		this.type=type;
	}
	
	/**
	 * 
	 * @return : Methode qui permet de retourner le type du chargeur
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * 
	 * @param type : Methode qui permet de modifier le type du chargeur
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * Méthode qui renvoit la description d'un chargeur sous forme de chaîne de caractères
	 * @return une chaine de caractères
	 */
	public String toString() {
		String chaine = super.toString();
		chaine += "Type : \t\t" + this.type + "\n";
		return chaine;
	}
	
	public Element toXml() {
		Element classe = super.toXml();
		Element attribut = new Element("type");
		attribut.addContent(this.type);
		classe.addContent(attribut);
		
		return classe;
	}


}