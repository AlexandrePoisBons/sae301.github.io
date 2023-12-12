package ihm.previsionnel.ressources.ressourcesNord;

import java.awt.BorderLayout;

//Import classes java
import javax.swing.JPanel;

import ihm.previsionnel.ressources.PanelRessources;

public class PRNord extends JPanel {
	private PanelRessources 	panelMere		;
	private PanelModule 		panelModule		;
	private PanelHeureModule 	panelHeureModule;

	public PRNord(PanelRessources panelMere){
		//Synchronisation des pages
		this.panelMere = panelMere;

		this.setLayout(new BorderLayout());

		//Initialisation des panels
		this.panelModule		 = new PanelModule		(this);
		this.panelHeureModule	 = new PanelHeureModule	(this);

		//Initialisation d'un panel de mise en page
		JPanel pnlEstVide		 = new JPanel				();

		this.add(this.panelModule, BorderLayout.CENTER		);
		this.add(this.panelHeureModule, BorderLayout.SOUTH	);
		
	}
}