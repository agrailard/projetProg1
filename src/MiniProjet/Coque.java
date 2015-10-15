package MiniProjet;
public class Coque extends Accessoire {

	private String couleur;

	public Coque() {
		// TODO - implement Chargeurs.Chargeurs
	}
	
	/**
	 * 
	 * @param unAcces : Une coque �tant un accessoire, un objet accessoire est pass� en parametre qui representera la coque
	 * @param couleur : Couleur de la coque : Rouge, bleu , vert, ...
	 */
	public Coque(Accessoire unAcces, String couleur) {
		super(unAcces.getReference(),unAcces.getIntitule(),unAcces.getPrix(),unAcces.getListeTelephones());
		this.couleur=couleur;
	}
	
	/**
	 * 
	 * @return : Retourne la couleur de la coque
	 */
	public String getCouleur() {
		return this.couleur;
	}

	/**
	 * 
	 * @param couleur : Permet de modifier la couleur de la coque
	 */
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	
	/**
	 * M�thode qui renvoit la description d'une coque sous forme de cha�ne de caract�res
	 * @return une chaine de caract�res
	 */
	public String toString() {
		String chaine = super.toString();
		chaine += "Couleur : \t" + this.couleur + "\n";
		return chaine;
	}


}