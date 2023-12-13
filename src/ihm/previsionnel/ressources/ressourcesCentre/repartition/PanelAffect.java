package ihm.previsionnel.ressources.ressourcesCentre.repartition;


import java.awt.Dimension;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PanelAffect extends JPanel{
	private PanelRepartition panelMere;
	private JTable tableauAffect;

	public PanelAffect(PanelRepartition panelMere){
		this.panelMere = panelMere;

		DefaultTableModel dtm = new DefaultTableModel();
		dtm.addColumn("Intervenant");
		dtm.addColumn("type");
		dtm.addColumn("nb sema");
		dtm.addColumn("nb Gp|nb h");
		dtm.addColumn("tot eqtd");
		dtm.addColumn("commentaire");

		this.tableauAffect = new JTable(dtm);

		JScrollPane scroll = new JScrollPane(this.tableauAffect);
		scroll.setPreferredSize(new Dimension(470, 250));

		this.add(scroll);
	}
	
}
