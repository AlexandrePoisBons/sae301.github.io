package ihm.previsionnel.sae.saeCentre.repartition.heureSae;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

public class PanelRepartitionHSae extends JPanel{
	private PanelRepartitionHeure panelMere;
	private JPanel panelSomme;

	public PanelRepartitionHSae(PanelRepartitionHeure panelMere){
		this.panelMere = panelMere;
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

		this.panelSomme 			= new JPanel();
		this.panelSomme.setLayout(new GridLayout(3, 1));

		this.add(this.panelSomme, BorderLayout.EAST);
	}
}