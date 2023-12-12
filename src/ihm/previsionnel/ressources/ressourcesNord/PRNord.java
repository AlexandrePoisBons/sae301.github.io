package ihm.previsionnel.ressources.ressourcesNord;

//Import classes java
import javax.swing.JPanel;

public class PRNord extends JPanel {
	private PanelRessources 	panelMere		;
	private PanelModule 		panelModule		;
	private PanelHeureModule 	panelHeureModule;

	public PRNord(PanelRessources panelMere){
		//Synchronisation des pages
		this.panelMere = panelMere;

		//Initialisation des panels
		this.panelModule		 = new PanelModule		(this);
		this.PanelHeureModule	 = new PanelHeureModule	(this);

		//Initialisation d'un panel de mise en page
		JPanel pnlEstVide		 = new JPanel				();
		
	}
}