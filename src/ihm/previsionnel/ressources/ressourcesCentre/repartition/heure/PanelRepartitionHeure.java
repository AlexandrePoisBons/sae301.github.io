package ihm.previsionnel.ressources.ressourcesCentre.repartition.heure;

import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import java.awt.Color;

import javax.swing.JPanel;

import ihm.previsionnel.ressources.ressourcesCentre.PanelRepartition;

public class PanelRepartitionHeure extends JPanel{
	private PanelRepartition panelMere;
	private PanelRepartitionHGauche panelRepartitionHGauche;
	private PanelRepartitionHDroite panelRepartitionHDroite;

	public PanelRepartitionHeure(PanelRepartition panelMere){
		this.panelMere = panelMere;
		this.setLayout(new GridLayout(1,2));
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

		this.panelRepartitionHGauche = new PanelRepartitionHGauche(this);
		this.panelRepartitionHDroite = new PanelRepartitionHDroite(this);

		this.add(this.panelRepartitionHGauche);
		this.add(this.panelRepartitionHDroite);
	}

	public HashMap<String, Integer> getNbSemaines() {
		return this.panelRepartitionHGauche.getNbSemaines();
	}

	public void setSommeCM(int somme) {
		this.panelRepartitionHDroite.setSommeCM(somme);
	}

	public void setSommeTD(int somme) {
		this.panelRepartitionHDroite.setSommeTD(somme);
	}

	public void setSommeTP(int somme) {
		this.panelRepartitionHDroite.setSommeTP(somme);
	}

	public void setSommeTotal(){
		this.panelRepartitionHDroite.setSommeTotal();
	}

	public void setHeureAffecte(int hSae, int hTut) {
		this.panelRepartitionHDroite.setHeureAffecte(hSae, hTut);
	}

	public int getNbGpTd(){return this.panelMere.getNbGpTd();}
	public int getNbGpTp(){return this.panelMere.getNbGpTp();}

	public int getNbHeureSemaine(int i){
		return this.panelRepartitionHGauche.getNbHeureSemaine(i);
	}
}
