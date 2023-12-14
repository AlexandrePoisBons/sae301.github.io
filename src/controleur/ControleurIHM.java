package controleur;

import ihm.accueil.FrameAccueil;

public class ControleurIHM {

	private FrameAccueil frame;
	private Controleur ctrl;


	public static void main(String[] args) { new ControleurIHM(null); }

	public ControleurIHM(Controleur ctrl) {
		this.ctrl = ctrl;
		this.frame = new FrameAccueil();
	}



}
