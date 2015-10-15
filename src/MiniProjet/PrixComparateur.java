package MiniProjet;

import java.util.Comparator;

public class PrixComparateur  implements Comparator<Article>{
	
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
