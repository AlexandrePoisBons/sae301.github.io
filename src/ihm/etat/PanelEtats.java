package ihm.etat;

import java.awt.Frame;
import java.awt.Toolkit;
import javax.swing.*;

import controleur.Controleur;
import controleur.ControleurIHM;
import ihm.accueil.FrameAccueil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelEtats extends JPanel implements ActionListener{
	private FrameAccueil frame;
	private JButton btnInter;
	private JButton btnModule;

	private PanelInter panelInter;
	private PanelModule panelModule;

	public PanelEtats(FrameAccueil frame){
		this.frame = frame;
		//Définition de la taille et la position de la fenêtre
		int hauteur = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()  - (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.05);
		int largeur = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int xSize = (int)(largeur*0.2);
		int ySize = (int)(hauteur*0.15);
		this.frame.setSize(xSize, ySize);
		this.frame.setLocation((int)(largeur*0.5-xSize*0.5),(int)(hauteur*0.5-ySize*0.5));

		this.btnInter 	= new JButton("Intervenant(s)");
		this.btnModule 	= new JButton("Modules");

		this.add(btnInter);
		this.add(btnModule);

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
			this.panelModule = new PanelModule(this.frame, this);
		}
	}
}