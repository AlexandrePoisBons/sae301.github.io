package ihm.previsionnel.ressources.ressourcesCentre.repartition.heure;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.*;

public class PanelRepartitionHGauche extends JPanel{
	private PanelRepartitionHeure 	panelMere		;
	private JPanel 					panelTotal		;
	private ArrayList<JTextField> 	ensJTextField	;

	public PanelRepartitionHGauche(PanelRepartitionHeure panelMere){
		this.panelMere = panelMere;
		this.ensJTextField = new ArrayList<JTextField>();

		JPanel panelC 	= new JPanel();
		for(int cpt=0; cpt < 6; cpt++){
			JTextField jtf = new JTextField(2);
			this.ensJTextField.add(jtf);	
		}

		panelC.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelC.add(new JLabel("nb Sem")		, gbc);
		gbc.gridx = 1;
		panelC.add(new JLabel("nb h/Sem")	, gbc);
		gbc.gridx = 2;
		gbc.insets = new Insets(0, 0, 5, 0);
		panelC.add(new JLabel("nb Sem")		, gbc);
		gbc.insets = new Insets(0, 0, 5, 0);
		gbc.gridx = 3;
		panelC.add(new JLabel("nb h/Sem")	, gbc);
		gbc.gridx = 4;
		gbc.insets = new Insets(0, 0, 5, 0);
		panelC.add(new JLabel("nb Sem")		, gbc);
		gbc.insets = new Insets(0, 0, 5, 0);
		gbc.gridx = 5;
		panelC.add(new JLabel("nb h/Sem")	, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(0, 0, 5, 0);
		panelC.add(this.ensJTextField.get(0), gbc);
		gbc.gridx = 1;
		panelC.add(this.ensJTextField.get(1), gbc);
		gbc.gridx = 2;
		gbc.insets = new Insets(0, 0, 5, 0);
		panelC.add(this.ensJTextField.get(2), gbc);
		gbc.gridx = 3;
		gbc.insets = new Insets(0, 0, 5, 0);
		panelC.add(this.ensJTextField.get(3), gbc);
		gbc.gridx = 4;
		gbc.insets = new Insets(0, 0, 5, 0);
		panelC.add(this.ensJTextField.get(4), gbc);
		gbc.gridx = 5;
		gbc.insets = new Insets(0, 0, 5, 0);
		panelC.add(this.ensJTextField.get(5), gbc);


		gbc.gridy = 2;
		gbc.insets = new Insets(0, 10, 5, 0);
		panelC.add(new JLabel("Total promo (eqtd)"		)	, gbc);
		gbc.gridy = 3;
		panelC.add(new JLabel("Total affecter (eqtd)"	)	, gbc);

		this.add(panelC);
		
	}
}
