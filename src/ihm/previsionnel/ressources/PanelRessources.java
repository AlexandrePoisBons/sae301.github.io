package ihm.previsionnel.ressources;

import ihm.previsionnel.PanelPrevi;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.util.HashMap;
import java.util.List;

import ihm.accueil.*;
import ihm.previsionnel.ressources.ressourcesCentre.PRCentre;
import ihm.previsionnel.ressources.ressourcesNord.PRNord;
import ihm.previsionnel.ressources.ressourcesSud.PanelSud;
import metier.Heure;
import metier.Intervenant;
import metier.Module;
import metier.TypeHeure;

public class PanelRessources extends JPanel {
	private FrameAccueil frame;
	private PanelPrevi  panelMere;
	private PRNord      pRNord;
	private PRCentre    pRCentre;
	private PanelSud    panelSud;
	private Module      module;

	public PanelRessources(FrameAccueil frame, PanelPrevi framePrevi, Module m) {
		this.frame = frame;
		this.panelMere = framePrevi;
		this.module = m;

		this.frame.setTitle("Prévisionnel - Module: Ressource"); //Définition du titre de la fenêtre
		//Définition de la taille et la position de la fenêtre
		int hauteur = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()  - (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.05);
		int largeur = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int xSize = (int)(largeur*0.65);
		int ySize = (int)(hauteur*0.9);
		this.frame.setSize(xSize, ySize);
		this.frame.setLocation((int)(largeur*0.5-xSize*0.5),(int)(hauteur*0.5-ySize*0.5));
		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(0, 5, 0, 10));


		this.pRNord   = new PRNord   (this, this.module);
		this.pRCentre = new PRCentre (this, this.module);
		this.panelSud = new PanelSud (this.frame, this.panelMere, this, this.module);


		// Utiliser BoxLayout pour organiser les composants horizontalement
		this.add( this.pRNord,   BorderLayout.NORTH  );
		this.add( this.pRCentre, BorderLayout.CENTER );
		this.add( this.panelSud, BorderLayout.SOUTH  );
	}


	public void enregistrer(Module m) {
		List<Heure> heures = this.pRCentre.getHeures();
		m.setHeures(heures);
		this.panelMere.ajouterModule(m);

		this.frame.changerPanel(new PanelPrevi(this.frame));

	}

	public void update(Module oldModule, Module newModule) {
		List<Heure> heures = this.pRCentre.getHeures();

		newModule.setHeures(heures);
		this.panelMere.updateModule(oldModule, newModule);

		this.frame.changerPanel(new PanelPrevi(this.frame));
	}

	public void setErreur(String message){
		this.panelSud.setErreur(message);
	}

	public String getSemestre()     { return this.panelMere.getSemestre();  }
	public int    getNbEtd()        { return this.panelMere.getNbEtd();     }
	public int    getNbGpTd()       { return this.panelMere.getNbGpTd();    }
	public int    getNbGpTp()       { return this.panelMere.getNbGpTp();    }
	public String getCode()         { return this.pRNord.getCode();         }
	public String getLibelle()      { return this.pRNord.getLibelle();      }
	public String getLibelleCourt() { return this.pRNord.getLibelleCourt(); }
	public Module getModule()       { return this.module;                   }

	public HashMap<String, Integer> getDataHeures() { return this.pRCentre.getData(); }
	public HashMap<String,Integer>  getData()       { return this.pRCentre.getData(); }

	public List<Intervenant> getIntervenants() { return this.frame.getControleur().getCtrl().metier().getIntervenants(); }
	public List<TypeHeure>   getTypesHeures()  { return this.frame.getControleur().getCtrl().metier().getTypesHeures();  }

}