package classes;

import java.util.Comparator;

public class RefComparateur implements Comparator<Article> {

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
