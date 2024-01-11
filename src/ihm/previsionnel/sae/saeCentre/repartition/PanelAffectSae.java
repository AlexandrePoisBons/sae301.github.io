package ihm.previsionnel.sae.saeCentre.repartition;

import java.awt.Dimension;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import metier.Heure;
import metier.Module;

import java.util.ArrayList;
import java.util.List;

public class PanelAffectSae extends JPanel {
	private PanelRepartitionSae panelMere;
	private JTable tableauAffect;
	private DefaultTableModel dtm;
	private List<Heure> heures;
	private List<Heure> deletedHeures;
	private Module module;

	public PanelAffectSae(PanelRepartitionSae panelMere, Module m) {
		this.panelMere = panelMere;
		this.module    = m;

		this.heures    = new ArrayList<>();
		this.deletedHeures = new ArrayList<>();

		this.dtm = new DefaultTableModel(){
			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		dtm.addColumn("Intervenant");
		dtm.addColumn("type");
		dtm.addColumn("nb h");
		dtm.addColumn("tot eqtd");
		dtm.addColumn("commentaire");

		this.tableauAffect = new JTable(dtm);

		JScrollPane scroll = new JScrollPane(this.tableauAffect);
		scroll.setPreferredSize(new Dimension(650, 250));

		this.add(scroll);
	}


	public void setHeures(List<Heure> heures) {
		this.heures = heures;
		for (Heure heure: heures) {
			this.ajouterLigne(heure);
		}
	}

	private void ajouterLigne(Heure heure) {
		Object[] objs = new Object[5];
		objs[0] = heure.getIntervenants().get(0).getNom()+" "+heure.getIntervenants().get(0).getPrenom().substring(0,1)+".";
		objs[1] = heure.getTypeHeure().getNomTypeHeure();
		objs[2] = heure.getDuree();
		objs[3] = heure.getDuree()*heure.getTypeHeure().getCoeff();
		objs[4] = heure.getCommentaire();
		this.dtm.addRow(objs);
	}

	public void ajouterHeure(Heure heure) {
		this.module.ajouterHeure(heure);

		this.ajouterLigne(heure);
	}

	public List<Heure> getDataHeures() {
		return this.heures;
	}

	public void supprimer() {
		if(this.tableauAffect.getSelectedRow() != -1) {
			this.deletedHeures.add(this.heures.get(this.tableauAffect.getSelectedRow()));
			// this.heures.remove(this.tableauAffect.getSelectedRow());
			this.dtm.removeRow(this.tableauAffect.getSelectedRow());
		}
		else {
			this.panelMere.setErreur("erreur");
		}
	}

	public DefaultTableModel getDtm() { return this.dtm; }
	public JTable getTableauAffect() { return this.tableauAffect; }
	public List<Heure> getDeletedHeures() { return this.deletedHeures; }

}
