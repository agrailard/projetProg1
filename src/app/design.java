package app;

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

public class design implements ActionListener {

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
	
	ArrayList listeTelephone = new ArrayList<Telephone>();
	ArrayList listeOperateur = new ArrayList<Operateur>();
	ArrayList listeOperateurString = new ArrayList<String>();
	ArrayList type = new ArrayList<String>();
	ArrayList listTelcompa = new ArrayList<Telephone>();
	
	private JButton btnAjoutAccessoire;
	private JButton btnAjoutTelephone;
	private JButton btnAjoutOperateur;
	
	JTextField ref = new JTextField();
	JTextField libelle = new JTextField();
	JTextField prix = new JTextField();
	JTextField compatible = new JTextField();
	JComboBox operateur = new JComboBox();
	JTextField reponse = new JTextField();
	JTextField reponseCoque = new JTextField();
	JComboBox listeTel;;
   

	char c;
	boolean virgule = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					design window = new design();
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
	public design() {
		initListeArticles("int");
		initJList();
		initialize();
		
		btnTrierParRfrence.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				initListeArticles("ref");
				initJList();
			}
		});
		btnTrierParPrix.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				initListeArticles("prix");
				initJList();
			}
		});
		btnTrierParNom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				initListeArticles("int");
				initJList();
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
	
	/*public String classeArticle(Article unArticle) {
		try {
			if(unArticle.getClass()==Class.forName("Telephone")){
				return "Opérateur";
			}
			else if (unArticle.getClass()==Class.forName("Accessoire")) {
				return "Liste des téléphones";
			}
			else
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Inventaire des articles");
		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menu = new JMenuBar();
		frame.setJMenuBar(menu);
		
		btnAjoutAccessoire = new JButton("Ajouter un accessoire");
		btnAjoutAccessoire.addActionListener(this);
		menu.add(btnAjoutAccessoire);
		
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
		
		labelTxtRef.setText(listeArticles.getArticle(listeDeroul.getSelectedIndex()).getReference());
		labelTxtRef.setBounds(92, 11, 475, 14);
		panel.add(labelTxtRef);
		
		labelTxtInt.setText(listeArticles.getArticle(listeDeroul.getSelectedIndex()).getIntitule());
		labelTxtInt.setBounds(92, 36, 475, 14);
		panel.add(labelTxtInt);
		
		labelTxtPrix.setText(Double.toString(listeArticles.getArticle(listeDeroul.getSelectedIndex()).getPrix()));
		labelTxtPrix.setBounds(92, 61, 475, 14);
		panel.add(labelTxtPrix);
		
		lblAccessoire.setBounds(10, 86, 72, 14);
		panel.add(lblAccessoire);
		
		lblTxtAccessoire.setBounds(92, 86, 475, 14);
		panel.add(lblTxtAccessoire);
	}
	/**
	 * Initialise les données des articles (pour les tests)
	 * @param tri : si égal à "ref" tri par références,
	 * si égal à "prix" tri par prix et
	 * si égal à "int", tri par nom
	 * si égal à autre chose : ne se passe rien
	 */
	private void initListeArticles(String tri){
		listeArticles.vider();
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
		Cordon cordon = new Cordon(new Accessoire("123","Cordon High Performance",10,listL2),25);
		Coque coque = new Coque(new Accessoire("01","Coque Mini",17.8,listL3),"Rouge");
		
		listeArticles.ajouterArticle(tel1);
		listeArticles.ajouterArticle(tel2);
		listeArticles.ajouterArticle(tel3);
		listeArticles.ajouterArticle(tel4);
		listeArticles.ajouterArticle(tel5);
		listeArticles.ajouterArticle(chargeur);
		listeArticles.ajouterArticle(chargeurNokia);
		listeArticles.ajouterArticle(cordon);
		
		if(tri=="ref") listeArticles.tousLesArticles_ParRef();
		else if (tri=="int") listeArticles.tousLesArticles_ParIntitule();
		else if (tri=="prix") listeArticles.tousLesArticles_ParPrix();
		
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

			try {
				listeArticles.enregistreXml("inventaire.xml");
				listeArticles.sauvegarde("save.txt");
				/*
				 * *************************************************************
				 * *******************
				 */
				JOptionPane.showMessageDialog(btnSauvegarder, "Fichier sauvegardï¿½!");
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
												Double.parseDouble(prix.getText()), utilisateurAjouterTel()),
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
												Double.parseDouble(prix.getText()), utilisateurAjouterTel()),
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
												Double.parseDouble(prix.getText()), utilisateurAjouterTel()),
										reponseCoque.getText()));
					}
				}
			}
			optionsClear();
			initJList();
		} else if (source == btnAjoutTelephone) {
			virgule = false;
			int a = listeOperateur.size();
			String[] test = new String[a];
			for (int i = 0; i < a; i++) {
				test[i] = listeOperateur.get(i).toString();
			}
			JComboBox ope = new JComboBox(listeOperateurString.toArray(new String[listeOperateurString.size()]));
			Object[] message = { "Reference : ", ref, "Nom : ", libelle, "Prix : ", prix, "Operateur :", ope };

			JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
			int option = JOptionPane.showConfirmDialog(null, message, "Ajout d'un telephone",
					JOptionPane.OK_CANCEL_OPTION);
			if (option != JOptionPane.OK_CANCEL_OPTION) {
				if (ref.getText().isEmpty() || libelle.getText().isEmpty() || prix.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Un ou plusieurs champ sont restés vides!");
				} else {
					Telephone tel = new Telephone(ref.getText(), libelle.getText(), Double.parseDouble(prix.getText()),
							recupOpe(ope.getSelectedItem().toString()));
					listeArticles.ajouterArticle(tel);
					listeTelephone.add(tel);
				}
			}
			optionsClear();
			initJList();
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

	private ArrayList<Telephone> utilisateurAjouterTel() {

		int option = JOptionPane.OK_OPTION;
		listeTel = new JComboBox(listeTelephone.toArray(new Telephone[listeTelephone.size()]));
		JOptionPane jop = new JOptionPane();
		while (option == JOptionPane.OK_OPTION) {
			if (listeTel.getItemCount() == 0) {
				option = jop.showConfirmDialog(null,
						"Vous avez dï¿½jï¿½ ajoutï¿½ tous les tï¿½lï¿½phones de la liste, veuillez ajouter un nouveau telephone avant de conitnuer.",
						"Ajout d'un telephone", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			} else {
				Object[] ajout = { "Telephone compatible(s) :", listeTel };
				jop.showConfirmDialog(null, ajout, "Ajout d'un telephone", JOptionPane.OK_CANCEL_OPTION);
				Object a = listeTel.getSelectedItem();
				listeTel.removeItem(a);
				option = jop.showConfirmDialog(null,
						"Un telephone a ï¿½tï¿½ ajoutï¿½, voulez-vous en ajouter un autre ?", "Ajout d'un telephone",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			}
		}
		return listTelcompa;
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

