package ihm.previsionnel.ressources.ressourcesCentre.repartition.heure;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

public class PanelRepartitionHGauche extends JPanel implements FocusListener, ActionListener{
	private PanelRepartitionHeure 	panelMere		;
	private JPanel 					panelHeure;
	private JPanel 					panelTotal		;
	private ArrayList<JTextField> 	ensJTextField	;
	private int sommeAction;

	public PanelRepartitionHGauche(PanelRepartitionHeure panelMere){
		this.panelMere = panelMere;
		this.panelHeure = new JPanel();
		this.ensJTextField = new ArrayList<JTextField>();

		this.setLayout(new GridLayout(2, 1));
		this.panelHeure.setLayout(new BorderLayout());

		JPanel panelHN 	= new JPanel();
		JPanel panelHC	= new JPanel();
		JPanel panelB 	= new JPanel();
		for(int cpt=0; cpt < 6; cpt++){
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

		for(int i=0;i<this.ensJTextField.size()/2;i++){
			this.ensJTextField.get(i).addActionListener(this);
			this.ensJTextField.get(i).addFocusListener(this);
		}

		this.add(this.panelHeure);
		this.add(panelB);

	}

	public HashMap<String, Integer> getNbSemaines(){
		HashMap<String, Integer> map = new HashMap<>();

		try { map.put("CM",Integer.parseInt(this.ensJTextField.get(0).getText())); }
		catch(NumberFormatException e) { map.put("CM", 0); }
		try { map.put("TD",Integer.parseInt(this.ensJTextField.get(2).getText())); }
		catch(NumberFormatException e) { map.put("TD", 0); }
		try { map.put("TP",Integer.parseInt(this.ensJTextField.get(4).getText())); }
		catch(NumberFormatException e) { map.put("TP", 0); }

		return map;
	}

	public void setSommePromo(int somme) {
		this.panelMere.setSommePromo(somme);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == this.ensJTextField.get(0)) {
			//pour que le focus passe au champ suivant quand  l'utilisteur clique sur "entrée"
			this.ensJTextField.get(0).transferFocus();
		}
		else if(e.getSource() == this.ensJTextField.get(1)) {

			this.ensJTextField.get(1).transferFocusBackward();
		}

		this.setSommePromo(Integer.parseInt(this.ensJTextField.get(0).getText()) + Integer.parseInt(this.ensJTextField.get(1).getText()));		
	}


	@Override
	public void focusLost(FocusEvent e) {
		//Addition des heures saisies dans les champs
		int somme = 0;
		
		try {
			//Vérification que la saisie de cette valeur n'a pas déjas été enregistrée dans somme
			if(this.ensJTextField.get(0).getText().equals(Integer.toString(somme)) && this.ensJTextField.get(1).getText().equals("")){
				System.out.println("Cette valeur a déjas été prise en compte");
			}
			else
				somme += Integer.parseInt(this.ensJTextField.get(0).getText()); 
		}
		catch(NumberFormatException ex) {
			System.out.println("Erreur de saisie, veuillez entrer un nombre entier");
		}
		
		try {
			//Vérification que la saisie de cette valeur n'a pas déjas été enregistrée dans somme
			if(this.ensJTextField.get(0).getText().equals(Integer.toString(somme)) && this.ensJTextField.get(0).getText().equals("")){
				System.out.println("Cette valeur a déjas été prise en compte");
			}
			else
				somme += Integer.parseInt(this.ensJTextField.get(1).getText());
		}
		catch(NumberFormatException ex) {
			System.out.println("Erreur de saisie, veuillez entrer un nombre entier");
		}
		this.setSommePromo(somme);
	}


	@Override
	public void focusGained(FocusEvent e) {}



}
