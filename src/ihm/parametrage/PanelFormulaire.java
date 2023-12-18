package ihm.parametrage;
//Imp
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Insets;

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

	public PanelFormulaire(PanelParam panelMere) {
		this.panelMere = panelMere;
		this.setLayout(new GridBagLayout());

		GridBagConstraints gbc 	= new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;

		this.txtNomStatut       = new JTextField(15);
		this.txtNbHeuresService = new JTextField(15);
		this.txtNbHeuresMax     = new JTextField(15);
		this.txtCoeff          	= new JTextField(15);
		this.btnValider         = new JButton("Valider");
		this.btnAnnuler         = new JButton("Annuler");

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

		this.btnValider.addActionListener(this);
		this.btnAnnuler.addActionListener(this);

		this.setVisible(true);
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
		float coeff = 0.0f;

		try { Integer.parseInt(this.txtNbHeuresService.getText()); }
		catch ( NumberFormatException e ) { System.out.println(this.txtNbHeuresService.getText() + " : pas un Integer"); }

		try { Integer.parseInt(this.txtNbHeuresMax.getText()); }
		catch ( NumberFormatException e ) { System.out.println(this.txtNbHeuresMax.getText() + " : pas un Integer"); }

		try { Float.parseFloat(this.txtCoeff.getText()); }
		catch ( NumberFormatException e ) { System.out.println(this.txtCoeff.getText() + " : pas un Float"); }

		this.panelMere.ajouterStatut( new Statut(this.txtNomStatut.getText(), nbHeuresService, nbHeuresMax, coeff) );
	}

	public void effacer() {
		this.txtNomStatut.setText("");
		this.txtNbHeuresService.setText("");
		this.txtNbHeuresMax.setText("");
		this.txtCoeff.setText("");
	}


	public void setLigne(String nomIntervenant, Integer nbHeureService, Integer nbHeureMax, Float coeff) {
		this.txtNomStatut.setText(nomIntervenant);
		this.txtNbHeuresService.setText("" + nbHeureService);
		this.txtNbHeuresMax.setText("" + nbHeureMax);
		this.txtCoeff.setText("" + coeff);
		System.out.println("test");
	}

}
