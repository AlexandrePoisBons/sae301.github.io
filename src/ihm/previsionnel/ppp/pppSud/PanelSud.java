package ihm.previsionnel.ppp.pppSud;
import ihm.accueil.*;
//Import des classes externes au package
import ihm.previsionnel.*;
import ihm.previsionnel.ppp.PanelPpp;
import metier.Module;

import java.util.HashMap;

//Import des classes Java
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;

public class PanelSud extends JPanel implements ActionListener {
	private FrameAccueil frame;
	private PanelPrevi   panelPrevi;
	private JButton      boutonEnregistrer;
	private JButton      boutonAnnuler;
	private JPanel       panelWest;
	private PanelPpp     panelPpp;

	public PanelSud(FrameAccueil frame, PanelPrevi panelPrevi, PanelPpp panelPpp) {
		this.frame = frame;
		this.panelPrevi = panelPrevi;
		this.panelPpp = panelPpp;
		this.setLayout(new BorderLayout());

		this.panelWest = new JPanel();
		this.boutonEnregistrer = new JButton("Enregistrer");
		this.boutonAnnuler = new JButton("Annuler");
		this.panelWest.add(this.boutonEnregistrer);
		this.panelWest.add(this.boutonAnnuler);

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


	public void enregistrer() {
		String typeModule   = "PPP";
		String semestre     = this.panelPpp.getSemestre();
		String libelle      = this.panelPpp.getLibelle();
		String libelleCourt = this.panelPpp.getLibelleCourt();
		String code         = this.panelPpp.getCode();

		int nbEtudiants;
		try { nbEtudiants = Integer.parseInt(this.panelPrevi.getNbEtd()); }
		catch (NumberFormatException e) { nbEtudiants = 0;}

		int nbGpTD;
		try { nbGpTD = Integer.parseInt(this.panelPrevi.getNbGpTd()); }
		catch (NumberFormatException e) { nbGpTD = 0; }

		int nbGpTP;
		try { nbGpTP = Integer.parseInt(this.panelPrevi.getNbGpTp()); }
		catch (NumberFormatException e) { nbGpTP = 0; }
	
		int nbSemaines = 0;
		int nbHeures   = 0;

		HashMap<String, Integer> map = this.panelPpp.getDataHeures();
		for (String heure : map.keySet() ){
			if (map.get(heure) > nbSemaines)
				nbSemaines = map.get(heure);
			nbHeures+= map.get(heure);
		}

		Module module = Module.creerModule( typeModule, semestre, libelle, libelleCourt, code, nbEtudiants, nbGpTD, nbGpTP, nbSemaines, nbHeures );

		System.out.println(this.frame.getControleur().getCtrl().metier().ajouterModule( module ));

		System.out.println(module.toString());

	}

}