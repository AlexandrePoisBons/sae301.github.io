package ihm.previsionnel.ressources;

//Imports des classes externes
import ihm.previsionnel.*;

public class PanelRessources{
	//Attributs
	private PanelPrevi frame;
	private PRNord pRNord;

	public PanelRessources(PanelPrevi frame){
		//Synchronisation des pages
		this.frame = frame;

		this.pRNord = new PRNord(this);
	}
}