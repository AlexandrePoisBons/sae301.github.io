package ihm.previsionnel.stage.stageSud;

import ihm.accueil.*;
//Import des classes externes au package
import ihm.previsionnel.*;
import ihm.previsionnel.stage.PanelStage;

import metier.Module;

import java.util.HashMap;

//Import des classes Java
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;

public class PanelSudStage extends JPanel implements ActionListener{
	private FrameAccueil frame;
	private PanelPrevi panelPrevi;
	private JButton boutonEnregistrer;
	private JButton boutonAnnuler;
	private JLabel       lblErreur;
	private JPanel panelWest;
	private PanelStage panelStage;
	private Module module;
	private Module oldModule;


	public PanelSudStage(FrameAccueil frame, PanelPrevi panelPrevi, PanelStage panelStage, Module m) {
		this.frame      = frame;
		this.panelStage = panelStage;
		this.panelPrevi = panelPrevi;
		this.oldModule = m;
		this.module = m;

		this.setLayout(new BorderLayout());

		this.panelWest         = new JPanel();

		this.boutonEnregistrer = new JButton("Enregistrer");
		this.boutonAnnuler     = new JButton("Annuler");
		this.lblErreur         = new JLabel("");
		this.lblErreur.setForeground(java.awt.Color.RED);

		this.panelWest.add( this.boutonEnregistrer );
		this.panelWest.add( this.boutonAnnuler     );
		this.panelWest.add( this.lblErreur         );

		this.add(this.panelWest, BorderLayout.WEST);

		this.boutonEnregistrer.addActionListener(this);
		this.boutonAnnuler.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.boutonEnregistrer) {
			this.enregistrer();
		}
		else if(e.getSource() == this.boutonAnnuler) {
			this.frame.changerPanel(new PanelPrevi(this.frame));
		}
	}

	public void setErreur(String message) {
		if(message.equals("erreur")) {
			this.lblErreur.setText("Choisir une ligne");
		}
		else{
			this.lblErreur.setText("");
		}
	}

	public void enregistrer() {
		String typeModule   = "Stage";
		String semestre     = this.panelStage.getSemestre();
		String libelle      = this.panelStage.getLibelle();
		String libelleCourt = this.panelStage.getLibelleCourt();
		String code         = this.panelStage.getCode();
		int    nbEtudiants  = this.panelPrevi.getNbEtd();
		int    nbGpTD       = this.panelPrevi.getNbGpTd(); 
		int    nbGpTP       = this.panelPrevi.getNbGpTp(); 
		int    nbSemaines   = 0;
		int    nbHeures     = 0;
		boolean valide = this.panelStage.estValide();

		HashMap<String, Integer> map = this.panelStage.getDataHeures();
		for (String heure : map.keySet() ){
			if (map.get(heure) > nbSemaines)
				nbSemaines = map.get(heure);
			nbHeures+= map.get(heure);
		}

		Module m = this.panelStage.getModule();

		if ( this.module.getLibelle().length() < 1 ) {
			m.setTypeModule(typeModule);
			m.setSemestre(semestre);
			m.setLibelle(libelle);
			m.setLibelleCourt(libelleCourt);
			m.setCode(code);
			m.setNbEtudiants(nbEtudiants);
			m.setNbGpTD(nbGpTD);
			m.setNbGpTP(nbGpTP);
			m.setNbSemaines(nbSemaines);
			m.setNbHeures(nbHeures);
			m.setValide(valide);
			this.panelStage.enregistrer(m);
		} else {
			this.module.setTypeModule(m.getTypeModule());
			this.module.setSemestre(m.getSemestre());
			this.module.setLibelle(m.getLibelle());
			this.module.setLibelleCourt(m.getLibelleCourt());
			this.module.setCode(m.getCode());
			this.module.setNbEtudiants(m.getNbEtudiants());
			this.module.setNbGpTD(m.getNbGpTD());
			this.module.setNbGpTP(m.getNbGpTP());
			this.module.setNbSemaines(m.getNbSemaines());
			this.module.setNbHeures(m.getNbHeures());
			this.module.setValide(m.isValide());
			this.panelStage.update(this.oldModule, this.module);
		}

	}



}
