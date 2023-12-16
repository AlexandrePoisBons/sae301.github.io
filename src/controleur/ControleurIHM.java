package controleur;

import ihm.accueil.FrameAccueil;

public class ControleurIHM {

	private FrameAccueil frame;
	private Controleur ctrl;


	public ControleurIHM(Controleur ctrl) {
		this.ctrl = ctrl;
		this.frame = new FrameAccueil(this);
	}
	
	public Controleur getCtrl() { return this.ctrl; }


}
