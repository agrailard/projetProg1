package classes;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class ListeArticle {

	private List<Article> listeArticles;
	
	static Element racine = new Element("Inventaire");
	static org.jdom2.Document document = new Document(racine);
	
	/**
	 * {@code} On instancie une ArrayList vide
	 */
	public ListeArticle() { //Constructeur de la Classe listeArticle qui vas instancier une ArrayList vide
		// TODO - implement ListeArticle.ListeArticle
		listeArticles = new ArrayList();   //On in
	}

	
	/**
	 * @param art : On passe en parametre un Article que l'on ajoutera dans l'Arraylist
	 */
	public void ajouterArticle(Article art) {
		// TODO - implement ListeArticle.ajouterArticle
		listeArticles.add(art);
	}

	
	/**
	 * @param art : On passe en parametre l'Article que l'on souhiate supprimer de la liste
	 */
	public void supprimerArticle(Article art) {
		// TODO - implement ListeArticle.supprimerArticle
		listeArticles.remove(art);
	}
	
	public void afficher(){
		for(int i=0;i<listeArticles.size();i++)
		System.out.println(listeArticles.get(i).getReference()+" nom :"+listeArticles.get(i).getIntitule());
	}
	
	public void tousLesArticles_ParRef(){
		Collections.sort(listeArticles,new RefComparateur());
	}
	
	public void tousLesArticles_ParIntitule(){
		Collections.sort(listeArticles,new IntituleComparateur());
	}
	
	public void tousLesArticles_ParPrix(){
		Collections.sort(listeArticles,new PrixComparateur());
	}
	
	public void sauvegarde(String nomFic) throws IOException{
		FileWriter fw = new FileWriter(nomFic);
		for(int i=0;i<listeArticles.size();i++){
			fw.write(listeArticles.get(i).toString()+"\n");
		}
		fw.close();
	}
	
	public void toXml() {
		
		Element article;
		
		for (Article unArticle : listeArticles) {
			article = unArticle.toXml();
			racine.addContent(article);
		}
	}
	
	public void afficheToXml() {
		toXml();
		
		try
		   {
		      //On utilise ici un affichage classique avec getPrettyFormat()
		      XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
		      sortie.output(document, System.out);
		   }
		   catch (java.io.IOException e){}
	}
	
	public int compter(){
		return this.listeArticles.size();
	}
	
	public Article getArticle(int i) {
		return listeArticles.get(i);
	}


	public void vider() {
		listeArticles.clear();
	}

}

