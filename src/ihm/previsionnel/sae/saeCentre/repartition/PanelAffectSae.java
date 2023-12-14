package ihm.previsionnel.sae.saeCentre.repartition;

import java.awt.Dimension;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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

		Object[] obj = this.panelMere.getHeuresProgNat();
		for (int i = 0; i < obj.length; i++) {
			System.out.println("dono"+i+":"+obj[i]);
		}

		Object[] objs = {"SylvainLevy",obj[1],1,1,1,1};

		// Object[] objsCM = {"SylvainLevy", "CM", progNat, PanelRepartitionHGauche, PanelRepartitionHDroite, "chillax"};
		// Object[] objsTP = {"PierreChabrier", "TP"}
		this.dtm.addRow(objs);
	}

	public void supprimer() {
		this.dtm.removeRow(this.tableauAffect.getSelectedRow());
	}
}