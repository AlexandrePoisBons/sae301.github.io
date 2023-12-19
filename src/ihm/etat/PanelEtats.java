package ihm.etat;

import java.awt.Toolkit;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelEtats extends JPanel implements ActionListener{
	private JButton btnInter;
	private JButton btnModule;

	public PanelEtats(){
		//Définition de la taille et la position de la fenêtre
		int hauteur = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()  - (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.05);
		int largeur = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int xSize = (int)(largeur*0.5);
		int ySize = (int)(hauteur*0.5);
		this.setSize(xSize, ySize);
		this.setLocation((int)(largeur*0.5-xSize*0.5),(int)(hauteur*0.5-ySize*0.5));

		this.btnInter 	= new JButton("Intervenant(s)")	;
		this.btnModule 	= new JButton("Modules")		;

		this.add(btnInter);
		this.add(btnModule);

		this.btnInter.addActionListener(this);
		this.btnModule.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
	}
}