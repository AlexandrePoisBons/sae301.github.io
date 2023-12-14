package ihm.previsionnel.sae.saeCentre.repartition;

//Imports classes Java
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//Imports classes externes
import ihm.previsionnel.sae.saeCentre.PCentreSae;
import ihm.previsionnel.sae.saeCentre.repartition.heureSae.PanelRepH;

public class PanelRepartitionSae extends JPanel implements ActionListener {
	private PCentreSae panelMere;
	private PanelRepH panelRepH;
	private PanelAffectSae panelAffect;
	private JButton btnAjouter;
	private JButton btnSuppr;

	public PanelRepartitionSae(PCentreSae panelMere) {
		this.panelMere = panelMere;
		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(0, 5, 0, 0));

		JPanel panelBtn = new JPanel();

		this.panelRepH 	= new PanelRepH					(this)				;
		this.panelAffect	 		= new PanelAffectSae			(this)				;	
		this.btnAjouter 			= new JButton					("Ajouter")	;
		this.btnSuppr				= new JButton					("Supprimmer")	;	

		//Ajout des boutons au panel de boutons
		panelBtn.add(this.btnAjouter)								;
		panelBtn.add(this.btnSuppr)									;

		this.add(this.panelRepH, BorderLayout.NORTH);
		this.add(this.panelAffect, BorderLayout.CENTER);
		this.add(panelBtn, BorderLayout.SOUTH);

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
