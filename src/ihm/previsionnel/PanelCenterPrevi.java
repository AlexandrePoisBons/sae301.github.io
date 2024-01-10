package ihm.previsionnel;

//Imports classes Java
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import ihm.accueil.FrameAccueil;
import metier.Module;

public class PanelCenterPrevi extends JPanel {
	// Attribut(s) final(s)
	private final int NB_SEMESTRE = 6;

	// Attribut(s)
	private FrameAccueil frame;
	private ArrayList<PanelSemestre> listSemestre;
	private JTabbedPane modules;

	// Constructeur
	public PanelCenterPrevi(FrameAccueil frameAccueil) {

		// Synchronisation des pages
		this.frame = frameAccueil;
		this.frame.setTitle("Prévisionnel");

		this.listSemestre = new ArrayList<>();

		 //Définition de la taille et la position de la fenêtre
		 int hauteur = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()  - (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.05);
		 int largeur = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		 int xSize = (int)(largeur*0.7);
		 int ySize = (int)(hauteur*0.55);
		 this.frame.setSize(xSize, ySize);
		 this.frame.setLocation((int)(largeur*0.5-xSize*0.5),(int)(hauteur*0.5-ySize*0.5));

		// création de la liste des semestres et des semestres qu'elle contient
		this.listSemestre = new ArrayList<PanelSemestre>();
		for (int i = 0; i < this.NB_SEMESTRE; i++)
			this.listSemestre.add(new PanelSemestre(this.frame, i+1));

		// création du JTabbedPane
		this.modules = new JTabbedPane(JTabbedPane.TOP);
		this.modules.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

		// Initialiser la taille du JTabbedPane
		this.modules.setPreferredSize(new Dimension(820, 250));

		// Ajouter les onglets au JTabbedPanes
		for (int i = 0; i < this.listSemestre.size(); i++) { this.modules.add(this.listSemestre.get(i), "s" + (i + 1)); }

		// Ajouter le JTabbedPane à la fenêtre
		this.add(this.modules);

		// Affichage
		this.setVisible(true);
	}

	public PanelSemestre getCurrentSemestre() {
		int i = this.modules.getSelectedIndex();

		for ( PanelSemestre panelSemestre : this.listSemestre ){
			if (panelSemestre.getIdSemestre() == i+1) {
				return panelSemestre;
			}
		}
		return null;
	}

	public void setModules(List<Module> alModules) {
		// Ajouter les modules au JTabbedPane
		List<Module> list;
		int i=1;
		for (PanelSemestre semestre : this.listSemestre) {
			list = new ArrayList<Module>();
			for (Module module : alModules) {
				if ( module.getSemestre().equals("S"+i) ) list.add(module);
			}
			semestre.setModules(list);
			i++;
		}
	}

	public void updateModule(Module oldModule, Module newModule) {

		for(Module m : this.getCurrentSemestre().getModules()) {
			if ( m.getIdModule() == newModule.getIdModule()) {
				this.frame.getControleur().updateModule(oldModule, newModule);
				this.getCurrentSemestre().getModules().remove(oldModule);
				this.getCurrentSemestre().getModules().add(newModule);
				return;
			}
		}
	}

	public void ajouterModule(Module module) {
		this.getCurrentSemestre().ajouterModule(module);
		for( Module m : this.getCurrentSemestre().getModules() ) {
			if ( m.getIdModule() == module.getIdModule() ) {
				this.frame.getControleur().ajouterModule(module);
				for (PanelSemestre panelSemestre : this.listSemestre) {
					if (module.getSemestre().equals("S"+panelSemestre.getIdSemestre())){
						panelSemestre.ajouterModule(module);
					}
				}
				return;
			}
		}

	}

	public int getNumCurrentModule() { return this.modules.getSelectedIndex();           }
	public int getNbEtd()            { return this.getCurrentSemestre().getNbEtd();      }
	public int getNbGpTd()           { return this.getCurrentSemestre().getNbGpTd();     }
	public int getNbGpTp()           { return this.getCurrentSemestre().getNbGpTp();     }
	public int getNbSemaines()       { return this.getCurrentSemestre().getNbSemaines(); }

	public boolean moduleSelectionne() {
		return this.getCurrentSemestre().moduleSelectionne();
	}

}
