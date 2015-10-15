package classes;
public class Cordon extends Accessoire {

	private float longueur; //Longueur du cordon en cm

	public Cordon() {
		// TODO - implement Chargeurs.Chargeurs
	}
	
	/**
	 * 
	 * @param unAcces : Un Cordon �tant un accessoire, un objet accessoire est pass� en parametre qui representera ce Cordon
	 * @param longueur : Variable qui definira la longueur du cordon
	 */
	public Cordon(Accessoire unAcces, float longueur) {
		super(unAcces.getReference(),unAcces.getIntitule(),unAcces.getPrix(),unAcces.getListeTelephones());
		this.longueur=longueur;
	}

	/**
	 * 
	 * @return : Retourne la longueur du cordon
	 */
	public float getLongueur() {
		return this.longueur;
	}

	/**
	 * 
	 * @param longueur : Permet de modifier la longueur du cordon
	 */
	public void setLongueur(float longueur) {
		this.longueur = longueur;
	}
	
	/**
	 * M�thode qui renvoit la description d'un cordon sous forme de cha�ne de caract�res
	 * @return une chaine de caract�res
	 */
	public String toString() {
		String chaine = super.toString();
		chaine += "Longueur : \t" + this.longueur + " cm\n";
		return chaine;
	}

}