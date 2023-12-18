package ihm.parametrage;
//Imp
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import metier.Statut;

public class PanelFormulaire extends JPanel implements ActionListener {
	private PanelParam panelMere;
	private JTextField txtNomStatut; 
	private JTextField txtNbHeuresService; 
	private JTextField txtNbHeuresMax; 
	private JTextField txtCoeff; 
	private JButton    btnValider;
	private JButton    btnAnnuler;
	private Statut     statut;
	private JLabel    lblErreur;

	public PanelFormulaire() {}

	public PanelFormulaire(PanelParam panelMere, Statut statut) {
		this.panelMere = panelMere;
		this.statut = statut;
		this.setLayout(new GridBagLayout());

		GridBagConstraints gbc 	= new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;

		this.txtNomStatut       = new JTextField(15);
		this.txtNbHeuresService = new JTextField(15);
		this.txtNbHeuresMax     = new JTextField(15);
		this.txtCoeff          	= new JTextField(15);
		this.btnValider         = new JButton("Valider");
		this.btnAnnuler         = new JButton("Annuler");
		this.lblErreur          = new JLabel("");

		//Coloration du label d'erreurs en rouge
		this.lblErreur.setForeground(java.awt.Color.RED);


		gbc.gridy = 0;
		gbc.gridx = 0;
		this.add(new JLabel("Nom du statut   "				), gbc);
		gbc.gridy = 1;
		this.add(new JLabel("Nombre d'heures de service   "), gbc);
		gbc.gridy = 2;
		this.add(new JLabel("Nombre d'heures maximum   "	), gbc);
		gbc.gridy = 3;
		this.add(new JLabel("Coefficient   "				), gbc);		
		gbc.gridx = 1;
		gbc.gridy = 0;
		this.add(this.txtNomStatut		, gbc);
		gbc.gridy = 1;
		this.add(this.txtNbHeuresService, gbc);
		gbc.gridy = 2;
		this.add(this.txtNbHeuresMax	, gbc);
		gbc.gridy = 3;
		this.add(this.txtCoeff			, gbc);

		gbc.gridy = 5;
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.EAST;
		this.add(this.btnValider, gbc);
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.WEST;
		this.add(this.btnAnnuler, gbc);
		gbc.gridy = 6;
		gbc.gridx = 0;
		this.add(this.lblErreur, gbc);

		this.btnValider.addActionListener(this);
		this.btnAnnuler.addActionListener(this);

		this.setVisible(true);

		this.setValues();
	}


	public void setValues() {
		if ( statut != null ) {
			this.txtNomStatut.setText(this.statut.getNomStatut());
			this.txtNbHeuresService.setText("" + this.statut.getNbHeureService());
			this.txtNbHeuresMax.setText("" + this.statut.getNbHeuresMax());
			this.txtCoeff.setText("" + this.statut.getCoeffTP());
		} else {
			this.txtCoeff.setText(""+0.0f);
		}

	}


	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == this.btnValider) {
			this.valider();
		}
		if(e.getSource() == this.btnAnnuler){
			this.effacer();
		}
	}

	public void valider() {

		int nbHeuresService = 0;
		int nbHeuresMax = 0;
		float coeff = 1.0f;

		try { nbHeuresService = Integer.parseInt(this.txtNbHeuresService.getText()); System.out.println(this.txtNbHeuresService.getText()); }
		catch ( NumberFormatException e ) {
			this.lblErreur.setText(this.txtNbHeuresService.getText() + " : pas un Integer"); 
			this.repaint();
			this.revalidate();
		}

		try { nbHeuresMax = Integer.parseInt(this.txtNbHeuresMax.getText()); }
		catch ( NumberFormatException e ) {
			this.lblErreur.setText(this.txtNbHeuresService.getText() + " : pas un Integer"); 
			this.repaint();
			this.revalidate();
		}

		try { coeff =  Float.parseFloat(this.txtCoeff.getText()); }
		catch ( NumberFormatException e ) {
			this.lblErreur.setText(this.txtNbHeuresService.getText() + " : pas un Float"); 
			this.repaint();
			this.revalidate();
		}

		System.out.println("nbHeuresService: "+nbHeuresService);
		System.out.println("nbHeuresMax    : "+nbHeuresMax);
		System.out.println("coeff          : "+coeff);

		Statut s = new Statut(this.txtNomStatut.getText(), nbHeuresService, nbHeuresMax, coeff);

		if ( this.statut != null )
			this.panelMere.majStatut(this.statut, s);


		//verification qu'il n'ajoute pas si c'est les valeurs par défaut
		if (nbHeuresService == 0 || nbHeuresMax == 0 || coeff == 0.0f) {
			this.lblErreur.setText("Vérifier vos valeurs");
		}
		else {
			this.panelMere.ajouterStatut(s);
			if ( statut != null ) this.panelMere.supprimerStatut();
			this.effacer();
		}


		this.effacer();

	}

	public void effacer() {
		this.txtNomStatut.setText("");
		this.txtNbHeuresService.setText("");
		this.txtNbHeuresMax.setText("");
		this.txtCoeff.setText("");
	}


	

}
