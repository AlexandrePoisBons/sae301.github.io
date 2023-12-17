package ihm.previsionnel.ressources.ressourcesCentre.repartition;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import ihm.previsionnel.ressources.ressourcesCentre.PRCentre;
import ihm.previsionnel.ressources.ressourcesCentre.repartition.heure.PanelRepartitionHeure;
import metier.Intervenant;

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
		this.btnSuppr				= new JButton				("Supprimer")	;

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
			new FrameFormulaire(this);
		}
		if(e.getSource() == this.btnSuppr){
			this.panelAffect.supprimer();
		}
	}

	public HashMap<String,Integer> getNbSemaines() { return this.panelRepartitionHeure.getNbSemaines(); }

	public HashMap<String,Integer> getTabData() { return this.panelMere.getData(); }

	public List<Intervenant> getIntervenants() { return this.panelMere.getIntervenants(); }

	public void ajouterLigne(Object[] objs) { this.panelAffect.ajouterLigne(objs); }
	




	
}
