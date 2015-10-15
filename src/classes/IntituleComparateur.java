package classes;

import java.util.Comparator;

public class IntituleComparateur implements Comparator<Article>{

    public int compare(Article a1 , Article a2){
    	return a1.getIntitule().compareToIgnoreCase(a2.getIntitule());
	}
}
