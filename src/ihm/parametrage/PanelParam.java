package ihm.parametrage;

import javax.swing.*;

import java.util.*;

import metier.Statut;
import metier.db.Requetes;

import ihm.accueil.FrameAccueil;
import ihm.accueil.PanelAcceuil;

import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent		;
import java.awt.event.ActionListener	;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class PanelParam extends JPanel implements ActionListener{

	private ArrayList<String> listStatut;
	private ArrayList<String> listCoef;

	private FrameAccueil      frame;
	private JPanel            panelG;
	private JPanel            panelD;
	private JPanel            panelSud;

	private JButton           btnAjoutStat;
	private JButton           btnAjoutCoef;
	private JButton           btnSuppStat;
	private JButton           btnSuppCoef;
	private JButton           btnRetour;
	private JButton           btnEnregistrer;

	private JTable            tableauStatut;
	private JTable            tableauCoef;
	private DefaultTableModel dtmStatut;
	private DefaultTableModel dtmCoef;

	public PanelParam(FrameAccueil frame) {
		this.frame      = frame;
		this.listStatut = new ArrayList<String>();
		this.listCoef   = new ArrayList<String>();

		this.setLayout(new BorderLayout());
		this.frame.setSize(1000, 600);

		// Creation des éléments de la page 
		this.dtmStatut      = new DefaultTableModel();
		this.dtmCoef        = new DefaultTableModel();
		this.panelG         = new JPanel();
		this.panelD         = new JPanel();
		this.panelSud       = new JPanel();

		this.btnAjoutStat   = new JButton("Ajouter");
		this.btnAjoutCoef   = new JButton("Ajouter");
		this.btnSuppStat    = new JButton("Supprimer");
		this.btnSuppCoef    = new JButton("Supprimer");
		this.btnRetour      = new JButton("Retour");
		this.btnEnregistrer = new JButton("Enregistrer");


		// Creation des tableau
		this.dtmStatut.addColumn("Nom");
		this.dtmCoef  .addColumn("Coeff");

		this.tableauStatut  = new JTable(this.dtmStatut);
		this.tableauCoef    = new JTable(this.dtmCoef);
		
		// Creation des scrollpane
		JScrollPane scrollStatut = new JScrollPane(this.tableauStatut);
		scrollStatut.setPreferredSize(new Dimension(300, 300));
		JScrollPane scrollCoef   = new JScrollPane(this.tableauCoef);
		scrollCoef.setPreferredSize(new Dimension(300, 300));

		// Creation sous panels
		JPanel panelTableauG = new JPanel();
		JPanel panelTableauD = new JPanel();
		JPanel panelBtnG     = new JPanel();
		JPanel panelBtnD     = new JPanel();

		this.panelG.setLayout(new BorderLayout());
		this.panelD.setLayout(new BorderLayout());
		
		panelTableauG.add(scrollStatut);
		panelTableauD.add(scrollCoef);

		panelBtnG.add(this.btnAjoutStat);
		panelBtnG.add(this.btnSuppStat);

		panelBtnD.add(this.btnAjoutCoef);
		panelBtnD.add(this.btnSuppCoef);

		// Ajout dans panel gauche
		this.panelG.add(panelTableauG, BorderLayout.NORTH);
		this.panelG.add(panelBtnG);

		// Ajout dans panel droit
		this.panelD.add(panelTableauD, BorderLayout.NORTH);
		this.panelD.add(panelBtnD);

		//Ajout dans panel retour
		this.panelSud.add(this.btnRetour);
		this.panelSud.add(this.btnEnregistrer);

		// Ajout des panels
		this.add(this.panelG   , BorderLayout.WEST);
		this.add(this.panelD   , BorderLayout.EAST);
		this.add(this.panelSud , BorderLayout.SOUTH);

		// Activation des boutons 
		this.btnAjoutStat  .addActionListener(this);
		this.btnAjoutCoef  .addActionListener(this);
		this.btnSuppStat   .addActionListener(this);
		this.btnSuppCoef   .addActionListener(this);
		this.btnRetour     .addActionListener(this);
		this.btnEnregistrer.addActionListener(this);

		this.init();

		this.setVisible(true);
	}


	public void init() {
		List<Statut> statuts = this.frame.getControleur().getCtrl().metier().getStatuts();

		for (Statut statut : statuts)
			this.ajouterStatut(statut);

	}

	public void afficheCoeff() {
		
	}


	// Ajout des statut
	private void ajouterStatut(Statut statut) {
		Object[] objs = {statut.getNomStatut()};
		this.dtmStatut.addRow(objs);
		System.out.println("taille: " + listStatut.size());
	}

	// Ajout des coef
	private void ajouterCoef() {
		Object[] objs = {"0.0"};
		this.dtmCoef.addRow(objs);
	}

	// Supprimer Statut
	public void supprimerStatut() {
		int ligneSelectionne = this.tableauStatut.getSelectedRow();
		
		if (ligneSelectionne != -1) {
			String valeurASupp = (String) this.dtmStatut.getValueAt(ligneSelectionne, 0);
			this.dtmStatut.removeRow(ligneSelectionne);
	
			if (!this.listStatut.isEmpty() && this.listStatut.contains(valeurASupp)) {
				this.listStatut.remove(valeurASupp);
			}
		}
	
		System.out.println("taille : " + listStatut.size());
		System.out.println(this.listStatut);
	}
	

	// Supprimer coef
	public void supprimerCoef(){
		int ligneSelectionne = this.tableauCoef.getSelectedRow();
		
		if (ligneSelectionne != -1) {
			String valeurASupp = (String) this.dtmCoef.getValueAt(ligneSelectionne, 0);
			this.dtmCoef.removeRow(ligneSelectionne);
	
			if (!this.listCoef.isEmpty() && this.listCoef.contains(valeurASupp)) {
				this.listCoef.remove(valeurASupp);
			}
		}	
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btnAjoutStat){
			this.ajouterStatut(null);
		}

		if(e.getSource() == this.btnAjoutCoef){
			ajouterCoef();
		}

		if(e.getSource() == this.btnSuppStat){
			supprimerStatut();
		}

		if(e.getSource() == this.btnSuppCoef){
			supprimerCoef();
		}

		// Ajout des valeurs dans listStatut et listCoef quand on clique sur Enregistrer
		if(e.getSource() == this.btnEnregistrer){
			
			for (int i = 0; i < this.dtmStatut.getRowCount(); i++) {
				if(this.dtmStatut.getValueAt(i, 0) != ""){
					if(!this.listStatut.contains((String) this.dtmStatut.getValueAt(i, 0))){
						this.listStatut.add((String) this.dtmStatut.getValueAt(i, 0));
					}
				}
				System.out.println("enreg: " + listStatut);	
			}
				
			for (int i = 0; i < this.dtmCoef.getRowCount(); i++) {
				if(this.dtmCoef.getValueAt(i, 0) != "0.0"){
					if(!this.listCoef.contains((String) this.dtmCoef.getValueAt(i, 0))){
						this.listCoef.add((String)this.dtmCoef.getValueAt(i, 0));
					}
				}	
			}			
		}

		if(e.getSource() == this.btnRetour){
			this.frame.changerPanel(new PanelAcceuil(frame));
			this.frame.setSize(350, 200);
		}
	}

	public ArrayList<String> getListStatut(){
		return this.listStatut;
	}
}
