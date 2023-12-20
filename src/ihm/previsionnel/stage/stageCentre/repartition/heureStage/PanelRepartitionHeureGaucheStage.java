package ihm.previsionnel.stage.stageCentre.repartition.heureStage;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

import ihm.previsionnel.sae.saeCentre.repartition.heureSae.PanelRepH;

public class PanelRepartitionHeureGaucheStage extends JPanel implements ActionListener, FocusListener {

	private PanelRepHStage panelMere;
	private ArrayList<JTextField> ensJTextField;

	public PanelRepartitionHeureGaucheStage(PanelRepHStage panelRepH) {
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

		for(int i=0;i<this.ensJTextField.size()/2;i++){
			this.ensJTextField.get(i).addActionListener(this);
			this.ensJTextField.get(i).addFocusListener(this);
		}

	}


	public HashMap<String, Integer> getNbSemaines() {
		HashMap<String, Integer> map = new HashMap<>();

		try { map.put("REH", Integer.parseInt(this.ensJTextField.get(0).getText())); }
		catch (NumberFormatException e) { map.put("REH", 0); }

		try { map.put("TUT", Integer.parseInt(this.ensJTextField.get(1).getText())); }
		catch (NumberFormatException e) { map.put("TUT", 0); }

		return map;
	}

	public void setSommePromo(int somme) {
		this.panelMere.setSommePromo(somme);
	}

	public void setSommeAffecte() {
		int somme = 0;
		for(int i=2;i<this.ensJTextField.size();i++) {
			somme += Integer.parseInt(this.ensJTextField.get(i).getText());
		}
		this.panelMere.setSommeAffecte(somme);
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

	public void setHeureAffecte(int hSae, int hTut) {
		System.out.println("panelHeureDroite");
		this.ensJTextField.get(2).setText("" + hSae);
		this.ensJTextField.get(3).setText("" + hTut);
		this.repaint();
		this.revalidate();
	}


}
