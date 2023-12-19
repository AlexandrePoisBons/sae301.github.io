package ihm.previsionnel.sae.saeCentre.repartition;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;

import metier.Intervenant;
import metier.Statut;
import metier.TypeHeure;

public class FrameFormulaire extends JFrame implements ActionListener{
	private PanelRepartitionSae     panelMere;
	private JPanel                  panelFormulaire;
	private JComboBox<Intervenant>  ddlstIntervenant;
	private JComboBox<TypeHeure>    ddlstTypesHeures;
	private JTextField              txtType; 
	private JTextField              txtNbH; 
	private JTextField              txtTotEqtd;
	private JTextField              txtCommentaire;
	private JButton                 btnValider;
	private JButton                 btnAnnuler;

	public FrameFormulaire(PanelRepartitionSae panelMere){
		this.panelMere       = panelMere;
		
		//Définition de la taille et la position de la fenêtre
		int hauteur = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()  - (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.05);
		int largeur = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int xSize   = (int)(largeur*0.5);
		int ySize   = (int)(hauteur*0.5);
		
		this.setSize(xSize, ySize);
		this.setLocation((int)(largeur*0.5-xSize*0.5),(int)(hauteur*0.5-ySize*0.5));

		this.panelFormulaire = new JPanel();

		this.panelFormulaire.setLayout(new GridBagLayout());

		GridBagConstraints gbc 	= new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;

		this.remplirListe(this.panelMere.getIntervenants(), this.panelMere.getTypesHeures());
		this.txtType        = new JTextField(15);
		this.txtNbH         = new JTextField(15);
		this.txtTotEqtd     = new JTextField(15);
		this.txtCommentaire = new JTextField(15);
		this.btnValider     = new JButton("Valider");
		this.btnAnnuler     = new JButton("Annuler");

		gbc.gridy = 0;
		gbc.gridx = 0;
		this.panelFormulaire.add(new JLabel("Intervenants"				), gbc);
		gbc.gridy = 1;
		this.panelFormulaire.add(new JLabel("Type"), gbc);
		gbc.gridy = 2;
		this.panelFormulaire.add(new JLabel("Nb heure"	), gbc);
		gbc.gridy = 3;
		this.panelFormulaire.add(new JLabel("tot eqtd"				), gbc);	
		gbc.gridy = 4;
		this.panelFormulaire.add(new JLabel("commentaire"				), gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		this.panelFormulaire.add(this.ddlstIntervenant, gbc);
		gbc.gridy = 1;
		this.panelFormulaire.add(this.ddlstTypesHeures, gbc);
		gbc.gridy = 2;
		this.panelFormulaire.add(this.txtNbH	, gbc);
		gbc.gridy = 3;
		this.panelFormulaire.add(this.txtTotEqtd, gbc);
		gbc.gridy = 4;
		this.panelFormulaire.add(this.txtCommentaire, gbc);

		gbc.gridy = 5;
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.EAST;
		this.panelFormulaire.add(this.btnValider, gbc);
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.WEST;
		this.panelFormulaire.add(this.btnAnnuler, gbc);

		this.btnValider.addActionListener(this);
		this.btnAnnuler.addActionListener(this);

		this.add(this.panelFormulaire);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btnValider){
			this.valider();
			this.dispose();
		}
		if(e.getSource() == this.btnAnnuler){
			this.annuler();
		}
	}

	public void annuler() {
		this.txtCommentaire.setText("");
		this.txtNbH.setText("");
		this.txtTotEqtd.setText("");
		this.txtType.setText("");
		this.dispose();
	}

	public void remplirListe(List<Intervenant> intervenants, List<TypeHeure> typesHeures) {
		Intervenant[] tabInter = new Intervenant[intervenants.size()];
		TypeHeure[] tabTypesHeures = new TypeHeure[typesHeures.size()];

		for(int i=0;i<intervenants.size();i++)
			tabInter[i] = intervenants.get(i);
		
		for (int i = 0; i < tabTypesHeures.length; i++)
			tabTypesHeures[i] = typesHeures.get(i);
		
		this.ddlstIntervenant = new JComboBox<>(tabInter);
		this.ddlstTypesHeures = new JComboBox<>(tabTypesHeures);
	}



	private void valider() {
		Object[] objs = new Object[6];
		Intervenant intervenant = (Intervenant)(this.ddlstIntervenant.getSelectedItem());
		objs[0] = intervenant.getNom()+" "+intervenant.getPrenom().substring(0,1)+".";
		objs[1] = ((TypeHeure)this.ddlstTypesHeures.getSelectedItem()).getNomTypeHeure();
		objs[2] = this.txtNbH.getText();
		objs[3] = this.txtTotEqtd.getText();
		objs[4] = this.txtCommentaire.getText();
		this.panelMere.ajouterLigne(objs);
	}
}