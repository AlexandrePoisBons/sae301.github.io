package ihm.previsionnel.stage.stageCentre.repartition;

//Imports classes Java
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//Imports classes externes
import ihm.previsionnel.stage.stageCentre.PCentreStage;
import ihm.previsionnel.stage.stageCentre.repartition.heureStage.PanelRepHStage;
import metier.Intervenant;

public class PanelRepartitionStage extends JPanel implements ActionListener{
	private PCentreStage panelMere;
	private PanelRepHStage panelRepH;
	private PanelAffectStage panelAffect;
	private JButton btnAjouter;
	private JButton btnSuppr;

	public PanelRepartitionStage(PCentreStage panelMere) {
		this.panelMere = panelMere;
		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(0, 5, 0, 0));

		JPanel panelBtn = new JPanel();

		this.panelRepH 	= new PanelRepHStage					(this)				;
		this.panelAffect	 		= new PanelAffectStage			(this)				;	
		this.btnAjouter 			= new JButton					("Ajouter")	;
		this.btnSuppr				= new JButton					("Supprimer")	;	

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

	//A DECOMMENTER
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btnAjouter){
			new FrameFormulaire(this);
		}
		if(e.getSource() == this.btnSuppr){
			//this.panelAffect.supprimer();
		}
	}

	public HashMap<String,Integer> getRepartitionHeures() {
		return this.panelRepH.getRepartitionHeures();
	}

	public HashMap<String,Integer> getData(){
		return this.panelMere.getData();
	}
	public List<Intervenant> getIntervenants(){
		return this.panelMere.getIntervenants();
	}

	public void ajouterLigne(String string, String text, int int1, int int2, String text2) {
		this.panelAffect.ajouterLigne(string, text, int1, int2, text2);
	}

}

	