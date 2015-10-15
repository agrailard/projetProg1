package classes;
public class Operateur {

	private int identifiant;
	private String libelle;

	/**
	 * 
	 * @param identifiant : Identifiant de l'operateur
	 * @param libelle : Son nom
	 */
	public Operateur(int identifiant, String libelle) {
		this.identifiant=identifiant;
		this.libelle=libelle;
	}
	
	/**
	 * 
	 * @return : Retourne le nom de l'operateur
	 */
	public String getLibelle() {
		return this.libelle;
	}

	/**
	 * 
	 * @param libelle : Permet de modifier le libelle de l'operateur
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * 
	 * @return : Retourne l'identifiant de l'operateur
	 */
	public int getIdentifiant() {
		return this.identifiant;
	}

	/**
	 * 
	 * @param identifiant : Permet de modifer l'identifiant de l'operateur
	 */
	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}

}