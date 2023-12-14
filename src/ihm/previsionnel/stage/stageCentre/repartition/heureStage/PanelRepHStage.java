package ihm.previsionnel.stage.stageCentre.repartition.heureStage;
import ihm.previsionnel.sae.saeCentre.repartition.PanelRepartitionSae;
import ihm.previsionnel.sae.saeCentre.repartition.heureSae.PanelRepartitionHeureDroiteSae;
import ihm.previsionnel.sae.saeCentre.repartition.heureSae.PanelRepartitionHeureGaucheSae;
import ihm.previsionnel.stage.stageCentre.repartition.PanelRepartitionStage;
import java.awt.GridLayout;

import javax.swing.BorderFactory;

import java.awt.Color;

import javax.swing.JPanel;

public class PanelRepHStage extends JPanel{
	private PanelRepartitionStage panelMere;
	private PanelRepartitionHeureGaucheStage panelRepartitionHGauche;
	private PanelRepartitionHeureDroiteStage panelRepartitionHDroite;

	public PanelRepHStage(PanelRepartitionStage panelMere){
		this.panelMere = panelMere;
		this.setLayout(new GridLayout(1,2));
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

		this.panelRepartitionHGauche = new PanelRepartitionHeureGaucheStage(this);
		this.panelRepartitionHDroite = new PanelRepartitionHeureDroiteStage();

		this.add(this.panelRepartitionHGauche);
		this.add(this.panelRepartitionHDroite);
	}
}
