package ihm.previsionnel.sae.saeCentre.repartition.heureSae;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.*;

public class PanelRepartitionHeure extends JPanel{
	private PanelRepartitionHeureSae panelMere;
	private JTextField txtHSaePromo;
	private JTextField txtHSaeAffecte;
	private JTextField txtHTutPromo;
	private JTextField txtHTutAffecte;

	public PanelRepartitionHeure(PanelRepartitionHeureSae panelMere){
		this.panelMere = panelMere;
		this.setLayout(new GridLayout(1, 2));

		JPanel panelC = new JPanel();
		JPanel panelW = new JPanel();

		panelC.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		panelW.setLayout(new GridLayout(2, 1));
		this.setLayout(new GridLayout(3, 1));

		this.txtHSaePromo = new JTextField(2);
		this.txtHSaeAffecte = new JTextField(2);
		this.txtHTutPromo = new JTextField(2);
		this.txtHTutAffecte = new JTextField(2);


		// Ajout des composants avec GridBagLayout
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 3, 5, 0);

		//Ajout des éléments
		panelC.add(new JLabel("h Sae"), gbc);
		gbc.gridx = 1;
		panelC.add(new JLabel("h Tut"), gbc);
		gbc.gridy = 1;
		gbc.gridx = 0;
		panelC.add(this.txtHSaePromo, gbc);
		gbc.gridx = 1;
		panelC.add(this.txtHTutPromo, gbc);
		gbc.gridy = 2;
		gbc.gridx = 0;
		panelC.add(this.txtHSaeAffecte, gbc);
		gbc.gridx = 1;
		panelC.add(this.txtHTutAffecte, gbc);

		panelW.add(new JLabel("Total promo (eqtd)"));
		panelW.add(new JLabel("Total affecté (eqtd)"));

		this.add(panelW);
		this.add(panelC);
	}
}
