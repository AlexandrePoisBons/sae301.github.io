package ihm.previsionnel.sae.saeCentre.repartition.heureSae;
//Imports classes Java
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.BorderFactory;

import java.awt.Color;

import javax.swing.JPanel;

import ihm.previsionnel.sae.saeCentre.repartition.PanelRepartitionSae;


public class PanelRepH extends JPanel{
	private PanelRepartitionSae panelMere;
	private PanelRepartitionHeureGaucheSae panelRepartitionHGauche;
	private PanelRepartitionHeureDroiteSae panelRepartitionHDroite;

	public PanelRepH(PanelRepartitionSae panelMere){
		this.panelMere = panelMere;
		this.setLayout(new GridLayout(1,2));
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

		this.panelRepartitionHGauche = new PanelRepartitionHeureGaucheSae(this);
		this.panelRepartitionHDroite = new PanelRepartitionHeureDroiteSae();

		this.add(this.panelRepartitionHGauche);
		this.add(this.panelRepartitionHDroite);
	}

	public HashMap<String, Integer> getNbSemaines() {
		return this.panelRepartitionHGauche.getNbSemaines();
	}

	public void setSommePromo(int somme) {
		this.panelRepartitionHDroite.setSommePromo(somme);
	}

	public void setHeureAffecte(int hSae, int hTut) {
		this.panelRepartitionHDroite.setHeureAffecte(hSae, hTut);
	}


}
