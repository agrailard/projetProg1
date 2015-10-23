package classes;

import org.jdom2.Element;

public class Chargeur extends Accessoire {

	private String type;

	public Chargeur() {
		// TODO - implement Chargeurs.Chargeurs
	}
	
	/**
	 * 
	 * @param unAcces : Un chargeur �tant un accessoire, on lui passe un objet de type accessoire auquel il sera li�
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
	 * M�thode qui renvoit la description d'un chargeur sous forme de cha�ne de caract�res
	 * @return une chaine de caract�res
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