package ihm.intervenants;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import metier.Statut;

public class FrameFormulaire extends JFrame implements ActionListener{
	private PanelInter              panelMere;
	private JPanel                  panelFormulaire;
	private JComboBox<String>       ddlstIntervenant;
	private JTextField              txtNom; 
	private JTextField              txtPrenom;
	private JTextField              txtHServ;
	private JTextField              txtHMax;
	private JTextField              txtCoefTP;
	private JButton                 btnValider;
	private JButton                 btnAnnuler;
	private JLabel                  lblErreur;

	public FrameFormulaire(PanelInter panelMere){
		this.panelMere       = panelMere;
		//Définition de la taille et la position de la fenêtre
		int hauteur = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()  - (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.05);
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

		//Colorisation du label d'erreur en rouge
		this.lblErreur.setForeground(java.awt.Color.RED);

		gbc.gridy = 0;
		gbc.gridx = 0;
		this.panelFormulaire.add(new JLabel("Catégorie"), gbc);
		gbc.gridy = 1;
		this.panelFormulaire.add(new JLabel("Nom"), gbc);
		gbc.gridy = 2;
		this.panelFormulaire.add(new JLabel("Prénom"	), gbc);
		gbc.gridy = 3;
		this.panelFormulaire.add(new JLabel("Heures services"), gbc);	
		gbc.gridy = 4;
		this.panelFormulaire.add(new JLabel("Heures max"), gbc);
		gbc.gridy = 5;
		this.panelFormulaire.add(new JLabel("Coef TP"), gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		this.panelFormulaire.add(this.ddlstIntervenant, gbc);
		gbc.gridy = 1;
		this.panelFormulaire.add(this.txtNom, gbc);
		gbc.gridy = 2;
		this.panelFormulaire.add(this.txtPrenom	, gbc);
		gbc.gridy = 3;
		this.panelFormulaire.add(this.txtHServ			, gbc);
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

		this.btnValider.addActionListener(this);
		this.btnAnnuler.addActionListener(this);

		this.add(this.panelFormulaire);

		this.setVisible(true);
	}	

	public void actionPerformed(ActionEvent e){
		if(e.getSource() == this.btnValider){
			this.ajouterLigne();
			this.dispose();
		}
		if(e.getSource() == this.btnAnnuler){
			
			this.dispose();
		}
	}

	public void remplirListe(List<Statut> statuts){
		String[] tabStatut = new String[statuts.size()];
		for(int i=0;i<statuts.size();i++){
			tabStatut[i] = statuts.get(i).getNomStatut();
		}
		this.ddlstIntervenant = new JComboBox<String>(tabStatut);
		//ajouter un catégorie
		this.ddlstIntervenant.addItem("Enseignant");
	}

	public void ajouterLigne(){
		try {
			this.panelMere.ajouterLigne(this.ddlstIntervenant.getSelectedItem().toString(),
										this.txtNom.getText(),
										this.txtPrenom.getText(),
										this.txtHServ.getText(),
										this.txtHMax.getText(),
										this.txtCoefTP.getText());
			this.panelMere.revalidate();
			this.panelMere.repaint();
		} catch (Exception err) {
			this.lblErreur.setText("Erreur, vérifier vos valeurs");
			this.repaint();
			this.revalidate();
			//System.err.println("Erreur, vérifier vos valeurs");
		}
	}
}