package ihm.etat;

import javax.swing.*;

import ihm.accueil.FrameAccueil;

public class PanelModule extends JPanel {
	private FrameAccueil frame;
	private PanelEtats panelMere;

	public PanelModule(FrameAccueil frame, PanelEtats panelMere) {
		this.frame = frame;
		this.panelMere = panelMere;
	}
}