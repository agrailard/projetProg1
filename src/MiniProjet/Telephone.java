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
	 * M�thode qui renvoit la description d'un t�l�phone sous forme de cha�ne de caract�res
	 * @return une chaine de caract�res
	 */
	public String toString() {
		String chaine = super.toString();
		chaine += "Op�rateur : " + this.operateur.getLibelle() + "\n";
		return chaine;
	}

}