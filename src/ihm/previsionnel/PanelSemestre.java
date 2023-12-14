package ihm.previsionnel;

//Imports classes Java
import java.awt.BorderLayout;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import ihm.accueil.FrameAccueil;
import metier.Module;


public class PanelSemestre extends JPanel {

	// Attributs
	private FrameAccueil frame;
	private JTextField txtNbGpTd		;
	private JTextField txtNbGpTp		;
	private JTextField txtNbEtd			;
	private JTextField txtNbSemaine		;
	private DefaultTableModel dtm       ;
	private JTable tabModule			;
	private int id						;

	// Constructeur
	public PanelSemestre(FrameAccueil frameAccueil, int idSemestre) {

		// Synchronisation des pages
		this.frame 	= frameAccueil		;

		// Permet d'identifier le semestre cliqué
		this.id 	= idSemestre		;

		// JPanels
		JPanel panelNord		= new JPanel()	;
		JPanel panelCentre	 	= new JPanel()	;	

		// Layout
		this.setLayout			(new BorderLayout()	);
		panelCentre.setLayout	(new BorderLayout()	);

		//Instanciations des composants
		this.txtNbGpTd = new JTextField(2			);
		this.txtNbGpTp = new JTextField(2			);
		this.txtNbEtd = new JTextField(2			);
		this.txtNbSemaine = new JTextField(2		);

		this.dtm = new DefaultTableModel();
		this.dtm.addRow(new String[] {"R1", "dev", "2/6"});
		this.tabModule = new JTable(this.dtm);

		JScrollPane tableau = new JScrollPane(this.tabModule);

		// Ajout des composants
		panelNord.add	(new JLabel("nb gp TD")		    );
		panelNord.add	(this.txtNbGpTd				    );
		panelNord.add	(new JLabel("nb gp TP")		    );
		panelNord.add	(this.txtNbGpTp				    );
		panelNord.add	(new JLabel("nb Etd")			);		
		panelNord.add	(this.txtNbEtd					);
		panelNord.add	(new JLabel("nb semaines")		);		
		panelNord.add	(this.txtNbSemaine				);
		panelCentre.add	(new JLabel("Liste des modules :")	, BorderLayout.NORTH	);
		panelCentre.add	(tableau							, BorderLayout.CENTER	);

		this.add(panelNord									, BorderLayout.NORTH	);
		this.add(panelCentre								, BorderLayout.CENTER	);

		// Affichage
		this.setVisible(true);
	}

	// Getters
	public int    getIdSemestre() { return this.id;                    }
	public String getNbEtd()      { return this.txtNbEtd.getText();    }
	public String getNbGpTd()     { return this.txtNbGpTd.getText();   }
	public String getNbGpTp()     { return this.txtNbGpTp.getText();   }
	public int    getNbSemaines() { return Integer.parseInt(this.txtNbSemaine.getText());}

	public void   setModules(List<Module> list) {
		for (Module module : list) {
			String[] s = {module.getCode(), module.getLibelle()};
			this.dtm.addRow(s);
		}
	}
}