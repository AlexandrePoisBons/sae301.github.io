package ihm.intervenants;

//Imports classes externes
import ihm.accueil.FrameAccueil;
import metier.Intervenant;
import ihm.accueil.PanelAcceuil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

public class PanelInter extends JPanel implements ActionListener {
	private FrameAccueil frame;

	private JPanel panelPrincipal;
	private JPanel panelBtn;
	private JButton btnAjouter;
	private JButton btnAnnuler;
	private JButton btnSupprimer;
	private JButton btnEnregistrer;
	private JTable tableauInter;
	private DefaultTableModel dtm;

	public PanelInter(FrameAccueil frAcceuil) {
		// Synchronisation des pages
		this.frame = frAcceuil;

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

		this.dtm = new DefaultTableModel();

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
		List<Intervenant> list = this.frame.getControleur().getCtrl().metier().getIntervenants();
		String categorie, nom, prenom;
		Float nbHeures, nbHeuresMax, coeff;
		Float s1, s2, s3, s4, s5, s6;
		Float totPair, totImpair, tot;

		Object[] vals = new Object[15];
		for (Intervenant intervenant : list) {
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

	public void ajouterLigne(Object[] values) {
		this.dtm.addRow(values);
	}

	public void ajouter() {
		TableColumn tc = this.tableauInter.getColumnModel().getColumn(0);
		tc.setCellEditor(new ComboBoxCellEditor());
		Object[] objs = { "", "", "", 0, 0, "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0" };
		this.dtm.addRow(objs);
	}

	public void supprimer() {
		this.dtm.removeRow(this.tableauInter.getSelectedRow());
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnAjouter) {
			new FrameFormulaire(this);
			// this.ajouter();
		}

		if (e.getSource() == this.btnSupprimer) {
			this.supprimer();
		}

		if (e.getSource() == this.btnEnregistrer) {
			boolean bOk = true;
			for (int i = 0; i < this.dtm.getRowCount() && bOk; i++) {
				for (int j = 0; j < this.dtm.getColumnCount() && bOk; j++) {
					if (j < 3) {
						if (!(this.dtm.getValueAt(i, j) instanceof String)) {
							bOk = false;
							System.out.println("String faux");
						}
					}

					if (j == 3 || j == 4) {
						if (!(this.dtm.getValueAt(i, j) instanceof Integer)) {
							bOk = false;
							System.out.println("int faux");
						}
					}
					if (j > 4 && j < this.dtm.getColumnCount()) {
						if (!(this.dtm.getValueAt(i, j) instanceof Float)) {
							bOk = false;
							System.out.println("float faux");
						}
					}
				}
			}
			if (bOk) {
				System.out.println("Tout bon");
			}
		}

		if (e.getSource() == this.btnAnnuler) {
			this.frame.changerPanel(new PanelAcceuil(frame));
		}
	}

	public List<Intervenant> getIntervenants() {
		return this.frame.getControleur().getCtrl().metier().getIntervenants();
	}

	public void ajouterLigne(String categorie, String nom, String prenom, String hServ, String hMax, String coeff) {
		List<Intervenant> list = this.frame.getControleur().getCtrl().metier().getIntervenants();
		Float s1 = (float) 0.0;
		Float s2 = (float) 0.0;
		Float s3 = (float) 0.0;
		Float s4 = (float) 0.0;
		Float s5 = (float) 0.0;
		Float s6 = (float) 0.0;
		Float totPair = (float) 0.0;
		Float totImpair = (float) 0.0;
		Float tot = (float) 0.0;
		
		Object[] vals = new Object[15];
		for (Intervenant intervenant : list) {
			if (intervenant.getStatut().getNomStatut() == nom) {
				// calculer les semestres
				s1 = intervenant.getNbHeuresParSemestre().get("S1");
				s2 = intervenant.getNbHeuresParSemestre().get("S2");
				s3 = intervenant.getNbHeuresParSemestre().get("S3");
				s4 = intervenant.getNbHeuresParSemestre().get("S4");
				s5 = intervenant.getNbHeuresParSemestre().get("S5");
				s6 = intervenant.getNbHeuresParSemestre().get("S6");
				totPair = s2 + s4 + s6;
				totImpair = s1 + s3 + s5;
				tot = totPair + totImpair;
			}

			vals[0] = categorie; // categorie
			vals[1] = nom; // nom
			vals[2] = prenom; // prenom
			vals[3] = hServ; // heures de service
			vals[4] = hMax; // heures max
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
}
