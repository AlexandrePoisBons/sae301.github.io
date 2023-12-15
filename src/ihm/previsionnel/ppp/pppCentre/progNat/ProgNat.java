package ihm.previsionnel.ppp.pppCentre.progNat;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Color;

public class ProgNat extends JPanel{
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


	public HashMap<String,Integer> getHeuresTot(){
		HashMap<String,Integer> map = new HashMap<>();

		try { map.put("CM", Integer.parseInt(this.txtCMWrite.getText())); }
		catch(NumberFormatException e) { map.put("CM", 0); }
		
		try { map.put("TD", Integer.parseInt(this.txtTDWrite.getText())); }
		catch(NumberFormatException e) { map.put("TD", 0); }
		
		try { map.put("TUT", Integer.parseInt(this.txtHTutWrite.getText())); }
		catch(NumberFormatException e) { map.put("TUT", 0); }
		
		try { map.put("HP", Integer.parseInt(this.txtHPonctWrite.getText())); }
		catch(NumberFormatException e) { map.put("HP", 0); }

		return map;
	}





}//"Total (eqtd) promo"));