package ihm.previsionnel;

//Imports classes Java
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.*;

import ihm.accueil.FrameAccueil;

public class PanelCenterPrevi extends JPanel {
	// Attribut(s) final(s)
	private final int NB_SEMESTRE = 6;

	// Attribut(s)
	private FrameAccueil panelMere;
	private ArrayList<PanelSemestre> listSemestre;

	// Constructeur
	public PanelCenterPrevi(FrameAccueil frameAccueil) {

		// Synchronisation des pages
		this.panelMere = frameAccueil;

		// Définition de la taille de la fenêtre
		this.panelMere.setSize(800, 300);

		// création de la liste des semestres et des semestres qu'elle contient
		this.listSemestre = new ArrayList<PanelSemestre>()			;
		for (int i = 0; i < this.NB_SEMESTRE; i++)
			this.listSemestre.add(new PanelSemestre(this.panelMere, i));

		// création du JTabbedPane
		JTabbedPane modules = new JTabbedPane(JTabbedPane.TOP)		;
		modules.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT)	;

		// Initialiser la taille du JTabbedPane
		modules.setPreferredSize(new Dimension(550, 200))			;

		// Ajouter les onglets au JTabbedPanes
		for (int i = 0; i < this.listSemestre.size(); i++) {modules.add(this.listSemestre.get(i), "s" + (i + 1));}

		// Ajouter le JTabbedPane à la fenêtre
		this.add(modules);

		// Affichage
		this.setVisible(true);
	}
}