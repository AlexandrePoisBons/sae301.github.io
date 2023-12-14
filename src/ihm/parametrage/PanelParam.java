package ihm.parametrage;

import javax.swing.*;

import java.util.*;

import ihm.accueil.FrameAccueil;
import ihm.accueil.PanelAcceuil;

import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent		;
import java.awt.event.ActionListener	;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class PanelParam extends JPanel implements ActionListener{

	private ArrayList<String> listStatut;
	private ArrayList<String> listCoef;

	private FrameAccueil      frame;
	private JPanel            panelG;
	private JPanel            panelD;
	private JPanel            panelSud;

	private JButton           btnAjoutStat;
	private JButton           btnAjoutCoef;
	private JButton           btnSuppStat;
	private JButton           btnSuppCoef;
	private JButton           btnRetour;
	private JButton           btnEnregistrer;

	private JTable            tableauStatut;
	private JTable            tableauCoef;
	private DefaultTableModel dtmStatut;
	private DefaultTableModel dtmCoef;
	
	public PanelParam(FrameAccueil frame){
		this.frame = frame;
		this.listStatut = new ArrayList<String>();
		this.listCoef   = new ArrayList<String> ();

		this.setLayout(new BorderLayout());
		this.frame.setSize(1000, 600);

		// Creation des éléments de la page 
		this.dtmStatut    = new DefaultTableModel();
		this.dtmCoef      = new DefaultTableModel();
		this.panelG       = new JPanel();
		this.panelD       = new JPanel();
		this.panelSud     = new JPanel();

		this.btnAjoutStat   = new JButton("Ajouter");
		this.btnAjoutCoef   = new JButton("Ajouter");
		this.btnSuppStat    = new JButton("Supprimer");
		this.btnSuppCoef    = new JButton("Supprimer");
		this.btnRetour      = new JButton("Retour");
		this.btnEnregistrer = new JButton("Enregistrer");


		// Creation des tableau
		this.dtmStatut.addColumn("Nom");
		this.dtmCoef  .addColumn("Coeff");

		this.tableauStatut  = new JTable(this.dtmStatut);
		this.tableauCoef    = new JTable(this.dtmCoef);
		
		// Creation des scrollpane
		JScrollPane scrollStatut = new JScrollPane(this.tableauStatut);
		scrollStatut.setPreferredSize(new Dimension(300, 300));
		JScrollPane scrollCoef   = new JScrollPane(this.tableauCoef);
		scrollCoef.setPreferredSize(new Dimension(300, 300));

		// Creation sous panels
		JPanel panelTableauG = new JPanel();
		JPanel panelTableauD = new JPanel();
		JPanel panelBtnG     = new JPanel();
		JPanel panelBtnD     = new JPanel();

		this.panelG.setLayout(new BorderLayout());
		this.panelD.setLayout(new BorderLayout());
		
		panelTableauG.add(scrollStatut);
		panelTableauD.add(scrollCoef);

		panelBtnG.add(this.btnAjoutStat);
		panelBtnG.add(this.btnSuppStat);

		panelBtnD.add(this.btnAjoutCoef);
		panelBtnD.add(this.btnSuppCoef);

		// Ajout dans panel gauche
		this.panelG.add(panelTableauG, BorderLayout.NORTH);
		this.panelG.add(panelBtnG);

		// Ajout dans panel droit
		this.panelD.add(panelTableauD, BorderLayout.NORTH);
		this.panelD.add(panelBtnD);

		//Ajout dans panel retour
		this.panelSud.add(this.btnRetour);
		this.panelSud.add(this.btnEnregistrer);

		// Ajout des panels
		this.add(this.panelG   , BorderLayout.WEST);
		this.add(this.panelD   , BorderLayout.EAST);
		this.add(this.panelSud , BorderLayout.SOUTH);

		// Activation des boutons 
		this.btnAjoutStat  .addActionListener(this);
		this.btnAjoutCoef  .addActionListener(this);
		this.btnSuppStat   .addActionListener(this);
		this.btnSuppCoef   .addActionListener(this);
		this.btnRetour     .addActionListener(this);
		this.btnEnregistrer.addActionListener(this);

		this.setVisible(true);
	}

	public void ajouterStatut(){
		Object[] objs = {""};
		this.dtmStatut.addRow(objs);
	}

	public void ajouterCoef(){
		Object[] objs = {"0.0"};
		this.dtmCoef.addRow(objs);
	}

	public void supprimerStatut(){

		System.out.println("valeur selectionné: " + this.dtmStatut.getValueAt(this.tableauStatut.getSelectedRow(), 0));
		System.out.println("row selectionné: " + this.tableauStatut.getSelectedRow());

		if(this.tableauStatut.getSelectedRow() != -1){
			this.dtmStatut.removeRow(this.tableauStatut.getSelectedRow());

			if(this.dtmStatut.getValueAt(this.tableauStatut.getSelectedRow(), 0) != "")
				this.listStatut.remove(this.dtmStatut.getValueAt(this.tableauStatut.getSelectedRow(), 0));
			
		}

	}

	public void supprimerCoef(){
		if(this.tableauCoef.getSelectedRow() != -1){
			this.dtmCoef.removeRow(this.tableauCoef.getSelectedRow());

			// if(this.dtmCoef.getValueAt(this.tableauCoef.getSelectedRow(), 0) != "0.0")
			// 	this.listCoef.remove(this.dtmCoef.getValueAt(this.tableauCoef.getSelectedRow(), 0));
		}

	
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

		if(e.getSource() == this.btnEnregistrer){
			
			for (int i = 0; i < this.dtmStatut.getRowCount(); i++) {
				if(this.dtmStatut.getValueAt(i, 0) != "")
					this.listStatut.add((String) this.dtmStatut.getValueAt(i, 0));

					System.out.println(this.listStatut.size());
					System.out.println(this.listStatut);
			}
				
				

			for (int i = 0; i < this.dtmCoef.getRowCount(); i++) 
			{
				System.out.println(this.dtmCoef.getValueAt(i, 0));
				if(this.dtmCoef.getValueAt(i, 0) != "0.0")
					this.listCoef.add((String)this.dtmCoef.getValueAt(i, 0));
			}
					
		}

		if(e.getSource() == this.btnRetour){
			this.frame.changerPanel(new PanelAcceuil(frame));
			this.frame.setSize(350, 200);
		}
	}

	public ArrayList<String> getListStatut(){
		return this.listStatut;
	}
}
