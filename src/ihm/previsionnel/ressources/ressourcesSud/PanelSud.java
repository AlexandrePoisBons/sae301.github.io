package ihm.previsionnel.ressources.ressourcesSud;
import ihm.accueil.*;
//Import des classes externes au package
import ihm.previsionnel.*;
import ihm.previsionnel.ressources.PanelRessources;
import metier.Module;

//Import des classes Java
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;

import java.util.HashMap;

public class PanelSud extends JPanel implements ActionListener {
	private FrameAccueil    frame;
	private PanelPrevi      panelPrevi;
	private JButton         boutonEnregistrer;
	private JButton         boutonAnnuler;
	private JLabel          lblErreur;
	private JPanel          panelWest;
	private PanelRessources panelRessources;
	private Module          module;
	private Module          oldModule;

	public PanelSud(FrameAccueil frame, PanelPrevi panelPrevi, PanelRessources panelRessources, Module m) {
		this.frame = frame;
		this.panelPrevi        = panelPrevi;
		this.panelRessources = panelRessources;
		this.oldModule = m;
		this.module = m;

		this.setLayout(new BorderLayout());

		this.panelWest         = new JPanel();

		this.boutonEnregistrer = new JButton("Enregistrer");
		this.boutonAnnuler     = new JButton("Annuler");
		this.lblErreur         = new JLabel("");
		this.lblErreur.setForeground(java.awt.Color.RED);
		
		this.panelWest.add(this.boutonEnregistrer);
		this.panelWest.add(this.boutonAnnuler);
		this.panelWest.add(this.lblErreur);

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

	public void setErreur(String message){
		if(message.equals("erreur")){
			this.lblErreur.setText("Choisir une ligne");
		}
		else {
			this.lblErreur.setText("");
		}
		this.repaint();
		this.revalidate();
	}

	public void enregistrer() {

		String typeModule   = "Ressource";
		String semestre     = this.panelRessources.getSemestre();
		String libelle      = this.panelRessources.getLibelle();
		String libelleCourt = this.panelRessources.getLibelleCourt();
		String code         = this.panelRessources.getCode();

		int nbEtudiants = this.panelPrevi.getNbEtd(); 
		int nbGpTD      = this.panelPrevi.getNbGpTd();
		int nbGpTP      = this.panelPrevi.getNbGpTp();
	
		int nbSemaines = 0;
		int nbHeures   = 0;

		HashMap<String, Integer> map = this.panelRessources.getDataHeures();
		for (String heure : map.keySet()) {
			if (map.get(heure) > nbSemaines)
				nbSemaines = map.get(heure);
			nbHeures += map.get(heure);
		}

		Module m = this.panelRessources.getModule();

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
			this.panelRessources.enregistrer(m);
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
			this.panelRessources.update(this.oldModule, this.module);
		}

	}

}
