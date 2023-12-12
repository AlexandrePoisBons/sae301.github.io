package ihm.previsionnel.ressources.ressourcesNord;
//Import classes Java

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PanelModule extends JPanel {
	// Attibuts
	private PRNord panelMere;
	private JTextField txtCode;
	private JTextField txtLibLong;
	private JTextField txtLibCourt;

	public PanelModule(PRNord panelMere) {
		// Synchronisation des pages
		this.panelMere = panelMere;

		// Définition des Layouts
		this.setLayout(new GridLayout(2, 5));

		// Création des éléments
		this.txtCode 		= new JTextField();
		this.txtLibCourt 	= new JTextField();
		this.txtLibCourt 	= new JTextField();

		// Ajouts des éléments
		this.add(new JLabel("type module"	));
		this.add(new JLabel("semestre"		));
		this.add(new JLabel("code"			));
		this.add(new JLabel("libellé long"	));
		this.add(new JLabel("libellé court"	));

		this.add(new JTextField(/* A compléter */)	);
		this.add(new JTextField(/* A compléter */)	);
		this.add(this.txtCode						);
		this.add(this.txtLibLong					);
		this.add(this.txtLibCourt					);

		
	}
}
