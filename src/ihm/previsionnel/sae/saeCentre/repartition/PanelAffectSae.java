package ihm.previsionnel.sae.saeCentre.repartition;

import java.awt.Dimension;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import metier.Heure;
import metier.Intervenant;
import metier.TypeHeure;
import metier.Module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PanelAffectSae extends JPanel {
	private PanelRepartitionSae panelMere;
	private JTable tableauAffect;
	private DefaultTableModel dtm;
	private List<Heure> heures;
	private Module module;

	public PanelAffectSae(PanelRepartitionSae panelMere, Module m) {
		this.panelMere = panelMere;
		this.module = m;

		this.heures = new ArrayList<>();

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

	// public void ajouter() {

	// 	HashMap<String,Integer> map = this.panelMere.getData();

	// 	Object[] objs = new Object[5];
	// 	for ( String type : map.keySet() ) {
	// 		objs[0] = "";
	// 		objs[1] = type;
	// 		objs[2] = 999;
	// 		objs[3] = 999;
	// 		objs[4] = "";
	// 		this.dtm.addRow(objs);
	// 	}

	// }

	public void setHeures(List<Heure> heures) {
		// this.heures = heures;
		for (Heure heure : heures) {
			this.ajouterHeure(heure);
		}
	}

	private void ajouterLigne(Object[] objs) {
		this.dtm.addRow(objs);
	}

	public void ajouterHeure(Heure heure) {
		this.module.ajouterHeure(heure);

		Object[] objs = new Object[6];
		objs[0] = heure.getIntervenants().get(0).getNom()+" "+heure.getIntervenants().get(0).getPrenom().substring(0,1)+".";
		objs[1] = heure.getTypeHeure().getNomTypeHeure();
		objs[2] = heure.getDuree();
		objs[3] = heure.getDuree()*heure.getTypeHeure().getCoeff();
		objs[4] = heure.getCommentaire();
		this.ajouterLigne(objs);
	}


	public List<Heure> getDataHeures() {
		List<Heure> heures = new ArrayList<>();

		return this.module.getHeures();

/*
		int nb = this.dtm.getRowCount();

		List<TypeHeure> ths = this.panelMere.getTypesHeures();

		Heure tmpH;
		TypeHeure tmpTH = null;
		for (int i = 0; i < nb; i++) {
			for (TypeHeure typeHeure : ths)
				if (typeHeure.getNomTypeHeure().equals(this.dtm.getValueAt(i, 1).toString()))
					tmpTH = typeHeure;
			tmpH = Heure.creerHeure(module, tmpTH, Float.parseFloat(this.dtm.getValueAt(i, 3).toString()), this.dtm.getValueAt(i,4).toString());

			List<Intervenant> thI = this.panelMere.getIntervenants();
			System.out.println("ehhehehehehehee"+thI.size());
			for (Intervenant intervenant : thI) {
				String int1Name = intervenant.getNom()+" "+intervenant.getPrenom().substring(0,1)+".";
				String int2Name = this.dtm.getValueAt(i, 0).toString();
				System.out.println("aaaa");
				if( int1Name.equals(int2Name) ){
					System.out.println("jout");
					tmpH.ajouterIntervenant(intervenant);
				}
			}

			heures.add(tmpH);
		}


		System.out.println(" COMBIEN D'HEURES ??"+heures.size());

		return heures;*/
	}

	public void supprimer() {
		if(this.tableauAffect.getSelectedRow() != -1) {
			this.dtm.removeRow(this.tableauAffect.getSelectedRow());
		}
		else {
			this.panelMere.setErreur("erreur");
		}	
	}
}
