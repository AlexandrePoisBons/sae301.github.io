package ihm.previsionnel.sae.saeCentre.repartition;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import metier.Statut;

public class FrameFormulaire extends JFrame implements ActionListener{
	private PanelRepartitionSae panelMere;
	private JPanel panelFormulaire;
	private JTextField txtIntervenant; 
	private JTextField txtType; 
	private JTextField txtNbH; 
	private JTextField txtTotEqtd;
	private JTextField txtCommentaire;
	private JButton    btnValider;
	private JButton    btnAnnuler;

	public FrameFormulaire(PanelRepartitionSae panelMere){
		this.panelMere       = panelMere;
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
		
		this.txtIntervenant  = new JTextField(15);
		this.txtType         = new JTextField(15);
		this.txtNbH          = new JTextField(15);
		this.txtTotEqtd      = new JTextField(15);
		this.txtCommentaire  = new JTextField(15);
		this.btnValider      = new JButton("Valider");
		this.btnAnnuler      = new JButton("Annuler");

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
		this.panelFormulaire.add(this.txtIntervenant		, gbc);
		gbc.gridy = 1;
		this.panelFormulaire.add(this.txtType, gbc);
		gbc.gridy = 2;
		this.panelFormulaire.add(this.txtNbH	, gbc);
		gbc.gridy = 3;
		this.panelFormulaire.add(this.txtTotEqtd			, gbc);
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

	public void actionPerformed(ActionEvent e){
		if(e.getSource() == this.btnValider){
			
		}
		if(e.getSource() == this.btnAnnuler){
			
		}
	}

}