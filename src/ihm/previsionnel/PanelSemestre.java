package ihm.previsionnel;

//Imports classes Java
import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controleur.Controleur;
import ihm.accueil.FrameAccueil;
import metier.Module;


public class PanelSemestre extends JPanel {

	// Attributs
	private FrameAccueil      frame        ;
	private Controleur        ctrl         ;
	private JTextField        txtNbGpTd    ;
	private JTextField        txtNbGpTp    ;
	private JTextField        txtNbEtd     ;
	private JTextField        txtNbSemaine ;
	private DefaultTableModel dtm          ;
	private JTable            tabModule	   ;
	private int               id           ;
	private List<Module>      modules      ;

	// Constructeur
	public PanelSemestre(FrameAccueil frameAccueil, int idSemestre) {

		// Synchronisation des pages
		this.ctrl = frameAccueil.getControleur().getCtrl();

		// Permet d'identifier le semestre cliqué
		this.id 	= idSemestre;

		// JPanels
		JPanel panelNord		= new JPanel()	;
		JPanel panelCentre	 	= new JPanel()	;

		// Layout
		this.setLayout			(new BorderLayout());
		panelCentre.setLayout	(new BorderLayout());

		//Instanciations des composants
		this.txtNbGpTd    = new JTextField(2);
		this.txtNbGpTp    = new JTextField(2);
		this.txtNbEtd     = new JTextField(2);
		this.txtNbSemaine = new JTextField(2);


		this.dtm = new DefaultTableModel(null, new String[] {"Liste des modules", "", "", ""}) {
			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};

		this.tabModule = new JTable(this.dtm);
		DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
		headerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.LEFT);
		headerRenderer.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); // Ajoute une bordure inférieure

		this.tabModule.getTableHeader().setDefaultRenderer(headerRenderer);

		Border tableBorder = BorderFactory.createLineBorder(Color.BLACK);
		this.tabModule.setBorder(tableBorder);

		// Ajout des composants
		panelNord.add	(new JLabel("nb gp TD")         );
		panelNord.add	(this.txtNbGpTd                 );
		panelNord.add	(new JLabel("nb gp TP")         );
		panelNord.add	(this.txtNbGpTp	                );
		panelNord.add	(new JLabel("nb Etd")           );
		panelNord.add	(this.txtNbEtd                  );
		panelNord.add	(new JLabel("nb semaines")      );
		panelNord.add	(this.txtNbSemaine              );
		//panelCentre.add	(new JLabel("Liste des modules :")  , BorderLayout.NORTH  );
		panelCentre.add (new JScrollPane(this.tabModule), BorderLayout.CENTER );

		this.add(panelNord                              , BorderLayout.NORTH  );
		this.add(panelCentre                            , BorderLayout.CENTER );

		this.modules = new ArrayList<Module>();

		// Affichage
		this.setVisible(true);

	}

	// Getters
	public int getIdSemestre() { return this.id;                    }
	public int getNbEtd()      { return Integer.parseInt(this.txtNbEtd.getText());    }
	public int getNbGpTd()     { return Integer.parseInt(this.txtNbGpTd.getText());   }
	public int getNbGpTp()     { return Integer.parseInt(this.txtNbGpTp.getText());   }
	public int getNbSemaines() { return Integer.parseInt(this.txtNbSemaine.getText());}

	public void setModules(List<Module> list) {
		this.modules = list;
		for (Module module : this.modules) {
			String[] s = {module.getCode(), module.getLibelle()};
			this.dtm.addRow(s);
		}
	}

	public void ajouterModule(Module module) {
		this.modules.add(module);
		String[] s = {module.getCode()};
		this.dtm.addRow(s);
	}

	public void updateModule(Module module) {
		this.modules.remove(this.tabModule.getSelectedRow());
		String[] s = {module.getCode()};
		this.dtm.addRow(s);
	}

	public Module getCurrentModule() { 
		if(this.tabModule.getSelectedRow() != -1) {
			return this.modules.get(this.tabModule.getSelectedRow());
		}
		return null;
	}

	public void removeModule() throws SQLException{
		if(this.tabModule.getSelectedRow() != -1){
			this.ctrl.metier().supprimerModule(this.getCurrentModule());
			this.modules.remove(this.tabModule.getSelectedRow());
			this.dtm.removeRow (this.tabModule.getSelectedRow());
		}
		else {
			System.err.println("Sélectionner une ligne");
		}
		
	}

	public List<Module> getModules() { return this.modules; }


}