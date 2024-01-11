package ihm.previsionnel.stage.stageCentre.repartition.heureStage;
import ihm.previsionnel.stage.stageCentre.repartition.PanelRepartitionStage;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.BorderFactory;
import java.awt.Color;
import javax.swing.JPanel;

import metier.Module;
import metier.TypeHeure;

public class PanelRepHStage extends JPanel{
	private PanelRepartitionStage panelMere;
	private PanelRepartitionHeureGaucheStage panelRepartitionHGauche;
	private PanelRepartitionHeureDroiteStage panelRepartitionHDroite;

	private Module module;

	public PanelRepHStage(PanelRepartitionStage panelMere, Module m) {
		this.panelMere = panelMere;
		this.module = m;
		this.setLayout(new GridLayout(1,2));
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

		this.panelRepartitionHGauche = new PanelRepartitionHeureGaucheStage(this, this.module);
		this.panelRepartitionHDroite = new PanelRepartitionHeureDroiteStage();
		this.panelRepartitionHGauche = new PanelRepartitionHeureGaucheStage(this, module);

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

	public void setHeureAffecte(int hREH, int hTut) {
		this.panelRepartitionHGauche.setHeureAffecte(hREH, hTut);
	}

	public void setSommeAffecte(int somme) {
		this.panelRepartitionHDroite.setSommeAffecte(somme);
	}

	public int getSommeAffecte() {
		return this.panelRepartitionHDroite.getSommeAffecte();
	}


	public void setSommeAffecte() {
		this.panelRepartitionHGauche.setSommeAffecte();
	}


}
