package app;
//Grailard Arthur
//Fabien Ganivet
import java.awt.EventQueue;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;

import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import classes.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Magasin implements ActionListener {

	private JFrame frame;
	private ListeArticle listeArticles = new ListeArticle();
	private JList<Article> listeDeroul;
	private JPanel panel = new JPanel();
	private JLabel lblReference = new JLabel("Reference : ");
	private JLabel lblIntitul = new JLabel("Intitule : ");
	private JLabel lblPrix = new JLabel("Prix : ");
	private JLabel labelTxtRef = new JLabel("");
	private JLabel labelTxtInt = new JLabel("");
	private JLabel labelTxtPrix = new JLabel("");
	private JButton btnSauvegarder;
	private JButton btnTrierParRfrence;
	private final JButton btnTrierParPrix = new JButton("Trier par Prix");
	private Article[] articles;
	private DefaultListModel dlm;
	private JSplitPane splitPane = new JSplitPane();
	private final JButton btnTrierParNom = new JButton("Trier par nom");
	private JLabel lblAccessoire = new JLabel("");
	private JLabel lblTxtAccessoire = new JLabel("");
	
	ArrayList listeOperateur = new ArrayList<Operateur>();
	ArrayList listeOperateurString = new ArrayList<String>();
	ArrayList type = new ArrayList<String>();
	ArrayList listTelcompa = new ArrayList<Telephone>();
	
	private JButton btnAjoutAccessoire;
	private JButton btnAjoutTelephone;
	private JButton btnAjoutOperateur;
	private JButton btnSuppr;
	
	JTextField ref = new JTextField();
	JTextField libelle = new JTextField();
	JTextField prix = new JTextField();
	JTextField compatible = new JTextField();
	JComboBox operateur = new JComboBox();
	JTextField reponse = new JTextField();
	JTextField reponseCoque = new JTextField();
	JComboBox listeTel;
	JComboBox listeArt;
   

	char c;
	boolean virgule = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Magasin window = new Magasin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Magasin() {
		initListeArticles();

		btnTrierParRfrence.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				trier("ref");
			}
		});
		btnTrierParPrix.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				trier("prix");
			}
		});
		btnTrierParNom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				trier("int");
			}
		});
	}
	/**
	 * Initialise la liste(JList) des articles
	 */
	public void initJList() {
		dlm = new DefaultListModel<>();
		articles = new Article[listeArticles.compter()];
		for(int i = 0 ; i<listeArticles.compter();i++) {
			articles[i] = listeArticles.getArticle(i);
			dlm.add(i, listeArticles.getArticle(i).getIntitule());
		}
		
		listeDeroul = new JList(articles);
		
		listeDeroul.setModel(dlm);
		listeDeroul.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listeDeroul.setSelectedIndex(0);
		splitPane.setLeftComponent(listeDeroul);
		
		listeDeroul.addListSelectionListener(listener);
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		type.add("Chargeur");
		type.add("Coque");
		type.add("Cordon");
		frame = new JFrame();
		frame.setTitle("Inventaire des articles");
		frame.setSize(950, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menu = new JMenuBar();
		frame.setJMenuBar(menu);
		
		btnAjoutAccessoire = new JButton("Ajouter un accessoire");
		btnAjoutAccessoire.addActionListener(this);
		menu.add(btnAjoutAccessoire);
		
		btnSuppr = new JButton("Supprimer un Article");
		btnSuppr.addActionListener(this);
		menu.add(btnSuppr);
		
		btnAjoutTelephone = new JButton("Ajouter un Telephone");
		btnAjoutTelephone.addActionListener(this);
		menu.add(btnAjoutTelephone);	
		
		btnAjoutOperateur = new JButton("Ajouter un Operateur");
		btnAjoutOperateur.addActionListener(this);
		menu.add(btnAjoutOperateur);
		
		btnSauvegarder = new JButton("Sauvegarder");
		btnSauvegarder.addActionListener(this);
		menu.add(btnSauvegarder);
		
		btnTrierParRfrence = new JButton("Trier par r\u00E9f\u00E9rence");
		
		menu.add(btnTrierParRfrence);
		
		menu.add(btnTrierParPrix);
		
		menu.add(btnTrierParNom);
		frame.getContentPane().setLayout(null);
		
		splitPane.setBounds(0, 0, 584, 337);
		frame.getContentPane().add(splitPane);
		
		
		splitPane.setRightComponent(panel);
		panel.setLayout(null);
		
		lblReference.setBounds(10, 11, 92, 14);
		panel.add(lblReference);
		
		lblIntitul.setBounds(10, 36, 92, 14);
		panel.add(lblIntitul);
		
		lblPrix.setBounds(10, 61, 92, 14);
		panel.add(lblPrix);
		try{
		labelTxtRef.setText(listeArticles.getArticle(listeDeroul.getSelectedIndex()).getReference());
		labelTxtPrix.setText(Double.toString(listeArticles.getArticle(listeDeroul.getSelectedIndex()).getPrix()));
		labelTxtInt.setText(listeArticles.getArticle(listeDeroul.getSelectedIndex()).getIntitule());
		}catch(Exception e){

		}
		labelTxtRef.setBounds(92, 11, 475, 14);
		panel.add(labelTxtRef);
		

		labelTxtInt.setBounds(92, 36, 475, 14);
		panel.add(labelTxtInt);
		
		labelTxtPrix.setBounds(92, 61, 475, 14);
		panel.add(labelTxtPrix);

		
		lblAccessoire.setBounds(10, 86, 72, 14);
		panel.add(lblAccessoire);
		
		lblTxtAccessoire.setBounds(92, 86, 475, 14);
		panel.add(lblTxtAccessoire);
	}
	/**
	 * Initialise les données des articles (pour les tests)
	 *
	 */
	private void initListeArticles(){
		try{
			listeArticles=listeArticles.xmlToList();
		}catch(Exception e){
			  final JPanel panel = new JPanel();
			  JOptionPane.showMessageDialog(panel, "Impossible de lire inventaire.xml", "Error", JOptionPane.ERROR_MESSAGE);
		}finally{
			initJList();
			initialize();
		}


		
	}
	/***
	 @param tri : si égal à "ref" tri par références,
	 * si égal à "prix" tri par prix et
	 * si égal à "int", tri par nom
	 * si égal à autre chose : ne se passe rien
	 */
	private void trier(String tri){
		if(tri=="ref") listeArticles.tousLesArticles_ParRef();
		else if (tri=="int") listeArticles.tousLesArticles_ParIntitule();
		else if (tri=="prix") listeArticles.tousLesArticles_ParPrix();
		initJList();
	}
	/**
	 * Ecouteur du changement d'option dans la Jlist
	 */
	ListSelectionListener listener = new ListSelectionListener() {
		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			labelTxtRef.setText(listeArticles.getArticle(listeDeroul.getSelectedIndex()).getReference());
			labelTxtInt.setText(listeArticles.getArticle(listeDeroul.getSelectedIndex()).getIntitule());
			labelTxtPrix.setText(Double.toString(listeArticles.getArticle(listeDeroul.getSelectedIndex()).getPrix()));
		}
	};

	public void actionPerformed(ActionEvent evt) {
		ref.addKeyListener(keyListener);
		prix.addKeyListener(keyListener);
		reponseCoque.addKeyListener(keyListener);
		Object source = evt.getSource();
		if (source == btnSauvegarder) {
			 String[] choix = {"Texte", "XML"};
			    JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
			    int rang = jop.showOptionDialog(null, "Veuillez choisir le format d'enregistrement","Sauvegarde de l'inventaire",
			      JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null,choix,choix[1]);

			try {
				if(rang == 0){
					listeArticles.sauvegarde("save.txt");
					JOptionPane.showMessageDialog(btnSauvegarder, "Fichier sauvegardé");
				}
				else if(rang ==1){
					listeArticles.enregistreXml("inventaire.xml");	
					JOptionPane.showMessageDialog(btnSauvegarder, "Fichier sauvegardé");
				}
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(btnSauvegarder, "Une Erreur est survenu : " + e.getMessage());
			}
		} else if (source == btnAjoutAccessoire) {
			virgule = false;

			JComboBox typeAccessoire = new JComboBox(type.toArray(new String[type.size()]));
			Object[] message = { "Reference de l'article: ", ref, "Libelle : ", libelle, "Type d'accessoire : ",
					typeAccessoire, "Prix : ", prix, };
			int option = JOptionPane.showConfirmDialog(null, message, "Ajout d'un accessoire",
					JOptionPane.OK_CANCEL_OPTION);
			if (option != JOptionPane.OK_CANCEL_OPTION) {
				if (typeAccessoire.getSelectedItem().toString().equalsIgnoreCase("Chargeur")) {
					Object[] messageCh = { "Type de chargeur : ", reponse };
					int option2 = JOptionPane.showConfirmDialog(null, messageCh, "Ajout d'un chargeur",
							JOptionPane.OK_CANCEL_OPTION);
					if (option2 != JOptionPane.OK_CANCEL_OPTION) {
						listeArticles
								.ajouterArticle(new Chargeur(
										new Accessoire(ref.getText(), libelle.getText(),
												Double.parseDouble(prix.getText()), AjoutListTel()),
										reponse.getText()));
					}
				} else if (typeAccessoire.getSelectedItem().toString().equalsIgnoreCase("Cordon")) {
					Object[] messageCo = { "Longueur du cordon : ", reponse };
					int option2 = JOptionPane.showConfirmDialog(null, messageCo, "Ajout d'un cordon",
							JOptionPane.OK_CANCEL_OPTION);
					if (option2 != JOptionPane.OK_CANCEL_OPTION) {
						listeArticles
								.ajouterArticle(new Chargeur(
										new Accessoire(ref.getText(), libelle.getText(),
												Double.parseDouble(prix.getText()), AjoutListTel()),
										reponse.getText()));
					}
				} else {
					Object[] messageCoq = { "Couleur de la coque : ", reponseCoque };
					int option2 = JOptionPane.showConfirmDialog(null, messageCoq, "Ajout d'une coque",
							JOptionPane.OK_CANCEL_OPTION);
					if (option2 != JOptionPane.OK_CANCEL_OPTION) {
						listeArticles
								.ajouterArticle(new Chargeur(
										new Accessoire(ref.getText(), libelle.getText(),
												Double.parseDouble(prix.getText()), AjoutListTel()),
										reponseCoque.getText()));
					}
				}
			}
			optionsClear();
			initJList();
		} else if (source == btnAjoutTelephone) {
			createTel(true);
			
		} else if (source == btnAjoutOperateur) {
			Object[] message = { "Identifiant : ", ref, "Nom : ", libelle, };
			int option = JOptionPane.showConfirmDialog(null, message, "Ajout d'un operateur",
					JOptionPane.OK_CANCEL_OPTION);
			if (option != JOptionPane.OK_CANCEL_OPTION) {
				if (ref.getText().isEmpty() || libelle.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Un ou plusieurs champ sont restés vides!");
				} else {
					Operateur op = new Operateur(Integer.parseInt(ref.getText()), libelle.getText());
					listeOperateur.add(op);
					listeOperateurString.add(op.getLibelle());
				}
			}
			optionsClear();
		}
		else if (source == btnSuppr){
			listeArticles.supprimerArticle(listeArticles.getArticle(listeDeroul.getSelectedIndex()));
			trier("int");


		}
	}


	private ArrayList<Telephone> AjoutListTel(){
		createTel(false);
		int option = JOptionPane.showConfirmDialog(null, "Ajout d'un autre telephone compatible");
		if(option!=JOptionPane.CANCEL_OPTION && option!=JOptionPane.NO_OPTION){
			listTelcompa.add(createTel(false));
		}
		return listTelcompa;
	}
	
	private Telephone createTel(boolean methode){
		Telephone tel = null ;	
		int option;
		optionsClear();
		int a = listeOperateur.size();
		String[] test = new String[a];
		for (int i = 0; i < a; i++) {
			test[i] = listeOperateur.get(i).toString();
		}
		JComboBox ope = new JComboBox(listeOperateurString.toArray(new String[listeOperateurString.size()]));
		Object[] message = { "Reference : ", ref, "Nom : ", libelle, "Prix : ", prix, "Operateur :", ope };
		if(listeOperateurString.isEmpty()){
			  final JPanel panel = new JPanel();
			  JOptionPane.showMessageDialog(panel, "La liste des opérateurs est vide, veuillez tout d'abord en ajouter!", "Erreur", JOptionPane.ERROR_MESSAGE);
			return null;
		}else{
			JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
			if(methode==false)
			{
				option = JOptionPane.showConfirmDialog(null, message, "Ajout d'un telephone compatible",
						JOptionPane.OK_CANCEL_OPTION);	
			}
			else{
				option = JOptionPane.showConfirmDialog(null, message, "Ajout d'un telephone",
						JOptionPane.OK_CANCEL_OPTION);
			}
	
			if (option != JOptionPane.OK_CANCEL_OPTION) {
				if (ref.getText().isEmpty() || libelle.getText().isEmpty() || prix.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Un ou plusieurs champ sont restés vides!");
				} else {
					tel = new Telephone(ref.getText(), libelle.getText(), Double.parseDouble(prix.getText()),
							recupOpe(ope.getSelectedItem().toString()));
					if(methode==true){
						listeArticles.ajouterArticle(tel);
						initJList();
					}
				}
	
			}
			optionsClear();
			return tel;
		}
	}
	
	private Operateur recupOpe(String opeSelectionne) {
		for (int i = 0; i < listeOperateur.size(); i++) {
			Operateur o = (Operateur) listeOperateur.get(i);
			if (o.getLibelle().equalsIgnoreCase(opeSelectionne)) {
				return o;
			}
		}
		return null;
	}

	private void optionsClear() {
		ref.setText("");
		libelle.setText("");
		prix.setText("");
	}

	KeyListener keyListener = new KeyListener() {
		public void keyPressed(KeyEvent keyEvent) {
			c = keyEvent.getKeyChar();
		}

		public void keyReleased(KeyEvent keyEvent) {
		}

		public void keyTyped(KeyEvent keyEvent) {
			Object source = keyEvent.getSource();

			if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE))) {
				if ((source == prix && c == '.' || source == reponseCoque) && virgule != true) {
					if (c == '.') {
						virgule = true;
					}
				} else {
					keyEvent.consume();
				}
			}
		}
	};

	/*
	 * -------------------------------------------------------------------------
	 * -------
	 */
}

