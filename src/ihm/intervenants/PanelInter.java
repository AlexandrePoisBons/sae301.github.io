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

		this.intervenants = this.frame.getControleur().getCtrl().metier().getIntervenants();


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
		this.intervenants.remove(this.tableauInter.getSelectedRow());
		this.dtm.removeRow(this.tableauInter.getSelectedRow());
		this.lblErreur.setText("");
		this.repaint();
		this.revalidate();
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
			} catch (Exception err) {
				this.lblErreur.setText("Il n'y a pas de ligne à supprimer");
				this.repaint();
				this.revalidate();
			}
		}

		if (e.getSource() == this.btnEnregistrer) {
			boolean bOk = true;
			for (int i = 0; i < this.dtm.getRowCount() && bOk; i++) {
				for (int j = 0; j < this.dtm.getColumnCount() && bOk; j++) {
					if (j < 3) {
						if (!(this.dtm.getValueAt(i, j) instanceof String)) {
							bOk = false;
							this.lblErreur.setText("Erreur, veuillez vérifier que le type de données est bien un STRING");
							this.repaint();
							this.revalidate();
						}
					}

					if (j == 3 || j == 4) {
						if (!(this.dtm.getValueAt(i, j) instanceof Integer)) {
							bOk = false;
							this.lblErreur.setText("Erreur, veuillez vérifier que le type de données est bien un INTEGER");
							this.repaint();
							this.revalidate();
						}
					}

					if (j > 4) {
						if (!(this.dtm.getValueAt(i, j) instanceof Float)) {
							System.out.println(this.dtm.getValueAt(i, j));
							bOk = false;
							this.lblErreur.setText("Erreur, veuillez vérifier que le type de données est bien un FLOAT");
							this.repaint();
							this.revalidate();
						}
					}
				}
			}

			if(bOk) {
				this.enregistrer();
				this.frame.changerPanel(new PanelAcceuil(this.frame));
			}
		}

		if (e.getSource() == this.btnAnnuler) {
			this.frame.changerPanel(new PanelAcceuil(frame));
		}
	}

	// Méthode permettant d'ajouter les nouveaux intervenants à la bd 

	private void enregistrer() {




	}


	public void ajouterLigne(Object[] values) {
		this.dtm.addRow(values);
		this.repaint();
		this.revalidate();
	}

	public List<Statut> getStatuts() { return this.frame.getControleur().getCtrl().metier().getStatuts(); }


	public void ajouterLigne(String categorie, String nom, String prenom, Integer hServ, Integer hMax, Float coeff) {

	}

}
