package ihm.previsionnel.sae.saeCentre.progNatSae;
//import classes java
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;

public class ProgNatSae extends JPanel{
	private JPanel panelPrincipal;
	private JPanel panelValidation;
	private JTextField txtHSae;
	private JTextField txtHTut;
	private JTextField txtOE;
	private JCheckBox checkValid;

		public ProgNatSae() {
		this.setLayout(new BorderLayout());

		//Initialisation des composants
		this.panelPrincipal = new JPanel();
		this.panelValidation = new JPanel();
		this.txtOE = new JTextField(4);
		this.checkValid = new JCheckBox();
		this.panelPrincipal.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		
		//Rendre certain champ de sasie non modifiable
		this.txtOE.setEditable(false);

		//Rendre non coché
		this.checkValid.setSelected(false);
		this.checkValid.setEnabled(false);



		//Layouts
		//this.setLayout(new BorderLayout());
		this.panelPrincipal.setLayout(new BorderLayout());
		this.panelPrincipal.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		// Ajout des composants avec GridBagLayout
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(11, 0, 2, 0);
		this.panelPrincipal.add(new JLabel("h Sae"), gbc);
		gbc.gridx = 2;
		this.panelPrincipal.add(new JLabel("h Tut"), gbc);
		gbc.insets = new Insets(11, 10, 2, 0);
		gbc.gridx = 4;
		this.panelPrincipal.add(new JLabel("Σ"), gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.insets = new Insets(0, 0, 2, 0);
		this.panelPrincipal.add(this.txtHSae, gbc);
		gbc.gridx = 2;
		this.panelPrincipal.add(this.txtHTut, gbc);
		gbc.insets = new Insets(0, 10, 2, 0);
		gbc.gridx = 4;
		this.panelPrincipal.add(this.txtOE, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.insets = new Insets(0, 0, 2, 0);
		this.panelPrincipal.add(this.txtHSae, gbc);
		gbc.gridx = 2;
		this.panelPrincipal.add(this.txtHTut, gbc);
		gbc.insets = new Insets(0, 10, 2, 0);
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

		this.setVisible(true);
	}


	public Object[] getHeuresTot(){

		Object[] objs = new Object[3];

		objs[0] = Integer.parseInt(this.txtHSae.getText());
		objs[1] = Integer.parseInt(this.txtHTut.getText());
		objs[2] = Integer.parseInt(this.txtOE.getText());

		return objs;
	}
}