package classes;
//Grailard Arthur
//Fabien Ganivet
import java.util.Comparator;

public class RefComparateur implements Comparator<Article> {
	//Classe permettant de comparer des articles suivant leurs reference (croissante)
    public int compare(Article a1 , Article a2){
		if(Integer.parseInt(a1.getReference())>Integer.parseInt(a2.getReference())){
			return 1;
		}else if(Integer.parseInt(a1.getReference())<Integer.parseInt(a2.getReference())){
			return -1;
		}
		else{
			return 0;
		}
		
	}
}
