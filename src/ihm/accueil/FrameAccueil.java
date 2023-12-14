package ihm.accueil			;

import javax.swing.*		;
import java.awt.BorderLayout;
import java.awt.Dimension	;

public class FrameAccueil extends JFrame {
	//Attribut(s)
	private JPanel panel;

	//Constructeur
	public FrameAccueil() {
		this.setTitle("Accueil");
		//Layout
		this.setLayout(new BorderLayout());

		//Position
		this.setLocation(100, 100);

		//Taille
		this.setSize(350, 200);

		//Composants						//Dimension des panels pour centrer le panel au milieu
		//JPanel eastPanel = new JPanel();	eastPanel.setPreferredSize(new Dimension(100, 1));
		//JPanel westPanel = new JPanel();	westPanel.setPreferredSize(new Dimension(100, 1));

		//Panel principal
		this.panel = new PanelAcceuil(this);

		//Ajout des composants	  
		//this.add(new JPanel()	, BorderLayout.NORTH	);
		this.add(this.panel		, BorderLayout.CENTER	);
		//this.add(new JPanel()	, BorderLayout.SOUTH	);
		//this.add(eastPanel		, BorderLayout.EAST		);
		//this.add(westPanel		, BorderLayout.WEST		);

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
