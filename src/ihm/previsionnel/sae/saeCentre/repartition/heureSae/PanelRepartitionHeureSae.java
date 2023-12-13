package ihm.previsionnel.sae.saeCentre.repartition.heureSae;

import javax.swing.JPanel;
import javax.swing.JTextField;

import ihm.previsionnel.sae.saeCentre.repartition.PanelRepartitionSae;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class PanelRepartitionHeureSae extends JPanel{
	//4,3
	private PanelRepartitionSae panelMere;
	private PanelRepartitionHeure panelPrincipal;
	private JPanel panelE;
	private JTextField txtSommePromo;
	private JTextField txtSommeAffecte;

	public PanelRepartitionHeureSae(PanelRepartitionSae panelMere){
		this.panelMere = panelMere;
		this.setLayout(new GridLayout(1, 2));
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

		this.panelPrincipal = new PanelRepartitionHeure(this);
		this.panelE = new JPanel();
		this.txtSommePromo = new JTextField(2);
		this.txtSommeAffecte = new JTextField(2);
		
		this.panelE.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridy = 0;
		gbc.gridx = 0;
		this.panelE.add(new JLabel("Σ"));
		gbc.gridy = 1;
		this.panelE.add(this.txtSommePromo);
		gbc.gridy = 2;
		this.panelE.add(this.txtSommeAffecte);

		this.add(this.panelPrincipal);
		this.add(this.panelE);
	}
}