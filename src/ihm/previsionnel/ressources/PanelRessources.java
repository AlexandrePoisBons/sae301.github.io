package ihm.previsionnel.ressources;
//Imports classes Java
import javax.swing.JPanel;

//Imports des classes externes
import ihm.previsionnel.*;
import ihm.previsionnel.ressources.ressourcesNord.PRNord;

public class PanelRessources extends JPanel{
	//Attributs
	private PanelPrevi frame;
	private PRNord pRNord;

	public PanelRessources(PanelPrevi frame){
		//Synchronisation des pages
		this.frame = frame;

		this.pRNord = new PRNord(this);
	}
}