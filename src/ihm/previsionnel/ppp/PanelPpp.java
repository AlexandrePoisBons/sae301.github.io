package ihm.previsionnel.ppp;

import ihm.previsionnel.PanelPrevi;
import ihm.previsionnel.ppp.pppCentre.PanelPppCentre;
import ihm.previsionnel.ppp.pppNord.PanelPppNord;

import javax.swing.*;
import java.awt.*;

import ihm.accueil.*;
import ihm.previsionnel.ppp.pppSud.PanelSud;

public class PanelPpp extends JPanel {
    private FrameAccueil frame;
    private PanelPrevi  panelMere;
    private PanelPppNord      panelPppNord;
    private PanelPppCentre    panelPppCentre;
    private PanelSud    pSud;

    public PanelPpp(FrameAccueil frame, PanelPrevi framePrevi) {
        this.frame = frame;
        this.panelMere = framePrevi;

        this.frame.setTitle("Prévisionnel - Module: Ressources"); //Définition du titre de la fenêtre
        //Définition de la taille et la position de la fenêtre
		int hauteur = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()  - (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.05);
		int largeur = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int xSize = (int)(largeur*0.75);
		int ySize = (int)(hauteur*0.9);
		this.frame.setSize(xSize, ySize);
		this.frame.setLocation((int)(largeur*0.5-xSize*0.5),(int)(hauteur*0.5-ySize*0.5));
		this.setLayout(new BorderLayout());

        this.panelPppNord = new PanelPppNord(this);
        this.panelPppCentre = new PanelPppCentre(this);
        this.pSud = new PanelSud(this.frame, this.panelMere);

        // Utiliser BoxLayout pour organiser les composants horizontalement
        this.add(this.panelPppNord, BorderLayout.NORTH);
        this.add(this.panelPppCentre, BorderLayout.CENTER);
        this.add(this.pSud, BorderLayout.SOUTH);
    }

    public String getSemestre() { return this.panelMere.getSemestre(); }
    public String getNbEtd() { return this.panelMere.getNbEtd(); }
    public String getNbGpTd() { return this.panelMere.getNbGpTd(); }
    public String getNbGpTp() { return this.panelMere.getNbGpTp(); }

}
