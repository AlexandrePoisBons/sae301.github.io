package ihm.previsionnel.ppp.pppCentre.repartition.heure;

import java.awt.BorderLayout;
import java.awt.Color;
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

public class PanelRepartitionHGauche extends JPanel implements ActionListener, FocusListener {
	private PanelRepartitionHeure panelMere;
	private JPanel panelHeure;
	private ArrayList<JTextField> ensJTextField;

	public PanelRepartitionHGauche(PanelRepartitionHeure panelMere) {
		this.panelMere = panelMere;
		this.panelHeure = new JPanel();
		this.ensJTextField = new ArrayList<JTextField>();

		this.setLayout(new GridLayout(2, 1));
		this.panelHeure.setLayout(new BorderLayout());

		JPanel panelHN = new JPanel();
		JPanel panelHC = new JPanel();
		JPanel panelB = new JPanel();
		for (int cpt = 0; cpt < 6; cpt++) {
			JTextField jtf = new JTextField(3);
			this.ensJTextField.add(jtf);
		}

		panelHN.setLayout(new GridLayout(1, 3));
		panelHC.setLayout(new GridBagLayout());
		GridBagConstraints gbcH = new GridBagConstraints();
		panelB.setLayout(new GridLayout(2, 1));

		gbcH.gridx = 0;
		gbcH.gridy = 0;
		panelHC.add(new JLabel("nbSem"), gbcH);
		gbcH.gridx = 1;
		panelHC.add(new JLabel("nbH"), gbcH);
		gbcH.gridx = 2;
		gbcH.insets = new Insets(0, 7, 5, 0);
		panelHC.add(new JLabel("nbSem"), gbcH);
		gbcH.insets = new Insets(0, 0, 5, 0);
		gbcH.gridx = 3;
		panelHC.add(new JLabel("nbH"), gbcH);
		gbcH.gridx = 4;
		gbcH.insets = new Insets(0, 7, 5, 0);
		panelHC.add(new JLabel("nbSem"), gbcH);
		gbcH.insets = new Insets(0, 0, 5, 0);
		gbcH.gridx = 5;
		panelHC.add(new JLabel("nbH"), gbcH);

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

		panelB.add(new JLabel("Total promo (eqtd)", JLabel.RIGHT));
		panelB.add(new JLabel("Total affect (eqtd)", JLabel.RIGHT));

		this.panelHeure.add(panelHN, BorderLayout.NORTH);
		this.panelHeure.add(panelHC, BorderLayout.CENTER);

		this.add(this.panelHeure);
		this.add(panelB);

		this.setVisible(true);

		// initialisatasion des listener
		for (int cpt = 0; cpt < 6; cpt++) {
			this.ensJTextField.get(cpt).addActionListener(this);
			this.ensJTextField.get(cpt).addFocusListener(this);
		}

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

	public HashMap<String, Integer> getNbSemaines() {
		HashMap<String, Integer> map = new HashMap<>();

		try {
			map.put("SAE", Integer.parseInt(this.ensJTextField.get(0).getText()));
		} catch (NumberFormatException e) {
			map.put("SAE", 0);
		}

		try {
			map.put("TUT", Integer.parseInt(this.ensJTextField.get(1).getText()));
		} catch (NumberFormatException e) {
			map.put("TUT", 0);
		}

		return map;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.ensJTextField.get(0)) {
			// pour que le focus passe au champ suivant quand l'utilisteur clique sur
			// "entrée"
			this.ensJTextField.get(0).transferFocus();
		} else if (e.getSource() == this.ensJTextField.get(1)) {

			this.ensJTextField.get(1).transferFocus();
		} else if (e.getSource() == this.ensJTextField.get(2)) {
			this.ensJTextField.get(2).transferFocus();
		} else if (e.getSource() == this.ensJTextField.get(3)) {
			this.ensJTextField.get(3).transferFocus();
		} else if (e.getSource() == this.ensJTextField.get(4)) {
			this.ensJTextField.get(4).transferFocus();
		} else if (e.getSource() == this.ensJTextField.get(5)) {
			this.ensJTextField.get(5).transferFocus();
		}

	}

	@Override
	public void focusLost(FocusEvent e) {
		try {
			// Addition des heures saisies dans les champs
			int somme = -1;
			if (e.getSource() == this.ensJTextField.get(0)) {
				// Vérification que la saisie de cette valeur n'a pas déjas été enregistrée dans
				// somme
				if (this.panelMere.estChiffre(this.ensJTextField.get(0).getText()) == false) {
					this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
					this.setCouleurErreur(true, this.ensJTextField.get(0));
				}
				if (this.ensJTextField.get(0).getText().equals(Integer.toString(somme))
						&& this.ensJTextField.get(1).getText().equals("")) {
					System.out.println("Cette valeur a déjas été prise en compte");
				} else {
					if (this.ensJTextField.get(0).getText().equals("")
							|| Integer.parseInt(this.ensJTextField.get(0).getText()) < 0)
						this.ensJTextField.get(0).setText("0");
					this.setLabelErreur("");
					this.setCouleurErreur(false, this.ensJTextField.get(0));
					if (this.ensJTextField.get(1).getText().equals("0")
							|| this.ensJTextField.get(1).getText().equals("")) {
						somme += Integer.parseInt(this.ensJTextField.get(0).getText());
						this.panelMere.setSommeCM();
						this.panelMere.setSommeTotal();
					}

					else {
						somme = Integer.parseInt(this.ensJTextField.get(1).getText())
								* Integer.parseInt(this.ensJTextField.get(0).getText());
						this.panelMere.setSommeCM(somme);
						this.panelMere.setSommeTotal();
					}
				}
			}

			if (e.getSource() == this.ensJTextField.get(1)) {
				try {
					// Vérification que la saisie de cette valeur n'a pas déjas été enregistrée dans
					// somme
					if (this.panelMere.estChiffre(this.ensJTextField.get(1).getText()) == false) {
						this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
						this.setCouleurErreur(true, this.ensJTextField.get(1));
					}
					if (this.ensJTextField.get(1).getText().equals(Integer.toString(somme))
							&& this.ensJTextField.get(0).getText().equals("")) {
						System.out.println("Cette valeur a déjas été prise en compte");
					} else {
						if (this.ensJTextField.get(1).getText().equals("")
								|| Integer.parseInt(this.ensJTextField.get(1).getText()) < 0)
							this.ensJTextField.get(1).setText("0");
						this.setLabelErreur("");
						this.setCouleurErreur(false, this.ensJTextField.get(1));
						if (this.ensJTextField.get(0).getText().equals("0")
								|| this.ensJTextField.get(0).getText().equals("")) {
							somme += Integer.parseInt(this.ensJTextField.get(1).getText());
							this.panelMere.setSommeCM();
							this.panelMere.setSommeTotal();
						} else {
							somme = Integer.parseInt(this.ensJTextField.get(1).getText())
									* Integer.parseInt(this.ensJTextField.get(0).getText());
							this.panelMere.setSommeCM(somme);
							this.panelMere.setSommeTotal();
						}
					}
				}

				catch (NumberFormatException ex) {}
			}

		} catch (Exception ex) {}

		try {
			// Addition des heures saisies dans les champs
			int somme = -1;
			if (e.getSource() == this.ensJTextField.get(2)) {
				try {
					// Vérification que la saisie de cette valeur n'a pas déjas été enregistrée dans
					// somme
					if (this.panelMere.estChiffre(this.ensJTextField.get(2).getText()) == false) {
						this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
						this.setCouleurErreur(true, this.ensJTextField.get(2));
					}
					if (this.ensJTextField.get(2).getText().equals(Integer.toString(somme))
							&& this.ensJTextField.get(3).getText().equals("")) {
						System.out.println("Cette valeur a déjas été prise en compte");
					} else {
						if (this.ensJTextField.get(2).getText().equals("")
								|| Integer.parseInt(this.ensJTextField.get(2).getText()) < 0)
							this.ensJTextField.get(2).setText("0");
						this.setLabelErreur("");
						this.setCouleurErreur(false, this.ensJTextField.get(2));
						if (this.ensJTextField.get(3).getText().equals("0")
								|| this.ensJTextField.get(3).getText().equals("")) {
							somme += Integer.parseInt(this.ensJTextField.get(2).getText());
							this.panelMere.setSommeTD();
							this.panelMere.setSommeTotal();
						} else {
							somme = Integer.parseInt(this.ensJTextField.get(2).getText())
									* Integer.parseInt(this.ensJTextField.get(3).getText());
							this.panelMere.setSommeTD(somme);
							this.panelMere.setSommeTotal();
						}
					}
				}

				catch (NumberFormatException ex) {}
			}

			if (e.getSource() == this.ensJTextField.get(3)) {
				try {
					// Vérification que la saisie de cette valeur n'a pas déjas été enregistrée dans
					// somme
					if (this.panelMere.estChiffre(this.ensJTextField.get(3).getText()) == false) {
						this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
						this.setCouleurErreur(true, this.ensJTextField.get(3));
					}
					if (this.ensJTextField.get(3).getText().equals(Integer.toString(somme))
							&& this.ensJTextField.get(2).getText().equals("")) {
						System.out.println("Cette valeur a déjas été prise en compte");
					} else {
						if (this.ensJTextField.get(3).getText().equals("")
								|| Integer.parseInt(this.ensJTextField.get(3).getText()) < 0)
							this.ensJTextField.get(3).setText("0");
						this.setLabelErreur("");
						this.setCouleurErreur(false, this.ensJTextField.get(3));
						if (this.ensJTextField.get(2).getText().equals("0")
								|| this.ensJTextField.get(2).getText().equals("")) {
							somme += Integer.parseInt(this.ensJTextField.get(3).getText());
							this.panelMere.setSommeTD();
							this.panelMere.setSommeTotal();
						} else {
							somme = Integer.parseInt(this.ensJTextField.get(2).getText())
									* Integer.parseInt(this.ensJTextField.get(3).getText());
							this.panelMere.setSommeTD(somme);
							this.panelMere.setSommeTotal();
						}
					}
				}

				catch (NumberFormatException ex) {}
			}
		} catch (Exception ex) {}

		try {
			// Addition des heures saisies dans les champs
			int somme = -1;

			if (e.getSource() == this.ensJTextField.get(4)) {
				try {
					// Vérification que la saisie de cette valeur n'a pas déjas été enregistrée dans
					// somme
					if (this.panelMere.estChiffre(this.ensJTextField.get(4).getText()) == false) {
						this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
						this.setCouleurErreur(true, this.ensJTextField.get(4));
					}
					if (this.ensJTextField.get(4).getText().equals(Integer.toString(somme))
							&& this.ensJTextField.get(5).getText().equals("")) {
						// System.out.println("Cette valeur a déjas été prise en compte");
					} else {
						if (this.ensJTextField.get(4).getText().equals("")
								|| Integer.parseInt(this.ensJTextField.get(4).getText()) < 0)
							this.ensJTextField.get(4).setText("0");
						this.setLabelErreur("");
						this.setCouleurErreur(false, this.ensJTextField.get(4));
						if (this.ensJTextField.get(5).getText().equals("0")
								|| this.ensJTextField.get(5).getText().equals("")) {
							somme += Integer.parseInt(this.ensJTextField.get(4).getText());
							this.panelMere.setSommeTP();
							this.panelMere.setSommeTotal();
						} else {
							somme = Integer.parseInt(this.ensJTextField.get(4).getText())
									* Integer.parseInt(this.ensJTextField.get(5).getText());
							this.panelMere.setSommeTP(somme);
							this.panelMere.setSommeTotal();
						}
					}
				}

				catch (NumberFormatException ex) {}
			}

			if (e.getSource() == this.ensJTextField.get(5)) {
				try {
					// Vérification que la saisie de cette valeur n'a pas déjas été enregistrée dans
					// somme
					if (this.panelMere.estChiffre(this.ensJTextField.get(5).getText()) == false) {
						this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
						this.setCouleurErreur(true, this.ensJTextField.get(5));
					}
					if (this.ensJTextField.get(5).getText().equals(Integer.toString(somme))
							&& this.ensJTextField.get(4).getText().equals("")) {
						// System.out.println("Cette valeur a déjas été prise en compte");
					} else {
						if (this.ensJTextField.get(5).getText().equals("")
								|| Integer.parseInt(this.ensJTextField.get(5).getText()) < 0)
							this.ensJTextField.get(5).setText("0");
						this.setLabelErreur("");
						this.setCouleurErreur(false, this.ensJTextField.get(5));
						if (this.ensJTextField.get(4).getText().equals("0")
								|| this.ensJTextField.get(4).getText().equals("")) {
							somme += Integer.parseInt(this.ensJTextField.get(5).getText());
							this.panelMere.setSommeTP();
							this.panelMere.setSommeTotal();
						} else {
							somme = Integer.parseInt(this.ensJTextField.get(4).getText())
									* Integer.parseInt(this.ensJTextField.get(5).getText());
							this.panelMere.setSommeTP(somme);
							this.panelMere.setSommeTotal();
						}
					}
				}

				catch (NumberFormatException ex) {}
			}

		} catch (Exception ex) {}
	}

	@Override
	public void focusGained(FocusEvent e) {
		// ré-affichage du label erreur si il y a une erreur dans un des txtField
		if (e.getSource() == this.ensJTextField.get(0)) {
			if (this.panelMere.estChiffre(this.ensJTextField.get(0).getText()) == false) {
				this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
				this.setCouleurErreur(true, this.ensJTextField.get(0));
			} else {
				this.setLabelErreur("");
				this.setCouleurErreur(false, this.ensJTextField.get(0));
			}
		}

		if (e.getSource() == this.ensJTextField.get(1)) {
			if (this.panelMere.estChiffre(this.ensJTextField.get(1).getText()) == false) {
				this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
				this.setCouleurErreur(true, this.ensJTextField.get(1));
			} else {
				this.setLabelErreur("");
				this.setCouleurErreur(false, this.ensJTextField.get(1));
			}
		}

		if (e.getSource() == this.ensJTextField.get(2)) {
			if (this.panelMere.estChiffre(this.ensJTextField.get(2).getText()) == false) {
				this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
				this.setCouleurErreur(true, this.ensJTextField.get(2));
			} else {
				this.setLabelErreur("");
				this.setCouleurErreur(false, this.ensJTextField.get(2));
			}
		}

		if (e.getSource() == this.ensJTextField.get(3)) {
			if (this.panelMere.estChiffre(this.ensJTextField.get(3).getText()) == false) {
				this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
				this.setCouleurErreur(true, this.ensJTextField.get(3));
			} else {
				this.setLabelErreur("");
				this.setCouleurErreur(false, this.ensJTextField.get(3));
			}
		}

		if (e.getSource() == this.ensJTextField.get(4)) {
			if (this.panelMere.estChiffre(this.ensJTextField.get(4).getText()) == false) {
				this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
				this.setCouleurErreur(true, this.ensJTextField.get(4));
			} else {
				this.setLabelErreur("");
				this.setCouleurErreur(false, this.ensJTextField.get(4));
			}
		}

		if (e.getSource() == this.ensJTextField.get(5)) {
			if (this.panelMere.estChiffre(this.ensJTextField.get(5).getText()) == false) {
				this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
				this.setCouleurErreur(true, this.ensJTextField.get(5));
			} else {
				this.setLabelErreur("");
				this.setCouleurErreur(false, this.ensJTextField.get(5));
			}
		}
		
	}

	public void setHeureAffecte(int hSae, int hTut) {
		this.ensJTextField.get(2).setText("" + hSae);
		this.ensJTextField.get(3).setText("" + hTut);
		this.repaint();
		this.revalidate();
	}

	public int getNbHeureSemaine(int i) {
		return Integer.parseInt(this.ensJTextField.get(i).getText());
	}

	public boolean txtRempli() {
		boolean bOk = true;
		for(int i=1;i<this.ensJTextField.size() && bOk;i+=2) {
			if(this.ensJTextField.get(i).getText().equals("")) {
				bOk = false;
			}
		}
		return bOk;
	}

}
