package ihm.etat;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import javax.swing.*;

import controleur.Controleur;
import controleur.ControleurIHM;
import ihm.accueil.FrameAccueil;
import ihm.accueil.PanelAcceuil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelEtats extends JPanel implements ActionListener{
	private FrameAccueil frame;
	private JButton btnInter;
	private JButton btnModule;
	private JButton btnRetour;

	private PanelInter  panelInter;
	private PanelModule panelModule;

	public PanelEtats(FrameAccueil frame){
		this.frame = frame;
		this.setLayout(new BorderLayout());
		//Définition de la taille et la position de la fenêtre
		int hauteur = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()  - (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.05);
		int largeur = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int xSize = (int)(largeur*0.2);
		int ySize = (int)(hauteur*0.15);
		this.frame.setSize(xSize, ySize);
		this.frame.setLocation((int)(largeur*0.5-xSize*0.5),(int)(hauteur*0.5-ySize*0.5));

		this.btnInter 	= new JButton("Intervenant(s)");
		this.btnModule 	= new JButton("Modules");
		this.btnRetour  = new JButton("Retour");

		JPanel panelBas  = new JPanel();
		JPanel panelHaut = new JPanel();

		panelHaut.add(btnInter);
		panelHaut.add(btnModule);

		panelBas.add(this.btnRetour);

		this.add(panelHaut, BorderLayout.NORTH);
		this.add(panelBas , BorderLayout.SOUTH);

		this.btnRetour.addActionListener(this);
		this.btnInter.addActionListener(this);
		this.btnModule.addActionListener(this);
		
	}

	public ControleurIHM getControleur() {
		return this.frame.getControleur();
	}

	public void changerPanel(JPanel panel) {
		this.frame.changerPanel(panel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() ==  this.btnInter){
			this.frame.changerPanel(new PanelInter(this.frame, this));
		}

		if(e.getSource() ==  this.btnModule){
			this.frame.changerPanel(new PanelModule(this.frame, this));
		}

		if(e.getSource() ==  this.btnRetour){
			this.frame.changerPanel(new PanelAcceuil(frame));
		}
	}
}