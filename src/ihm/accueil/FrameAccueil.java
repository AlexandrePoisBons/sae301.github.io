package ihm.accueil			;

import javax.swing.*		;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

public class FrameAccueil extends JFrame {
	//Attribut(s)
	private JPanel panel;


	//Constructeur
	public FrameAccueil() {
		this.setTitle("Accueil");
		int hauteur = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		int largeur = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		
		//Position
		this.setLocation((int)(hauteur*0.60), (int)(largeur*0.15));

		//Taille
		this.setSize(350, 200);


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

}
