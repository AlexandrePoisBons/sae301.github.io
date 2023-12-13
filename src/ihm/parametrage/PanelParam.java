package ihm.parametrage;

import javax.swing.*;
import java.util.*;
import metier.Statut;

import ihm.accueil.FrameAccueil;

import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent		;
import java.awt.event.ActionListener	;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class PanelParam extends JPanel implements ActionListener{

	private ArrayList<String> listStatut;
	private ArrayList<Float>  listCoef;

	private FrameAccueil      frame;
	private JPanel            panelG;
	private JPanel            panelD;

	private JButton           btnAjoutStat;
	private JButton           btnAjoutCoef;
	private JButton           btnSuppStat;
	private JButton           btnSuppCoef;

	private JTable            tableauStatut;
	private JTable            tableauCoef;
	private DefaultTableModel dtmStatut;
	private DefaultTableModel dtmCoef;
	
	public PanelParam(FrameAccueil frame){
		this.frame = frame;

		this.setLayout(new BorderLayout());

		this.dtmStatut    = new DefaultTableModel();
		this.dtmCoef      = new DefaultTableModel();

		this.btnAjoutStat = new JButton("Ajouter");
		this.btnAjoutCoef = new JButton("Ajouter");
		this.btnSuppStat  = new JButton("Supprimer");
		this.btnSuppCoef  = new JButton("Supprimer");

		this.dtmStatut.addColumn("Nom");
		this.dtmStatut.addColumn("Coeff");

		this.tableauStatut  = new JTable(this.dtmStatut);
		this.tableauCoef    = new JTable(this.dtmCoef);

		this.panelG.add(this.tableauStatut);
		this.panelG.add(this.btnAjoutStat);
		this.panelG.add(this.btnSuppStat);

		this.panelD.add(this.tableauCoef);
		this.panelD.add(this.btnAjoutCoef);
		this.panelD.add(this.btnSuppCoef);

		// ajouter les panels
		this.add(this.panelG, BorderLayout.WEST);
		this.add(this.panelD, BorderLayout.EAST);

		// activer les boutons 
		this.btnAjoutStat.addActionListener(this);
		this.btnAjoutCoef.addActionListener(this);
		this.btnSuppStat .addActionListener(this);
		this.btnSuppCoef .addActionListener(this);

		this.setVisible(true);
	}

	public void ajouterStatut(){
		this.listStatut.add("");
		// this.dtmStatut.addRow();
	}

	public void ajouterCoef(){
		this.listCoef.add(0f);
		// this.dtmStatut.addRow();
	}

	public void supprimerStatut(){
		this.listStatut.remove("");
		this.dtmStatut.removeRow(this.tableauStatut.getSelectedRow());
	}

	public void supprimerCoef(){
		this.listCoef.remove(0f);
		this.dtmCoef.removeRow(this.tableauCoef.getSelectedRow());
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btnAjoutStat){
			ajouterStatut();
		}

		if(e.getSource() == this.btnAjoutCoef){
			ajouterCoef();
		}

		if(e.getSource() == this.btnSuppStat){
			supprimerStatut();
		}

		if(e.getSource() == this.btnSuppCoef){
			supprimerCoef();
		}
	}

	
}
