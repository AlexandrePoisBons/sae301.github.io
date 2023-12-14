package ihm.previsionnel.sae.saeCentre.repartition.heureSae;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.*;

public class PanelRepartitionHeureGaucheSae extends JPanel{
	private PanelRepH panelMere;
	private ArrayList<JTextField> 	ensJTextField	;

	public PanelRepartitionHeureGaucheSae(PanelRepH panelRepH){
		this.panelMere = panelRepH;
		this.ensJTextField = new ArrayList<JTextField>();


		JPanel panelHC	= new JPanel();
		for(int cpt=0; cpt < 6; cpt++){
			JTextField jtf = new JTextField(3);
			this.ensJTextField.add(jtf);	
		}
		panelHC.setLayout(new GridBagLayout());
		GridBagConstraints gbcH = new GridBagConstraints();


		gbcH.gridx = 1;
		gbcH.gridy = 0;
		gbcH.insets = new Insets(10, 0, 2, 0);
		panelHC.add(new JLabel("nbSem")		, gbcH);
		gbcH.gridx = 2;
		panelHC.add(new JLabel("nbH")	, gbcH);


		gbcH.gridx = 0;
		gbcH.gridy = 1;
		gbcH.insets = new Insets(0, 0, 2, 0);
		panelHC.add(new JLabel("Total promo (eqtd)"), gbcH);
		gbcH.gridy = 2;
		panelHC.add(new JLabel("Total affect (eqtd)"), gbcH);
		gbcH.insets = new Insets(0, 0, 2, 0);
		gbcH.gridx = 1;
		gbcH.gridy = 1;
		panelHC.add(this.ensJTextField.get(0), gbcH);
		gbcH.gridx = 2;
		panelHC.add(this.ensJTextField.get(1), gbcH);
		gbcH.gridy = 2;
		gbcH.gridx = 1;
		gbcH.insets = new Insets(0, 0, 2, 0);
		panelHC.add(this.ensJTextField.get(3), gbcH);
		gbcH.gridx = 2;
		gbcH.insets = new Insets(0, 0, 2, 0);
		panelHC.add(this.ensJTextField.get(4), gbcH);

		this.add(panelHC);
	}
}
