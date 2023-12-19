package ihm.previsionnel.sae.saeCentre.repartition;

//Imports classes Java
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
	private Module module;

	public PanelRepartitionSae(PCentreSae panelMere, Module m) {
		this.panelMere = panelMere;
		this.module = m;
		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(0, 5, 0, 10));

		JPanel panelBtn = new JPanel();

		this.panelRepH 	            = new PanelRepH					(this)		    ;
		this.panelAffect	 		= new PanelAffectSae			(this, this.module);
		this.btnAjouter 			= new JButton					("Ajouter")   	;
		this.btnSuppr				= new JButton					("Supprimer")	;	

		//Ajout des boutons au panel de boutons
		panelBtn.add(this.btnAjouter)								;
		panelBtn.add(this.btnSuppr)									;

		this.add(this.panelRepH   , BorderLayout.NORTH);
		this.add(this.panelAffect , BorderLayout.CENTER);
		this.add(panelBtn         , BorderLayout.SOUTH);

		//Ajout des
		this.btnAjouter.addActionListener	(this);
		this.btnSuppr.addActionListener		(this);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btnAjouter) {
			new FrameFormulaire(this,this.module);
			//this.panelAffect.ajouter();
			this.setErreur("");
		}
		if(e.getSource() == this.btnSuppr) {
			this.panelAffect.supprimer();
			this.setErreur("");
		}
	}

	public HashMap<String,Integer> getData() { return this.panelMere.getData(); }

	public void setHeures(List<Heure> heures) { this.panelAffect.setHeures(heures); }

	public HashMap<String,Integer> getNbSemaines() { return this.panelRepH.getNbSemaines(); }
	public HashMap<String,Integer> getTabData() { return this.panelMere.getData(); }
	public List<Intervenant> getIntervenants() { return this.panelMere.getIntervenants(); }
	public List<TypeHeure> getTypesHeures() { return this.panelMere.getTypesHeures(); }

	public void ajouterHeure(Heure heure) { this.panelAffect.ajouterHeure(heure); }

	public List<Heure> getHeures(Module m) { return this.panelAffect.getDataHeures(); }

	public void setErreur(String message) {
		this.panelMere.setErreur(message);
	}

}
