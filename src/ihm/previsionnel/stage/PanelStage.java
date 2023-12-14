package ihm.previsionnel.stage;

//Import classes Java
import javax.swing.*;
import java.awt.*;

//Import classes externes
import ihm.accueil.*;
import ihm.previsionnel.PanelPrevi;
import ihm.previsionnel.stage.stageCentre.PCentreStage;
import ihm.previsionnel.stage.stageSud.PanelSudStage;
import ihm.previsionnel.stage.stageNord.PNordStage;

public class PanelStage extends JPanel {
	private FrameAccueil frame;
	private PanelPrevi panelMere;
	private PNordStage pNordStage;
	private PCentreStage pCentreStage;
	private PanelSudStage pSudStage;

	public PanelStage(FrameAccueil frame, PanelPrevi panelMere){
		this.frame = frame;
		this.panelMere = panelMere;

		this.frame.setTitle("Prévisionnel - Module: SAE");
		this.frame.setSize(1100, 650);
		this.frame.setLocation(20, 20);
		this.setLayout(new BorderLayout());

        this.pNordStage = new PNordStage(this);
        this.pCentreStage = new PCentreStage(this);
        this.pSudStage = new PanelSudStage(this.frame, this.panelMere);

        // Utiliser BoxLayout pour organiser les composants horizontalement
        this.add(this.pNordStage, BorderLayout.NORTH);
        this.add(this.pCentreStage, BorderLayout.CENTER);
        this.add(this.pSudStage, BorderLayout.SOUTH);
	}
}
