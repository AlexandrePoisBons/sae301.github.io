package ihm.previsionnel.stage.stageCentre.repartition.heureStage;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

import metier.Module;
import metier.TypeHeure;


public class PanelRepartitionHeureGaucheStage extends JPanel implements ActionListener, FocusListener {

	private PanelRepHStage panelMere;
	private ArrayList<JTextField> ensJTextField;

	private int sommeHSae = 0;
	private int sommeHTut = 0;
	private int totalSomme = 0;

	private Module module;

	public PanelRepartitionHeureGaucheStage(PanelRepHStage panelRepH, Module m) {
		this.panelMere = panelRepH;
		this.module = m;

		this.ensJTextField = new ArrayList<JTextField>();


		JPanel panelHC	= new JPanel();
		for(int cpt=0; cpt < 4; cpt++){
			JTextField jtf = new JTextField(3);
			if(cpt>1){
				jtf.setEditable(false);
			}
			this.ensJTextField.add(jtf);
		}
		panelHC.setLayout(new GridBagLayout());
		GridBagConstraints gbcH = new GridBagConstraints();


		gbcH.gridx = 1;
		gbcH.gridy = 0;
		gbcH.insets = new Insets(10, 0, 2, 0);
		panelHC.add(new JLabel("REH"), gbcH);
		gbcH.gridx = 2;
		panelHC.add(new JLabel("h TUT"), gbcH);


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
		panelHC.add(this.ensJTextField.get(2), gbcH);
		gbcH.gridx = 2;
		gbcH.insets = new Insets(0, 0, 2, 0);
		panelHC.add(this.ensJTextField.get(3), gbcH);

		this.add(panelHC);

		for(int i=0;i<this.ensJTextField.size()/2;i++){
			this.ensJTextField.get(i).addActionListener(this);
			this.ensJTextField.get(i).addFocusListener(this);
		}

		if ( this.module != null )
			this.initValues();

	}


	public void initValues() {
		HashMap<TypeHeure, HashMap<String,Integer>> map = this.panelMere.getHeuresParTypesHeures(this.module);
		
		if ( map != null )
			for (TypeHeure typeHeure : map.keySet()) {
				switch (typeHeure.getNomTypeHeure()) {
					case "REH"  -> {
						this.ensJTextField.get(0).setText(""+map.get(typeHeure).get("nb_heures"));
					} case "TUT"  -> {
						this.ensJTextField.get(1).setText(""+map.get(typeHeure).get("nb_heures"));
					}
				}
			}

	}

	public HashMap<String, HashMap<String,Integer>> getDataHeuresTypesHeures() {
		HashMap<String, HashMap<String,Integer>> map = new HashMap<>();

		HashMap<String,Integer> temp = new HashMap<>();
		temp.put("nb_heures", this.getIntVal(this.ensJTextField.get(0)) );
		map.put("REH", temp);

		temp = new HashMap<>();
		temp.put("nb_heures", this.getIntVal(this.ensJTextField.get(1)) );
		map.put("TUT", temp);


		return map;

	}

	public int getIntVal(JTextField txt) {
		int nb;
		try {
			nb = Integer.parseInt(txt.getText());
		} catch(NumberFormatException e) { nb=0; }

		return nb;
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

	
	public void setLabelErreur(String message) { this.panelMere.setLabelErreur(message); }
	//méthode pour gérer la couleur du cadre en fonction de la validité de la saisie
	public void setCouleurErreur(boolean b, JTextField txt) {
		if (b == true) {
			txt.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
		} else {
			txt.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		}
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
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == this.ensJTextField.get(0)) {
			try {
				// Vérification que la saisie de cette valeur n'a pas déjas été enregistrée dans
				// somme
				if(this.panelMere.estChiffre(this.ensJTextField.get(0).getText())==false) {
					this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
					this.setCouleurErreur(true, this.ensJTextField.get(0));
				}
				if (this.ensJTextField.get(0).getText().equals(Integer.toString(this.sommeHSae))
						&& this.ensJTextField.get(1).getText().equals("")){
					System.out.println("Cette valeur a déjas été prise en compte");
				} else {
					if (this.ensJTextField.get(0).getText().equals("") || Integer.parseInt(this.ensJTextField.get(0).getText()) < 0)
						this.ensJTextField.get(0).setText("0");
					this.setLabelErreur("");
					this.setCouleurErreur(false, this.ensJTextField.get(0));
					this.sommeHSae = Integer.parseInt(this.ensJTextField.get(0).getText());
				}
			} catch (Exception err) {
				System.out.print("");
			}
		}

		if (e.getSource() == this.ensJTextField.get(1)) {
			try {
				// Vérification que la saisie de cette valeur n'a pas déjas été enregistrée dans
				// somme
				if(this.panelMere.estChiffre(this.ensJTextField.get(1).getText())==false) {
					this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
					this.setCouleurErreur(true, this.ensJTextField.get(1));
				}
				if (this.ensJTextField.get(1).getText().equals(Integer.toString(this.sommeHTut))
						&& this.ensJTextField.get(0).getText().equals("")) {
					System.out.println("Cette valeur a déjas été prise en compte");
				} else {
					if (this.ensJTextField.get(1).getText().equals("")|| Integer.parseInt(this.ensJTextField.get(1).getText()) < 0)
						this.ensJTextField.get(1).setText("0");
					this.setLabelErreur("");
					this.setCouleurErreur(false, this.ensJTextField.get(1));
					this.sommeHTut = Integer.parseInt(this.ensJTextField.get(1).getText());
				}
			} catch (Exception err) {
				System.out.print("");
			}
		}
		this.totalSomme = this.sommeHSae + this.sommeHTut;
		this.setSommePromo(this.totalSomme);
	}
	@Override
	public void focusGained(FocusEvent e) {
		// ré-affichage du label erreur si il y a une erreur dans un des txtField
		if (e.getSource() == this.ensJTextField.get(0)) {
			if (this.panelMere.estChiffre(this.ensJTextField.get(0).getText()) == false) {
				this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
				this.setCouleurErreur(true, this.ensJTextField.get(0));
			}
			else{
				this.setLabelErreur("");
				this.setCouleurErreur(false, this.ensJTextField.get(0));
			}
		}

		if (e.getSource() == this.ensJTextField.get(1)) {
			if (this.panelMere.estChiffre(this.ensJTextField.get(1).getText()) == false) {
				this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
				this.setCouleurErreur(true, this.ensJTextField.get(1));
			}
			else{
				this.setLabelErreur("");
				this.setCouleurErreur(false, this.ensJTextField.get(1));
			}
		}
	}

	public void setHeureAffecte(int hREH, int hTut) {
		this.ensJTextField.get(2).setText("" + hREH);
		this.ensJTextField.get(3).setText("" + hTut);
		this.repaint();
		this.revalidate();
	}


}
