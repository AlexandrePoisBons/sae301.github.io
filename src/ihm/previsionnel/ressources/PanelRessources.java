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
import ihm.previsionnel.ressources.ressourcesSud.PanelSudRessources;
import metier.Heure;
import metier.Intervenant;
import metier.Module;
import metier.TypeHeure;

public class PanelRessources extends JPanel {
	private FrameAccueil frame;
	private PanelPrevi  panelMere;
	private PRNord      pRNord;
	private PRCentre    pRCentre;
	private PanelSudRessources    panelSudRessources;
	private Module      module;

	public PanelRessources(FrameAccueil frame, PanelPrevi framePrevi, Module m) {
		this.frame = frame;
		this.panelMere = framePrevi;
		this.module = m;

		this.frame.setTitle("Prévisionnel - Module : Ressource"); //Définition du titre de la fenêtre
		//Définition de la taille et la position de la fenêtre
		int hauteur = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()  - (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.05);
		int largeur = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int xSize = (int)(largeur*0.7);
		int ySize = (int)(hauteur*0.9);
		this.frame.setSize(xSize, ySize);
		this.frame.setLocation((int)(largeur*0.5-xSize*0.5),(int)(hauteur*0.5-ySize*0.5));
		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(0, 5, 0, 10));


		this.pRNord   = new PRNord   (this, this.module);
		this.pRNord.setValues();
		this.pRCentre = new PRCentre (this, this.module);
		this.panelSudRessources = new PanelSudRessources (this.frame, this.panelMere, this, this.module);


		// Utiliser BoxLayout pour organiser les composants horizontalement
		this.add( this.pRNord,   BorderLayout.NORTH  );
		this.add( this.pRCentre, BorderLayout.CENTER );
		this.add( this.panelSudRessources, BorderLayout.SOUTH  );
	}

	public void saveDataHeures() {
		HashMap<TypeHeure, HashMap<String,Integer>> map = this.pRCentre.getDataHeuresTypesHeures();

		this.frame.getControleur().majTypesHeuresModule(this.module, map);

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

	public String getSemestre()     { return this.panelMere.getSemestre();  }
	public int    getNbEtd()        { return this.panelMere.getNbEtd();     }
	public int    getNbGpTd()       { return this.panelMere.getNbGpTd();    }
	public int    getNbGpTp()       { return this.panelMere.getNbGpTp();    }
	public String getCode()         { return this.pRNord.getCode();         }
	public String getLibelle()      { return this.pRNord.getLibelle();      }
	public String getLibelleCourt() { return this.pRNord.getLibelleCourt(); }
	public Module getModule()       { return this.module;                   }
	public TypeHeure getTypeHeureParNom(String nom) { return this.frame.getControleur().getTypeHeureByNom(nom); }
	public HashMap<TypeHeure, HashMap<String,Integer>> getHeuresParTypesHeures(Module module) { return this.frame.getControleur().getTypesHeuresParModule(module); }

	//public HashMap<TypeHeure, HashMap<String,Integer>> getValeursHeuresParTypesHeures(Module module) { return null; }

	public boolean estValide() { return this.pRCentre.estValide(); }

	public HashMap<String, Integer> getDataHeures()    { return this.pRCentre.getData();     }
	public HashMap<String,Integer>  getData()          { return this.pRCentre.getData();     }
	public List<Heure>              getDeletedHeures() { return pRCentre.getDeletedHeures(); }

	public List<Intervenant> getIntervenants() { return this.frame.getControleur().getIntervenants(); }
	public List<TypeHeure>   getTypesHeures()  { return this.frame.getControleur().getTypesHeures();  }

	//pour mettre à jour les données dans pCentre
	public int getNbGpTdMaj() { return this.pRNord.getTxtNbGpTd(); }
	public int getNbGpTpMaj() { return this.pRNord.getTxtNbGpTp(); }
	public void fermerFrameFormulaire() { this.pRCentre.fermerFrameFormulaire(); }

}
