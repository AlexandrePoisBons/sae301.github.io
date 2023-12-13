package ihm.previsionnel.ressources.ressourcesCentre.repartition;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import ihm.previsionnel.ressources.ressourcesCentre.PRCentre;
import ihm.previsionnel.ressources.ressourcesCentre.repartition.heure.PanelRepartitionHeure;

public class PanelRepartition extends JPanel implements ActionListener{
	private PRCentre panelMere;
	private PanelRepartitionHeure panelRepartitionHeure;
	private PanelAffect panelAffect;
	private JButton btnAjouter;
	private JButton btnSuppr;

	public PanelRepartition(PRCentre panelMere) {
		this.panelMere = panelMere;
		JPanel pnlAlignementNordCentre = new JPanel();
		this.setLayout(new BorderLayout());
		pnlAlignementNordCentre.setLayout(new GridBagLayout());

		JPanel panelBoutons = new JPanel();

		this.panelRepartitionHeure 	= new PanelRepartitionHeure	(this)				;
		this.panelAffect	 		= new PanelAffect			(this)				;
		panelBoutons	 			= new JPanel				(    )				;
		this.btnAjouter 			= new JButton				("Ajouter")	;
		this.btnSuppr				= new JButton				("Supprimmer")	;	

		//Ajout des boutons au panel de boutons
		panelBoutons.add(this.btnAjouter)								;
		panelBoutons.add(this.btnSuppr)									;

		//Ajout des panels au panel principal
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 1, 0);
		pnlAlignementNordCentre.add		(this.panelRepartitionHeure, gbc)	;
		gbc.gridy = 1;
		pnlAlignementNordCentre.add		(this.panelAffect, gbc)				;
		this.add		(pnlAlignementNordCentre, BorderLayout.NORTH)	;
		this.add		(panelBoutons, BorderLayout.CENTER)				;

		//Ajout des
		this.btnAjouter.addActionListener	(this);
		this.btnSuppr.addActionListener		(this);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btnAjouter){
			this.panelAffect.ajouter();
		}
		if(e.getSource() == this.btnSuppr){
			this.panelAffect.supprimer();
		}
	}

	public Object[] getHeuresProgNat(){
		return this.panelMere.getHeuresProgNat();
	}

}
