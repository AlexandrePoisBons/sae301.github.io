package ihm.Panels;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ihm.FrameAccueil;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelPrevi extends JPanel implements ActionListener {
	private FrameAccueil frame;
	private PanelCenterPrevi panelCenterPrevi;
	private JButton btnCreerRessource;
	private JButton btnCreerSAE;
	private JButton btnCreerStage;
	private JButton btnModifier;
	private JButton btnSupprimer;	

	public PanelPrevi(FrameAccueil frAccueil){
		this.frame = frAccueil;
		this.setLayout(new BorderLayout());
		JPanel panelSud = new JPanel();
		panelSud.setLayout(new GridLayout(1, 4, 5, 0));
		this.panelCenterPrevi = new PanelCenterPrevi(this.frame);

		this.btnCreerRessource = new JButton("Créer \n Ressource");
		this.btnCreerSAE = new JButton("Créer SAE");
		this.btnCreerStage = new JButton("Créer stage/suivi");
		this.btnModifier = new JButton("Modifier");
		this.btnSupprimer = new JButton("Supprimer");

		panelSud.add(this.btnCreerRessource);
		panelSud.add(this.btnCreerSAE);
		panelSud.add(this.btnCreerStage);
		panelSud.add(this.btnModifier);
		panelSud.add(this.btnSupprimer);


		this.add(panelCenterPrevi, BorderLayout.CENTER);
		this.add(panelSud, BorderLayout.SOUTH);

		
		this.btnCreerRessource.addActionListener(this);
		this.btnCreerSAE.addActionListener(this);
		this.btnCreerStage.addActionListener(this);
		this.btnModifier.addActionListener(this);
		this.btnSupprimer.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btnCreerRessource){

		}
		if(e.getSource() == this.btnCreerSAE){

		}
		if(e.getSource() == this.btnCreerStage){

		}
		if(e.getSource() == this.btnModifier){

		}
		if(e.getSource() == this.btnSupprimer){

		}
	}
}
