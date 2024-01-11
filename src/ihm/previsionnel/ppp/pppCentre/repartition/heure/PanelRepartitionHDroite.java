package ihm.previsionnel.ppp.pppCentre.repartition.heure;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import metier.Module;
import metier.TypeHeure;

public class PanelRepartitionHDroite extends JPanel implements ActionListener, FocusListener {
	// Constante coefficient de conversion des heures de CM en heures TD (1h de TD = 1.5h CM)
	private static final double COEFF_CM_TD = 1.5;

	private PanelRepartitionHeure panelMere;
	private JPanel panelN;
	private JPanel panelC;
	private ArrayList<JTextField> ensTxtFld;

	private int sommeActionTD, sommeActionTP, sommeActionCM;
	private int clcCM, clcTD, clcTP;
	private int valTut, valPonct;

	private Module module;

	public PanelRepartitionHDroite(PanelRepartitionHeure panelMere, Module m) {
		this.panelMere = panelMere;
		this.module = m;

		this.sommeActionTD = this.sommeActionTP = this.sommeActionCM = 0;
		this.valTut = this.valPonct = 0;
		this.panelN = new JPanel();
		this.panelC = new JPanel();
		this.ensTxtFld = new ArrayList<JTextField>();
		for ( int i = 0; i < 18; i++ ) {
			JTextField textField = new JTextField(3);
			if (i == 3 || i == 4) {
				textField.setEditable(true);
			} else {
				textField.setEditable(false);
			}
			this.ensTxtFld.add(textField);
		}

		this.setLayout(new BorderLayout());
		this.panelN.setLayout(new GridBagLayout());
		GridBagConstraints gbcN = new GridBagConstraints();
		this.panelC.setLayout(new GridBagLayout());
		GridBagConstraints gbcC = new GridBagConstraints();

		gbcN.gridy = 0;
		gbcN.gridx = 3;
		this.panelN.add(new JLabel("      heures"), gbcN);
		gbcN.gridx = 4;
		this.panelN.add(new JLabel("     heures"), gbcN);

		// Ajout des composants avec GridBagLayout
		gbcC.gridx = 1;
		gbcC.gridy = 0;
		gbcC.insets = new Insets(0, 3, 5, 0);

		// Ajout des éléments
		this.panelC.add(new JLabel("CM"), gbcC);
		gbcC.gridx = 2;
		this.panelC.add(new JLabel("TD"), gbcC);
		gbcC.gridx = 3;
		this.panelC.add(new JLabel("TP"), gbcC);
		gbcC.insets = new Insets(0, 3, 5, 0);
		gbcC.gridx = 4;
		this.panelC.add(new JLabel("tutorées"), gbcC);
		gbcC.gridx = 5;
		this.panelC.add(new JLabel("ponctuelles"), gbcC);
		gbcC.gridx = 6;
		gbcC.insets = new Insets(0, 50, 5, 0);
		this.panelC.add(new JLabel("Σ"), gbcC);
		gbcC.gridx = 1;
		gbcC.gridy = 1;
		gbcC.insets = new Insets(0, 3, 5, 0);

		this.panelC.add(this.ensTxtFld.get(0), gbcC);
		gbcC.gridx = 2;
		this.panelC.add(this.ensTxtFld.get(1), gbcC);
		gbcC.gridx = 3;
		this.panelC.add(this.ensTxtFld.get(2), gbcC);
		gbcC.insets = new Insets(0, 9, 8, 0);
		gbcC.gridx = 4;
		this.panelC.add(this.ensTxtFld.get(3), gbcC);
		//
		gbcC.gridx = 5;
		this.panelC.add(this.ensTxtFld.get(4), gbcC);
		//
		gbcC.gridx = 6;
		gbcC.insets = new Insets(0, 50, 5, 0);
		this.panelC.add(this.ensTxtFld.get(5), gbcC);
		gbcC.insets = new Insets(0, 3, 8, 0);
		gbcC.gridx = 1;
		gbcC.gridy = 2;

		this.panelC.add(this.ensTxtFld.get(6), gbcC);
		gbcC.gridx = 2;
		this.panelC.add(this.ensTxtFld.get(7), gbcC);
		gbcC.gridx = 3;
		this.panelC.add(this.ensTxtFld.get(8), gbcC);
		gbcC.insets = new Insets(0, 9, 8, 0);
		gbcC.gridx = 4;
		this.panelC.add(this.ensTxtFld.get(9), gbcC);
		//
		gbcC.gridx = 5;
		this.panelC.add(this.ensTxtFld.get(10), gbcC);
		//
		gbcC.gridx = 6;
		gbcC.insets = new Insets(0, 50, 5, 0);
		this.panelC.add(this.ensTxtFld.get(11), gbcC);
		gbcC.insets = new Insets(0, 3, 8, 0);
		gbcC.gridx = 1;
		gbcC.gridy = 3;

		this.panelC.add(this.ensTxtFld.get(12), gbcC);
		gbcC.gridx = 2;
		this.panelC.add(this.ensTxtFld.get(13), gbcC);
		gbcC.gridx = 3;
		this.panelC.add(this.ensTxtFld.get(14), gbcC);
		gbcC.insets = new Insets(0, 9, 8, 0);
		gbcC.gridx = 4;
		this.panelC.add(this.ensTxtFld.get(15), gbcC);
		//
		gbcC.gridx = 5;
		this.panelC.add(this.ensTxtFld.get(16), gbcC);
		//
		gbcC.insets = new Insets(0, 50, 5, 0);
		gbcC.gridx = 6;
		this.panelC.add(this.ensTxtFld.get(17), gbcC);

		this.add(this.panelN, BorderLayout.NORTH);
		this.add(this.panelC, BorderLayout.CENTER);

		this.setVisible(true);

		// initialisation des listener
		for ( int cpt = 0; cpt < 18; cpt++ ) {
			this.ensTxtFld.get(cpt).addActionListener(this);
			this.ensTxtFld.get(cpt).addFocusListener(this);
		}

		if ( this.module != null )
			this.initValues();

	}

	public void initValues() {
		HashMap<TypeHeure, HashMap<String,Integer>> map = this.panelMere.getHeuresParTypesHeures(this.module);

		if ( map != null )
			for (TypeHeure typeHeure : map.keySet()) {
				switch (typeHeure.getNomTypeHeure()) {
					case "TUT" -> {
						this.ensTxtFld.get(3).setText(""+map.get(typeHeure).get("nb_heures"));
					}
					case "HP" -> {
						this.ensTxtFld.get(4).setText(""+map.get(typeHeure).get("nb_heures"));
					}
				}
			}

	}

	public HashMap<String, HashMap<String,Integer>> getDataHeuresTypesHeures() {

		HashMap<String, HashMap<String,Integer>> map = new HashMap<>();

		int nb;
		try { nb = Integer.parseInt(this.ensTxtFld.get(3).getText()); }
		catch (NumberFormatException e) { nb=0; }
		HashMap<String,Integer> temp = new HashMap<>();
		temp.put("nb_heures", nb);

		map.put("TUT", temp);

		try { nb = Integer.parseInt(this.ensTxtFld.get(4).getText()); }
		catch (NumberFormatException e) { nb=0; }
		temp = new HashMap<>();
		temp.put("nb_heures", nb);

		map.put("HP", temp);

		return map;
	}

	public void setLabelErreur(String message) {
		this.panelMere.setLabelErreur(message);
	}

	// méthode pour gérer la couleur du cadre en fonction de la validité de la
	// saisie
	public void setCouleurErreur(boolean b, JTextField txt) {
		if (b) {
			txt.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
		} else {
			txt.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		}
	}

	public void setSommeTD(int somme) {
		this.ensTxtFld.get(1).setText("" + somme);
		this.sommeActionTD = somme;
		this.clcTD = this.sommeActionTD * this.panelMere.getNbGpTd();
		//this.setSommeTotal();
	}

	public void setSommeTD() {
		this.ensTxtFld.get(1).setText("0");
		this.ensTxtFld.get(7).setText("0");
		this.repaint();
		this.revalidate();
	}

	public void setSommeTP(int somme) {
		this.ensTxtFld.get(2).setText("" + somme);
		this.sommeActionTP = somme;
		this.clcTP = this.sommeActionTP * this.panelMere.getNbGpTp();
		//this.setSommeTotal();
	}

	public void setSommeTP() {
		this.ensTxtFld.get(2).setText("0");
		this.ensTxtFld.get(8).setText("0");
		this.repaint();
		this.revalidate();
	}

	public void setSommeCM(int somme) {
		this.ensTxtFld.get(0).setText("" + somme);
		this.sommeActionCM = somme;
		this.clcCM = (int) (this.sommeActionCM * COEFF_CM_TD);
		//this.setSommeTotal();
	}

	public void setSommeCM() {
		this.ensTxtFld.get(0).setText("0");
		this.ensTxtFld.get(6).setText("0");
		this.repaint();
		this.revalidate();
	}

	public void setSommeTotal() {
		int somme = this.sommeActionTD + this.sommeActionTP + this.sommeActionCM + this.valTut + this.valPonct;
		int sommeAffecte = this.clcCM + this.clcTD + this.clcTP + this.valTut + this.valPonct;
		

		this.ensTxtFld.get(5).setText("" + somme);
		if (this.clcCM >= 0 && !this.ensTxtFld.get(0).getText().equals(""))
			this.ensTxtFld.get(6).setText("" + clcCM);
		if (this.clcTD >= 0 && !this.ensTxtFld.get(1).getText().equals(""))
			this.ensTxtFld.get(7).setText("" + clcTD);
		if (this.clcTP >= 0 && !this.ensTxtFld.get(2).getText().equals(""))
			this.ensTxtFld.get(8).setText("" + clcTP);
		if (this.valTut >= 0 && !this.ensTxtFld.get(3).getText().equals(""))
			this.ensTxtFld.get(9).setText("" + valTut);
		if (this.valPonct >= 0 && !this.ensTxtFld.get(4).getText().equals(""))
			this.ensTxtFld.get(10).setText("" + valPonct);
		this.ensTxtFld.get(11).setText("" + sommeAffecte);
		this.repaint();
		this.revalidate();
	}

	public int getSommeAffecte() {
		return Integer.parseInt(this.ensTxtFld.get(1).getText());
	}

	public void setSommeAffecte() {
		int calcul = Integer.parseInt(this.ensTxtFld.get(12).getText()) +
				Integer.parseInt(this.ensTxtFld.get(13).getText()) +
				Integer.parseInt(this.ensTxtFld.get(14).getText()) +
				Integer.parseInt(this.ensTxtFld.get(15).getText()) +
				Integer.parseInt(this.ensTxtFld.get(16).getText());
		this.ensTxtFld.get(17).setText("" + calcul);
	}

	public void setHeureAffecte(int hCM, int hTD, int hTP, int hTut, int hHP) {
		this.ensTxtFld.get(12).setText("" + hCM);
		this.ensTxtFld.get(13).setText("" + hTD);
		this.ensTxtFld.get(14).setText("" + hTP);
		this.ensTxtFld.get(15).setText("" + hTut);
		this.ensTxtFld.get(16).setText("" + hHP);
		this.setSommeAffecte();
		this.repaint();
		this.revalidate();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.ensTxtFld.get(3)) {
			try {
				if ( !this.panelMere.estChiffre(this.ensTxtFld.get(3).getText()) ) {
					this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
					this.setCouleurErreur(true, this.ensTxtFld.get(3));
				}
				if (this.ensTxtFld.get(3).getText().equals("") || Integer.parseInt(this.ensTxtFld.get(3).getText()) < 0)
					this.ensTxtFld.get(3).setText("0");
				this.setLabelErreur("");
				this.setCouleurErreur(false, this.ensTxtFld.get(3));
				this.valTut = Integer.parseInt(this.ensTxtFld.get(3).getText());
				this.setSommeTotal();
				this.ensTxtFld.get(3).transferFocus();
				this.repaint();
				this.revalidate();
			} catch (Exception err) {}

		} else if (e.getSource() == this.ensTxtFld.get(4)) {
			try {
				if (this.panelMere.estChiffre(this.ensTxtFld.get(4).getText()) == false) {
					this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
					this.setCouleurErreur(true, this.ensTxtFld.get(4));
				}
				if (this.ensTxtFld.get(4).getText().equals("") || Integer.parseInt(this.ensTxtFld.get(4).getText()) < 0)
					this.ensTxtFld.get(4).setText("0");
				this.setLabelErreur("");
				this.setCouleurErreur(false, this.ensTxtFld.get(4));
				this.valPonct = Integer.parseInt(this.ensTxtFld.get(4).getText());
				this.setSommeTotal();
				this.ensTxtFld.get(4).transferFocusBackward();
				this.repaint();
				this.revalidate();
			} catch (Exception err) {}

		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == this.ensTxtFld.get(3)) {
			if ( !this.panelMere.estChiffre(this.ensTxtFld.get(3).getText()) ) {
				this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
				this.setCouleurErreur(true, this.ensTxtFld.get(3));
			}
			try {
				if (this.ensTxtFld.get(3).getText().equals("") || Integer.parseInt(this.ensTxtFld.get(3).getText()) < 0)
					this.ensTxtFld.get(3).setText("0");
				this.setLabelErreur("");
				this.setCouleurErreur(false, this.ensTxtFld.get(3));
				if (Integer.parseInt(this.ensTxtFld.get(3).getText()) > 0) {
					this.valTut = Integer.parseInt(this.ensTxtFld.get(3).getText());
					this.setSommeTotal();
				} else {
					this.ensTxtFld.get(3).setText("0");
					this.valTut = Integer.parseInt(this.ensTxtFld.get(3).getText());
				}
				this.repaint();
					this.revalidate();
			} catch (Exception er) { }
		} else if ( e.getSource() == this.ensTxtFld.get(4) ) {
			if ( !this.panelMere.estChiffre(this.ensTxtFld.get(4).getText()) ) {
				this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
				this.setCouleurErreur(true, this.ensTxtFld.get(4));
			}
			try {
				if ( this.ensTxtFld.get(4).getText().equals("") || Integer.parseInt(this.ensTxtFld.get(4).getText()) < 0 )
					this.ensTxtFld.get(4).setText("0");
				this.setLabelErreur("");
				this.setCouleurErreur(false, this.ensTxtFld.get(4));
				if (Integer.parseInt(this.ensTxtFld.get(4).getText()) > 0) {
					this.valPonct = Integer.parseInt(this.ensTxtFld.get(4).getText());
					this.setSommeTotal();
				} else {
					this.ensTxtFld.get(4).setText("0");
					this.valPonct = Integer.parseInt(this.ensTxtFld.get(4).getText());
				}
				this.repaint();
				this.revalidate();
			} catch (Exception er) { }
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		if ( e.getSource() == this.ensTxtFld.get(3) ) {
			if ( !this.panelMere.estChiffre(this.ensTxtFld.get(3).getText()) ) {
				this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
				this.setCouleurErreur(true, this.ensTxtFld.get(3));
			} else{
				this.setLabelErreur("");
				this.setCouleurErreur(false, this.ensTxtFld.get(3));
			}
		} else if ( e.getSource() == this.ensTxtFld.get(4) ) {
			if ( !this.panelMere.estChiffre(this.ensTxtFld.get(4).getText()) ) {
				this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
				this.setCouleurErreur(true, this.ensTxtFld.get(4));
			} else {
				this.setLabelErreur("");
				this.setCouleurErreur(false, this.ensTxtFld.get(4));
			}
		}

	}

}
