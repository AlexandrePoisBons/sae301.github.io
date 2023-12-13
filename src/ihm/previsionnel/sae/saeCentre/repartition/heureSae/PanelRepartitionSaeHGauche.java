package ihm.previsionnel.sae.saeCentre.repartition.heureSae;

//Imports des classesnJava
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.*;

public class PanelRepartitionSaeHGauche extends JPanel{
	private PanelRepartitionHSae panelMere;
	private JPanel 					panelHeure;
	private JPanel 					panelTotal		;
	private ArrayList<JTextField> 	ensJTextField	;

	public PanelRepartitionSaeHGauche(PanelRepartitionHSae panelMere){
		this.panelMere = panelMere;
		this.panelHeure = new JPanel();
		this.ensJTextField = new ArrayList<JTextField>();

		this.setLayout(new GridLayout(2, 1));
		this.panelHeure.setLayout(new BorderLayout());

		JPanel panelHN 	= new JPanel();
		JPanel panelHC	= new JPanel();
		JPanel panelB 	= new JPanel();
		for(int cpt=0; cpt < 4; cpt++){
			JTextField jtf = new JTextField(3);
			this.ensJTextField.add(jtf);	
		}

		panelHN.setLayout(new GridLayout(1, 3));
		panelHC.setLayout(new GridBagLayout());
		GridBagConstraints gbcH = new GridBagConstraints();
		panelB.setLayout(new GridLayout(2, 1));


		gbcH.gridx = 0;
		gbcH.gridy = 0;
		panelHC.add(new JLabel("nbSem")		, gbcH);
		gbcH.gridx = 1;
		panelHC.add(new JLabel("nbH")	, gbcH);
		gbcH.gridx = 2;
		gbcH.insets = new Insets(0, 7, 5, 0);
		panelHC.add(new JLabel("nbSem")		, gbcH);
		gbcH.insets = new Insets(0, 0, 5, 0);
		gbcH.gridx = 3;
		panelHC.add(new JLabel("nbH")	, gbcH);
		gbcH.gridx = 4;
		gbcH.insets = new Insets(0, 7, 5, 0);
		panelHC.add(new JLabel("nbSem")		, gbcH);
		gbcH.insets = new Insets(0, 0, 5, 0);
		gbcH.gridx = 5;
		panelHC.add(new JLabel("nbH")	, gbcH);

		gbcH.gridx = 0;
		gbcH.gridy = 1;
		gbcH.insets = new Insets(0, 0, 5, 0);
		panelHC.add(this.ensJTextField.get(0), gbcH);
		gbcH.gridx = 1;
		panelHC.add(this.ensJTextField.get(1), gbcH);
		gbcH.gridx = 2;
		gbcH.insets = new Insets(0, 7, 5, 0);
		panelHC.add(this.ensJTextField.get(2), gbcH);
		gbcH.gridx = 3;
		gbcH.insets = new Insets(0, 0, 5, 0);
		panelHC.add(this.ensJTextField.get(3), gbcH);
		gbcH.gridx = 4;
		gbcH.insets = new Insets(0, 7, 5, 0);
		panelHC.add(this.ensJTextField.get(4), gbcH);
		gbcH.gridx = 5;
		gbcH.insets = new Insets(0, 0, 5, 10);
		panelHC.add(this.ensJTextField.get(5), gbcH);

		panelHN.add(new JLabel("CM", JLabel.CENTER));
		panelHN.add(new JLabel("TD", JLabel.CENTER));
		panelHN.add(new JLabel("TP", JLabel.CENTER));

		panelB.add(new JLabel("Total promo (eqtd)", JLabel.RIGHT	));
		panelB.add(new JLabel("Total affect (eqtd)", JLabel.RIGHT	));

		this.panelHeure.add(panelHN, BorderLayout.NORTH);
		this.panelHeure.add(panelHC, BorderLayout.CENTER);

		this.add(this.panelHeure);
		this.add(panelB);
		
	}
}