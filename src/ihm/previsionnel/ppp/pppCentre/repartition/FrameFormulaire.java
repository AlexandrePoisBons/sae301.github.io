package ihm.previsionnel.ppp.pppCentre.repartition;


import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;

import metier.Heure;
import metier.Intervenant;
import metier.TypeHeure;
import metier.Module;

public class FrameFormulaire extends JFrame implements ActionListener, FocusListener {
	private PanelRepartition        panelMere;
	private JPanel                  panelFormulaire;
	private JComboBox<String>       ddlstIntervenants;
	private JComboBox<String>       ddlstTypesHeures;
	private JLabel                  lblNbGpNbH;
	private JTextField              txtNbSemaine;
	private JTextField              txtNbH; 
	private JTextField              txtTotEqtd;
	private JTextField              txtCommentaire;
	private JButton                 btnValider;
	private JButton                 btnAnnuler;

	private Module module;

	public FrameFormulaire(PanelRepartition panelMere, Module m) {
		this.panelMere       = panelMere;
		this.module = m;


		//Définition de la taille et la position de la fenêtre
		int hauteur = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()  - (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.05);
		int largeur = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int xSize = (int)(largeur*0.5);
		int ySize = (int)(hauteur*0.5);
		this.setSize(xSize, ySize);
		this.setLocation((int)(largeur*0.5-xSize*0.5),(int)(hauteur*0.5-ySize*0.5));

		this.panelFormulaire = new JPanel();

		this.panelFormulaire.setLayout(new GridBagLayout());

		GridBagConstraints gbc 	= new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		
		this.remplirListe(this.panelMere.getIntervenants(), this.panelMere.getTypesHeures());
		this.lblNbGpNbH      = new JLabel("Nb groupe");
		this.txtNbSemaine    = new JTextField(15);
		this.txtNbH          = new JTextField(15);
		this.txtTotEqtd      = new JTextField(15);
		this.txtCommentaire  = new JTextField(15);
		this.btnValider      = new JButton("Valider");
		this.btnAnnuler      = new JButton("Annuler");

		this.txtTotEqtd.setEditable(false);

		gbc.gridy = 0;
		gbc.gridx = 0;
		this.panelFormulaire.add(new JLabel("Intervenants"				), gbc);
		gbc.gridy = 1;
		this.panelFormulaire.add(new JLabel("Type"), gbc);
		gbc.gridy = 2;
		this.panelFormulaire.add(new JLabel("Nb Semaines"	), gbc);
		gbc.gridy = 3;
		this.panelFormulaire.add(this.lblNbGpNbH, gbc);	
		gbc.gridy = 4;
		this.panelFormulaire.add(new JLabel("tot eqtd"				), gbc);
		gbc.gridy = 5;
		this.panelFormulaire.add(new JLabel("Commentaire"), gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		this.panelFormulaire.add(this.ddlstIntervenants		, gbc);
		gbc.gridy = 1;
		this.panelFormulaire.add(this.ddlstTypesHeures, gbc);
		gbc.gridy = 2;
		this.panelFormulaire.add(this.txtNbSemaine	, gbc);
		gbc.gridy = 3;
		this.panelFormulaire.add(this.txtNbH			, gbc);
		gbc.gridy = 4;
		this.panelFormulaire.add(this.txtTotEqtd, gbc);
		gbc.gridy = 5;
		this.panelFormulaire.add(this.txtCommentaire, gbc);

		gbc.gridy = 6;
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.EAST;
		this.panelFormulaire.add(this.btnValider, gbc);
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.WEST;
		this.panelFormulaire.add(this.btnAnnuler, gbc);

		this.btnValider.addActionListener(this);
		this.btnAnnuler.addActionListener(this);
		this.ddlstTypesHeures.addActionListener(this);
		this.txtNbH.addFocusListener(this);

		this.add(this.panelFormulaire);
		

		this.setVisible(true);
	}	

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btnValider) {
			this.valider();
			this.setHeureAffecte();
			this.dispose();
		}
		if(e.getSource() == this.btnAnnuler){
			this.dispose();
		}
		if(e.getSource() == this.ddlstTypesHeures){
			if(!(this.ddlstTypesHeures.getSelectedItem().toString().equals("HP") || 
				 this.ddlstTypesHeures.getSelectedItem().toString().equals("TUT"))) {
				this.lblNbGpNbH.setText("Nb groupe");
				this.txtNbSemaine.setEditable(true);
			}
			else {
				this.lblNbGpNbH.setText("Nb heure");
				this.txtNbSemaine.setEditable(false);
			}
		}
	}


	private void setHeureAffecte() {
		this.panelMere.setHeureAffecte();
	}

	public void remplirListe(List<Intervenant> intervenants, List<TypeHeure> typesHeures) {
		String[] tabInter = new String[intervenants.size()];
		int[] heures = new int[typesHeures.size()];
		int cpt = 0;
		

		for(int i=0;i<intervenants.size();i++) {
			tabInter[i] = intervenants.get(i).getNom() + " " + intervenants.get(i).getPrenom();
		}

		for(int i=0;i<typesHeures.size();i++){
			if(typesHeures.get(i).getNomTypeHeure().equals("TUT") ||
			   typesHeures.get(i).getNomTypeHeure().equals("CM") ||
			   typesHeures.get(i).getNomTypeHeure().equals("TD") ||
			   typesHeures.get(i).getNomTypeHeure().equals("TP") ||
			   typesHeures.get(i).getNomTypeHeure().equals("HP")) {
				heures[cpt] = i;
				cpt++;
			}
		}
			
		String[] tabTypesHeures = new String[cpt];

		for (int i = 0; i < tabTypesHeures.length; i++){
			tabTypesHeures[i] = typesHeures.get(heures[i]).getNomTypeHeure();
		}
		
		this.ddlstIntervenants = new JComboBox<String>(tabInter);
		this.ddlstTypesHeures = new JComboBox<String>(tabTypesHeures);
	}

	private void valider() {
		TypeHeure typeHeure = null;
		Intervenant intervenant   = null;
		Heure heure = null;
		for (TypeHeure tH : this.panelMere.getTypesHeures()) {
			if(this.ddlstTypesHeures.getSelectedItem().toString() == tH.getNomTypeHeure()){
				typeHeure = tH;
			}
		}
		for (Intervenant inter : this.panelMere.getIntervenants()) {
			if(this.ddlstIntervenants.getSelectedItem().toString().equals(inter.getNom() + " " + inter.getPrenom())) {
				intervenant = inter;
			}
		}

		if(!(this.ddlstTypesHeures.getSelectedItem().toString().equals("HP") ||
			 this.ddlstTypesHeures.getSelectedItem().toString().equals("TUT"))){
			heure = Heure.creerHeure( this.module,
									    (typeHeure),
									    Integer.parseInt(this.txtNbSemaine.getText()),
									    Integer.parseInt(this.txtNbH.getText()),
										Float.parseFloat(this.txtTotEqtd.getText()),
										this.txtCommentaire.getText() );
		}
		else {
			heure = Heure.creerHeure( this.module,
									  (typeHeure),
									  0,
									  Integer.parseInt(this.txtNbH.getText()),
									  Float.parseFloat(this.txtTotEqtd.getText()),
									  this.txtCommentaire.getText());
		}
		
		heure.ajouterIntervenant(intervenant);
		this.panelMere.ajouterHeure(heure);
		this.panelMere.setHeureAffecte();
	}

	@Override
	public void focusGained(FocusEvent e) {}

	@Override
	public void focusLost(FocusEvent e) {
		int nbHSemaine = 0;
		int nbSemaine = 0;
		int nbGp = 0;
		int calcul = 0;

		switch(this.ddlstTypesHeures.getSelectedItem().toString()) {
			case "TD" ->
				nbHSemaine = this.panelMere.getNbHeureSemaine(3);
			case "TP" -> 
				nbHSemaine = this.panelMere.getNbHeureSemaine(5);
			case "CM" -> 
				nbHSemaine = this.panelMere.getNbHeureSemaine(1);
			default -> 
				nbHSemaine = 1;
		}

		if(!(this.ddlstTypesHeures.getSelectedItem().toString().equals("HP") || this.ddlstTypesHeures.getSelectedItem().toString().equals("TUT"))){
			nbSemaine = Integer.parseInt(this.txtNbSemaine.getText());
			nbGp      = Integer.parseInt(this.txtNbH.getText());
			calcul    = nbSemaine * nbGp * nbHSemaine;
		}
		else {
			calcul = Integer.parseInt(this.txtNbH.getText());
		}
		this.txtTotEqtd.setText("" + calcul);
	}

}
