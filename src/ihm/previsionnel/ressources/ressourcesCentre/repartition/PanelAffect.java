package ihm.previsionnel.ressources.ressourcesCentre.repartition;

import java.awt.Dimension;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import metier.Heure;
import metier.Module;
import metier.TypeHeure;
import metier.Intervenant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PanelAffect extends JPanel {
	private PanelRepartition panelMere;
	private JTable tableauAffect;
	private DefaultTableModel dtm;

	public PanelAffect(PanelRepartition panelMere) {
		this.panelMere = panelMere;

		this.dtm = new DefaultTableModel();
		dtm.addColumn("Intervenant");
		dtm.addColumn("type");
		dtm.addColumn("nb semaines");
		dtm.addColumn("nb Gp|nb h");
		dtm.addColumn("tot eqtd");
		dtm.addColumn("commentaire");

		this.tableauAffect = new JTable(dtm);

		JScrollPane scroll = new JScrollPane(this.tableauAffect);
		scroll.setPreferredSize(new Dimension(504, 250));

		this.add(scroll);
	}

	public void ajouter() {
		HashMap<String, Integer> map = this.panelMere.getTabData();

		Object[] objs = new Object[6];
		for ( String type : map.keySet() ) {
			objs[0] = "";
			objs[1] = type;
			objs[2] = map.get(type);
			objs[3] = "";
			objs[4] = "";
			objs[5] = "";
			this.dtm.addRow(objs);
		}
	}

	public void setHeures(List<Heure> heures) {
		Object[] objs;
		System.out.println(heures);
		System.out.println("taille: "+heures.size());
		for (Heure heure : heures) {
			System.out.println(heure.getIntervenants());
			objs = new Object[6];
			System.out.println("donova");
			for (Intervenant intervenant : heure.getIntervenants()) {
				System.out.println("dd");
				if ( intervenant.getHeures().contains(heure) ) {
					System.out.println("dono "+heure.getIntervenants().size());
					objs[0] = intervenant.getNom();
					objs[1] = heure.getTypeHeure().getNomTypeHeure();
					objs[2] = heure.getDuree();
					objs[3] = "";
					objs[4] = "tot";
					objs[5] = heure.getCommentaire();
					this.ajouterLigne(objs);
				}
			}
		}
	}

	public void ajouterLigne(Object[] objs) {
		this.dtm.addRow(objs);
	}

	public ArrayList<Heure> getDataHeures(Module module, TypeHeure typeHeure) {
		ArrayList<Heure> heures = new ArrayList<>();

		float duree = 0;
		//duree  = this.dtm.getValueAt(, 6).toString();

		int nb = this.dtm.getRowCount();

		Heure tmp;
		for (int i = 0; i < nb; i++) {
			tmp = Heure.creerHeure(module, typeHeure, duree, this.dtm.getValueAt(i,6).toString());
			heures.add(tmp);
		}

		return heures;
	}

	public void supprimer() {
		this.dtm.removeRow(this.tableauAffect.getSelectedRow());
	}
}
