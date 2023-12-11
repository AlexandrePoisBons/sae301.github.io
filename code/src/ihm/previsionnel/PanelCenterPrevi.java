package previsionnel;

//Pour permettre la synchronisation des pages  
import acceuil.FrameAccueil;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.*;


public class PanelCenterPrevi extends JPanel {
	private final int NB_SEMESTRE = 6;

	private FrameAccueil frame;

	private ArrayList<PanelSemestre> listSemestre;

	public PanelCenterPrevi(FrameAccueil frameAccueil) {
		this.frame = frameAccueil;
		this.listSemestre = new ArrayList<PanelSemestre>();
		this.frame.setSize(800, 300);

		for(int i=0;i<this.NB_SEMESTRE;i++)
		{
			this.listSemestre.add(new PanelSemestre(this.frame, i));
		}

		JTabbedPane modules = new JTabbedPane(JTabbedPane.TOP);
		modules.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

		// Changer la taille du JTabbedPane
		modules.setPreferredSize(new Dimension(550, 200));		

		// Ajouter les onglets au JTabbedPanes
		for(int i=0;i<this.listSemestre.size();i++)
		{
			modules.add(this.listSemestre.get(i), "s" + (i+1));
		}

		// Ajouter le JTabbedPane à la fenêtre
		this.add(modules);

		this.setVisible(true);
	}
}