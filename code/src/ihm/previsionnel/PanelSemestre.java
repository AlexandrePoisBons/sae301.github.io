package previsionnel;

//Pour permettre la synchronisation des pages  
import acceuil.FrameAccueil;

import java.awt.BorderLayout;
import javax.swing.*;


public class PanelSemestre extends JPanel {
	private FrameAccueil frame;

	private JTextField txtNbGpTd;
	private JTextField txtNbGpTp;
	private JTextField txtNbEtd;
	private JTextField txtNbSemaine;
	private JTable tabModule;

	private int id;

	public PanelSemestre(FrameAccueil frameAccueil, int idSemestre) {
		this.frame = frameAccueil;
		this.id = idSemestre;

		// JPanels
		JPanel panelNord = new JPanel();
		JPanel panelCentre = new JPanel();

		// Layout
		this.setLayout(new BorderLayout());
		panelCentre.setLayout(new BorderLayout());

		//Création de la liste des modules
		tabModule = new JTable();

		// Ajout des composants
		panelNord.add(new JLabel("nb gp TD"));
		this.txtNbGpTd = new JTextField(2);
		panelNord.add(this.txtNbGpTd);

		panelNord.add(new JLabel("nb gp TP"));
		this.txtNbGpTp = new JTextField(2);
		panelNord.add(this.txtNbGpTp);

		panelNord.add(new JLabel("nb Etd"));
		this.txtNbEtd = new JTextField(2);
		panelNord.add(this.txtNbEtd);

		panelNord.add(new JLabel("nb semaines"));
		this.txtNbSemaine = new JTextField(2);
		panelNord.add(this.txtNbSemaine);

		panelCentre.add(new JLabel("Liste des modules :"), BorderLayout.NORTH);
		panelCentre.add(tabModule, BorderLayout.CENTER);

		this.add(panelNord, BorderLayout.NORTH);
		this.add(panelCentre, BorderLayout.CENTER);

		this.setVisible(true);
	}

	// Getters id
	public int getIdSemestre() {
		return this.id;
	}
}