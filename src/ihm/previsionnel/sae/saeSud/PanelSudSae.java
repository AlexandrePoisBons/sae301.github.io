package ihm.previsionnel.sae.saeSud;

import ihm.FrameValidation;
import ihm.accueil.*;
//Import des classes externes au package
import ihm.previsionnel.*;
import ihm.previsionnel.sae.PanelSae;
import metier.Heure;
import metier.Module;

//Import des classes Java
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.awt.BorderLayout;

public class PanelSudSae extends JPanel implements ActionListener{
	private FrameAccueil frame;
	private PanelPrevi   panelPrevi;
	private JButton      boutonEnregistrer;
	private JButton      boutonAnnuler;
	private JLabel       lblErreur;
	private JPanel       panelWest;
	private PanelSae     panelSae;
	private Module       module;
	private Module       oldModule;

	public PanelSudSae(FrameAccueil frame, PanelPrevi panelPrevi, PanelSae panelSae, Module m) {
		this.frame      = frame;
		this.panelPrevi = panelPrevi;
		this.panelSae   = panelSae;
		this.oldModule  = m;
		this.module     = m;

		this.setLayout(new BorderLayout());

		this.panelWest = new JPanel();

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
			new FrameValidation(this);
		}
		else if(e.getSource() == this.boutonAnnuler) {
			this.frame.changerPanel(new PanelPrevi(this.frame));
		}
	}

	public void validation(boolean valide) {
		if ( valide ) {
			this.enregistrer();
			// this.panelSae.fermerFrameFormulaire();
		} else {
			// this.panelRessources.fermerFrameFormulaire();
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
		String typeModule   = "SAE";
		String semestre     = this.panelSae.getSemestre();
		String libelle      = this.panelSae.getLibelle();
		String libelleCourt = this.panelSae.getLibelleCourt();
		String code         = this.panelSae.getCode();
		int    nbEtudiants  = this.panelPrevi.getNbEtd();
		int    nbGpTD       = this.panelPrevi.getNbGpTd();
		int    nbGpTP       = this.panelPrevi.getNbGpTp();
		int    nbSemaines   = 0;
		int    nbHeures     = 0;
		boolean valide = this.panelSae.estValide();

		if ( code.length() <= 0 || libelle.length() <= 0 || libelleCourt.length() <= 0 ) {
			this.frame.changerPanel(new PanelPrevi(this.frame));
			return;
		}

		HashMap<String, Integer> map = this.panelSae.getDataHeures();
		for (String heure : map.keySet()) {
			if (map.get(heure) > nbSemaines)
				nbSemaines = map.get(heure);
			nbHeures+= map.get(heure);
		}

		List<Heure> deletedHeures = this.panelSae.getDeletedHeures();

		Module m = this.panelSae.getModule();

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

			for ( Heure heure : m.getHeures() )
				if ( deletedHeures.contains(heure) )
					m.getHeures().remove(heure);

			this.panelSae.enregistrer(m);
		} else {
			this.module.setTypeModule(m.getTypeModule());
			this.module.setSemestre(m.getSemestre());
			this.module.setLibelle(libelle);
			this.module.setLibelleCourt(libelleCourt);
			this.module.setCode(code);
			this.module.setNbEtudiants(m.getNbEtudiants());
			this.module.setNbGpTD(m.getNbGpTD());
			this.module.setNbGpTP(m.getNbGpTP());
			this.module.setNbSemaines(m.getNbSemaines());
			this.module.setNbHeures(m.getNbHeures());
			this.module.setValide(valide);

			for( Heure heure : this.module.getHeures() )
				if ( deletedHeures.contains(heure) )
					this.module.getHeures().remove(heure);

			this.panelSae.update(this.oldModule, this.module);
		}

	}

	// public boolean estValide() { return this.panelSae.estValide(); }

}
