package ihm.intervenants;


//Imports classes externes
import ihm.accueil.FrameAccueil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent		;
import java.awt.event.ActionListener	;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

public class PanelInter extends JPanel implements ActionListener{
	private FrameAccueil      frame;

	private JPanel 			  panelPrincipal;
	private JPanel			  panelBtn;
	private JButton 	      btnAjouter;
	private JButton           btnAnnuler;
	private JButton		      btnSupprimer;
	private JButton           btnEnregistrer;
	private JTable            tableauInter;
	private DefaultTableModel dtm;

	public PanelInter(FrameAccueil frAcceuil){
		//Synchronisation des pages
		this.frame = frAcceuil;
		this.frame.setLocation(0, 0);
		int hauteur = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int largeur = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.05);
		this.frame.setSize(hauteur, largeur);
		this.setLayout(new BorderLayout());

		JPanel panelAjSup 	= new JPanel();
		this.panelPrincipal = new JPanel();
		this.panelBtn		= new JPanel(new FlowLayout(FlowLayout.LEFT));
		this.panelPrincipal.setLayout(new BorderLayout());
		

        this.btnAjouter     = new JButton("ajouter");
		this.btnSupprimer   = new JButton("supprimer");
		this.btnEnregistrer = new JButton("enregistrer");
		this.btnAnnuler     = new JButton("annuler");
		
		this.dtm            = new DefaultTableModel();

		this.dtm.addColumn("Categorie");
		this.dtm.addColumn("Nom");
		this.dtm.addColumn("Prenom");
		this.dtm.addColumn("hServ");
		this.dtm.addColumn("hMax");
		this.dtm.addColumn("coefTp");
		this.dtm.addColumn("S1");
		this.dtm.addColumn("S3");
		this.dtm.addColumn("S5");
		this.dtm.addColumn("sTot");
		this.dtm.addColumn("S2");
		this.dtm.addColumn("S4");
		this.dtm.addColumn("S6");
		this.dtm.addColumn("sTot");
		this.dtm.addColumn("Total");

		this.tableauInter  = new JTable(this.dtm);

		JScrollPane scroll = new JScrollPane(this.tableauInter);
		scroll.setPreferredSize(new Dimension(1000, 400));

		panelAjSup.add(this.btnAjouter);
		panelAjSup.add(this.btnSupprimer);

		this.panelPrincipal.add(new JLabel("Liste des intervenants"), BorderLayout.NORTH);
		this.panelPrincipal.add(scroll, BorderLayout.CENTER);
		this.panelPrincipal.add(panelAjSup, BorderLayout.SOUTH);

		this.panelBtn.add(this.btnEnregistrer);
		this.panelBtn.add(this.btnAnnuler);

		this.add(this.panelPrincipal, BorderLayout.CENTER);
		this.add(this.panelBtn, BorderLayout.SOUTH);

		this.btnAjouter.addActionListener(this);
		this.btnSupprimer.addActionListener(this);
		this.btnEnregistrer.addActionListener(this);
		this.btnAnnuler.addActionListener(this);

		this.setVisible(true);
	}

	public void ajouter()
	{
		Object[] objs = {"", "", "", 0, 0, "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0"};
		
		this.dtm.addRow(objs);
	}

	public void supprimer()
	{
		this.dtm.removeRow(this.tableauInter.getSelectedRow());
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btnAjouter){
			this.ajouter();
		}

		if(e.getSource() == this.btnSupprimer){
			this.supprimer();
		}

		if(e.getSource() == this.btnEnregistrer){
			boolean bOk = true;
			for(int i=0;i<this.dtm.getRowCount() && bOk;i++){
				for(int j=0;j<this.dtm.getColumnCount() && bOk;j++){
					if(j<3){
						if(!(this.dtm.getValueAt(i, j) instanceof String) ){
							bOk = false;
							System.out.println("String faux");
						}
					}

					if(j == 3 || j == 4 ){
						if(!(this.dtm.getValueAt(i, j) instanceof Integer) ){
							bOk = false;
							System.out.println("int faux");
						}	
					}
					if(j>4 && j<this.dtm.getColumnCount()){
						if(!(this.dtm.getValueAt(i, j) instanceof Float) ){
							bOk = false;
							System.out.println("float faux");
						}
					}				
				}
			}
			if(bOk){
				System.out.println("Tout bon");
			}
		}

		if(e.getSource() == this.btnAnnuler){
			//this.frame.changerPanel();
		}
	}

    
	
}
