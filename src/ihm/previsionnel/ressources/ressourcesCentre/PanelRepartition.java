package ihm.previsionnel.ressources.ressourcesCentre;

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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ihm.previsionnel.ressources.ressourcesCentre.repartition.FrameFormulaire;
import ihm.previsionnel.ressources.ressourcesCentre.repartition.PanelAffect;
import ihm.previsionnel.ressources.ressourcesCentre.repartition.heure.PanelRepartitionHeure;
import metier.Heure;
import metier.Intervenant;
import metier.TypeHeure;
import metier.Module;

public class PanelRepartition extends JPanel implements ActionListener {
	private PRCentre panelMere;
	private PanelRepartitionHeure panelRepartitionHeure;
	private PanelAffect panelAffect;
	private JButton btnAjouter;
	private JButton btnSuppr;
	private Module module;
	private FrameFormulaire frameFormulaire;

	public PanelRepartition(PRCentre panelMere, Module m) {
		this.panelMere = panelMere;
		this.module = m;
		JPanel pnlAlignementNordCentre = new JPanel();
		this.setLayout(new BorderLayout());
		pnlAlignementNordCentre.setLayout(new GridBagLayout());
		this.setBorder(new EmptyBorder(0, 5, 0, 10)); /* PEUT ETRE PAS -> depend du design */

		JPanel panelBoutons = new JPanel();

		this.panelRepartitionHeure = new PanelRepartitionHeure(this, this.module);
		this.panelAffect = new PanelAffect(this, this.module);
		panelBoutons     = new JPanel();
		this.btnAjouter  = new JButton("Ajouter");
		this.btnSuppr    = new JButton("Supprimer");

		//Ajout des boutons au panel de boutons
		panelBoutons.add(this.btnAjouter);
		panelBoutons.add(this.btnSuppr);

		//Ajout des panels au panel principal
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 1, 0);
		pnlAlignementNordCentre.add(this.panelRepartitionHeure, gbc);
		gbc.gridy = 1;
		pnlAlignementNordCentre.add(this.panelAffect, gbc);
		this.add(pnlAlignementNordCentre, BorderLayout.NORTH);
		this.add(panelBoutons, BorderLayout.CENTER);

		//Ajout des
		this.btnAjouter.addActionListener(this);
		this.btnSuppr.addActionListener(this);
	}

	public void setLabelErreur(String message) { this.panelMere.setLabelErreur(message); }

	public void actionPerformed(ActionEvent e) {
		if ( e.getSource() == this.btnAjouter ) {
			if ( this.panelRepartitionHeure.txtRempli() ) {
				this.frameFormulaire = new FrameFormulaire(this, this.module);
			}
			else {
				this.setLabelErreur("Veuillez renseigner le nombre d'heures par semaine");
			}
		}
		if ( e.getSource() == this.btnSuppr ) {
			this.panelAffect.supprimer();
		}
	}

	public HashMap<String, HashMap<String, Integer>> getDataHeuresTypesHeures() {
		return this.panelRepartitionHeure.getDataHeuresTypesHeures();
	}


	public HashMap<String,Integer> getData() { return this.panelMere.getData(); }
	public HashMap<String,Integer> getNbSemaines() { return this.panelRepartitionHeure.getNbSemaines(); }
	public List<Intervenant> getIntervenants() { return this.panelMere.getIntervenants(); }
	public List<TypeHeure> getTypesHeures() { return this.panelMere.getTypesHeures(); }
	public List<Heure> getHeures() { return this.panelAffect.getDataHeures(); }
	public List<Heure> getDeletedHeures() { return this.panelAffect.getDeletedHeures(); }
	public void setHeures(List<Heure> heures) { this.panelAffect.setHeures(heures); }
	public void ajouterHeure(Heure heure) { this.panelAffect.ajouterHeure(heure); }
	public void fermerFrameFormulaire() { if ( this.frameFormulaire != null) this.frameFormulaire.dispose(); }

	public HashMap<TypeHeure, HashMap<String,Integer>> getHeuresParTypesHeures(Module module) { return this.panelMere.getHeuresParTypesHeures(module); }

	public int getNbGpTd() {return this.panelMere.getNbGpTd();}
	public int getNbGpTp() {return this.panelMere.getNbGpTp();}

	public int getNbHeureSemaine(int i) {
		return this.panelRepartitionHeure.getNbHeureSemaine(i);
	}

	public void setHeureAffecte() {
		DefaultTableModel dtm = this.panelAffect.getDtm();
		int heureCM = 0;
		int heureTD = 0;
		int heureTP = 0;
		int heureHP = 0;
		for ( int i=0; i<dtm.getRowCount(); i++ ) {
			if ( dtm.getValueAt(i, 1).toString().equals("CM") ) {
				heureCM += Integer.parseInt(dtm.getValueAt(i, 4).toString());
			}
			if ( dtm.getValueAt(i, 1).toString().equals("TD") ) {
				heureTD += Integer.parseInt(dtm.getValueAt(i, 4).toString());
			}
			if ( dtm.getValueAt(i, 1).toString().equals("TP") ) {
				heureTP += Integer.parseInt(dtm.getValueAt(i, 4).toString());
			}
			if ( dtm.getValueAt(i, 1).toString().equals("HP") ) {
				heureHP += Integer.parseInt(dtm.getValueAt(i, 4).toString());
			}
		}
		this.panelRepartitionHeure.serHeureAffecte(heureCM, heureTD, heureTP, heureHP);
	}

}
