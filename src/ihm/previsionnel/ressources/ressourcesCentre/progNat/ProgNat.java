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

public class ProgNat extends JPanel implements FocusListener, ActionListener{
	//Constante coefficient de conversion des heures de CM en heures TD (1h de TD = 1.5h CM)
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


	public ProgNat(PRCentre panelMere) {
		this.panelMere = panelMere;
		this.setLayout(new BorderLayout());
		//Initialisation des composants
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
		this.checkValid = new JCheckBox();
		this.panelPrincipal.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		
		//Rendre certain champ de sasie non modifiable
		this.txtCM.setEditable(false);
		this.txtTD.setEditable(false);
		this.txtTP.setEditable(false);
		this.txtOE.setEditable(false);
		this.txtOEWrite.setEditable(false);


		//Layouts
		//this.setLayout(new BorderLayout());
		this.panelPrincipal.setLayout(new BorderLayout());
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

		//Ajout des composants
		this.panelValidation.add(new JLabel("Validation"));
		this.panelValidation.add(this.checkValid);

		this.add(this.panelPrincipal, BorderLayout.NORTH);
		this.add(this.panelValidation, BorderLayout.CENTER);

		//implémentation des listener
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


	public HashMap<String,Integer> getHeuresTot(){
		HashMap<String,Integer> map = new HashMap<>();

		try { map.put("CM", Integer.parseInt(this.txtCMWrite.getText())); }
		catch(NumberFormatException e) { map.put("CM", 0); }
		try { map.put("TD", Integer.parseInt(this.txtTDWrite.getText())); }
		catch(NumberFormatException e) { map.put("TD", 0); }
		try { map.put("TP", Integer.parseInt(this.txtTPWrite.getText())); }
		catch(NumberFormatException e) { map.put("TP", 0); }

		return map;
	}


	


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.txtCMWrite) {
			this.txtCMWrite.transferFocus();
		}
		else if(e.getSource() == this.txtTDWrite) {
			this.txtTDWrite.transferFocus();
		}
		else if(e.getSource() == this.txtTPWrite) {
			this.txtTPWrite.transferFocus();
		}


		
	}

	@Override
	public void focusLost(FocusEvent e) {
		//Addition des heures saisies dans les champs
		int somme = 0;

		try {
			//Vérification que la saisie de cette valeur n'a pas déjas été enregistrée dans somme
			if(this.txtCMWrite.getText().equals(Integer.toString(somme)) && (this.txtTPWrite.getText().equals("")&&this.txtTDWrite.getText().equals(""))) {
				System.out.println("Cette valeur a déjas été prise en compte");
			}
			else {
				somme += Integer.parseInt(this.txtCMWrite.getText());
				//calcul du txtField CM non modifiable (txtField modifiable * coeffCM_TD)
				this.txtCM.setText(Integer.toString((int)(Integer.parseInt(this.txtCMWrite.getText())*COEFF_CM_TD)));
				this.txtCM.repaint();
				this.txtCM.revalidate();
			}
		} catch (Exception err) {
			System.out.println("Erreur de saisie, veuillez entrer un nombre entier");
		}

		try {
			//Vérification que la saisie de cette valeur n'a pas déjas été enregistrée dans somme
			if(this.txtTDWrite.getText().equals(Integer.toString(somme)) && (this.txtTPWrite.getText().equals("")&&this.txtCMWrite.getText().equals(""))) {
				System.out.println("Cette valeur a déjas été prise en compte");
			}
			else {
				somme += Integer.parseInt(this.txtTDWrite.getText());
				//calcul du txtField TD non modifiable (txtField modifiable * nbGpTD de panelMere)
				this.txtTD.setText(Integer.toString((int)(Integer.parseInt(this.txtTDWrite.getText())*this.panelMere.getNbGpTd())));
				this.txtTD.repaint();
				this.txtTD.revalidate();
			}
		} catch (Exception err) {
			System.out.println("Erreur de saisie, veuillez entrer un nombre entier");
		}

		try {
			//Vérification que la saisie de cette valeur n'a pas déjas été enregistrée dans somme
			if(this.txtTPWrite.getText().equals(Integer.toString(somme)) && (this.txtCMWrite.getText().equals("")&&this.txtTDWrite.getText().equals(""))) {
				System.out.println("Cette valeur a déjas été prise en compte");
			}
			else {
				somme += Integer.parseInt(this.txtTPWrite.getText());
				//calcul du txtField TP non modifiable (txtField modifiable * nbGpTP de panelMere)
				this.txtTP.setText(Integer.toString((int)(Integer.parseInt(this.txtTPWrite.getText())*this.panelMere.getNbGpTp())));
				this.txtTP.repaint();
				this.txtTP.revalidate();
			}
		} catch (Exception err) {
			System.out.println("Erreur de saisie, veuillez entrer un nombre entier");
		}

		this.txtOEWrite.setText(Integer.toString(somme));
		this.txtOEWrite.repaint();
		this.txtOEWrite.revalidate();

		this.txtOE.setText(Integer.toString(Integer.parseInt(this.txtCM.getText()) + Integer.parseInt(this.txtTD.getText()) + Integer.parseInt(this.txtTP.getText())));
		this.txtOE.repaint();
		this.txtOE.revalidate();

	}

	//Méthode inutilisée mais obligatoire
	@Override
	public void focusGained(FocusEvent e) {}

}