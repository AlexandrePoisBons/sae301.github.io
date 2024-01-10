package ihm.previsionnel.ressources.ressourcesCentre.progNat;

import javax.swing.*;

import ihm.previsionnel.ressources.ressourcesCentre.PRCentre;

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

public class ProgNat extends JPanel implements FocusListener, ActionListener {
	// Constante coefficient de conversion des heures de CM en heures TD (1h de TD =
	// 1.5h CM)
	private static final double COEFF_CM_TD = 1.5;

	private PRCentre panelMere;

	private JPanel panelPrincipal;
	private JPanel panelValidation;
	private JTextField txtCMWrite;
	private JTextField txtTDWrite;
	private JTextField txtTPWrite;
	private JTextField txtOEWrite;
	private JTextField txtCM;
	private JTextField txtTD;
	private JTextField txtTP;
	private JTextField txtOE;
	private JCheckBox checkValid;

	private JLabel labelErreur;

	private Module module;

	private int sommeCM = 0;
	private int sommeTD = 0;
	private int sommeTP = 0;

	private int totalSomme = 0;
	private int sommeAvecCoeff = 0;
	private int sommeCMAvecCoeff = 0;
	private int sommeTDAvecCoeff = 0;
	private int sommeTPAvecCoeff = 0;

	public ProgNat(PRCentre panelMere, Module m) {
		this.panelMere = panelMere;
		this.module = m;

		this.setLayout(new BorderLayout());
		// Initialisation des composants
		JPanel pnlCentre = new JPanel();
		this.panelPrincipal = new JPanel();
		this.panelValidation = new JPanel();
		this.txtCMWrite = new JTextField(4);
		this.txtTDWrite = new JTextField(4);
		this.txtTPWrite = new JTextField(4);
		this.txtOEWrite = new JTextField(4);
		this.txtCM = new JTextField(4);
		this.txtTD = new JTextField(4);
		this.txtTP = new JTextField(4);
		this.txtOE = new JTextField(4);
		this.labelErreur = new JLabel("");
		//set la couleur du label erreur en rouge
		this.labelErreur.setForeground(Color.RED);
		this.checkValid = new JCheckBox();
		this.checkValid.setSelected(this.module.isValide());
		this.panelPrincipal.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

		// Rendre certain champ de sasie non modifiable
		this.txtCM.setEditable(false);
		this.txtTD.setEditable(false);
		this.txtTP.setEditable(false);
		this.txtOE.setEditable(false);
		this.txtOEWrite.setEditable(false);

		// Layouts
		// this.setLayout(new BorderLayout());
		pnlCentre.setLayout(new BorderLayout());
		this.panelPrincipal.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		// Ajout des composants avec GridBagLayout
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.insets = new Insets(11, 0, 2, 0);
		this.panelPrincipal.add(new JLabel("CM"), gbc);
		gbc.gridx = 2;
		this.panelPrincipal.add(new JLabel("TD"), gbc);
		gbc.gridx = 3;
		this.panelPrincipal.add(new JLabel("TP"), gbc);
		gbc.insets = new Insets(11, 5, 2, 0);
		gbc.gridx = 4;
		this.panelPrincipal.add(new JLabel("OE"), gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.insets = new Insets(0, 0, 2, 0);
		this.panelPrincipal.add(this.txtCMWrite, gbc);
		gbc.gridx = 2;
		this.panelPrincipal.add(this.txtTDWrite, gbc);
		gbc.gridx = 3;
		this.panelPrincipal.add(this.txtTPWrite, gbc);
		gbc.insets = new Insets(0, 5, 2, 0);
		gbc.gridx = 4;
		this.panelPrincipal.add(this.txtOEWrite, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.insets = new Insets(0, 0, 2, 0);
		this.panelPrincipal.add(this.txtCM, gbc);
		gbc.gridx = 2;
		this.panelPrincipal.add(this.txtTD, gbc);
		gbc.gridx = 3;
		this.panelPrincipal.add(this.txtTP, gbc);
		gbc.insets = new Insets(0, 5, 2, 0);
		gbc.gridx = 4;
		this.panelPrincipal.add(this.txtOE, gbc);

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

		// implémentation des listener
		this.txtCMWrite.addFocusListener(this);
		this.txtTDWrite.addFocusListener(this);
		this.txtTPWrite.addFocusListener(this);
		this.txtOEWrite.addFocusListener(this);

		this.txtCMWrite.addActionListener(this);
		this.txtTDWrite.addActionListener(this);
		this.txtTPWrite.addActionListener(this);
		this.txtOEWrite.addActionListener(this);

		this.setVisible(true);
	}

	public HashMap<String, Integer> getHeuresTot() {
		HashMap<String, Integer> map = new HashMap<>();

		try {
			map.put("CM", Integer.parseInt(this.txtCMWrite.getText()));
		} catch (NumberFormatException e) {
			map.put("CM", 0);
		}
		try {
			map.put("TD", Integer.parseInt(this.txtTDWrite.getText()));
		} catch (NumberFormatException e) {
			map.put("TD", 0);
		}
		try {
			map.put("TP", Integer.parseInt(this.txtTPWrite.getText()));
		} catch (NumberFormatException e) {
			map.put("TP", 0);
		}

		return map;
	}
	private boolean estChiffre(String texte) {
        // Vérifie chaque caractère dans la chaîne pour s'assurer qu'il s'agit d'un chiffre.
        for (char c : texte.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

	//méthode pour gérer la couleur du cadre en fonction de la validité de la saisie
	public void setCouleurErreur(boolean b, JTextField txt) {
		if (b == true) {
			txt.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
		} else {
			txt.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		}
	}

	//méthode pour set le labelErreur
	public void setLabelErreur(String s) {
		this.labelErreur.setText(s);
		this.labelErreur.repaint();
		this.labelErreur.revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.txtCMWrite) {
			this.txtCMWrite.transferFocus();
		} else if (e.getSource() == this.txtTDWrite) {
			this.txtTDWrite.transferFocus();
		} else if (e.getSource() == this.txtTPWrite) {
			this.txtTPWrite.transferFocus();
		}

	}

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == this.txtCMWrite) {
			try {
				// Vérification que la saisie de cette valeur n'a pas déjas été enregistrée dans
				// somme
				if(this.estChiffre(this.txtCMWrite.getText()) == false){
					//this.txtCMWrite.setText("0");
					this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
					this.setCouleurErreur(true, this.txtCMWrite);
				}
				if (this.txtCMWrite.getText().equals(Integer.toString(this.sommeCM))
						&& (this.txtTPWrite.getText().equals("") && this.txtTDWrite.getText().equals(""))) {
					System.out.println("Cette valeur a déjas été prise en compte");
				} else {
					if (this.txtCMWrite.getText().equals("") ||Integer.parseInt(this.txtCMWrite.getText()) < 0 )
						this.txtCMWrite.setText("0");
					this.setLabelErreur("");
					this.setCouleurErreur(false, this.txtCMWrite);
					this.sommeCM = Integer.parseInt(this.txtCMWrite.getText());
					// calcul du txtField CM non modifiable (txtField modifiable * coeffCM_TD)
					this.sommeCMAvecCoeff = (int) (Integer.parseInt(this.txtCMWrite.getText()) * COEFF_CM_TD);
					this.txtCM.setText(Integer.toString(this.sommeCMAvecCoeff));
					this.txtCM.repaint();
					this.txtCM.revalidate();
				}
			} catch (Exception err) {
				//gestion des erreurs de saisies via le labelErreur
				System.out.print("");
			}
		}

		if (e.getSource() == this.txtTDWrite) {
			try {
				// Vérification que la saisie de cette valeur n'a pas déjas été enregistrée dans
				// somme
				if(this.estChiffre(this.txtTDWrite.getText()) == false){
					//this.txtTDWrite.setText("0");
					this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
					this.setCouleurErreur(true, this.txtTDWrite);
				}
				if (this.txtTDWrite.getText().equals(Integer.toString(this.sommeTD))
						&& (this.txtTPWrite.getText().equals("") && this.txtCMWrite.getText().equals(""))) {
					System.out.println("Cette valeur a déjas été prise en compte");
				} else {
					if (this.txtTDWrite.getText().equals("") || Integer.parseInt(this.txtTDWrite.getText()) < 0)
						this.txtTDWrite.setText("0");
					this.setLabelErreur("");
					this.setCouleurErreur(false, this.txtTDWrite);
					this.sommeTD = Integer.parseInt(this.txtTDWrite.getText());
					// calcul du txtField TD non modifiable (txtField modifiable * nbGpTD de
					// panelMere)
					this.sommeTDAvecCoeff = (int) (Integer.parseInt(this.txtTDWrite.getText())
							* this.panelMere.getNbGpTd());
					this.txtTD.setText(Integer.toString(this.sommeTDAvecCoeff));
					this.txtTD.repaint();
					this.txtTD.revalidate();
				}
			} catch (Exception err) {
				//gestion des erreurs de saisies via le labelErreur
				System.out.print("");
			}
		}

		if (e.getSource() == this.txtTPWrite) {
			try {
				// Vérification que la saisie de cette valeur n'a pas déjas été enregistrée dans
				// somme
				if(this.estChiffre(this.txtTPWrite.getText()) == false){
					//this.txtTPWrite.setText("");
					this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
					this.setCouleurErreur(true, this.txtTPWrite);
				}
				else {
					if (this.txtTPWrite.getText().equals("") || Integer.parseInt(this.txtTPWrite.getText()) < 0	)
						this.txtTPWrite.setText("0");
					this.setLabelErreur("");
					this.setCouleurErreur(false, this.txtTPWrite);
					this.sommeTP = Integer.parseInt(this.txtTPWrite.getText());
					// calcul du txtField TP non modifiable (txtField modifiable * nbGpTP de
					// panelMere)
					this.sommeTPAvecCoeff = (int) (Integer.parseInt(this.txtTPWrite.getText())
							* this.panelMere.getNbGpTp());
					this.txtTP.setText(Integer.toString(this.sommeTPAvecCoeff));
					this.txtTP.repaint();
					this.txtTP.revalidate();
				}
			} catch (Exception err) {
				//gestion des erreurs de saisies via le labelErreur
				System.out.print("");
			}
		}

		this.totalSomme = this.sommeCM + this.sommeTD + this.sommeTP;
		this.txtOEWrite.setText(Integer.toString(this.totalSomme));
		this.txtOEWrite.repaint();
		this.txtOEWrite.revalidate();

		this.sommeAvecCoeff = this.sommeCMAvecCoeff + this.sommeTDAvecCoeff + this.sommeTPAvecCoeff;
		this.txtOE.setText(Integer.toString(this.sommeAvecCoeff));
		this.txtOE.repaint();
		this.txtOE.revalidate();
	}

	public boolean estValide() { 
		return this.checkValid.isSelected(); 
	}

	// Méthode inutilisée mais obligatoire
	@Override
	public void focusGained(FocusEvent e) {
		// ré-affichage du label erreur si il y a une erreur dans un des txtField
		if (e.getSource() == this.txtCMWrite) {
			if (this.estChiffre(this.txtCMWrite.getText()) == false) {
				this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
				this.setCouleurErreur(true, this.txtCMWrite);
			}
			else{
				this.setLabelErreur("");
				this.setCouleurErreur(false, this.txtCMWrite);
			}
		}
		

		if (e.getSource() == this.txtTDWrite) {
			if (this.estChiffre(this.txtTDWrite.getText()) == false) {
				this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
				this.setCouleurErreur(true, this.txtTDWrite);
			}
			else{
				this.setLabelErreur("");
				this.setCouleurErreur(false, this.txtTDWrite);
			}
		}
		

		if (e.getSource() == this.txtTPWrite) {
			if (this.estChiffre(this.txtTPWrite.getText()) == false) {
				this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
				this.setCouleurErreur(true, this.txtTPWrite);
			}
			else{
				this.setLabelErreur("");
				this.setCouleurErreur(false, this.txtTPWrite);
			}
		}
		

	 }

}