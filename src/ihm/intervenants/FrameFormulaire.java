package ihm.intervenants;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import metier.Intervenant;
import metier.Statut;

public class FrameFormulaire extends JFrame implements ActionListener{
	private PanelInter              panelMere;
	private JPanel                  panelFormulaire;
	private JComboBox<String>       ddlstStatut;
	private JTextField              txtNom;
	private JTextField              txtPrenom;
	private JTextField              txtHServ;
	private JTextField              txtHMax;
	private JTextField              txtCoefTP;
	private JButton                 btnValider;
	private JButton                 btnAnnuler;
	private JLabel                  lblErreur;
	private List<Statut>            statuts;

	public FrameFormulaire(PanelInter panelMere) {
		this.panelMere       = panelMere;

		//Définition de la taille et la position de la fenêtre
		int hauteur = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.05);
		int largeur = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int xSize = (int)(largeur*0.5);
		int ySize = (int)(hauteur*0.5);
		this.setSize(xSize, ySize);
		this.setLocation((int)(largeur*0.5-xSize*0.5),(int)(hauteur*0.5-ySize*0.5));

		this.panelFormulaire = new JPanel();

		this.panelFormulaire.setLayout(new GridBagLayout());

		GridBagConstraints gbc 	= new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		
		this.remplirListe(this.panelMere.getStatuts());
		this.txtNom         = new JTextField(15);
		this.txtPrenom      = new JTextField(15);
		this.txtHServ       = new JTextField(15);
		this.txtHMax        = new JTextField(15);
		this.txtCoefTP      = new JTextField(15);
		this.btnValider     = new JButton("Valider");
		this.btnAnnuler     = new JButton("Annuler");
		this.lblErreur      = new JLabel("");

		this.txtHServ.setEditable(false);
		this.txtHMax.setEditable(false);
		this.txtCoefTP.setEditable(false);

		this.majCoefTp();
		this.majHeures();

		//Colorisation du label d'erreur en rouge
		this.lblErreur.setForeground(java.awt.Color.RED);

		gbc.gridy = 0;
		gbc.gridx = 0;
		this.panelFormulaire.add(new JLabel("Catégorie"), gbc);
		gbc.gridy = 1;
		this.panelFormulaire.add(new JLabel("Nom"), gbc);
		gbc.gridy = 2;
		this.panelFormulaire.add(new JLabel("Prénom"), gbc);
		gbc.gridy = 3;
		this.panelFormulaire.add(new JLabel("Heures services"), gbc);
		gbc.gridy = 4;
		this.panelFormulaire.add(new JLabel("Heures max"), gbc);
		gbc.gridy = 5;
		this.panelFormulaire.add(new JLabel("Coef TP"), gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		this.panelFormulaire.add(this.ddlstStatut, gbc);
		gbc.gridy = 1;
		this.panelFormulaire.add(this.txtNom, gbc);
		gbc.gridy = 2;
		this.panelFormulaire.add(this.txtPrenom, gbc);
		gbc.gridy = 3;
		this.panelFormulaire.add(this.txtHServ, gbc);
		gbc.gridy = 4;
		this.panelFormulaire.add(this.txtHMax, gbc);
		gbc.gridy = 5;
		this.panelFormulaire.add(this.txtCoefTP, gbc);

		gbc.gridy = 6;
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.EAST;
		this.panelFormulaire.add(this.btnValider, gbc);
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		this.panelFormulaire.add(this.btnAnnuler, gbc);
		gbc.gridx = 0;
		gbc.gridy = 7;
		this.panelFormulaire.add(this.lblErreur, gbc);

		this.ddlstStatut.addActionListener(this);
		this.btnValider.addActionListener(this);
		this.btnAnnuler.addActionListener(this);

		this.add(this.panelFormulaire);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if ( e.getSource() == this.ddlstStatut ) {
			this.majCoefTp();
			this.majHeures();
		} else if ( e.getSource() == this.btnValider ) {
			this.valider();
			this.dispose();
		} else if ( e.getSource() == this.btnAnnuler ) {
			this.dispose();
		}
	}

	private void majCoefTp() {
		if(this.ddlstStatut.getSelectedItem().equals("Vacataire")){
			this.txtCoefTP.setText("0.66");
		}
		else {
			this.txtCoefTP.setText("1.0");
		}
	}

	private void majHeures(){
		List<Statut> lstStatuts = this.panelMere.getStatuts();
		for(Statut statut : lstStatuts){
			if(statut.getNomStatut().equals(this.ddlstStatut.getSelectedItem())) {
				this.txtHServ.setText(String.valueOf(statut.getNbHeureService()));
				this.txtHMax.setText(String.valueOf(statut.getNbHeuresMax()));
			}
		}
	}

	private void remplirListe(List<Statut> statuts) {
		this.statuts = statuts;
		String[] tabStatut = new String[statuts.size()];
		for( int i=0; i<statuts.size(); i++ ) {
			tabStatut[i] = statuts.get(i).getNomStatut();
		}
		this.ddlstStatut = new JComboBox<String>(tabStatut);
	}

	public void valider() {
		try {
			String stt = this.ddlstStatut.getSelectedItem().toString();
			Statut statut = null;
			for ( Statut s : this.statuts ) {
				if ( s.getNomStatut().equals(stt) )
					statut = s;
			}
			Intervenant inter = Intervenant.tempIntervenant(this.txtPrenom.getText(), this.txtNom.getText(), statut, 0);
			this.panelMere.ajouterLigne( stt,
			                             this.txtNom.getText(),
			                             this.txtPrenom.getText(),
			                             Integer.parseInt(this.txtHServ.getText()),
			                             Integer.parseInt(this.txtHMax.getText()),
			                             Float.parseFloat(this.txtCoefTP.getText()) );

			this.panelMere.ajouterIntervenant(inter);
			this.panelMere.revalidate();
			this.panelMere.repaint();
		} catch (Exception e) {
			this.lblErreur.setText("Erreur, vérifier vos valeurs");
			this.repaint();
			this.revalidate();
		}
	}

}
