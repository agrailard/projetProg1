package classes;
//Grailard Arthur
//Fabien Ganivet
import java.util.Comparator;

public class IntituleComparateur implements Comparator<Article>{
	//Classe permettant de comparer des articles suivant leurs intitules
    public int compare(Article a1 , Article a2){
    	return a1.getIntitule().compareToIgnoreCase(a2.getIntitule());
	}
}
