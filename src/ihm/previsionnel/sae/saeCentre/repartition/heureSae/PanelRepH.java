package ihm.previsionnel.sae.saeCentre.repartition.heureSae;
//Imports classes Java
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.BorderFactory;

import java.awt.Color;

import javax.swing.JPanel;

import ihm.previsionnel.sae.saeCentre.repartition.PanelRepartitionSae;
import metier.Module;


public class PanelRepH extends JPanel{
	private PanelRepartitionSae panelMere;
	private Module m;
	private PanelRepartitionHeureGaucheSae panelRepartitionHGauche;
	private PanelRepartitionHeureDroiteSae panelRepartitionHDroite;

	public PanelRepH(PanelRepartitionSae panelMere, Module m) {
		this.m = m;
		this.panelMere = panelMere;
		this.setLayout(new GridLayout(1,2));
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

		this.panelRepartitionHDroite = new PanelRepartitionHeureDroiteSae();
		this.panelRepartitionHGauche = new PanelRepartitionHeureGaucheSae(this, this.m);

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
		System.out.println("panelRepH");
		this.panelRepartitionHGauche.setHeureAffecte(hSae, hTut);
	}

	public void setSommeAffecte(int somme) {
		this.panelRepartitionHDroite.setSommeAffecte(somme);
	}

	public int getSommeAffecte() {
		return this.panelRepartitionHDroite.getSommeAffecte();
	}

	public void actualiserSomme() {
		this.panelRepartitionHGauche.actualiserSomme();
	}

	public void actualiserHeureAffecte() {
		System.out.println("ici");
		this.panelMere.setHeureAffecte();
	}
}
