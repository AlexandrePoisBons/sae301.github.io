package ihm.previsionnel.sae.saeCentre.repartition.heureSae;

import javax.swing.JPanel;
import javax.swing.JTextField;

import ihm.previsionnel.sae.saeCentre.repartition.PanelRepartitionSae;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;

public class PanelRepartitionHeure extends JPanel{
	//4,3
	private PanelRepartitionSae panelMere;
	private JPanel panelC;
	private JPanel panelW;
	private JTextField txtHSaePromo;
	private JTextField txtHSaeAffecte;
	private JTextField txtHTutPromo;
	private JTextField txtHTutAffecte;

	public PanelRepartitionHeure(PanelRepartitionSae panelMere){
		this.panelMere = panelMere;
		this.setLayout(new BorderLayout());

		this.panelC = new JPanel();
		this.panelW = new JPanel();
		this.txtHSaePromo = new JTextField();
		this.txtHSaeAffecte = new JTextField();
		this.txtHTutPromo = new JTextField();
		this.txtHTutAffecte = new JTextField();
		
		
		this.panelC.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		this.panelW.setLayout(new GridLayout(2, 1));


		// Ajout des composants avec GridBagLayout
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 3, 5, 0);

		//Ajout des éléments
		this.panelC.add(new JLabel("h Sae"), gbc);
		gbc.gridx = 1;
		this.panelC.add(new JLabel("h Tut"), gbc);
		gbc.gridy = 1;
		gbc.gridx = 0;
		this.panelC.add(this.txtHSaePromo, gbc);
		gbc.gridx = 1;
		this.panelC.add(this.txtHSaeAffecte, gbc);
		gbc.gridy = 2;
		gbc.gridx = 0;
		this.panelC.add(this.txtHTutPromo, gbc);
		gbc.gridx = 1;
		this.panelC.add(this.txtHTutAffecte, gbc);

		this.panelW.add(new JLabel("Total promo (eqtd)"));
		this.panelW.add(new JLabel("Total affecté (eqtd)"));

		this.add(this.panelC, BorderLayout.CENTER);
		this.add(this.panelW, BorderLayout.WEST);
	}
}