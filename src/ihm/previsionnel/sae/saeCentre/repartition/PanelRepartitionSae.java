package ihm.previsionnel.sae.saeCentre.repartition;

//Imports classes Java
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

//Imports classes externes
import ihm.previsionnel.sae.saeCentre.PCentreSae;
import ihm.previsionnel.sae.saeCentre.repartition.heureSae.PanelRepH;
import metier.Heure;
import metier.Intervenant;
import metier.TypeHeure;
import metier.Module;

public class PanelRepartitionSae extends JPanel implements ActionListener {
	private PCentreSae panelMere;
	private PanelRepH panelRepH;
	private PanelAffectSae panelAffect;
	private JButton btnAjouter;
	private JButton btnSuppr;
	private FrameFormulaire frameFormulaire;

	private Module module;

	public PanelRepartitionSae(PCentreSae panelMere, Module m) {
		this.panelMere = panelMere;
		this.module = m;

		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(0, 5, 0, 10));

		JPanel panelBtn = new JPanel();

		this.panelRepH = new PanelRepH(this, this.module);
		this.panelAffect= new PanelAffectSae(this, this.module);
		this.btnAjouter = new JButton("Ajouter");
		this.btnSuppr = new JButton("Supprimer");

		//Ajout des boutons au panel de boutons
		panelBtn.add(this.btnAjouter);
		panelBtn.add(this.btnSuppr);

		this.add(this.panelRepH   , BorderLayout.NORTH);
		this.add(this.panelAffect , BorderLayout.CENTER);
		this.add(panelBtn         , BorderLayout.SOUTH);

		//Ajout des
		this.btnAjouter.addActionListener(this);
		this.btnSuppr.addActionListener(this);
	}

	public void setLabelErreur(String message) { this.panelMere.setLabelErreur(message); }

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btnAjouter) {
			this.frameFormulaire = new FrameFormulaire(this,this.module);
			this.setErreur("");
		}
		if(e.getSource() == this.btnSuppr) {
			this.panelAffect.supprimer();
		}
	}

	public HashMap<String, HashMap<String, Integer>> getDataHeuresTypesHeures() {
		return this.panelRepH.getDataHeuresTypesHeures();
	}

	public HashMap<String,Integer> getNbSemaines() { return this.panelRepH.getNbSemaines(); }
	public HashMap<String,Integer> getData() { return this.panelMere.getData(); }
	public List<Intervenant> getIntervenants() { return this.panelMere.getIntervenants(); }
	public List<TypeHeure> getTypesHeures() { return this.panelMere.getTypesHeures(); }
	public List<Heure> getDeletedHeures() { return this.panelAffect.getDeletedHeures(); }
	public List<Heure> getHeures() { return this.panelAffect.getDataHeures(); }
	public int getSommeAffecte() { return this.panelRepH.getSommeAffecte(); }
	public HashMap<TypeHeure, HashMap<String,Integer>> getHeuresParTypesHeures(Module module) { return this.panelMere.getHeuresParTypesHeures(module); }

	public void setHeures(List<Heure> heures) { this.panelAffect.setHeures(heures); }
	public void ajouterHeure(Heure heure) { this.panelAffect.ajouterHeure(heure); }
	public void fermerFrameFormulaire() { if (this.frameFormulaire != null) this.frameFormulaire.dispose(); }

	public void setErreur(String message) {
		this.panelMere.setErreur(message);
	}

	public void setHeureAffecte() {
		System.out.println("la");
		DefaultTableModel dtm = this.panelAffect.getDtm();
		JTable tableauAffect  = this.panelAffect.getTableauAffect();
		int hSae = 0;
		int hTut = 0;
		for(int i=0;i<tableauAffect.getRowCount();i++) {
			if(dtm.getValueAt(i, 1).equals("SAE")){
				hSae += Float.parseFloat(dtm.getValueAt(i, 2).toString());
			}
			if(dtm.getValueAt(i, 1).equals("TUT")){
				hTut += Float.parseFloat(dtm.getValueAt(i, 2).toString());
			}
		}
		this.panelRepH.setHeureAffecte(hSae, hTut);
	}

	public void actualiserSomme() {
		this.panelRepH.actualiserSomme();
	}

}
