package ihm.accueil			;

import javax.swing.*		;

import controleur.ControleurIHM;

import java.awt.GridBagLayout;
import java.awt.Toolkit;

public class FrameAccueil extends JFrame {
	//Attribut(s)
	private JPanel panel;

	private ControleurIHM ctrl;

	//Constructeur
	public FrameAccueil(ControleurIHM ctrl) {
		this.ctrl = ctrl;
		this.setTitle("Accueil");
		this.setResizable(false);

		//Panel principal
		this.panel = new PanelAcceuil(this);

		//Ajout des composants
		 this.add(this.panel);


		//Permet la fermeture de la fenetre quand on appuie sur la croix
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		//Affichage
		this.setVisible(true);
	}

	//Permet de changer le panel en fonction du choix de l'utilisateur
	public void changerPanel(JPanel panel) {
		this.remove(this.panel);
		this.panel = panel;
		this.add(this.panel);
		this.setVisible(true);
	}


	public ControleurIHM getControleur() { return this.ctrl; }


}
