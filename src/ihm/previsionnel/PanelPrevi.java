package ihm.previsionnel;

import ihm.accueil.FrameAccueil;
import ihm.previsionnel.ppp.PanelPpp;
import ihm.previsionnel.ressources.PanelRessources;
import ihm.previsionnel.sae.PanelSae;
import ihm.previsionnel.stage.PanelStage;
import metier.Module;

//Imports classes Java
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.*;

public class PanelPrevi extends JPanel implements ActionListener {
	//Attributs
	private FrameAccueil 		frame;
	private PanelCenterPrevi 	panelCenterPrevi;
	private JButton 			btnCreerRessource;
	private JButton 			btnCreerSAE;
	private JButton 			btnCreerStage;
	private JButton             btnCreerPpp;
	private JButton 			btnModifier;
	private JButton 			btnSupprimer;

	//Constructeur
	public PanelPrevi(FrameAccueil frAccueil) {
		//Synchronisation des pages
		this.frame = frAccueil;

		// création des composants
		JPanel panelSud 		= new JPanel			(					     );
		this.panelCenterPrevi 	= new PanelCenterPrevi	(this.frame			     );
		this.btnCreerRessource 	= new JButton			("Créer ressource"	 );
		this.btnCreerSAE 		= new JButton			("Créer SAE"		 );
		this.btnCreerStage 		= new JButton			("Créer stage/suivi");
		this.btnCreerPpp        = new JButton           ("Créer PPP"        );
		this.btnModifier 		= new JButton			("Modifier"		 );
		this.btnSupprimer 		= new JButton			("Supprimer"		 );

		//Layout
		this.setLayout		(new BorderLayout	()			);
		panelSud.setLayout	(new GridLayout		(1, 5, 5, 0));

		//Ajout des composants
		panelSud.add(this.btnCreerRessource						);
		panelSud.add(this.btnCreerSAE							);
		panelSud.add(this.btnCreerStage							);
		panelSud.add(this.btnCreerPpp                           );
		panelSud.add(this.btnModifier							);
		panelSud.add(this.btnSupprimer							);
		this	.add(panelCenterPrevi, 		BorderLayout.CENTER	);
		this	.add(panelSud, 				BorderLayout.SOUTH	);

		//ActionListeners
		this.btnCreerRessource  .addActionListener(this);
		this.btnCreerSAE        .addActionListener(this);
		this.btnCreerStage      .addActionListener(this);
		this.btnCreerPpp        .addActionListener(this);
		this.btnModifier        .addActionListener(this);
		this.btnSupprimer       .addActionListener(this);

		List<Module> alModule = this.frame.getControleur().getCtrl().metier().getModules();
		Module m = Module.creerModule("coucou", "a", "a", "a", "a", 0, 0, 0, 0, 0);
		alModule.add(m);
		System.out.println("coucou"+alModule);
		this.panelCenterPrevi.setModules( alModule );

	}

	public String getSemestre()   { return "S"+this.panelCenterPrevi.getCurrentSemestre().getIdSemestre();   }
	public String getNbEtd()      { return this.panelCenterPrevi.getNbEtd();      }
	public String getNbGpTd()     { return this.panelCenterPrevi.getNbGpTd();     }
	public String getNbGpTp()     { return this.panelCenterPrevi.getNbGpTp();     }
	public int    getNbSemaines() { return this.panelCenterPrevi.getNbSemaines(); }

	//Permets de faire une action en fonction du bouton cliqué
	@Override
	public void actionPerformed(ActionEvent e) {
		if ( e.getSource() == this.btnCreerRessource ) {
			this.frame.changerPanel( new PanelRessources(this.frame, this) );
		}
		if(e.getSource() == this.btnCreerSAE) {
			//this.frame.changerPanel( new PanelSae(this.frame, this) );
			this.frame.changerPanel( new PanelSae(this.frame, this) );
		}
		if(e.getSource() == this.btnCreerStage) {
			this.frame.changerPanel(new PanelStage(this.frame, this));
		}
		if(e.getSource() == this.btnCreerPpp) {
			this.frame.changerPanel(new PanelPpp(this.frame, this));
		}
		if(e.getSource() == this.btnModifier) {
			Module m = this.panelCenterPrevi.getCurrentSemestre().getCurrentModule();

			// switch(m.getTypeModule()) {
			// case "SAE"       -> this.frame.changerPanel(new PanelSae         (this.frame, this, m ) );
			// case "Ressource" -> this.frame.changerPanel(new PanelRessources  (this.frame, this, m ) );
			// case "Stage"     -> this.frame.changerPanel(new PanelStage       (this.frame, this, m ) );
			// case "PPP"       -> this.frame.changerPanel(new PanelPpp         (this.frame, this, m ) );
			// default          -> System.err.println("TypeModule inexistant");
 			// }
		}

		if(e.getSource() == this.btnSupprimer) {
			try { this.panelCenterPrevi.getCurrentSemestre().removeModule(); }
			catch(SQLException eq) {eq.printStackTrace(); }
		}
	}
}