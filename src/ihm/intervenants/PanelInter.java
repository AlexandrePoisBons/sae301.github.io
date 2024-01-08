package ihm.intervenants;

//Imports classes externes
import ihm.accueil.FrameAccueil;
import metier.Intervenant;
import metier.Statut;
import ihm.accueil.PanelAcceuil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

public class PanelInter extends JPanel implements ActionListener {
	private FrameAccueil frame;

	private JPanel panelPrincipal;
	private FrameFormulaire panelFormulaire;
	private JPanel panelBtn;
	private JButton btnAjouter;
	private JButton btnAnnuler;
	private JButton btnSupprimer;
	private JButton btnEnregistrer;
	private JTable tableauInter;
	private DefaultTableModel dtm;
	private JLabel lblErreur;

	private List<Intervenant> intervenants;

	public PanelInter( FrameAccueil frAcceuil ) {
		// Synchronisation des pages
		this.frame = frAcceuil;

		//this.intervenants = new ArrayList<>();

		List<Intervenant> list = this.frame.getControleur().getCtrl().metier().getIntervenants();
		this.intervenants = new ArrayList<>(list);


		// Définition de la taille et la position de la fenêtre
		int hauteur = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()
		            - (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.05);
		int largeur = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int xSize = (int) (largeur * 0.70);
		int ySize = (int) (hauteur * 0.5);
		this.frame.setSize(xSize, ySize);
		this.frame.setLocation((int) (largeur * 0.5 - xSize * 0.5), (int) (hauteur * 0.5 - ySize * 0.5));
		this.setLayout(new BorderLayout());

		JPanel panelAjSup = new JPanel();
		this.panelPrincipal = new JPanel();
		this.panelBtn = new JPanel(new FlowLayout(FlowLayout.LEFT));
		this.panelPrincipal.setLayout(new BorderLayout());

		this.btnAjouter = new JButton("ajouter");
		this.btnSupprimer = new JButton("supprimer");
		this.btnEnregistrer = new JButton("enregistrer");
		this.btnAnnuler = new JButton("annuler");
		this.lblErreur = new JLabel("");

		//Coloration du label d'erreurs en rouge
		this.lblErreur.setForeground(java.awt.Color.RED);

		this.dtm = new DefaultTableModel(){
			@Override
			public boolean isCellEditable(int row, int col){
				return false;
			}
		};

		this.dtm.addColumn("Categorie");
		this.dtm.addColumn("Nom");
		this.dtm.addColumn("Prenom");
		this.dtm.addColumn("hServ");
		this.dtm.addColumn("hMax");
		this.dtm.addColumn("coefTp");
		this.dtm.addColumn("S1");
		this.dtm.addColumn("S3");
		this.dtm.addColumn("S5");
		this.dtm.addColumn("sTot");
		this.dtm.addColumn("S2");
		this.dtm.addColumn("S4");
		this.dtm.addColumn("S6");
		this.dtm.addColumn("sTot");
		this.dtm.addColumn("Total");

		this.tableauInter = new JTable(this.dtm);

		JScrollPane scroll = new JScrollPane(this.tableauInter);
		scroll.setPreferredSize(new Dimension(1000, 400));

		panelAjSup.add(this.btnAjouter);
		panelAjSup.add(this.btnSupprimer);

		this.panelPrincipal.add(new JLabel("Liste des intervenants"), BorderLayout.NORTH);
		this.panelPrincipal.add(scroll, BorderLayout.CENTER);
		this.panelPrincipal.add(panelAjSup, BorderLayout.SOUTH);

		this.panelBtn.add(this.btnEnregistrer);
		this.panelBtn.add(this.btnAnnuler);
		this.panelBtn.add(this.lblErreur, FlowLayout.RIGHT);

		this.add(this.panelPrincipal, BorderLayout.CENTER);
		this.add(this.panelBtn, BorderLayout.SOUTH);

		this.btnAjouter.addActionListener(this);
		this.btnSupprimer.addActionListener(this);
		this.btnEnregistrer.addActionListener(this);
		this.btnAnnuler.addActionListener(this);

		this.init();

		this.setVisible(true);
		this.frame.setTitle("Liste des intervenants");
	}

	public void init() {
		String categorie, nom, prenom;
		Float nbHeures, nbHeuresMax;
		Float coeff;
		Float s1, s2, s3, s4, s5, s6;
		Float totPair, totImpair, tot;

		Object[] vals = new Object[15];
		for (Intervenant intervenant : this.intervenants) {
			HashMap<String, Float> map = intervenant.getNbHeuresParSemestre();
			categorie = intervenant.getStatut().getNomStatut();
			nom = intervenant.getNom();
			prenom = intervenant.getPrenom();
			nbHeures = intervenant.getNbHeures();
			nbHeuresMax = intervenant.getNbEqTD();
			coeff = intervenant.getStatut().getCoeffTP();
			s1 = map.get("S1");
			s2 = map.get("S2");
			s3 = map.get("S3");
			s4 = map.get("S4");
			s5 = map.get("S5");
			s6 = map.get("S6");
			totPair = s2 + s4 + s6;
			totImpair = s1 + s3 + s5;
			tot = totPair + totImpair;

			vals[0] = categorie; // categorie
			vals[1] = nom; // nom
			vals[2] = prenom; // prenom
			vals[3] = nbHeures; // heures de service
			vals[4] = nbHeuresMax; // heures max
			vals[5] = coeff; // coeff TP
			vals[6] = s1; // S1
			vals[7] = s3; // S3
			vals[8] = s5; // S5
			vals[9] = totImpair; // sTot : total des semestes impaires
			vals[10] = s2; // S2
			vals[11] = s4; // S4
			vals[12] = s6; // S6
			vals[13] = totPair; // sTot : total des semestres pairs
			vals[14] = tot; // Total des semestres
			this.ajouterLigne(vals);
		}

	}

	public void supprimer() {
		int lig = this.tableauInter.getSelectedRow();
		
		if ( this.intervenants.get(lig).getHeures().size() > 0 ) {
			this.lblErreur.setText("Impossible de supprimer cet Intervenant");
		} else {
			this.dtm.removeRow(lig);
			this.intervenants.remove(lig); // on est baisé: ca change l'objet qui est stocké que sous forme d'adresse memoire
			this.lblErreur.setText("");
		}

	}

	public void actionPerformed(ActionEvent e) {
		this.lblErreur.setText("");

		if (e.getSource() == this.btnAjouter) {
			this.panelFormulaire = new FrameFormulaire(this);
			this.lblErreur.setText("");
			this.panelFormulaire.setVisible(true);
			this.panelFormulaire.repaint();
		}

		if (e.getSource() == this.btnSupprimer) {
			try {
				this.supprimer();
			} catch (Exception ex) {
				this.lblErreur.setText("Aucune ligne à supprimer");
			}
			this.repaint();
			this.revalidate();
		}

		if (e.getSource() == this.btnEnregistrer) {
			this.enregistrer();
			this.frame.changerPanel(new PanelAcceuil(this.frame));
		}

		if (e.getSource() == this.btnAnnuler) {
			this.frame.changerPanel(new PanelAcceuil(frame));
		}

	}


	// Méthode permettant d'ajouter les nouveaux intervenants à la bd 

	private void enregistrer() {

		int i = this.intervenants.size();

		for (Intervenant intervenant : this.frame.getControleur().getCtrl().metier().getIntervenants()) {
			if ( !this.intervenants.contains(intervenant) ) {
				try {
					this.frame.getControleur().getCtrl().metier().supprimerIntervenant(intervenant);
				} catch (SQLException e) { }
			}
		}

		Intervenant tmp;
		for (int j = 0; j < i; j++) {
			tmp = this.intervenants.get(j);
			if ( tmp.getIdIntervenant() == -1 ) {
				Intervenant inter = Intervenant.creerIntervenant(tmp.getPrenom(), tmp.getNom(), tmp.getStatut(), tmp.getNbEqTD());
				this.intervenants.remove(tmp);
				//this.intervenants.add(inter);
				this.frame.getControleur().getCtrl().metier().ajouterIntervenant(inter);
			}
		}

	}


	public void ajouterLigne(Object[] values) {
		this.dtm.addRow(values);
		this.repaint();
		this.revalidate();
	}

	public List<Statut> getStatuts() { return this.frame.getControleur().getCtrl().metier().getStatuts(); }

	public void ajouterIntervenant( Intervenant intervenant ) {
		this.intervenants.add(intervenant);
	}

	public void ajouterLigne(String statut, String nom, String prenom, Integer hServ, Integer hMax, Float coeff) {
		Object[] objs = new Object[15];
		objs[0] = statut;
		objs[1] = nom;
		objs[2] = prenom;
		objs[3] = Float.parseFloat(""+hServ);
		objs[4] = Float.parseFloat(""+hMax);
		objs[5] = coeff;
		objs[6] = 0.0f; objs[7] = 0.0f; objs[8] = 0.0f; objs[9] = 0.0f; objs[10] = 0.0f; objs[11] = 0.0f; objs[12] = 0.0f; objs[13] = 0.0f; objs[14] = 0.0f;
		this.ajouterLigne(objs);
	}

}
