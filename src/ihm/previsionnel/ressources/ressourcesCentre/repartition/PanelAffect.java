package ihm.previsionnel.ressources.ressourcesCentre.repartition;

import java.awt.Dimension;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import ihm.previsionnel.ressources.ressourcesCentre.PanelRepartition;
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
		for (Heure heure : heures) {
			objs = new Object[6];
			objs[0] = heure.getIntervenants().get(0).getNom()+" "+heure.getIntervenants().get(0).getPrenom().substring(0,1);
			objs[1] = heure.getTypeHeure().getNomTypeHeure();
			objs[2] = heure.getDuree();
			objs[3] = "";
			objs[4] = "tot";
			objs[5] = heure.getCommentaire();
			this.ajouterLigne(objs);
		}
	}

	public void ajouterLigne(Object[] objs) {
		this.dtm.addRow(objs);
	}

	public List<Heure> getDataHeures(Module module) {
		List<Heure> heures = new ArrayList<>();

		float duree = 0;
		//duree  = this.dtm.getValueAt(, 6).toString();

		int nb = this.dtm.getRowCount();

		List<TypeHeure> ths = this.panelMere.getTypesHeures();

		Heure tmpH;
		TypeHeure tmpTH = null;
		for (int i = 0; i < nb; i++) {
			for (TypeHeure typeHeure : ths)
				if (typeHeure.getNomTypeHeure().equals(this.dtm.getValueAt(i, 1).toString()))
					tmpTH = typeHeure;
			tmpH = Heure.creerHeure(module, tmpTH, duree, this.dtm.getValueAt(i,5).toString());

			List<Intervenant> thI = this.panelMere.getIntervenants();
			for (Intervenant intervenant : thI) {
				String int1Name = intervenant.getNom()+" "+intervenant.getPrenom().substring(0,1)+".";
				String int2Name = this.dtm.getValueAt(i, 0).toString();
				if( int1Name.equals(int2Name) ){
					tmpH.ajouterIntervenant(intervenant);
				}
			}

			heures.add(tmpH);
		}

		return heures;
	}

	public void supprimer() {
		this.dtm.removeRow(this.tableauAffect.getSelectedRow());
	}

}
