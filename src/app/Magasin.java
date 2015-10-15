package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import classes.Accessoire;
import classes.Chargeur;
import classes.Coque;
import classes.Cordon;
import classes.ListeArticle;
import classes.Operateur;
import classes.Telephone;

public class Magasin {

	private String nom;
	private ListeArticle listeArticles;

	/**
	 * 
	 * @return : Permet de recupere le Nom
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * 
	 * @param nom : Permet de changer le nom du magasin
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	public Magasin() {
		// TODO - implement Magasin.Magasin
	}
	
	public static void main(String args[]) throws IOException{
		Operateur free = new Operateur(1,"free");
		Operateur orange = new Operateur(2,"Orange");
		Operateur bouygues = new Operateur(3,"Bouygues Telecom");
		Telephone tel1 = new Telephone("1548","iphone4",420,free);
		Telephone tel2 = new Telephone("1549","Nokia 3310",50,orange);
		Telephone tel3 = new Telephone("1550","Samsung Galaxy",100,free);
		Telephone tel4 = new Telephone("1551","Nokia xxxx",47,orange);
		Telephone tel5 = new Telephone("1552","Samsung Galaxy Mini",61,bouygues);
	
		
		ArrayList listL1 = new ArrayList<Telephone>();
		listL1.add(tel1);
		listL1.add(tel3);
		
		ArrayList listL2 = new ArrayList<Telephone>();
		listL2.add(tel2);
		listL2.add(tel4);
		
		ArrayList listL3 = new ArrayList<Telephone>();
		listL3.add(tel2);
		listL3.add(tel3);
		
		Chargeur chargeur = new Chargeur(new Accessoire("45", "Chargeur K2", 15, listL1),"USB");
		Chargeur chargeurNokia = new Chargeur(new Accessoire("44","Chargeur High Performance",13,listL2),"Secteur");
		Cordon cordon = new Cordon(new Accessoire("12","Cordon High Performance",10,listL2),25);
		Coque coque = new Coque(new Accessoire("01","Coque Mini",17.8,listL3),"Rouge");
		
		ListeArticle liste = new ListeArticle();
		liste.ajouterArticle(tel1);
		liste.ajouterArticle(chargeur);
		liste.ajouterArticle(chargeurNokia);
		liste.ajouterArticle(cordon);
		liste.afficher();
		liste.tousLesArticles_ParRef();
		System.out.println("////////");
		liste.afficher();
		liste.sauvegarde("save.txt");
		
		liste.afficheToXml();

	}

}