package ihm.previsionnel.stage;

//Import classes Java
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.util.HashMap;

import metier.Heure;
import metier.Intervenant;
import java.util.List;


//Import classes externes
import ihm.accueil.*;
import ihm.previsionnel.PanelPrevi;
import ihm.previsionnel.stage.stageCentre.PCentreStage;
import ihm.previsionnel.stage.stageSud.PanelSudStage;
import ihm.previsionnel.stage.stageNord.PNordStage;
import metier.Module;
import metier.TypeHeure;

public class PanelStage extends JPanel {
	private FrameAccueil  frame;
	private PanelPrevi    panelMere;
	private PNordStage    pNordStage;
	private PCentreStage  pCentreStage;
	private PanelSudStage pSudStage;
	private Module        module;

	public PanelStage(FrameAccueil frame, PanelPrevi panelMere, Module m) {
		this.frame     = frame;
		this.panelMere = panelMere;
		this.module    = m;


		this.frame.setTitle("Prévisionnel - Module: Stage");

		//Définition de la taille et la position de la fenêtre
		int hauteur = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()  - (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.05);
		int largeur = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int xSize   = (int)(largeur*0.75);
		int ySize   = (int)(hauteur*0.85);

		this.frame.setSize(xSize, ySize);
		this.frame.setLocation((int)(largeur*0.5-xSize*0.5),(int)(hauteur*0.5-ySize*0.5));
		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(0, 5, 0, 10));

		this.pNordStage   = new PNordStage(this, this.module);
		this.pNordStage.setValues();
		this.pCentreStage = new PCentreStage(this, this.module);
		this.pSudStage    = new PanelSudStage(this.frame, this.panelMere, this, this.module);

		// Utiliser BoxLayout pour organiser les composants horizontalement
		this.add( this.pNordStage,   BorderLayout.NORTH  );
		this.add( this.pCentreStage, BorderLayout.CENTER );
		this.add( this.pSudStage,    BorderLayout.SOUTH  );
	}


	public void enregistrer(Module m) {
		List<Heure> heures = this.pCentreStage.getHeures();

		m.setHeures(heures);
		this.panelMere.ajouterModule(m);

		this.frame.changerPanel(new PanelPrevi(this.frame));
	}

	public void update(Module oldModule, Module newModule) {
		List<Heure> heures = this.pCentreStage.getHeures();

		newModule.setHeures(heures);
		this.panelMere.updateModule(oldModule, newModule);

		this.frame.changerPanel(new PanelPrevi(this.frame));
	}

	public void setErreur(String message) {
		this.pSudStage.setErreur(message);
	}


	public String getSemestre()     { return this.panelMere.getSemestre();      }
	public int    getNbEtd()        { return this.panelMere.getNbEtd();         }
	public int    getNbGpTd()       { return this.panelMere.getNbGpTd();        }
	public int    getNbGpTp()       { return this.panelMere.getNbGpTp();        }
	public String getCode()         { return this.pNordStage.getCode();         }
	public String getLibelle()      { return this.pNordStage.getLibelle();      }
	public String getLibelleCourt() { return this.pNordStage.getLibelleCourt(); }
	public Module getModule()       { return this.module;                       }
	public boolean estValide()      { return this.pCentreStage.estValide();     }
	public void fermerFrameFormulaire() { this.pCentreStage.fermerFrameFormulaire(); }

	public HashMap<String, Integer> getDataHeures() { return this.pCentreStage.getData(); }
	public HashMap<String,Integer>  getData()       { return this.pCentreStage.getData(); }
	public List<Heure> getDeletedHeures() { return this.pCentreStage.getDeletedHeures(); }
	
	public List<Intervenant> getIntervenants() { return this.frame.getControleur().getCtrl().metier().getIntervenants(); }
	public List<TypeHeure>   getTypesHeures()  { return this.frame.getControleur().getCtrl().metier().getTypesHeures();  }




}
