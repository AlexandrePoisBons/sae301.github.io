package ihm.previsionnel.ppp;

import ihm.previsionnel.PanelPrevi;
import ihm.previsionnel.ppp.pppCentre.PanelPppCentre;
import ihm.previsionnel.ppp.pppNord.PanelPppNord;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;

import ihm.accueil.*;
import ihm.previsionnel.ppp.pppSud.PanelSud;
import metier.Heure;
import metier.Intervenant;
import metier.Module;
import metier.TypeHeure;

public class PanelPpp extends JPanel {
	private FrameAccueil frame;
	private PanelPrevi  panelMere;
	private PanelPppNord      panelPppNord;
	private PanelPppCentre    panelPppCentre;
	private PanelSud    panelSud;
	private Module module;

	public PanelPpp(FrameAccueil frame, PanelPrevi framePrevi, Module m) {
		this.frame = frame;
		this.panelMere = framePrevi;
		this.module = m;


		this.frame.setTitle("Prévisionnel - Module: PPP"); 
		
		//Définition de la taille et la position de la fenêtre
		int hauteur = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()  - (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.05);
		int largeur = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int xSize = (int)(largeur*0.75);
		int ySize = (int)(hauteur*0.9);
		this.frame.setSize(xSize, ySize);
		this.frame.setLocation((int)(largeur*0.5-xSize*0.5),(int)(hauteur*0.5-ySize*0.5));
		this.setLayout(new BorderLayout());

		this.panelPppNord = new PanelPppNord(this, this.module);
		this.panelPppNord.setValues();
		this.panelPppCentre = new PanelPppCentre(this, this.module);
		this.panelSud = new PanelSud(this.frame, this.panelMere, this, this.module);

		// Utiliser BoxLayout pour organiser les composants horizontalement
		this.add(this.panelPppNord, BorderLayout.NORTH);
		this.add(this.panelPppCentre, BorderLayout.CENTER);
		this.add(this.panelSud, BorderLayout.SOUTH);
	}

	public void enregistrer(Module m) {
		List<Heure> heures = this.panelPppCentre.getHeures();

		m.setHeures(heures);
		this.panelMere.ajouterModule(m);

		this.frame.changerPanel(new PanelPrevi(this.frame));
	}

	public void update(Module oldModule, Module newModule) {
		List<Heure> heures = this.panelPppCentre.getHeures();

		newModule.setHeures(heures);
		this.panelMere.updateModule(oldModule, newModule);

		this.frame.changerPanel(new PanelPrevi(this.frame));
	}

	public void setErreur(String message) {
		this.panelSud.setErreur(message);
	}

	public String getSemestre()     { return this.panelMere.getSemestre();    }
	public int    getNbEtd()        { return this.panelMere.getNbEtd();       }
	public int    getNbGpTd()       { return this.panelMere.getNbGpTd();      }
	public int    getNbGpTp()       { return this.panelMere.getNbGpTp();      }
	public String getCode()         { return this.panelPppNord.getCode();         }
	public String getLibelle()      { return this.panelPppNord.getLibelle();      }
	public String getLibelleCourt() { return this.panelPppNord.getLibelleCourt(); }
	public Module getModule ()      { return this.module;                     }

	public HashMap<String, Integer> getDataHeures() { return this.panelPppCentre.getData(); }
	public HashMap<String,Integer>  getData()       { return this.panelPppCentre.getData(); }

	public List<Intervenant> getIntervenants() { return this.frame.getControleur().getCtrl().metier().getIntervenants(); }
	public List<TypeHeure>   getTypesHeures()  { return this.frame.getControleur().getCtrl().metier().getTypesHeures();  }

	public boolean isValide() {
		return this.panelPppCentre.isValide();
	}

	public int getSommeAffecte() {
		return this.panelPppCentre.getSommeAffecte();
	}

	public int getSommePN() {
		return this.panelPppCentre.getSommePN();
	}

}
