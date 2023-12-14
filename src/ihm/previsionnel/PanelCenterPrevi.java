package ihm.previsionnel;

//Imports classes Java
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.*;

import ihm.accueil.FrameAccueil;

public class PanelCenterPrevi extends JPanel {
	// Attribut(s) final(s)
	private final int NB_SEMESTRE = 6;

	// Attribut(s)
	private FrameAccueil frame;
	private ArrayList<PanelSemestre> listSemestre;
	private JTabbedPane modules;
	private ArrayList<JPanel> listPanelCreer;

	// Constructeur
	public PanelCenterPrevi(FrameAccueil frameAccueil) {

		// Synchronisation des pages
		this.frame = frameAccueil;
		this.frame.setTitle("Prévisionnel"); 

		 //Définition de la taille et la position de la fenêtre
		 int hauteur = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()  - (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.05);
		 int largeur = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		 int xSize = (int)(largeur*0.65);
		 int ySize = (int)(hauteur*0.45);
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
		this.modules.setPreferredSize(new Dimension(550, 200))			;

		// Ajouter les onglets au JTabbedPanes
		for (int i = 0; i < this.listSemestre.size(); i++) { this.modules.add(this.listSemestre.get(i), "s" + (i + 1)); }

		// Ajouter le JTabbedPane à la fenêtre
		this.add(this.modules);

		// Affichage
		this.setVisible(true);
	}

	public PanelSemestre getCurrentSemestre() {
		int i = this.modules.getSelectedIndex();

		for ( PanelSemestre panelSemestre : this.listSemestre )
			if (panelSemestre.getIdSemestre() == i+1)
				return panelSemestre;

		return null;
	}

	public int    getNumCurrentModule() { return this.modules.getSelectedIndex();               }
	public String getSemestre()         { return "S"+this.getCurrentSemestre().getIdSemestre(); }
	public String getNbEtd()            { return this.getCurrentSemestre().getNbEtd();          }
	public String getNbGpTd()           { return this.getCurrentSemestre().getNbGpTd();         }
	public String getNbGpTp()           { return this.getCurrentSemestre().getNbGpTp();         }

}
