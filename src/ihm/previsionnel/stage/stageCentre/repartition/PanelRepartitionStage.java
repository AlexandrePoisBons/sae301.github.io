package ihm.previsionnel.stage.stageCentre.repartition;

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
import ihm.previsionnel.stage.stageCentre.PCentreStage;
import ihm.previsionnel.stage.stageCentre.repartition.heureStage.PanelRepHStage;
import metier.Heure;
import metier.Intervenant;
import metier.Module;
import metier.TypeHeure;

public class PanelRepartitionStage extends JPanel implements ActionListener{
	private PCentreStage panelMere;
	private PanelRepHStage panelRepH;
	private PanelAffectStage panelAffect;
	private JButton btnAjouter;
	private JButton btnSuppr;
	FrameFormulaire frameFormulaire;

	private Module module;

	public PanelRepartitionStage(PCentreStage panelMere, Module m) {
		this.panelMere = panelMere;
		this.module = m;

		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(0, 5, 0, 0));

		JPanel panelBtn = new JPanel();

		this.panelRepH 	= new PanelRepHStage					(this)				;
		this.panelAffect	 		= new PanelAffectStage			(this, this.module)				;	
		this.btnAjouter 			= new JButton					("Ajouter")	;
		this.btnSuppr				= new JButton					("Supprimer")	;	

		//Ajout des boutons au panel de boutons
		panelBtn.add(this.btnAjouter)								;
		panelBtn.add(this.btnSuppr)									;

		this.add(this.panelRepH, BorderLayout.NORTH);
		this.add(this.panelAffect, BorderLayout.CENTER);
		this.add(panelBtn, BorderLayout.SOUTH);

		//Ajout des
		this.btnAjouter.addActionListener	(this);
		this.btnSuppr.addActionListener		(this);
	}
	public void setLabelErreur(String message) { this.panelMere.setLabelErreur(message); }


	//A DECOMMENTER
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btnAjouter){
			this.frameFormulaire = new FrameFormulaire(this, this.module);
			this.setErreur("");
		}
		if(e.getSource() == this.btnSuppr){
			this.panelAffect.supprimer();
		}
	}

	public HashMap<String,Integer> getData() { return this.panelMere.getData(); }
	public HashMap<String,Integer> getNbSemaines() { return this.panelRepH.getNbSemaines(); }
	public List<Intervenant> getIntervenants() { return this.panelMere.getIntervenants(); }
	public List<TypeHeure> getTypesHeures() { return this.panelMere.getTypesHeures(); }
	public List<Heure> getHeures() { return this.panelAffect.getDataHeures(); }
	public List<Heure> getDeletedHeures() { return this.panelAffect.getDeletedHeures(); }
	public int getSommeAffecte() { return this.panelRepH.getSommeAffecte(); }

	public void setHeures(List<Heure> heures) { this.panelAffect.setHeures(heures); }
	public void ajouterHeure(Heure heure) { this.panelAffect.ajouterHeure(heure); }
	public void fermerFrameFormulaire() { this.frameFormulaire.dispose(); }

	public void setErreur(String message) {
		this.panelMere.setErreur(message);
	}

	public void setHeureAffecte() {
		DefaultTableModel dtm = this.panelAffect.getDtm();
		JTable tableauAffect  = this.panelAffect.getTableauAffect();
		int hREH = 0;
		int hTut = 0;
		for(int i=0;i<tableauAffect.getRowCount();i++) {
			if(dtm.getValueAt(i, 1).equals("REH")){
				hREH += Float.parseFloat(dtm.getValueAt(i, 2).toString());
			}
			if(dtm.getValueAt(i, 1).equals("TUT")){
				hTut += Float.parseFloat(dtm.getValueAt(i, 2).toString());
			}
		}
		this.panelRepH.setHeureAffecte(hREH, hTut);
	}

	public void setSommeAffecte() {
		this.panelRepH.setSommeAffecte();
	}


}
