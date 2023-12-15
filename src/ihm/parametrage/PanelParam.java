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
import java.awt.Toolkit;

public class PanelParam extends JPanel implements ActionListener{

	private ArrayList<String> listStatut;
	HashMap<String, ArrayList<Integer>> hashCoef;

	private FrameAccueil      frame;
	private JPanel            panelG;
	private JPanel            panelD;
	private JPanel            panelSud;

	private JButton           btnAjoutStat;
	private JButton           btnSuppStat;
	private JButton           btnRetour;
	private JButton           btnEnregistrer;

	private JTable            tableauStatut;
	private DefaultTableModel dtmStatut;
	
	public PanelParam(FrameAccueil frame){
		this.frame      = frame;
		this.listStatut = new ArrayList<String>();

		//Placement de la frame
		int hauteur = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()  - (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.05);
		int largeur = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int xSize = (int)(largeur*0.70);
		int ySize = (int)(hauteur*0.5);
		this.frame.setSize(xSize, ySize);
		this.frame.setLocation((int)(largeur*0.5-xSize*0.5),(int)(hauteur*0.5-ySize*0.5));

		// Creation des éléments de la page 
		this.dtmStatut      = new DefaultTableModel();
		this.panelG         = new JPanel();
		this.panelD         = new JPanel();
		this.panelSud       = new JPanel();

		this.btnAjoutStat   = new JButton("Ajouter");
		this.btnSuppStat    = new JButton("Supprimer");
		this.btnRetour      = new JButton("Retour");
		this.btnEnregistrer = new JButton("Enregistrer");


		// Creation des tableau
		this.dtmStatut.addColumn("Statut");

		this.tableauStatut  = new JTable(this.dtmStatut);
		
		// Creation des scrollpane
		JScrollPane scrollStatut = new JScrollPane(this.tableauStatut);
		scrollStatut.setPreferredSize(new Dimension(300, 300));

		// Creation sous panels
		JPanel panelTableauG = new JPanel();
		JPanel panelTableauD = new JPanel();
		JPanel panelBtnG     = new JPanel();
		JPanel panelBtnD     = new JPanel();

		this.panelG.setLayout(new BorderLayout());
		this.panelD.setLayout(new BorderLayout());
		
		panelTableauG.add(scrollStatut);

		panelBtnG.add(this.btnAjoutStat);
		panelBtnG.add(this.btnSuppStat);


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
		this.btnSuppStat   .addActionListener(this);
		this.btnRetour     .addActionListener(this);
		this.btnEnregistrer.addActionListener(this);

		this.setVisible(true);
	}

	// Ajout des statut
	public void ajouterStatut(){
		Object[] objs = {""};
		this.dtmStatut.addRow(objs);
		System.out.println("taille: " + listStatut.size());
	}

	// Ajout des coef
	/*public void ajouterCoef(){
		Object[] objs = {"0.0"};
		this.dtmCoef.addRow(objs);
	}*/

	// Supprimer Statut
	public void supprimerStatut() {
		int ligneSelectionne = this.tableauStatut.getSelectedRow();
		
		if (ligneSelectionne != -1) {
			String valeurASupp = (String) this.dtmStatut.getValueAt(ligneSelectionne, 0);
			this.dtmStatut.removeRow(ligneSelectionne);
	
			if (!this.listStatut.isEmpty() && this.listStatut.contains(valeurASupp)) {
				this.listStatut.remove(valeurASupp);
			}
		}
	
		System.out.println("taille : " + listStatut.size());
		System.out.println(this.listStatut);
	}
	

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btnAjoutStat){
			ajouterStatut();
		}

		if(e.getSource() == this.btnSuppStat){
			supprimerStatut();
		}

		// Ajout des valeurs dans listStatut et listCoef quand on clique sur Enregistrer
		if(e.getSource() == this.btnEnregistrer){
			
			for (int i = 0; i < this.dtmStatut.getRowCount(); i++) {
				if(this.dtmStatut.getValueAt(i, 0) != ""){
					if(!this.listStatut.contains((String) this.dtmStatut.getValueAt(i, 0))){
						this.listStatut.add((String) this.dtmStatut.getValueAt(i, 0));
					}
				}
				System.out.println("enreg: " + listStatut);	
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
