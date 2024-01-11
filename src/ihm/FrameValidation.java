package ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;

import javax.swing.*;

import ihm.parametrage.PanelParam;
import ihm.previsionnel.ressources.ressourcesSud.PanelSudRessources;
import ihm.previsionnel.ppp.pppSud.PanelSudPpp;
import ihm.previsionnel.stage.stageSud.PanelSudStage;
import ihm.previsionnel.sae.saeSud.PanelSudSae;



public class FrameValidation extends JFrame implements ActionListener{
	
	private JPanel panelValidation;

	private JButton btnOui;
	private JButton btnNon;

	private String  nomPanel;

	private PanelParam          panelParam;
	private PanelSudPpp         panelSudPpp;
	private PanelSudRessources  panelSudRessources;
	private PanelSudSae         panelSudSae;
	private PanelSudStage       panelSudStage;

	public FrameValidation(PanelParam panelParam) {
		this.panelParam = panelParam;
		this.nomPanel = "PanelParam";

		this.init();
	}

	public FrameValidation(PanelSudPpp panelSudPpp) {
		this.panelSudPpp = panelSudPpp;
		this.nomPanel = "PanelSudPpp";

		this.init();
	}

	public FrameValidation(PanelSudRessources panelSudRessources) {
		this.panelSudRessources = panelSudRessources;
		this.nomPanel = "PanelSudRessources";

		this.init();
	}

	public FrameValidation(PanelSudSae panelSudSae) {
		this.panelSudSae = panelSudSae;
		this.nomPanel = "PanelSudSae";
	
		this.init();
	}

	public FrameValidation(PanelSudStage panelSudStage) {
		this.panelSudStage = panelSudStage;
		this.nomPanel = "PanelSudStage";

		this.init();
	}


	private void init() {
		//Définition de la taille et la position de la fenêtre
		int hauteur = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()  - (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.05);
		int largeur = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int xSize = (int)(largeur*0.2);
		int ySize = (int)(hauteur*0.2);
		this.setSize(xSize, ySize);
		this.setLocation((int)(largeur*0.5-xSize*0.5),(int)(hauteur*0.5-ySize*0.5));

		this.panelValidation = new JPanel();
		
		this.btnOui = new JButton("Oui");
		this.btnNon = new JButton("Non");

		this.btnOui.addActionListener(this);
		this.btnNon.addActionListener(this);

		this.panelValidation.add(new JLabel("Voulez-vous enregistrer les modifications ?"));
		this.panelValidation.add(this.btnOui);
		this.panelValidation.add(this.btnNon);


		this.add(this.panelValidation);
		this.setVisible(true);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		if ( e.getSource() == this.btnOui ) {

			switch( this.nomPanel ) {
				case "PanelParam"         -> this.panelParam.validation(true);
				case "PanelSudPpp"        -> this.panelSudPpp.validation(true);
				case "PanelSudRessources" -> this.panelSudRessources.validation(true);
				case "PanelSudSae"        -> this.panelSudSae.validation(true);
				case "PanelSudStage"      -> this.panelSudStage.validation(true);
			}
			this.dispose();
			this.repaint();
			this.revalidate();
		} else if ( e.getSource() == this.btnNon ) {

			switch( this.nomPanel ) {
				case "PanelParam"         -> this.panelParam.validation(false);
				case "PanelSudPpp"        -> this.panelSudPpp.validation(false);
				case "PanelSudRessources" -> this.panelSudRessources.validation(false);
				case "PanelSudSae"        -> this.panelSudSae.validation(false);
				case "PanelSudStage"      -> this.panelSudStage.validation(false);
			}
			this.dispose();
			this.repaint();
			this.revalidate();
		}
	}
}
