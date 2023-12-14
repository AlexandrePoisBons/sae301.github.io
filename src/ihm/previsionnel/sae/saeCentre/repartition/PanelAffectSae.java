package ihm.previsionnel.sae.saeCentre.repartition;

import java.awt.Dimension;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.util.HashMap;

public class PanelAffectSae extends JPanel{
	private PanelRepartitionSae panelMere;
	private JTable tableauAffect;
	private DefaultTableModel dtm;

	public PanelAffectSae(PanelRepartitionSae panelMere) {
		this.panelMere = panelMere;

		this.dtm = new DefaultTableModel();
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

	public void ajouter() {

		HashMap<String,Integer> map = this.panelMere.getData();

		Object[] objs = new Object[5];
		for ( String type : map.keySet() ) {
			objs[0] = "";
			objs[1] = type;
			objs[2] = 999;
			objs[3] = 999;
			objs[4] = "";
			this.dtm.addRow(objs);
		}

	}

	public void supprimer() {
		this.dtm.removeRow(this.tableauAffect.getSelectedRow());
	}


}