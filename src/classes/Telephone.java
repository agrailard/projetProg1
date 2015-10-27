package classes;

import org.jdom2.Element;

public class Telephone extends Article {
	
	private Operateur operateur;
	
	public Telephone(){
		super();
	}

	public Telephone(String reference, String intitule, double prix,Operateur operateur) {
		// TODO - implement telephone.telephone
		super(reference,intitule,prix);
		this.operateur=operateur;
	}
	
	/**
	 * Méthode qui renvoit la description d'un téléphone sous forme de chaîne de caractères
	 * @return une chaine de caractères
	 */
	public String toString() {
		String chaine = super.toString();
		chaine += "Opérateur : " + this.operateur.getLibelle() + "\n";
		return chaine;
	}
	
	public Element toXml() {
		Element classe = super.toXml();
		classe.setAttribute("type", "Telephone");
		Element attribut = new Element("operateur");
		attribut.setAttribute("id", Integer.toString(operateur.getIdentifiant()));
		attribut.setText(operateur.getLibelle());
		
		classe.addContent(attribut);
		
		return classe;
	}

}