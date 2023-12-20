package ihm.previsionnel.ppp.pppCentre.progNat;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Color;

public class ProgNat extends JPanel implements ActionListener, FocusListener{
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

	private int sommeAction;

	public ProgNat() {
		this.setLayout(new BorderLayout());

		//Initialisation des composants
		this.panelPrincipal  = new JPanel();
		this.panelValidation = new JPanel();
		this.txtHTutWrite    = new JTextField(4);
		this.txtHPonctWrite  = new JTextField(4);
		this.txtCMWrite      = new JTextField(4);
		this.txtTDWrite      = new JTextField(4);
		this.txtTPWrite      = new JTextField(4);
		this.txtCM           = new JTextField(4);
		this.txtTD           = new JTextField(4);
		this.txtTP           = new JTextField(4);
		this.txtSomme        = new JTextField(4);
		this.txtTotalSomme   = new JTextField(4);
		this.checkValid      = new JCheckBox();
		this.panelPrincipal.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

		//Layouts
		this.panelPrincipal.setLayout(new BorderLayout());
		this.panelPrincipal.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		//Rendre certain champ de sasie non modifiable
		this.txtCM.setEditable(false);
		this.txtTD.setEditable(false);
		this.txtTP.setEditable(false);
		this.txtSomme.setEditable(false);
		this.txtTotalSomme.setEditable(false);

		//Rendre non coché
		this.checkValid.setSelected(false);
		this.checkValid.setEnabled(false);

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

		//Ajout des composants
		this.panelValidation.add(new JLabel("Validation"));
		this.panelValidation.add(this.checkValid);

		this.add(this.panelPrincipal, BorderLayout.NORTH);
		this.add(this.panelValidation, BorderLayout.CENTER);

		this.setVisible(true);
	}


	public HashMap<String, Integer> getHeuresTot() {

		HashMap<String,Integer> map = new HashMap<>();

		try { map.put("Ponct", Integer.parseInt(this.txtHPonctWrite.getText())); }
		catch(NumberFormatException e) { map.put("SAE", 0); }

		try { map.put("TUT", Integer.parseInt(this.txtHTutWrite.getText())); }
		catch (NumberFormatException e) { map.put("TUT",0); }

		return map;
	}


	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == this.txtHPonctWrite) {
			//Vérification que l'utilsateur rentre un nombre entier supérieur ou égal à 0
			/*try {
				int val = Integer.parseInt(this.txtHSae.getText());
				if(val < 0) {
					this.txtHSae.setText("0");
				}
			}
			catch(NumberFormatException ex) {
				System.out.println("Erreur de saisie, veuillez entrer un nombre entier supérieur ou égal à 0");
			}
			//addition des heures saisies dans les champs
			try {
				//Vérification que la saisie de cette valeur n'a pas déjas été enregistrée dans somme
				if(this.txtHSae.getText().equals(Integer.toString(this.sommeAction)) && this.txtHTut.getText().equals("")) {
					System.out.println("Cette valeur a déjas été prise en compte");
				}
				else
					this.sommeAction += Integer.parseInt(this.txtHSae.getText());

			}
			catch(NumberFormatException ex) {
				System.out.println("Erreur de saisie, veuillez entrer un nombre entier");
			}*/
			//pour que le focus passe au champ suivant quand  l'utilisteur clique sur "entrée"
			this.txtHPonctWrite.transferFocus();
		}


		else if(e.getSource() == this.txtHTutWrite) {
			//Vérification que l'utilsateur rentre un nombre entier supérieur ou égal à 0
			/*try {
				int val = Integer.parseInt(this.txtHTut.getText());
				if(val < 0) {
					this.txtHTut.setText("0");
				}
			}
			catch(NumberFormatException ex) {
				System.out.println("Erreur de saisie, veuillez entrer un nombre entier supérieur ou égal à 0");
			}
			//addition des heures saisies dans les champs
			try {
				//Vérification que la saisie de cette valeur n'a pas déjas été enregistrée dans somme
				if(this.txtHTut.getText().equals(Integer.toString(this.sommeAction)) && this.txtHSae.getText().equals("")) {
					System.out.println("Cette valeur a déjas été prise en compte");
				}
				else
					this.sommeAction += Integer.parseInt(this.txtHTut.getText());

			}
			catch(NumberFormatException ex) {
				System.out.println("Erreur de saisie, veuillez entrer un nombre entier");
			}*/
			//pour que le focus passe au champ d'avant quand  l'utilisteur clique sur "entrée"
			this.txtHTutWrite.transferFocusBackward();
		}

		this.txtSomme.setText(Integer.toString(this.sommeAction));

		this.txtSomme.repaint();
		this.txtSomme.revalidate();
	}



	@Override
	public void focusLost(FocusEvent e) {
		//Addition des heures saisies dans les champs
		int somme = 0;
		
		try {
			//Vérification que la saisie de cette valeur n'a pas déjas été enregistrée dans somme
			if(this.txtHPonctWrite.getText().equals(Integer.toString(somme)) && this.txtHPonctWrite.getText().equals("")){
				System.out.println("Cette valeur a déjas été prise en compte");
			}
			else
				somme += Integer.parseInt(this.txtHPonctWrite.getText()); 
		}
		catch(NumberFormatException ex) {
			System.out.println("Erreur de saisie, veuillez entrer un nombre entier");
		}
		try {
			//Vérification que la saisie de cette valeur n'a pas déjas été enregistrée dans somme
			if(this.txtHTutWrite.getText().equals(Integer.toString(somme)) && this.txtHTutWrite.getText().equals("")){
				System.out.println("Cette valeur a déjas été prise en compte");
			}
			else
				somme += Integer.parseInt(this.txtHTutWrite.getText());
		}
		catch(NumberFormatException ex) {
			System.out.println("Erreur de saisie, veuillez entrer un nombre entier");
		}
		this.txtSomme.setText(Integer.toString(somme));
		this.txtSomme.repaint();
		this.txtSomme.revalidate();
	}
	@Override
	public void focusGained(FocusEvent e) {}


	public boolean estValide() {
		return this.checkValid.isSelected();
	}

	public int getSommePN() {
		return this.sommeAction;
	}








}//"Total (eqtd) promo"));