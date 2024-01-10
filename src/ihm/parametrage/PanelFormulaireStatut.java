package ihm.parametrage;
//Imp
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import metier.Statut;

public class PanelFormulaireStatut extends JPanel implements ActionListener {
	private PanelParam panelMere;
	private JTextField txtNomStatut;
	private JTextField txtNbHeuresService;
	private JTextField txtNbHeuresMax;
	private JTextField txtCoeff;
	private JButton    btnValider;
	private JButton    btnAnnuler;
	private Statut     statut;
	private JLabel     lblErreur;

	public PanelFormulaireStatut() {}

	public PanelFormulaireStatut(PanelParam panelMere, Statut statut) {
		this.panelMere = panelMere;
		this.statut = statut;
		this.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;

		this.txtNomStatut       = new JTextField(15);
		this.txtNbHeuresService = new JTextField(15);
		this.txtNbHeuresMax     = new JTextField(15);
		this.txtCoeff           = new JTextField(15);
		this.btnValider         = new JButton("Valider");
		this.btnAnnuler         = new JButton("Annuler");
		this.lblErreur          = new JLabel("");

		//Coloration du label d'erreurs en rouge
		this.lblErreur.setForeground(java.awt.Color.RED);


		gbc.gridy = 0;
		gbc.gridx = 0;
		this.add(new JLabel("Nom du statut   "), gbc);
		gbc.gridy = 1;
		this.add(new JLabel("Nombre d'heures de service   "), gbc);
		gbc.gridy = 2;
		this.add(new JLabel("Nombre d'heures maximum   "), gbc);
		gbc.gridy = 3;
		this.add(new JLabel("Coefficient   "), gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		this.add(this.txtNomStatut, gbc);
		gbc.gridy = 1;
		this.add(this.txtNbHeuresService, gbc);
		gbc.gridy = 2;
		this.add(this.txtNbHeuresMax, gbc);
		gbc.gridy = 3;
		this.add(this.txtCoeff, gbc);

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

		if ( e.getSource() == this.btnValider ) {
			this.valider();
		} else if ( e.getSource() == this.btnAnnuler ) {
			this.effacer();
		}

	}

	public void valider() {

		Statut s = null;
		int nbHeuresService = -1;
		int nbHeuresMax = -1;
		float coeff = -1.0f;

		if(this.txtNomStatut.getText().equals("")) {
			this.lblErreur.setText("Remplissez Nom Statut");
			return;
		}

		if(!this.txtNbHeuresService.getText().equals("")) {
			try {
				nbHeuresService = Integer.parseInt(this.txtNbHeuresService.getText()); 
			}
			catch ( NumberFormatException e ) {
				this.lblErreur.setText(this.txtNbHeuresService.getText() + " : pas un Integer"); 
				return;
			}
		} else {
			this.lblErreur.setText("Remplissez Heures Service");
			return;
		}



		if(!this.txtNbHeuresMax.getText().equals("")) {
			try {
				nbHeuresMax = Integer.parseInt(this.txtNbHeuresMax.getText()); 
			}
			catch ( NumberFormatException e ) {
				this.lblErreur.setText(this.txtNbHeuresMax.getText() + " : pas un Integer"); 
				return;
			}
		} else {
			this.lblErreur.setText("Remplissez Heures Max");
			return;
		}

		if(!this.txtCoeff.getText().equals("")) {
			try {
				coeff = Float.parseFloat(this.txtCoeff.getText()); 
			}
			catch ( NumberFormatException e ) {
				this.lblErreur.setText(this.txtCoeff.getText() + " : pas un Float"); 
				return;
			}
		} else {
			this.lblErreur.setText("Remplissez Coefficient");
			return;
		}

		if(nbHeuresService < nbHeuresMax) {
			s = new Statut(this.txtNomStatut.getText(), nbHeuresService, nbHeuresMax, coeff);
		}
		else {
			this.lblErreur.setText("nbHServ < nbHMax");
			this.txtNbHeuresService.setText("");
			return;
		}

		if ( this.statut != null )  {
			this.panelMere.majStatut(this.statut, s);
		}

		//verification qu'il n'ajoute pas si c'est les valeurs par défaut
		if ( s.getNomStatut().equals("") || s.getNbHeureService() == -1 || s.getNbHeuresMax() == -1 || s.getCoeffTP() == -1.0f) {
			this.lblErreur.setText("Vérifier vos valeurs");
			return;
		} else {
			this.panelMere.ajouterStatut(s);
			if ( statut != null ) this.panelMere.supprimerStatut();
		}

		this.effacer();
		this.lblErreur.setText("");
	}

	public void effacer() {
		this.txtNomStatut.setText("");
		this.txtNbHeuresService.setText("");
		this.txtNbHeuresMax.setText("");
		this.txtCoeff.setText("");
	}

}
