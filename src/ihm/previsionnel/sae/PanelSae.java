package ihm.previsionnel.sae;
//Import classes Java
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.util.List;
import java.util.HashMap;

//Import classes externes
import ihm.accueil.*;
import ihm.previsionnel.PanelPrevi;
import ihm.previsionnel.sae.saeCentre.PCentreSae;
import ihm.previsionnel.sae.saeNord.PNordSae;
import ihm.previsionnel.sae.saeSud.PanelSudSae;
import metier.Heure;
import metier.Intervenant;
import metier.Module;
import metier.TypeHeure;

public class PanelSae extends JPanel {
	private FrameAccueil frame;
	private PanelPrevi   panelMere;
	private PNordSae     pNordSae;
	private PCentreSae   pCentreSae;
	private PanelSudSae  pSudSae;
	private Module       module;

	public PanelSae(FrameAccueil frame, PanelPrevi panelMere, Module m) {
		this.frame     = frame;
		this.panelMere = panelMere;
		this.module    = m;


		this.frame.setTitle("Prévisionnel - Module: SAE");

		//Définition de la taille et la position de la fenêtre
		int hauteur = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()  - (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.05);
		int largeur = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int xSize = (int)(largeur*0.75);
		int ySize = (int)(hauteur*0.85);
		this.frame.setSize(xSize, ySize);
		this.frame.setLocation((int)(largeur*0.5-xSize*0.5),(int)(hauteur*0.5-ySize*0.5));
		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(0, 5, 0, 10));


		this.pNordSae   = new PNordSae(this, this.module);
		this.pNordSae.setValues();
		this.pCentreSae = new PCentreSae(this, this.module);
		this.pSudSae    = new PanelSudSae(this.frame, this.panelMere, this, this.module);

		// Utiliser BoxLayout pour organiser les composants horizontalement
		this.add(this.pNordSae, BorderLayout.NORTH);
		this.add(this.pCentreSae, BorderLayout.CENTER);
		this.add(this.pSudSae, BorderLayout.SOUTH);

	}

	public void enregistrer(Module m) {
		List<Heure> heures = this.pCentreSae.getHeures();

		m.setHeures(heures);
		this.panelMere.ajouterModule(m);

		this.frame.changerPanel(new PanelPrevi(this.frame));
	}

	public void update(Module oldModule, Module newModule) {
		List<Heure> heures = this.pCentreSae.getHeures();

		newModule.setHeures(heures);
		this.panelMere.updateModule(oldModule, newModule);

		this.frame.changerPanel(new PanelPrevi(this.frame));
	}

	public void setErreur(String message) {
		this.pSudSae.setErreur(message);
	}


	public String getSemestre()     { return this.panelMere.getSemestre();    }
	public int    getNbEtd()        { return this.panelMere.getNbEtd();       }
	public int    getNbGpTd()       { return this.panelMere.getNbGpTd();      }
	public int    getNbGpTp()       { return this.panelMere.getNbGpTp();      }
	public String getCode()         { return this.pNordSae.getCode();         }
	public String getLibelle()      { return this.pNordSae.getLibelle();      }
	public String getLibelleCourt() { return this.pNordSae.getLibelleCourt(); }
	public Module getModule ()      { return this.module;                     }

	public HashMap<String, Integer> getDataHeures() { return this.pCentreSae.getData(); }
	public HashMap<String,Integer>  getData()       { return this.pCentreSae.getData(); }

	public List<Intervenant> getIntervenants() { return this.frame.getControleur().getCtrl().metier().getIntervenants(); }
	public List<TypeHeure>   getTypesHeures()  { return this.frame.getControleur().getCtrl().metier().getTypesHeures();  }

	public boolean estValide() {
		return this.pCentreSae.estValide();
	}

	public int getSommeAffecte() {
		return this.pCentreSae.getSommeAffecte();
	}

	public int getSommePN() {
		return this.pCentreSae.getSommePN();
	}
}
