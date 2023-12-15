package ihm.previsionnel.ressources;

import ihm.previsionnel.PanelPrevi;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.util.HashMap;

import ihm.accueil.*;
import ihm.previsionnel.*;
import ihm.previsionnel.ressources.ressourcesCentre.PRCentre;
import ihm.previsionnel.ressources.ressourcesCentre.progNat.ProgNat;
import ihm.previsionnel.ressources.ressourcesNord.PRNord;
import ihm.previsionnel.ressources.ressourcesSud.PanelSud;

public class PanelRessources extends JPanel {
    private FrameAccueil frame;
    private PanelPrevi  panelMere;
    private PRNord      pRNord;
    private PRCentre    pRCentre;
    private PanelSud    pSud;

    public PanelRessources(FrameAccueil frame, PanelPrevi framePrevi) {
        this.frame = frame;
        this.panelMere = framePrevi;

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

        this.pRNord   = new PRNord   (this);
        this.pRCentre = new PRCentre (this);
        this.pSud     = new PanelSud (this.frame,this.panelMere, this);

        // Utiliser BoxLayout pour organiser les composants horizontalement
        this.add( this.pRNord,   BorderLayout.NORTH);
        this.add( this.pRCentre, BorderLayout.CENTER);
        this.add( this.pSud,     BorderLayout.SOUTH);
    }

    public String getSemestre()     { return this.panelMere.getSemestre();  }
    public String getNbEtd()        { return this.panelMere.getNbEtd();     }
    public String getNbGpTd()       { return this.panelMere.getNbGpTd();    }
    public String getNbGpTp()       { return this.panelMere.getNbGpTp();    }
    public String getCode()         { return this.pRNord.getCode();         }
    public String getLibelle()      { return this.pRNord.getLibelle();      }
    public String getLibelleCourt() { return this.pRNord.getLibelleCourt(); }

    public HashMap<String, Integer> getDataHeures() { return this.pRCentre.getData(); }

    public HashMap<String,Integer> getData() {
        return this.pRCentre.getData();
    }

}
