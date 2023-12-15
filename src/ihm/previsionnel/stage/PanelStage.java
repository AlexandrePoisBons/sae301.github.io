package ihm.previsionnel.stage;

//Import classes Java
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.util.HashMap;

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

		this.frame.setTitle("Prévisionnel - Module: Stage");
		//Définition de la taille et la position de la fenêtre
		int hauteur = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()  - (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.05);
		int largeur = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int xSize = (int)(largeur*0.75);
		int ySize = (int)(hauteur*0.85);
		this.frame.setSize(xSize, ySize);
		this.frame.setLocation((int)(largeur*0.5-xSize*0.5),(int)(hauteur*0.5-ySize*0.5));
		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(0, 5, 0, 10));

        this.pNordStage = new PNordStage(this);
        this.pCentreStage = new PCentreStage(this);
        this.pSudStage = new PanelSudStage(this.frame, this.panelMere, this);

        // Utiliser BoxLayout pour organiser les composants horizontalement
        this.add(this.pNordStage, BorderLayout.NORTH);
        this.add(this.pCentreStage, BorderLayout.CENTER);
        this.add(this.pSudStage, BorderLayout.SOUTH);
	}


	public String getSemestre()     { return this.panelMere.getSemestre();      }
    public String getNbEtd()        { return this.panelMere.getNbEtd();         }
    public String getNbGpTd()       { return this.panelMere.getNbGpTd();        }
    public String getNbGpTp()       { return this.panelMere.getNbGpTp();        }
    public String getCode()         { return this.pNordStage.getCode();         }
    public String getLibelle()      { return this.pNordStage.getLibelle();      }
    public String getLibelleCourt() { return this.pNordStage.getLibelleCourt(); }

    public HashMap<String, Integer> getDataHeures() { return this.pCentreStage.getData(); }

    public HashMap<String,Integer> getData() {
        return this.pCentreStage.getData();
    }

}
