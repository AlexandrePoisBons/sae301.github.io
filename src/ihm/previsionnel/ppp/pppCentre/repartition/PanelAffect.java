package ihm.previsionnel.ppp.pppCentre.repartition;

import java.awt.Dimension;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.util.HashMap;

public class PanelAffect extends JPanel {
	private PanelRepartition panelMere;
	private JTable tableauAffect;
	private DefaultTableModel dtm;

	public PanelAffect(PanelRepartition panelMere) {
		this.panelMere = panelMere;

		this.dtm = new DefaultTableModel(){
			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
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

	public void supprimer() {
		if(this.tableauAffect.getSelectedRow() != -1) {
			this.dtm.removeRow(this.tableauAffect.getSelectedRow());
		}
		else {
			System.err.println("Sélectionner une ligne");
		}
	}


}
