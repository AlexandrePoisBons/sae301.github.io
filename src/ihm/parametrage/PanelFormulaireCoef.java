package ihm.parametrage;
//Imp
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import metier.TypeHeure;

public class PanelFormulaireCoef extends JPanel implements ActionListener {
	private PanelParam panelMere;
	private JTextField txtTypeHeure; 
	private JTextField txtCoeff; 
	private JButton    btnValider;
	private JButton    btnAnnuler;
	private TypeHeure  typeHeure;

	public PanelFormulaireCoef() {}

	public PanelFormulaireCoef(PanelParam panelMere, TypeHeure typeHeure) {
		this.panelMere = panelMere;
		this.typeHeure = typeHeure;
		this.setLayout(new GridBagLayout());

		GridBagConstraints gbc 	= new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;

		this.txtTypeHeure       = new JTextField(15);
		this.txtTypeHeure.setEditable(false);
		this.txtCoeff          	= new JTextField(15);
		this.btnValider         = new JButton("Valider");
		this.btnAnnuler         = new JButton("Annuler");

		gbc.gridy = 0;
		gbc.gridx = 0;
		this.add(new JLabel("Type d'heure   "), gbc);
		gbc.gridy = 1;
		this.add(new JLabel("Coefficient   "), gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		this.add(this.txtTypeHeure, gbc);
		gbc.gridy = 1;
		this.add(this.txtCoeff, gbc);

		gbc.gridy = 3;
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.EAST;
		this.add(this.btnValider, gbc);
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.WEST;
		this.add(this.btnAnnuler, gbc);

		this.btnValider.addActionListener(this);
		this.btnAnnuler.addActionListener(this);

		this.setVisible(true);

		this.setValues();
	}


	public void setValues() {
		if ( typeHeure != null ) {
			this.txtTypeHeure.setText(this.typeHeure.getNomTypeHeure());
			this.txtCoeff.setText("" + this.typeHeure.getCoeff());
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
		float coeff = 1.0f;

		try { coeff =  Float.parseFloat(this.txtCoeff.getText()); }
		catch ( NumberFormatException e ) { System.out.println(this.txtCoeff.getText() + " : pas un Float"); }

		if ( this.typeHeure != null )
			this.typeHeure.setCoeff(coeff);
		

		System.out.println("id   : "+this.typeHeure.getIdTypeHeure());
		System.out.println("nom  : "+this.typeHeure.getNomTypeHeure());
		System.out.println("coeff: "+this.typeHeure.getCoeff());

		if ( this.typeHeure != null )
			this.panelMere.majTypeHeure(this.typeHeure, this.typeHeure);


		//verification qu'il n'ajoute pas si c'est les valeurs par défaut
		if (coeff == 0.0f) {
			System.out.println("Vérifier vos valeurs");
		}
		else {
			this.panelMere.ajouterTypeHeure(this.typeHeure);
			if ( typeHeure != null ) this.panelMere.supprimerTypeHeure();
			this.effacer();
		}
		this.effacer();

		System.out.println("\n\nTYPE HEURE:\t"+this.typeHeure.toString());

	}

	public void effacer() {
		this.txtTypeHeure.setText("");
		this.txtCoeff.setText("");
	}


}
