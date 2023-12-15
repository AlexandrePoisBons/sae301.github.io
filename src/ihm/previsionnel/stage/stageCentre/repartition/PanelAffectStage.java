package ihm.previsionnel.stage.stageCentre.repartition;
import java.awt.Dimension;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class PanelAffectStage extends JPanel {
	private PanelRepartitionStage panelMere;
	private JTable tableauAffect;
	private DefaultTableModel dtm;

	public PanelAffectStage(PanelRepartitionStage panelMere) {
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
			objs[2] = map.get(type);
			objs[3] = 999;
			objs[4] = "";
			this.dtm.addRow(objs);
		}

	}

	public void supprimer() {
		this.dtm.removeRow(this.tableauAffect.getSelectedRow());
	}
}