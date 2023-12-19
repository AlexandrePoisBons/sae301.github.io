package ihm.previsionnel;

// Import classes externes
import ihm.accueil.FrameAccueil;
import ihm.accueil.PanelAcceuil;
import ihm.previsionnel.ppp.PanelPpp;
import ihm.previsionnel.ressources.PanelRessources;
import ihm.previsionnel.sae.PanelSae;
import ihm.previsionnel.stage.PanelStage;
import metier.Module;

// Imports classes Java
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
	private JButton 			btnCreer;
	private JComboBox<String>   ddlstBox;
	private JButton 			btnModifier;
	private JButton 			btnSupprimer;
	private JButton             btnRetour;

	//Constructeur
	public PanelPrevi(FrameAccueil frAccueil) {
		//Synchronisation des pages
		this.frame = frAccueil;

		// création des composants
		JPanel panelSud        = new JPanel();
		this.panelCenterPrevi  = new PanelCenterPrevi	(this.frame);
		this.btnCreer          = new JButton("Créer ressources");
		String[] typeModules   = {"Ressources", "SAE", "Stage", "PPP"};
		this.ddlstBox          = new JComboBox<String>(typeModules);
		this.btnModifier       = new JButton("Modifier");
		this.btnSupprimer      = new JButton("Supprimer");
		this.btnRetour         = new JButton("Retour");


		//Layout
		this.setLayout(new GridBagLayout());
		panelSud.setLayout(new GridLayout(1, 5, 6, 0));
		GridBagConstraints gbc = new GridBagConstraints();

		//Ajout des composants
		panelSud.add(this.btnCreer);
		panelSud.add(this.ddlstBox);
		panelSud.add(this.btnModifier);
		panelSud.add(this.btnSupprimer);
		panelSud.add(this.btnRetour);
		gbc.gridy = 0;
		gbc.gridx = 0;
		this.add(panelCenterPrevi, gbc);
		gbc.gridy = 1;
		this.add(panelSud, gbc);


		//ActionListeners
		this.btnCreer           .addActionListener(this);
		this.ddlstBox           .addActionListener(this);
		this.btnRetour          .addActionListener(this);
		this.btnModifier        .addActionListener(this);
		this.btnSupprimer       .addActionListener(this);
		this.btnRetour          .addActionListener(this);

		List<Module> alModule = this.frame.getControleur().getCtrl().metier().getModules();
		// Module m = Module.creerModule("coucou", "a", "a", "a", "a", 0, 0, 0, 0, 0);
		// alModule.add(m);
		// System.out.println("coucou"+alModule);
		this.panelCenterPrevi.setModules( alModule );
	}





	public String getSemestre()   { return "S"+this.panelCenterPrevi.getCurrentSemestre().getIdSemestre();   }
	public String getNbEtd()      { return this.panelCenterPrevi.getNbEtd();      }
	public String getNbGpTd()     { return this.panelCenterPrevi.getNbGpTd();     }
	public String getNbGpTp()     { return this.panelCenterPrevi.getNbGpTp();     }
	public int    getNbSemaines() { return this.panelCenterPrevi.getNbSemaines(); }
	public void   ajouterModule(Module module) { this.panelCenterPrevi.ajouterModule(module); }
	public void   updateModule( Module oldModule, Module newModule) { this.panelCenterPrevi.updateModule(oldModule, newModule); }

	//Permets de faire une action en fonction du bouton cliqué
	@Override
	public void actionPerformed(ActionEvent e) {
		if ( e.getSource() == this.btnCreer ) {
			switch (this.ddlstBox.getSelectedItem().toString()) {
				case "Ressources" -> this.frame.changerPanel( new PanelRessources(this.frame, this, null) );
				case "SAE"       -> this.frame.changerPanel( new PanelSae       (this.frame, this, null) );
				case "Stage"     -> this.frame.changerPanel( new PanelStage     (this.frame, this, null) );
				case "PPP"       -> this.frame.changerPanel( new PanelPpp       (this.frame, this, null) );
				default -> System.err.println("TypeModule inexistant");
			}
		}

		if(e.getSource() == this.ddlstBox){
			this.btnCreer.setText("Créer " + this.ddlstBox.getSelectedItem().toString());
		}
		
		if (e.getSource() == this.btnModifier) {
			Module m = this.panelCenterPrevi.getCurrentSemestre().getCurrentModule();
			if(m != null){
				switch(this.panelCenterPrevi.getCurrentSemestre().getCurrentModule().getTypeModule()) {
				case "SAE"       -> this.frame.changerPanel(new PanelSae         (this.frame, this, m ) );
				case "Ressource" -> this.frame.changerPanel(new PanelRessources  (this.frame, this, m ) );
				case "Stage"     -> this.frame.changerPanel(new PanelStage       (this.frame, this, m ) );
				case "PPP"       -> this.frame.changerPanel(new PanelPpp         (this.frame, this, m ) );
				default          -> System.err.println("TypeModule inexistant");
 			}
			}
			else {
				System.err.println("Sélectionner une ligne");
			}
		}
		if(e.getSource() == this.btnSupprimer) {
			try { this.panelCenterPrevi.getCurrentSemestre().removeModule(); }
			catch(SQLException eq) {System.out.println("Sélectionner une ligne pour la supprimer");; }
		}
		if(e.getSource() == this.btnRetour){
			this.frame.changerPanel(new PanelAcceuil(this.frame));
		}
	}
}
