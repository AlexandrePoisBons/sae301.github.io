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

	public PanelRepartitionHeure(PanelRepartition panelMere) {
		this.panelMere = panelMere;
		//this.setLayout(new GridLayout(1,1));
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

		this.panelRepartitionHGauche = new PanelRepartitionHGauche(this);
		this.panelRepartitionHDroite = new PanelRepartitionHDroite(this);

		this.add(this.panelRepartitionHGauche);
		this.add(this.panelRepartitionHDroite);
	}

	public void setLabelErreur(String message) { this.panelMere.setLabelErreur(message); }

	public boolean estChiffre(String texte) {
		// Vérifie chaque caractère dans la chaîne pour s'assurer qu'il s'agit d'un chiffre.
		for ( char c : texte.toCharArray() ) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}

	public HashMap<String, Integer> getNbSemaines() { return this.panelRepartitionHGauche.getNbSemaines(); }

	public void setHeureAffecte(int hCM, int hTD, int hTP, int hTut, int hHP) { this.panelRepartitionHDroite.setHeureAffecte(hCM, hTD, hTP, hTut, hHP); }

	public int getSommeAffecte() { return this.panelRepartitionHDroite.getSommeAffecte(); }

	public int getNbHeureSemaine(int i) { return this.panelRepartitionHGauche.getNbHeureSemaine(i); }
	public void setSommeCM(int somme) { this.panelRepartitionHDroite.setSommeCM(somme); }
	public void setSommeCM() { this.panelRepartitionHDroite.setSommeCM(); }

	public void setSommeTD(int somme) { this.panelRepartitionHDroite.setSommeTD(somme); }
	public void setSommeTD() {this.panelRepartitionHDroite.setSommeTD();}

	public void setSommeTP(int somme) { this.panelRepartitionHDroite.setSommeTP(somme); }
	public void setSommeTP() {this.panelRepartitionHDroite.setSommeTP();}

	public void setSommeTotal(){ this.panelRepartitionHDroite.setSommeTotal(); }

	public int getNbGpTd(){return this.panelMere.getNbGpTd();}
	public int getNbGpTp(){return this.panelMere.getNbGpTp();}

	public boolean txtRempli() { return this.panelRepartitionHGauche.txtRempli(); }


}
