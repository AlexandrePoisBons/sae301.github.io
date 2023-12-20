package ihm.previsionnel.sae.saeCentre.repartition;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

import javax.swing.*;


import metier.Heure;
import metier.Intervenant;
import metier.TypeHeure;
import metier.Module;

public class FrameFormulaire extends JFrame implements ActionListener, FocusListener {

	private PanelRepartitionSae panelMere;
	private JPanel              panelFormulaire;
	private JComboBox<String>   ddlstIntervenant;
	private JComboBox<String>   ddlstTypesHeures;
	private JTextField          txtNbH; 
	private JTextField          txtTotEqtd;
	private JTextField          txtCommentaire;
	private JButton             btnValider;
	private JButton             btnAnnuler;
	private Module              module;

	public FrameFormulaire(PanelRepartitionSae panelMere, Module m) {
		this.panelMere = panelMere;
		this.module    = m;


		//Définition de la taille et la position de la fenêtre
		int hauteur = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()  - (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.05);
		int largeur = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int xSize   = (int)(largeur*0.5);
		int ySize   = (int)(hauteur*0.5);
		
		this.setSize(xSize, ySize);
		this.setLocation((int)(largeur*0.5-xSize*0.5),(int)(hauteur*0.5-ySize*0.5));

		this.panelFormulaire = new JPanel();

		this.panelFormulaire.setLayout(new GridBagLayout());

		GridBagConstraints gbc 	= new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;

		this.remplirListe(this.panelMere.getIntervenants(), this.panelMere.getTypesHeures());
		this.txtNbH         = new JTextField(15);
		this.txtTotEqtd     = new JTextField(15);
		this.txtTotEqtd.setEditable(false);
		this.txtCommentaire = new JTextField(15);
		this.btnValider     = new JButton("Valider");
		this.btnAnnuler     = new JButton("Annuler");

		gbc.gridy = 0;
		gbc.gridx = 0;
		this.panelFormulaire.add(new JLabel("Intervenants"				), gbc);
		gbc.gridy = 1;
		this.panelFormulaire.add(new JLabel("Type"), gbc);
		gbc.gridy = 2;
		this.panelFormulaire.add(new JLabel("Nb heure"	), gbc);
		gbc.gridy = 3;
		this.panelFormulaire.add(new JLabel("tot eqtd"				), gbc);	
		gbc.gridy = 4;
		this.panelFormulaire.add(new JLabel("commentaire"				), gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		this.panelFormulaire.add(this.ddlstIntervenant, gbc);
		gbc.gridy = 1;
		this.panelFormulaire.add(this.ddlstTypesHeures, gbc);
		gbc.gridy = 2;
		this.panelFormulaire.add(this.txtNbH	, gbc);
		gbc.gridy = 3;
		this.panelFormulaire.add(this.txtTotEqtd, gbc);
		gbc.gridy = 4;
		this.panelFormulaire.add(this.txtCommentaire, gbc);

		gbc.gridy = 5;
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.EAST;
		this.panelFormulaire.add(this.btnValider, gbc);
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.WEST;
		this.panelFormulaire.add(this.btnAnnuler, gbc);

		this.btnValider.addActionListener(this);
		this.btnAnnuler.addActionListener(this);
		this.txtNbH.addFocusListener(this);

		this.add(this.panelFormulaire);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btnValider) {
			this.valider();
			this.dispose();
		}
		if(e.getSource() == this.btnAnnuler){
			this.annuler();
		}
	}

	public void annuler() {
		this.txtCommentaire.setText("");
		this.txtNbH.setText("");
		this.txtTotEqtd.setText("");
		this.dispose();
	}

	public void remplirListe(List<Intervenant> intervenants, List<TypeHeure> typesHeures) {
		String[] tabInter = new String[intervenants.size()];
		String[] tabTypesHeures = new String[typesHeures.size()];

		for(int i=0;i<intervenants.size();i++)
			tabInter[i] = intervenants.get(i).getNom() + " " + intervenants.get(i).getPrenom();
		
		for (int i = 0; i < tabTypesHeures.length; i++){
			if(typesHeures.get(i).getNomTypeHeure().equals("SAE") || typesHeures.get(i).getNomTypeHeure().equals("TUT"))
				tabTypesHeures[i] = typesHeures.get(i).getNomTypeHeure();
		}
		
		this.ddlstIntervenant = new JComboBox<String>(tabInter);
		this.ddlstTypesHeures = new JComboBox<String>(tabTypesHeures);
	}



	private void valider() {
		TypeHeure typeHeure = null;
		Intervenant intervenant   = null;
		for (TypeHeure tH : this.panelMere.getTypesHeures()) {
			if(this.ddlstTypesHeures.getSelectedItem().toString() == tH.getNomTypeHeure()){
				typeHeure = tH;
			}
		}
		for (Intervenant inter : this.panelMere.getIntervenants()) {
			if(this.ddlstIntervenant.getSelectedItem().toString().equals(inter.getNom() + " " + inter.getPrenom())) {
				intervenant = inter;
			}
		}
		Heure heure = Heure.creerHeure(this.module,
		                               (typeHeure),
									   0,0,
		                                Float.parseFloat(this.txtNbH.getText()),
		                                this.txtCommentaire.getText() );
		
		heure.ajouterIntervenant(intervenant);
		this.panelMere.ajouterHeure(heure);
		this.panelMere.setHeureAffecte();
	}

	@Override
	public void focusGained(FocusEvent e) {}

	@Override
	public void focusLost(FocusEvent e) {
		float coef = 0.0f;
		for(TypeHeure tH : this.panelMere.getTypesHeures()){
			if(this.ddlstTypesHeures.getSelectedItem().toString().equals(tH.getNomTypeHeure())){
				coef = tH.getCoeff();
			}
		}
		int calcul = (int) (Integer.parseInt(this.txtNbH.getText()) * coef);
		this.txtTotEqtd.setText("" + calcul);
	}

}