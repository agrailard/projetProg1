package classes;

import java.util.Comparator;

public class PrixComparateur  implements Comparator<Article>{
	//Classe permettant de comparer des articles suivant leurs prix (croissant)
    public int compare(Article a1 , Article a2){
		if(a1.getPrix()>a2.getPrix()){
			return 1;
		}else if(a1.getPrix()<a2.getPrix()){
			return -1;
		}
		else{
			return 0;
		}
		
	}
}
