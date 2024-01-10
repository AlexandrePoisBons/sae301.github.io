package ihm.parametrage;

import javax.swing.*;

import java.util.*;

import metier.Statut;
import metier.TypeHeure;
import ihm.FrameValidation;
import ihm.FrameValidation;
import ihm.accueil.FrameAccueil;
import ihm.accueil.PanelAcceuil;

import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

public class PanelParam extends JPanel implements ActionListener{

	private List<Statut>       ensStatut;
	private List<TypeHeure>    ensTypeHeure;
	private PanelFormulaireStatut   panelFormulaireStatut;
	private PanelFormulaireCoef     panelFormulaireCoef;

	private FrameAccueil      frame;
	private JPanel            panelTableaux;
	private JPanel            panelSud;

	private JButton           btnModifierCoef;
	private JButton           btnAjoutStatut;
	private JButton           btnModifierStatut;
	private JButton           btnSuppStatut;
	private JButton           btnRetour;
	private JButton           btnEnregistrer;

	private JTable            tableauStatut;
	private JTable            tableauCoef;
	private DefaultTableModel dtmCoef;
	private DefaultTableModel dtmStatut;

	private JLabel     lblErreur;


	public PanelParam(FrameAccueil frame) {
		this.frame        = frame;
		this.ensStatut    = new ArrayList<Statut>();
		this.ensTypeHeure = new ArrayList<TypeHeure>();
		this.setLayout(new BorderLayout());

		//Placement de la frame
		int hauteur = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()  - (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.05);
		int largeur = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int xSize = (int)(largeur*0.5);
		int ySize = (int)(hauteur*0.55);
		this.frame.setSize(xSize, ySize);
		this.frame.setLocation((int)(largeur*0.5-xSize*0.5),(int)(hauteur*0.5-ySize*0.5));
		this.frame.setTitle("Paramétrage");

		// Creation des éléments de la page 
		this.panelFormulaireStatut     = new PanelFormulaireStatut();
		this.panelFormulaireCoef = new PanelFormulaireCoef();
		this.panelTableaux       = new JPanel();
		this.panelSud            = new JPanel();
		this.dtmStatut           = new DefaultTableModel();
		this.dtmCoef             = new DefaultTableModel();
		

		this.btnModifierCoef   = new JButton("Modifer");
		this.btnAjoutStatut    = new JButton("Ajouter");
		this.btnModifierStatut = new JButton("Modifier");
		this.btnSuppStatut     = new JButton("Supprimer");
		this.btnRetour         = new JButton("Retour");
		this.btnEnregistrer    = new JButton("Enregistrer");
		this.lblErreur         = new JLabel("");

		// Coloration en rouge du label d'erreur
		this.lblErreur.setForeground(java.awt.Color.RED);


		// Creation des tableaux
		this.dtmStatut.addColumn("Statut");
		this.tableauStatut  = new JTable(this.dtmStatut);
		this.dtmCoef.addColumn("Type d'heure");
		this.tableauCoef    = new JTable(this.dtmCoef);
		//rendre les lignes du tableau non éditables
		this.tableauStatut.setDefaultEditor(Object.class, null);
		this.tableauCoef.setDefaultEditor(Object.class, null);


		// Creation des scrollpane
		JScrollPane scrollStatut = new JScrollPane(this.tableauStatut);
		scrollStatut.setPreferredSize(new Dimension(300, 125));
		JScrollPane scrollCoef   = new JScrollPane(this.tableauCoef);
		scrollCoef.setPreferredSize(new Dimension(300, 125));
		

		// Creation sous panels
		JPanel panelTableauStatut = new JPanel();
		JPanel panelBtnStatut     = new JPanel();
		JPanel panelTableauCoef   = new JPanel();
		JPanel panelBtnCoef       = new JPanel();

		this.panelTableaux.setLayout(new GridLayout(2, 2, 5, 5));
		
		panelTableauStatut.add(scrollStatut);

		panelBtnStatut.add(this.btnAjoutStatut);
		panelBtnStatut.add(this.btnModifierStatut);
		panelBtnStatut.add(this.btnSuppStatut);

		panelTableauCoef.add(scrollCoef);

		panelBtnCoef.add(this.btnModifierCoef);

		// Ajout dans panel gauche
		this.panelTableaux.add(panelTableauCoef);
		this.panelTableaux.add(panelTableauStatut);
		this.panelTableaux.add(panelBtnCoef);
		this.panelTableaux.add(panelBtnStatut);


		//Ajout dans panel retour
		this.panelSud.add(this.btnRetour, FlowLayout.LEFT);
		this.panelSud.add(this.btnEnregistrer, FlowLayout.LEFT);
		this.panelSud.add(this.lblErreur);

		// Ajout des panels
		this.add(this.panelTableaux, BorderLayout.WEST);
		this.add(this.panelFormulaireStatut, BorderLayout.CENTER);
		this.add(this.panelSud, BorderLayout.SOUTH);

		// Activation des boutons 
		this.btnModifierCoef   .addActionListener(this);
		this.btnAjoutStatut    .addActionListener(this);
		this.btnModifierStatut .addActionListener(this);
		this.btnSuppStatut     .addActionListener(this);
		this.btnRetour         .addActionListener(this);
		this.btnEnregistrer    .addActionListener(this);

		this.init();

		this.setVisible(true);
	}


	public void init() {
		List<Statut> statuts = this.frame.getControleur().getStatuts();
		List<TypeHeure> typeHeures = this.frame.getControleur().getTypesHeures();

		for (Statut statut : statuts)
			this.ajouterStatut(statut);

		for ( TypeHeure typeHeure : typeHeures ) 
			this.ajouterTypeHeure(typeHeure);

	}

	public Statut getCurrentStatut() { return this.ensStatut.get(this.tableauStatut.getSelectedRow()); }

	public TypeHeure getCurrentTypeHeure() { return this.ensTypeHeure.get(this.tableauCoef.getSelectedRow()); }

	// Ajout des statut
	public void ajouterStatut(Statut statut) {
		String[] objs = {statut.getNomStatut()};

		this.dtmStatut.addRow(objs);
		this.ensStatut.add(statut);
	}

	// Modifier des statuts
	public void modifierStatut() {
		int ligneSelectionnee = this.tableauStatut.getSelectedRow();

		if ( ligneSelectionnee != -1 ) {
			int hauteur = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()  - (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.05);
			int largeur = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
			int xSize = (int)(largeur*0.8);
			int ySize = (int)(hauteur*0.7);
			this.frame.setSize(xSize, ySize);
			this.frame.setLocation((int)(largeur*0.5-xSize*0.5),(int)(hauteur*0.5-ySize*0.5));
			this.remove(this.panelFormulaireStatut);
			this.remove(this.panelFormulaireStatut);
			this.panelFormulaireStatut = new PanelFormulaireStatut(this, this.getCurrentStatut());
			this.add(this.panelFormulaireStatut, BorderLayout.CENTER);
			this.panelFormulaireStatut.revalidate();
			this.panelFormulaireStatut.repaint();
			this.panelFormulaireStatut.setVisible(true);
		} else {
			this.lblErreur.setText("Veuillez sélectionner un statut");
			this.repaint();
			this.revalidate();
		}
	}

	// Supprimer Statut
	public void supprimerStatut() {
		int ligneSelectionne = this.tableauStatut.getSelectedRow();

		if (ligneSelectionne != -1) {
			this.dtmStatut.removeRow(ligneSelectionne);
			this.ensStatut.remove(this.ensStatut.get(ligneSelectionne));
			this.lblErreur.setText("");
			this.repaint();
			this.revalidate();
		} else {
			this.lblErreur.setText("Veuillez sélectionner un statut");
			this.repaint();
			this.revalidate();
		}
	}

	public void ajouterTypeHeure(TypeHeure tH) {
		String[] objs = {tH.getNomTypeHeure()};

		this.dtmCoef.addRow(objs);
		this.ensTypeHeure.add(tH);
	}

	public void modifierTypeHeure() {
		int ligneSelectionnee = this.tableauCoef.getSelectedRow();
		if (ligneSelectionnee != -1) {
			int hauteur = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()  - (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.05);
			int largeur = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
			int xSize = (int)(largeur*0.8);
			int ySize = (int)(hauteur*0.7);
			this.frame.setSize(xSize, ySize);
			this.frame.setLocation((int)(largeur*0.5-xSize*0.5),(int)(hauteur*0.5-ySize*0.5));
			this.remove(this.panelFormulaireStatut);
			this.remove(this.panelFormulaireCoef);
			this.panelFormulaireCoef = new PanelFormulaireCoef(this, this.getCurrentTypeHeure());
			this.add(this.panelFormulaireCoef, BorderLayout.CENTER);
			this.panelFormulaireCoef.revalidate();
			this.panelFormulaireCoef.repaint();
			this.panelFormulaireCoef.setVisible(true);
		} else {
			this.lblErreur.setText("Veuillez sélectionner un type d'heure");
			this.repaint();
			this.revalidate();
		}
	}


	public void supprimerTypeHeure() {
		int ligneSelectionne = this.tableauCoef.getSelectedRow();

		if ( ligneSelectionne != -1 ) {
			this.dtmCoef.removeRow(ligneSelectionne);
			this.ensTypeHeure.remove(this.ensTypeHeure.get(ligneSelectionne));
		} else {
			this.lblErreur.setText("Veuillez sélectionner un type d'heure");
			this.repaint();
			this.revalidate();
		}
	}

	public boolean majTypeHeure(TypeHeure oldTH, TypeHeure newTH) {
		for ( TypeHeure typeHeure : this.ensTypeHeure ) {
			if ( typeHeure.getNomTypeHeure().equals(oldTH.getNomTypeHeure()) ) {
				typeHeure.setCoeff(newTH.getCoeff());
				return true;
			}
		}
		return false;
	}

	public void ajouter(PanelFormulaireStatut panel) {
		int hauteur = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()  - (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.05);
		int largeur = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int xSize = (int)(largeur*0.8);
		int ySize = (int)(hauteur*0.7);
		this.frame.setSize(xSize, ySize);
		this.frame.setLocation((int)(largeur*0.5-xSize*0.5),(int)(hauteur*0.5-ySize*0.5));
		this.remove(this.panelFormulaireCoef);
		this.remove(this.panelFormulaireStatut);
		this.panelFormulaireStatut = panel;
		this.add(this.panelFormulaireStatut, BorderLayout.CENTER);
		this.revalidate();
		this.repaint();
		this.setVisible(true);
	}

	public void ajouter(PanelFormulaireCoef panel) {
		int hauteur = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()  - (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.05);
		int largeur = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int xSize = (int)(largeur*0.8);
		int ySize = (int)(hauteur*0.7);
		this.frame.setSize(xSize, ySize);
		this.frame.setLocation((int)(largeur*0.5-xSize*0.5),(int)(hauteur*0.5-ySize*0.5));
		this.remove(this.panelFormulaireStatut);
		this.remove(this.panelFormulaireCoef);
		this.panelFormulaireCoef = panel;
		this.add(this.panelFormulaireCoef, BorderLayout.CENTER);
		this.revalidate();
		this.repaint();
		this.setVisible(true);
	}

	public void enregistrer() {
		this.frame.getControleur().majTypesHeures(this.ensTypeHeure);
		this.frame.getControleur().majStatuts(this.ensStatut);
	}

	//Supprimer Ligne
	public void supprimerLigne() {
		int r = this.tableauStatut.getSelectedRow();
		this.ensStatut.remove(r);
		this.dtmStatut.removeRow(r);
	}

	public List<Statut> getListStatut() {
		return this.ensStatut;
	}

	public boolean majStatut(Statut oldS, Statut newS) {
		for ( Statut statut : this.ensStatut ) {
			if ( statut.getNomStatut().equals(oldS.getNomStatut()) ) {
				statut.setCoeffTP(newS.getCoeffTP());
				statut.setNomStatut(newS.getNomStatut());
				statut.setNbHeureService(newS.getNbHeureService());
				statut.setNbHeuresMax(newS.getNbHeuresMax());
				return true;
			}
		}
		return false;
	}

	public int getLigne() { return this.tableauStatut.getSelectedRow(); }

	public void actionPerformed(ActionEvent e) {
		this.lblErreur.setText("");

		
		if ( e.getSource() == this.btnModifierCoef ) {
			this.modifierTypeHeure();
		} else if ( e.getSource() == this.btnAjoutStatut ) {
			this.ajouter(new PanelFormulaireStatut(this, null));
		} else if ( e.getSource() == this.btnSuppStatut ) {
			this.supprimerStatut();
		} else if ( e.getSource() == this.btnModifierStatut ) {
			this.modifierStatut();
		} else if ( e.getSource() == this.btnEnregistrer ) {
			new FrameValidation(this);
		} else if (e.getSource() == this.btnRetour) {
			this.frame.changerPanel(new PanelAcceuil(frame));
		}
	}


	public void validation(boolean valide) {
		if (valide ) {
			this.enregistrer();
			this.frame.dispose();
			this.frame = new FrameAccueil(this.frame.getControleur());
		} else {
			this.frame.dispose();
			this.frame.changerPanel(new PanelParam(this.frame));
		}
	}


}
