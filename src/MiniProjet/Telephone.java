package MiniProjet;

public class Telephone extends Article {
	
	private Operateur operateur;
	
	public Telephone(){
		super();
	}
	
	public Telephone(String reference, String intitule, float prix,Operateur operateur) {
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

}