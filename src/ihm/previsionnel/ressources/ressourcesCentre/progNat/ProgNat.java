package ihm.previsionnel.ressources.ressourcesCentre.progNat;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;

public class ProgNat extends JPanel{
	private JPanel panelCentre;
	private JTextField txtCMWrite;
	private JTextField txtTDWrite;
	private JTextField txtTPWrite;
	private JTextField txtOEWrite;
	private JTextField txtCM;
	private JTextField txtTD;
	private JTextField txtTP;
	private JTextField txtOE;
	private JCheckBox checkValid;


	public ProgNat(){
		//Initialisation des composants
		this.panelCentre = new JPanel();
		this.txtCMWrite = new JTextField(4);
		this.txtTDWrite = new JTextField(4);
		this.txtTPWrite = new JTextField(4);
		this.txtOEWrite = new JTextField(4);
		this.txtCM = new JTextField(4);
		this.txtTD = new JTextField(4);
		this.txtTP = new JTextField(4);
		this.txtOE = new JTextField(4);
		this.checkValid = new JCheckBox();
		
		//Rendre certain champ de sasie non modifiable
		this.txtCM.setEditable(false);
		this.txtTD.setEditable(false);
		this.txtTP.setEditable(false);
		this.txtOE.setEditable(false);
		this.txtOEWrite.setEditable(false);

		//Légender la checkbox et la rebdre non coché
		this.checkValid.setText("Validation");
		this.checkValid.setSelected(false);



		//Layouts
		this.setLayout(new BorderLayout());
		this.panelCentre.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		// Ajout des composants avec GridBagLayout
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 0);
		this.panelCentre.add(new JLabel("CM"), gbc);
		gbc.gridx = 2;
		this.panelCentre.add(new JLabel("TD"), gbc);
		gbc.gridx = 3;
		this.panelCentre.add(new JLabel("TP"), gbc);
		gbc.insets = new Insets(0, 5, 0, 0);
		gbc.gridx = 4;
		this.panelCentre.add(new JLabel("OE"), gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.insets = new Insets(0, 0, 0, 0);
		this.panelCentre.add(this.txtCMWrite, gbc);
		gbc.gridx = 2;
		this.panelCentre.add(this.txtTDWrite, gbc);
		gbc.gridx = 3;
		this.panelCentre.add(this.txtTPWrite, gbc);
		gbc.insets = new Insets(0, 5, 0, 0);
		gbc.gridx = 4;
		this.panelCentre.add(this.txtOEWrite, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.insets = new Insets(0, 0, 0, 0);
		this.panelCentre.add(this.txtCM, gbc);
		gbc.gridx = 2;
		this.panelCentre.add(this.txtTD, gbc);
		gbc.gridx = 3;
		this.panelCentre.add(this.txtTP, gbc);
		gbc.insets = new Insets(0, 5, 0, 0);
		gbc.gridx = 4;
		this.panelCentre.add(this.txtOE, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		this.panelCentre.add(new JLabel("Total (eqtd) promo"), gbc);

		//Ajout des composants
		this.add(new JLabel("PN local (nb h tot/etd)"), BorderLayout.NORTH);
		this.add(this.panelCentre, BorderLayout.CENTER);
		this.add(this.checkValid, BorderLayout.SOUTH);

		this.setVisible(true);
	}
}//"Total (eqtd) promo"));