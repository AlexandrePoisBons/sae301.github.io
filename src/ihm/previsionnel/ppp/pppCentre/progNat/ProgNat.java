package ihm.previsionnel.ppp.pppCentre.progNat;

import ihm.previsionnel.ppp.pppCentre.PanelPppCentre;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.HashMap;
import java.awt.Color;

import metier.Module;

public class ProgNat extends JPanel implements ActionListener, FocusListener {
	private static final double COEFF_CM_TD = 1.5;

	private PanelPppCentre panelMere;

	private JPanel panelPrincipal;
	private JPanel panelValidation;
	private JTextField txtHTutWrite;
	private JTextField txtHPonctWrite;
	private JTextField txtCMWrite;
	private JTextField txtTDWrite;
	private JTextField txtTPWrite;
	private JTextField txtCM;
	private JTextField txtTD;
	private JTextField txtTP;
	private JTextField txtSomme;
	private JTextField txtTotalSomme;
	private JCheckBox checkValid;

	private JLabel labelErreur;

	private Module module;

	private int sommeAction;
	private int sommeTotSansCoeff;
	private int sommeHPonct = 0;
	private int sommeHTut = 0;
	private int sommeCM = 0;
	private int sommeTD = 0;
	private int sommeTP = 0;

	private int totalSomme = 0;
	private int sommeCMAvecCoeff = 0;
	private int sommeTDAvecCoeff = 0;
	private int sommeTPAvecCoeff = 0;

	public ProgNat(PanelPppCentre panelMere, Module m) {
		this.panelMere = panelMere;
		this.module = m;

		this.setLayout(new BorderLayout());

		// Initialisation des composants
		JPanel pnlCentre = new JPanel();
		this.panelPrincipal = new JPanel();
		this.panelValidation = new JPanel();
		this.txtHTutWrite = new JTextField(4);
		this.txtHPonctWrite = new JTextField(4);
		this.txtCMWrite = new JTextField(4);
		this.txtTDWrite = new JTextField(4);
		this.txtTPWrite = new JTextField(4);
		this.txtCM = new JTextField(4);
		this.txtTD = new JTextField(4);
		this.txtTP = new JTextField(4);
		this.txtSomme = new JTextField(4);
		this.txtTotalSomme = new JTextField(4);
		this.labelErreur = new JLabel("");
		// set la couleur du label erreur en rouge
		this.labelErreur.setForeground(Color.RED);
		this.checkValid = new JCheckBox();
		this.checkValid.setSelected(this.module.isValide());
		this.panelPrincipal.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

		// Layouts
		pnlCentre.setLayout(new BorderLayout());
		this.panelPrincipal.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		// Rendre certain champ de sasie non modifiable
		this.txtCM.setEditable(false);
		this.txtTD.setEditable(false);
		this.txtTP.setEditable(false);
		this.txtSomme.setEditable(false);
		this.txtTotalSomme.setEditable(false);

		// Ajout des composants avec GridBagLayout
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.insets = new Insets(11, 0, 2, 0);
		this.panelPrincipal.add(new JLabel("H tut"), gbc);
		gbc.gridx = 2;
		this.panelPrincipal.add(new JLabel("H ponct"), gbc);
		gbc.gridx = 3;
		this.panelPrincipal.add(new JLabel("CM"), gbc);
		gbc.insets = new Insets(11, 5, 2, 0);
		gbc.gridx = 4;
		this.panelPrincipal.add(new JLabel("TD"), gbc);
		gbc.gridx = 5;
		this.panelPrincipal.add(new JLabel("TP"), gbc);
		gbc.gridx = 6;
		this.panelPrincipal.add(new JLabel("Σ"), gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.insets = new Insets(0, 0, 2, 0);
		this.panelPrincipal.add(this.txtHTutWrite, gbc);
		gbc.gridx = 2;
		this.panelPrincipal.add(this.txtHPonctWrite, gbc);
		gbc.gridx = 3;
		this.panelPrincipal.add(this.txtCMWrite, gbc);
		gbc.insets = new Insets(0, 5, 2, 0);
		gbc.gridx = 4;
		this.panelPrincipal.add(this.txtTDWrite, gbc);
		gbc.gridx = 5;
		this.panelPrincipal.add(this.txtTPWrite, gbc);
		gbc.gridx = 6;
		this.panelPrincipal.add(this.txtSomme, gbc);

		gbc.gridx = 3;
		gbc.gridy = 2;
		this.panelPrincipal.add(this.txtCM, gbc);
		gbc.insets = new Insets(0, 5, 2, 0);
		gbc.gridx = 4;
		this.panelPrincipal.add(this.txtTD, gbc);
		gbc.gridx = 5;
		this.panelPrincipal.add(this.txtTP, gbc);
		gbc.gridx = 6;
		this.panelPrincipal.add(this.txtTotalSomme, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		this.panelPrincipal.add(new JLabel("Total (eqtd) promo"), gbc);

		// Ajout des composants
		this.panelValidation.add(new JLabel("Validation"));
		this.panelValidation.add(this.checkValid);

		pnlCentre.add(this.panelValidation, BorderLayout.NORTH);
		pnlCentre.add(this.labelErreur, BorderLayout.WEST);

		this.add(this.panelPrincipal, BorderLayout.NORTH);
		this.add(pnlCentre, BorderLayout.CENTER);

		// Ajout des listeners
		this.txtHPonctWrite.addActionListener(this);
		this.txtHTutWrite.addActionListener(this);
		this.txtCMWrite.addActionListener(this);
		this.txtTDWrite.addActionListener(this);
		this.txtTPWrite.addActionListener(this);
		this.txtHPonctWrite.addFocusListener(this);
		this.txtHTutWrite.addFocusListener(this);
		this.txtCMWrite.addFocusListener(this);
		this.txtTDWrite.addFocusListener(this);
		this.txtTPWrite.addFocusListener(this);

		this.setVisible(true);
	}

	public HashMap<String, Integer> getHeuresTot() {

		HashMap<String, Integer> map = new HashMap<>();

		try {
			map.put("Ponct", Integer.parseInt(this.txtHPonctWrite.getText()));
		} catch (NumberFormatException e) {
			map.put("SAE", 0);
		}

		try {
			map.put("TUT", Integer.parseInt(this.txtHTutWrite.getText()));
		} catch (NumberFormatException e) {
			map.put("TUT", 0);
		}

		return map;
	}

	private boolean estChiffre(String texte) {
		// Vérifie chaque caractère dans la chaîne pour s'assurer qu'il s'agit d'un
		// chiffre.
		for ( char c : texte.toCharArray() ) {
			if ( !Character.isDigit(c) ) {
				return false;
			}
		}
		return true;
	}

	// méthode pour gérer la couleur du cadre en fonction de la validité de la
	// saisie
	public void setCouleurErreur(boolean b, JTextField txt) {
		if ( b ) {
			txt.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
		} else {
			txt.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		}
	}

	// méthode pour set le labelErreur
	public void setLabelErreur(String s) {
		this.labelErreur.setText(s);
		this.labelErreur.repaint();
		this.labelErreur.revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.txtHPonctWrite) {
			this.txtHPonctWrite.transferFocus();
		} else if (e.getSource() == this.txtHTutWrite) {
			this.txtHTutWrite.transferFocus();
		} else if (e.getSource() == this.txtCMWrite) {
			this.txtCMWrite.transferFocus();
		} else if (e.getSource() == this.txtTDWrite) {
			this.txtTDWrite.transferFocus();
		} else if (e.getSource() == this.txtTPWrite) {
			this.txtTPWrite.transferFocus();
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if ( e.getSource() == this.txtHPonctWrite ) {
			try {
				// Vérification que la saisie de cette valeur n'a pas déjas été enregistrée dans
				// somme
				if ( this.estChiffre(this.txtHPonctWrite.getText()) == false ) {
					this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
					this.setCouleurErreur(true, this.txtHPonctWrite);
				}
				if ( this.txtHPonctWrite.getText().equals(Integer.toString(sommeHPonct) )
						&& this.txtHPonctWrite.getText().equals("")) {
					System.out.println("Cette valeur a déjas été prise en compte");
				} else {
					if ( this.txtHPonctWrite.getText().equals("") || Integer.parseInt(this.txtHPonctWrite.getText()) < 0 )
						this.txtHPonctWrite.setText("0");
					this.setLabelErreur("");
					this.setCouleurErreur(false, this.txtHPonctWrite);
					sommeHPonct = Integer.parseInt(this.txtHPonctWrite.getText());
				}
			} catch (NumberFormatException ex) { }

		} else if ( e.getSource() == this.txtHTutWrite ) {
			try {
				// Vérification que la saisie de cette valeur n'a pas déjas été enregistrée dans
				// somme
				if (this.estChiffre(this.txtHTutWrite.getText()) == false) {
					this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
					this.setCouleurErreur(true, this.txtHTutWrite);
				}
				if (this.txtHTutWrite.getText().equals(Integer.toString(sommeHTut))
						&& this.txtHTutWrite.getText().equals("")) {
					System.out.println("Cette valeur a déjas été prise en compte");
				} else {
					if (this.txtHTutWrite.getText().equals("") || Integer.parseInt(this.txtHTutWrite.getText()) < 0)
						this.txtHTutWrite.setText("0");
					this.setLabelErreur("");
					this.setCouleurErreur(false, this.txtHTutWrite);
					sommeHTut = Integer.parseInt(this.txtHTutWrite.getText());
				}
			} catch (NumberFormatException ex) { }

		} else if ( e.getSource() == this.txtCMWrite ) {
			try {
				// Vérification que la saisie de cette valeur n'a pas déjas été enregistrée dans
				// somme

				if (this.estChiffre(this.txtCMWrite.getText()) == false) {
					this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
					this.setCouleurErreur(true, this.txtCMWrite);
				}
				if (this.txtCMWrite.getText().equals(Integer.toString(sommeCM))
						&& (this.txtTPWrite.getText().equals("") && this.txtTDWrite.getText().equals(""))) {
					System.out.println("Cette valeur a déjas été prise en compte");
				} else {
					if (this.txtCMWrite.getText().equals("") || Integer.parseInt(this.txtCMWrite.getText()) < 0)
						this.txtCMWrite.setText("0");
					this.setLabelErreur("");
					this.setCouleurErreur(false, this.txtCMWrite);
					sommeCM = Integer.parseInt(this.txtCMWrite.getText());
					// calcul du txtField CM non modifiable (txtField modifiable * coeffCM_TD)
					this.sommeCMAvecCoeff = (int) (Integer.parseInt(this.txtCMWrite.getText()) * COEFF_CM_TD);
					this.txtCM.setText(Integer.toString(this.sommeCMAvecCoeff));
					this.txtCM.repaint();
					this.txtCM.revalidate();
				}
			} catch (Exception err) { }

		} else if ( e.getSource() == this.txtTDWrite ) {
			try {
				// Vérification que la saisie de cette valeur n'a pas déjas été enregistrée dans
				// somme
				if (this.estChiffre(this.txtTDWrite.getText()) == false) {
					this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
					this.setCouleurErreur(true, this.txtTDWrite);
				}
				if (this.txtTDWrite.getText().equals(Integer.toString(sommeTD))
						&& (this.txtTPWrite.getText().equals("") && this.txtCMWrite.getText().equals(""))) {
					System.out.println("Cette valeur a déjas été prise en compte");
				} else {
					if (this.txtTDWrite.getText().equals("") || Integer.parseInt(this.txtTDWrite.getText()) < 0)
						this.txtTDWrite.setText("0");
					this.setLabelErreur("");
					this.setCouleurErreur(false, this.txtTDWrite);
					sommeTD = Integer.parseInt(this.txtTDWrite.getText());
					// calcul du txtField TD non modifiable (txtField modifiable * nbGpTD de
					// panelMere)
					this.sommeTDAvecCoeff = (int) (Integer.parseInt(this.txtTDWrite.getText())
							* this.panelMere.getNbGpTd());
					this.txtTD.setText(Integer.toString(this.sommeTDAvecCoeff));
					this.txtTD.repaint();
					this.txtTD.revalidate();
				}
			} catch (Exception err) { }

		} else if ( e.getSource() == this.txtTPWrite ) {
			try {
				// Vérification que la saisie de cette valeur n'a pas déjas été enregistrée dans
				// somme
				if (this.estChiffre(this.txtTPWrite.getText()) == false) {
					this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
					this.setCouleurErreur(true, this.txtTPWrite);
				}
				if (this.txtTPWrite.getText().equals(Integer.toString(sommeTP))
						&& (this.txtCMWrite.getText().equals("") && this.txtTDWrite.getText().equals(""))) {
					System.out.println("Cette valeur a déjas été prise en compte");
				} else {
					if (this.txtTPWrite.getText().equals("") || Integer.parseInt(this.txtTPWrite.getText()) < 0)
						this.txtTPWrite.setText("0");
					this.setLabelErreur("");
					this.setCouleurErreur(false, this.txtTPWrite);
					sommeTP = Integer.parseInt(this.txtTPWrite.getText());
					// calcul du txtField TP non modifiable (txtField modifiable * nbGpTP de
					// panelMere)
					this.sommeTPAvecCoeff = (int) (Integer.parseInt(this.txtTPWrite.getText())
							* this.panelMere.getNbGpTp());
					this.txtTP.setText(Integer.toString(this.sommeTPAvecCoeff));
					this.txtTP.repaint();
					this.txtTP.revalidate();
				}
			} catch (Exception err) { }
		}

		this.sommeTotSansCoeff = sommeHPonct + sommeHTut + sommeCM + sommeTD + sommeTP;
		this.txtSomme.setText(Integer.toString(this.sommeTotSansCoeff));

		this.totalSomme = this.sommeCMAvecCoeff + this.sommeTPAvecCoeff + this.sommeTDAvecCoeff;
		this.txtTotalSomme.setText(Integer.toString(this.totalSomme));
		this.txtTotalSomme.repaint();
		this.txtTotalSomme.revalidate();
	}

	@Override
	public void focusGained(FocusEvent e) {
		// ré-affichage du label erreur si il y a une erreur dans un des txtField
		if ( e.getSource() == this.txtCMWrite ) {
			if (this.estChiffre(this.txtCMWrite.getText()) == false) {
				this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
				this.setCouleurErreur(true, this.txtCMWrite);
			} else {
				this.setLabelErreur("");
				this.setCouleurErreur(false, this.txtCMWrite);
			}
		} else if ( e.getSource() == this.txtTDWrite ) {
			if (this.estChiffre(this.txtTDWrite.getText()) == false) {
				this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
				this.setCouleurErreur(true, this.txtTDWrite);
			} else {
				this.setLabelErreur("");
				this.setCouleurErreur(false, this.txtTDWrite);
			}
		} else if ( e.getSource() == this.txtTPWrite ) {
			if (this.estChiffre(this.txtTPWrite.getText()) == false) {
				this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
				this.setCouleurErreur(true, this.txtTPWrite);
			} else {
				this.setLabelErreur("");
				this.setCouleurErreur(false, this.txtTPWrite);
			}
		} else if ( e.getSource() == this.txtHPonctWrite ) {
			if (this.estChiffre(this.txtHPonctWrite.getText()) == false) {
				this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
				this.setCouleurErreur(true, this.txtHPonctWrite);
			} else {
				this.setLabelErreur("");
				this.setCouleurErreur(false, this.txtHPonctWrite);
			}
		} else if ( e.getSource() == this.txtHTutWrite ) {
			if (this.estChiffre(this.txtHTutWrite.getText()) == false) {
				this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
				this.setCouleurErreur(true, this.txtHTutWrite);
			} else {
				this.setLabelErreur("");
				this.setCouleurErreur(false, this.txtHTutWrite);
			}
		}
	}

	public boolean estValide() { return this.checkValid.isSelected(); }

	public int getSommePN() { return this.sommeAction; }

}
