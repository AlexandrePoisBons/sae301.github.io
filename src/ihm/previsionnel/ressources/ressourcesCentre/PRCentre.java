package ihm.previsionnel.ressources.ressourcesCentre;

import java.awt.BorderLayout;
import javax.swing.*;

import ihm.previsionnel.ressources.PanelRessources;
import ihm.previsionnel.ressources.ressourcesCentre.repartition.PanelRepartition;

public class PRCentre extends JPanel{
	private PanelRessources panelMere;
	private PanelRepartition panelRepartition;

	public PRCentre(PanelRessources panelMere){
		this.panelMere = panelMere;
		this.setLayout(new BorderLayout());

		this.panelRepartition = new PanelRepartition(this);

		this.add(this.panelRepartition, BorderLayout.CENTER);
	}
	
}
