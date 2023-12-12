package ihm.previsionnel.ressources.ressourcesCentre.repartition;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import ihm.previsionnel.ressources.ressourcesCentre.PRCentre;
import ihm.previsionnel.ressources.ressourcesCentre.repartition.heure.PanelRepartitionHeure;

public class PanelRepartition extends JPanel implements ActionListener{
	private PRCentre panelMere;
	private PanelRepartitionHeure panelRepartitionHeure;
	private PanelAffect PanelAffect;
	private JButton btnAjouter;
	private JButton btnSuppr;

	public PanelRepartition(PRCentre panelMere){
		this.panelMere = panelMere;
		this.setLayout(new BorderLayout());

		JPanel panelBoutons = new JPanel();

		this.panelRepartitionHeure 	= new PanelRepartitionHeure	(this)				;
		this.PanelAffect	 		= new PanelAffect			(this)				;
		panelBoutons	 			= new JPanel				(    )				;
		this.btnAjouter 			= new JButton				("Ajouter")	;
		this.btnSuppr				= new JButton				("Supprimmer")	;	

		//Ajout des boutons au panel de boutons
		panelBoutons.add(this.btnAjouter)								;
		panelBoutons.add(this.btnSuppr)									;

		//Ajout des panels au panel principal
		this.add		(this.panelRepartitionHeure, BorderLayout.NORTH);
		this.add		(this.PanelAffect, BorderLayout.CENTER)	;
		this.add		(panelBoutons, BorderLayout.SOUTH)				;

		//Ajout des
		this.btnAjouter.addActionListener	(this);
		this.btnSuppr.addActionListener		(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btnAjouter){

		}
		if(e.getSource() == this.btnSuppr){

		}
	}
}
