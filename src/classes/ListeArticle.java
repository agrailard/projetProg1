package classes;
//Grailard Arthur
//Fabien Ganivet
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
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
	//Methode permettant de trier les articles par Reference
	public void tousLesArticles_ParRef(){
		Collections.sort(listeArticles,new RefComparateur());
	}
	//Methode permettant de trier par Intitule
	public void tousLesArticles_ParIntitule(){
		Collections.sort(listeArticles,new IntituleComparateur());
	}
	//idem mais par prix
	public void tousLesArticles_ParPrix(){
		Collections.sort(listeArticles,new PrixComparateur());
	}
	//Methode permettant de sauveegarder les articles dans un fichier txt
	/**
	 * 
	 * @param nomFic : Nom du fichier dans lequel les articles seront sauvegarder
	 */
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
	
	public void enregistreXml(String fichier)
	{
		toXml();
		try{
			document.removeContent();
		}catch(Exception e){
			//fichier inexistant ce qui en soit n'est pas important au  début car il peut être
			//créer par l'utilisateur.
		}
	   try
	   {
	      //On utilise ici un affichage classique avec getPrettyFormat()
	      XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
	      //Remarquez qu'il suffit simplement de créer une instance de FileOutputStream
	      //avec en argument le nom du fichier pour effectuer la sérialisation.
	      sortie.output(document, new FileOutputStream(fichier));
	   }
	   catch (java.io.IOException e){}
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
	
	// retourne le nombre d'articles
	public int compter(){
		return this.listeArticles.size();
	}
	
	//getter d'article
	public Article getArticle(int i) {
		return listeArticles.get(i);
	}

	//vide la liste d'articles
	public void vider() {
		listeArticles.clear();
	}
	
	/**
	 * Méthode permettant de lire un fichier xml
	 * @return une liste d'article de la classe ListeArticle
	 */
	public ListeArticle xmlToList() {
		
      //On crée une instance de SAXBuilder
      SAXBuilder sxb = new SAXBuilder();
      try
      {
         //On crée un nouveau document JDOM avec en argument le fichier XML
         //Le parsing est terminé ;)
         document = sxb.build(new File("inventaire.xml"));
      }
      catch(Exception e){}

      //On initialise un nouvel élément racine avec l'élément racine du document.
      racine = document.getRootElement();
      
      
      //On crée une List contenant tous les noeuds "etudiant" de l'Element racine
      List listeArticles = racine.getChildren("Article");
      
      ListeArticle liste = new ListeArticle();
      ArrayList<Telephone> telephones;
      Accessoire accessoire;
      Chargeur chargeur;
      Coque coque;
      Cordon cordon;
      Telephone telephone;
      Operateur operateur;

      //On crée un Iterator sur notre liste
      Iterator i = listeArticles.iterator();
      while(i.hasNext())
      {
         //On recrée l'Element courant à chaque tour de boucle afin de
         //pouvoir utiliser les méthodes propres aux Element comme :
         //sélectionner un nœud fils, modifier du texte, etc...
         Element courant = (Element)i.next();
         if(courant.getAttribute("type").getValue().equals("Accessoire")) {
        	 telephones = new ArrayList<Telephone>();
        	 accessoire = new Accessoire(courant.getChildText("reference"), courant.getChildText("intitule"), Double.valueOf(courant.getChildText("prix")), telephones);
        	 if(courant.getChild("type")!=null) {
        		 chargeur = new Chargeur(accessoire, courant.getChildText("type"));
        		 liste.ajouterArticle(chargeur);
        	 } else if(courant.getChild("couleur")!=null) {
        		 coque = new Coque(accessoire, courant.getChildText("couleur"));
        		 liste.ajouterArticle(coque);
        	 } else if(courant.getChild("longueur")!=null) {
        		 cordon = new Cordon(accessoire, Float.valueOf(courant.getChildText("longueur")));
        		 liste.ajouterArticle(cordon);
        	 }
         } else if(courant.getAttribute("type").getValue().equals("Telephone")) {
        	 operateur = new Operateur(Integer.valueOf(courant.getChild("operateur").getAttributeValue("id")), courant.getChildText("operateur"));
        	 telephone = new Telephone(courant.getChildText("reference"), courant.getChildText("intitule"), Double.valueOf(courant.getChildText("prix")), operateur);
        	 liste.ajouterArticle(telephone);
         }
      }
      return liste;
   }
	
}

