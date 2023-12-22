package ihm.previsionnel.ppp.pppCentre.repartition.heure;

import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.BorderFactory;
import java.awt.Color;

import javax.swing.JPanel;

import ihm.previsionnel.ppp.pppCentre.repartition.PanelRepartition;

public class PanelRepartitionHeure extends JPanel{
	private PanelRepartition panelMere;
	private PanelRepartitionHGauche panelRepartitionHGauche;
	private PanelRepartitionHDroite panelRepartitionHDroite;

	public PanelRepartitionHeure(PanelRepartition panelMere){
		this.panelMere = panelMere;
		//this.setLayout(new GridLayout(1,1));
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

		this.panelRepartitionHGauche = new PanelRepartitionHGauche(this);
		this.panelRepartitionHDroite = new PanelRepartitionHDroite(this);

		this.add(this.panelRepartitionHGauche);
		this.add(this.panelRepartitionHDroite);
	}

	public HashMap<String, Integer> getNbSemaines() {
		return this.panelRepartitionHGauche.getNbSemaines();
	}

	/*public void setSommePromo(int somme) {
		this.panelRepartitionHDroite.setSommePromo(somme);
	}*/

	public void setHeureAffecte(int hSae, int hTut) {
		System.out.println("panelRepH");
		this.panelRepartitionHGauche.setHeureAffecte(hSae, hTut);
	}
/* 
	public void setSommeAffecte(int somme) {
		this.panelRepartitionHDroite.setSommeAffecte(somme);
	}*/

	public int getSommeAffecte() {
		return this.panelRepartitionHDroite.getSommeAffecte();
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

	public int getNbGpTd(){return this.panelMere.getNbGpTd();}
	public int getNbGpTp(){return this.panelMere.getNbGpTp();}

	
}
