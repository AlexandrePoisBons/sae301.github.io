package ihm.previsionnel.sae.saeCentre.repartition.heureSae;
//Imports classes Java
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.BorderFactory;

import java.awt.Color;

import javax.swing.JPanel;

import ihm.previsionnel.sae.saeCentre.repartition.PanelRepartitionSae;
import metier.Module;
import metier.TypeHeure;


public class PanelRepH extends JPanel{
	private PanelRepartitionSae panelMere;
	private Module module;
	private PanelRepartitionHeureGaucheSae panelRepartitionHGauche;
	private PanelRepartitionHeureDroiteSae panelRepartitionHDroite;

	public PanelRepH(PanelRepartitionSae panelMere, Module m) {
		this.module = m;
		this.panelMere = panelMere;
		this.setLayout(new GridLayout(1,2));
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

		this.panelRepartitionHDroite = new PanelRepartitionHeureDroiteSae();
		this.panelRepartitionHGauche = new PanelRepartitionHeureGaucheSae(this, this.module);

		this.add(this.panelRepartitionHGauche);
		this.add(this.panelRepartitionHDroite);
	}

	public void setLabelErreur(String message) { this.panelMere.setLabelErreur(message); }


	public boolean estChiffre(String texte) {
		// Vérifie chaque caractère dans la chaîne pour s'assurer qu'il s'agit d'un chiffre.
		for (char c : texte.toCharArray()) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}

	public HashMap<String, HashMap<String,Integer>> getDataHeuresTypesHeures() {

		HashMap<String, HashMap<String, Integer>> mapGauche = this.panelRepartitionHGauche.getDataHeuresTypesHeures();

		return mapGauche;
	}

	public HashMap<TypeHeure, HashMap<String,Integer>> getHeuresParTypesHeures(Module module) { return this.panelMere.getHeuresParTypesHeures(module); }
	
	public HashMap<String, Integer> getNbSemaines() {
		return this.panelRepartitionHGauche.getNbSemaines();
	}

	public void setSommePromo(int somme) {
		this.panelRepartitionHDroite.setSommePromo(somme);
	}

	public void setHeureAffecte(int hSae, int hTut) {
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
		this.panelMere.setHeureAffecte();
	}
}
