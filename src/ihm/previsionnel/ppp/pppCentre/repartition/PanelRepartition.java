package ihm.previsionnel.ppp.pppCentre.repartition;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ihm.previsionnel.ppp.pppCentre.PanelPppCentre;
import ihm.previsionnel.ppp.pppCentre.repartition.heure.PanelRepartitionHeure;
import metier.Heure;
import metier.Intervenant;
import metier.TypeHeure;
import metier.Module;

public class PanelRepartition extends JPanel implements ActionListener{
	private PanelPppCentre panelMere;
	private PanelRepartitionHeure panelRepartitionHeure;
	private PanelAffect panelAffect;
	private JButton btnAjouter;
	private JButton btnSuppr;

	private Module module;

	public PanelRepartition(PanelPppCentre panelMere, Module m) {
		this.panelMere = panelMere;
		this.module = m;

		JPanel pnlAlignementNordCentre = new JPanel();
		this.setLayout(new BorderLayout());
		pnlAlignementNordCentre.setLayout(new GridBagLayout());

		JPanel panelBoutons = new JPanel();

		this.panelRepartitionHeure 	= new PanelRepartitionHeure	(this)				;
		this.panelAffect	 		= new PanelAffect			(this, this.module)				;
		panelBoutons	 			= new JPanel				(    )				;
		this.btnAjouter 			= new JButton				("Ajouter")	;
		this.btnSuppr				= new JButton				("Supprimer")	;

		//Ajout des boutons au panel de boutons
		panelBoutons.add(this.btnAjouter)								;
		panelBoutons.add(this.btnSuppr)									;

		//Ajout des panels au panel principal
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 1, 0);
		pnlAlignementNordCentre.add		(this.panelRepartitionHeure, gbc)	;
		gbc.gridy = 1;
		pnlAlignementNordCentre.add		(this.panelAffect, gbc)				;
		this.add		(pnlAlignementNordCentre, BorderLayout.NORTH)	;
		this.add		(panelBoutons, BorderLayout.CENTER)				;

		//Ajout des
		this.btnAjouter.addActionListener(this);
		this.btnSuppr.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btnAjouter){
			new FrameFormulaire(this, this.module);
			this.setErreur("");
		}
		if(e.getSource() == this.btnSuppr){
			this.panelAffect.supprimer();
		}
	}

	public HashMap<String,Integer> getData() { return this.panelMere.getData(); }
	public HashMap<String,Integer> getNbSemaines() { return this.panelRepartitionHeure.getNbSemaines(); }
	public List<Intervenant> getIntervenants() { return this.panelMere.getIntervenants(); }
	public List<TypeHeure> getTypesHeures() { return this.panelMere.getTypesHeures(); }
	public List<Heure> getHeures() { return this.panelAffect.getDataHeures(); }
	public int getSommeAffecte() { return this.panelRepartitionHeure.getSommeAffecte(); }
	public List<Heure> getDeletedHeures() { return this.panelAffect.getDeletedHeures(); }

	public void setHeures(List<Heure> heures) { this.panelAffect.setHeures(heures); }
	public void ajouterHeure(Heure heure) { this.panelAffect.ajouterHeure(heure); }

	public void setErreur(String message) {
		this.panelMere.setErreur(message);
	}

	public void setLabelErreur(String message) { this.panelMere.setLabelErreur(message); }


	public void setHeureAffecte() {
		DefaultTableModel dtm = this.panelAffect.getDtm();
		JTable tableauAffect  = this.panelAffect.getTableauAffect();
		int hHP  = 0;
		int hTut = 0;
		int hCM  = 0;
		int hTD  = 0;
		int hTP  = 0;

		for(int i=0;i<tableauAffect.getRowCount();i++) {
			if(dtm.getValueAt(i, 1).equals("HP")){
				hHP += Float.parseFloat(dtm.getValueAt(i, 4).toString());
			}
			if(dtm.getValueAt(i, 1).equals("TUT")){
				hTut += Float.parseFloat(dtm.getValueAt(i, 4).toString());
			}
			if(dtm.getValueAt(i, 1).equals("TD")){
				hTD += Float.parseFloat(dtm.getValueAt(i, 4).toString());
			}
			if(dtm.getValueAt(i, 1).equals("TP")){
				hTP += Float.parseFloat(dtm.getValueAt(i, 4).toString());
			}
			if(dtm.getValueAt(i, 1).equals("CM")){
				hCM += Float.parseFloat(dtm.getValueAt(i, 4).toString());
			}
		}
		this.panelRepartitionHeure.setHeureAffecte(hCM, hTD, hTP, hTut, hHP);
	}

	public int getNbHeureSemaine(int i) {
		return this.panelRepartitionHeure.getNbHeureSemaine(i);
	}

	public int getNbGpTd() {return this.panelMere.getNbGpTd();}
	public int getNbGpTp() {return this.panelMere.getNbGpTp();}

}
