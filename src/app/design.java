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
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import classes.Accessoire;
import classes.Article;
import classes.Chargeur;
import classes.Coque;
import classes.Cordon;
import classes.ListeArticle;
import classes.Operateur;
import classes.Telephone;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	
	private JButton btnAjoutAccessoire;
	private JButton btnAjoutTelephone;
	private JButton btnAjoutOperateur;
	
   JTextField ref = new JTextField();
   JTextField libelle = new JTextField();
   JTextField prix = new JTextField();
   JTextField compatible = new JTextField();
   JComboBox operateur = new JComboBox();

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
	   Object source = evt.getSource();
	   if (source == btnSauvegarder){
/* -------------------------------------------------------------------------------- */
			try {
				listeArticles.sauvegarde("save.txt");
				JOptionPane.showMessageDialog(btnSauvegarder,
	                 "Fichier sauvegardé!");
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(btnSauvegarder,
	                 "Une Erreur est survenu : "+e.getMessage());
			}
	   }else if(source == btnAjoutAccessoire){

		Object[] message = {
				    "Reference de l'article: ", ref,
				    "Libelle : ", libelle,
				    "Prix : " , prix,
				    "Telephone compatible(s) :" ,compatible
				};
				int option = JOptionPane.showConfirmDialog(null, message, "Ajout d'un accessoire", JOptionPane.OK_CANCEL_OPTION);
				
	   }else if(source == btnAjoutTelephone){

				int a=listeOperateur.size();
				String[] test= new String[a];
				for(int i=0;i<a;i++){
					test[i]=listeOperateur.get(i).toString();
				}
				Object[] message = {
					    "Reference : ", ref,
					    "Nom : ", libelle,
					    "Prix : " , prix,
					    "Operateur :" ,""
					};
				
			    JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
			    String nom = (String)jop.showInputDialog(null,message,"rrrrr",JOptionPane.QUESTION_MESSAGE,null,test,a);
				
				if(ref.getText().isEmpty()||libelle.getText().isEmpty()||prix.getText().isEmpty()){
					JOptionPane.showMessageDialog(null,
	                        "Un ou plusieurs champ sont vides!");
				}
				else{
					Telephone tel = new Telephone(ref.getText(),libelle.getText(),Float.parseFloat(prix.getText()),new Operateur(1,"free"));
					listeTelephone.add(tel);
				}
				
				
	   }else if(source == btnAjoutOperateur){
		   Object[] message = {
				    "Identifiant : ", ref,
				    "Nom : ", libelle,
				}; 
		   int option = JOptionPane.showConfirmDialog(null, message, "Ajout d'un operateur", JOptionPane.OK_CANCEL_OPTION);
		   if(ref.getText().isEmpty()||libelle.getText().isEmpty()){
				JOptionPane.showMessageDialog(null,
                    "Un ou plusieurs champ sont vides!");
			}
			else{
				Operateur op = new Operateur(Integer.parseInt(ref.getText()),libelle.getText());
				listeOperateur.add(op.getLibelle());
			}
		   ref.setText("");
		   libelle.setText("");
	   }
		 
/* -------------------------------------------------------------------------------- */
	}
}
