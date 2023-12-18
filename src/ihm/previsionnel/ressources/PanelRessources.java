package ihm.previsionnel.ressources;

import ihm.previsionnel.PanelPrevi;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.util.HashMap;
import java.util.List;

import ihm.accueil.*;
import ihm.previsionnel.*;
import ihm.previsionnel.ressources.ressourcesCentre.PRCentre;
import ihm.previsionnel.ressources.ressourcesCentre.progNat.ProgNat;
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
	private PanelSud    pSud;
	private Module module;

	public PanelRessources(FrameAccueil frame, PanelPrevi framePrevi, Module m) {
		this.frame = frame;
		this.panelMere = framePrevi;
		this.module = m;

		if ( this.module != null )
			System.out.println("COUCOU TOI"+m.getHeures());

		this.frame.setTitle("Prévisionnel - Module: Ressources"); //Définition du titre de la fenêtre
		//Définition de la taille et la position de la fenêtre
		int hauteur = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()  - (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.05);
		int largeur = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int xSize = (int)(largeur*0.65);
		int ySize = (int)(hauteur*0.9);
		this.frame.setSize(xSize, ySize);
		this.frame.setLocation((int)(largeur*0.5-xSize*0.5),(int)(hauteur*0.5-ySize*0.5));
		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(0, 5, 0, 10));

		if ( this.module != null ) {
			this.pRNord   = new PRNord   (this, this.module);
			this.pRCentre = new PRCentre (this, this.module);
			this.pSud     = new PanelSud (this.frame, this.panelMere, this, this.module);
		} else {
			this.pRNord   = new PRNord   (this, null);
			this.pRCentre = new PRCentre (this, null);
			this.pSud     = new PanelSud (this.frame, this.panelMere, this, null);
		}


		// Utiliser BoxLayout pour organiser les composants horizontalement
		this.add( this.pRNord,   BorderLayout.NORTH);
		this.add( this.pRCentre, BorderLayout.CENTER);
		this.add( this.pSud,     BorderLayout.SOUTH);
	}


	public void enregistrer(Module m) {
		List<Heure> heures = this.pRCentre.getHeures(m);
		m.setHeures(heures);
		this.panelMere.ajouterModule(m);

		this.frame.changerPanel(new PanelPrevi(this.frame));

	}



	public String getSemestre()     { return this.panelMere.getSemestre();  }
	public String getNbEtd()        { return this.panelMere.getNbEtd();     }
	public String getNbGpTd()       { return this.panelMere.getNbGpTd();    }
	public String getNbGpTp()       { return this.panelMere.getNbGpTp();    }
	public String getCode()         { return this.pRNord.getCode();         }
	public String getLibelle()      { return this.pRNord.getLibelle();      }
	public String getLibelleCourt() { return this.pRNord.getLibelleCourt(); }
	public Module getModule() { return this.module; }

	public HashMap<String, Integer> getDataHeures() { return this.pRCentre.getData(); }

	public HashMap<String,Integer> getData() {
		return this.pRCentre.getData();
	}

	public List<Intervenant> getIntervenants() { return frame.getControleur().getCtrl().metier().getIntervenants(); }
	public List<TypeHeure> getTypesHeures() { return frame.getControleur().getCtrl().metier().getTypesHeures(); }

}
