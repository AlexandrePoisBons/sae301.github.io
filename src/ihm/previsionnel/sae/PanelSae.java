package ihm.previsionnel.sae;
//Import classes Java
import javax.swing.*;
import java.awt.*;

//Import classes externes
import ihm.accueil.*;
import ihm.previsionnel.PanelPrevi;
import ihm.previsionnel.sae.saeCentre.PCentreSae;
import ihm.previsionnel.sae.saeNord.PNordSae;
import ihm.previsionnel.sae.saeSud.PanelSudSae;

public class PanelSae extends JPanel{
	private FrameAccueil frame;
	private PanelPrevi panelMere;
	private PNordSae pNordSae;
	private PCentreSae pCentreSae;
	private PanelSudSae pSudSae;

	public PanelSae(FrameAccueil frame, PanelPrevi panelMere){
		this.frame = frame;
		this.panelMere = panelMere;

		this.frame.setTitle("Prévisionnel - Module: SAE");
		this.frame.setSize(1100, 650);
		this.frame.setLocation(20, 20);
		this.setLayout(new BorderLayout());

        this.pNordSae = new PNordSae(this);
        this.pCentreSae = new PCentreSae(this);
        this.pSudSae = new PanelSudSae(this.frame, this.panelMere);

        // Utiliser BoxLayout pour organiser les composants horizontalement
        this.add(this.pNordSae, BorderLayout.NORTH);
        this.add(this.pCentreSae, BorderLayout.CENTER);
        this.add(this.pSudSae, BorderLayout.SOUTH);
	}
}