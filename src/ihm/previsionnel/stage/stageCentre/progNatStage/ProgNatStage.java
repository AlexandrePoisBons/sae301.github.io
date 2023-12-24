package ihm.previsionnel.stage.stageCentre.progNatStage;

//import classes java
import javax.swing.*;

import ihm.previsionnel.stage.stageCentre.PCentreStage;
import ihm.previsionnel.stage.stageCentre.repartition.heureStage.PanelRepHStage;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.HashMap;
import java.awt.Color;

import metier.Module;

public class ProgNatStage extends JPanel implements ActionListener, FocusListener{
	private PCentreStage panelMere;
	private JPanel     panelPrincipal;
	private JPanel     panelValidation;
	private JTextField txtHSae;
	private JTextField txtHTut;
	private JTextField txtSomme;
	private JCheckBox  checkValid;
	private int sommeAction;

	private Module module;

	private int sommeHSae = 0;
	private int sommeHTut = 0;

	private int totalSomme = 0;

	public ProgNatStage(PCentreStage panelMere, Module m) {
		this.panelMere = panelMere;
		this.module = m;
		this.setLayout(new BorderLayout());

		//Initialisation des composants
		this.panelPrincipal  = new JPanel();
		this.panelValidation = new JPanel();
		this.txtHSae         = new JTextField(2);
		this.txtHTut         = new JTextField(2);
		this.txtSomme        = new JTextField(4);
		this.checkValid      = new JCheckBox();
		this.checkValid.setSelected(this.module.isValide());

		this.panelPrincipal.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		
		//Rendre certain champ de sasie non modifiable
		this.txtSomme.setEditable(false);


		//Layouts
		this.panelPrincipal.setLayout(new BorderLayout());
		this.panelPrincipal.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		// Ajout des composants avec GridBagLayout
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.insets = new Insets(11, 0, 2, 0);
		this.panelPrincipal.add(new JLabel("h Sae"), gbc);
		gbc.gridx = 2;
		this.panelPrincipal.add(new JLabel("h Tut"), gbc);
		gbc.insets = new Insets(11, 10, 2, 0);
		gbc.gridx = 4;
		this.panelPrincipal.add(new JLabel("Σ"), gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.insets = new Insets(0, 0, 2, 0);
		this.panelPrincipal.add(this.txtHSae, gbc);
		gbc.gridx = 2;
		this.panelPrincipal.add(this.txtHTut, gbc);
		gbc.insets = new Insets(0, 10, 2, 0);
		gbc.gridx = 4;
		this.panelPrincipal.add(this.txtSomme, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.insets = new Insets(0, 0, 2, 0);
		this.panelPrincipal.add(this.txtHSae, gbc);
		gbc.gridx = 2;
		this.panelPrincipal.add(this.txtHTut, gbc);
		gbc.insets = new Insets(0, 10, 2, 0);
		gbc.gridx = 4;
		this.panelPrincipal.add(this.txtSomme, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		this.panelPrincipal.add(new JLabel("Total (eqtd) promo"), gbc);

		//Ajout des composants
		this.panelValidation.add(new JLabel("Validation"));
		this.panelValidation.add(this.checkValid);

		this.add( this.panelPrincipal,  BorderLayout.NORTH  );
		this.add( this.panelValidation, BorderLayout.CENTER );

		this.setVisible(true);

		//Ajout des listeners
		this.txtHSae.addActionListener(this);
		this.txtHTut.addActionListener(this);
		this.txtHSae.addFocusListener(this);
		this.txtHTut.addFocusListener(this);
		
	}


	public HashMap<String, Integer> getHeuresTot() {

		HashMap<String,Integer> map = new HashMap<>();

		try { map.put("SAE", Integer.parseInt(this.txtHSae.getText())); }
		catch(NumberFormatException e) { map.put("SAE", 0); }

		try { map.put("TUT", Integer.parseInt(this.txtHTut.getText())); }
		catch (NumberFormatException e) { map.put("TUT",0); }

		return map;
	}

	private boolean estChiffre(String texte) {
        // Vérifie chaque caractère dans la chaîne pour s'assurer qu'il s'agit d'un chiffre.
        for (char c : texte.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }


	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == this.txtHSae) {
			this.txtHSae.transferFocus();
		}


		else if(e.getSource() == this.txtHTut) {
			this.txtHTut.transferFocusBackward();
		}
	}



	@Override
	public void focusLost(FocusEvent e) {
		if(e.getSource() == this.txtHSae){
		try {
			//Vérification que la saisie de cette valeur n'a pas déjas été enregistrée dans somme
			if(this.estChiffre(this.txtHSae.getText()) == false)
					this.txtHSae.setText("0");
			if(this.txtHSae.getText().equals(Integer.toString(this.sommeHSae)) && this.txtHTut.getText().equals("")){
				System.out.println("Cette valeur a déjas été prise en compte");
			}
			else{
				if (this.txtHSae.getText().equals("") || Integer.parseInt(this.txtHSae.getText()) < 0)
					this.txtHSae.setText("0");
				this.sommeHSae = Integer.parseInt(this.txtHSae.getText()); 
			}
		}
		catch(NumberFormatException ex) {
			System.out.println("Erreur de saisie, veuillez entrer un nombre entier");
		}
		}

		if(e.getSource() == this.txtHTut){
		try {
			//Vérification que la saisie de cette valeur n'a pas déjas été enregistrée dans somme
			if(this.estChiffre(this.txtHTut.getText()) == false)
				this.txtHTut.setText("0");
			if(this.txtHTut.getText().equals(Integer.toString(this.sommeHTut)) && this.txtHSae.getText().equals("")){
				System.out.println("Cette valeur a déjas été prise en compte");
			}
			else{
				if (this.txtHTut.getText().equals("") || Integer.parseInt(this.txtHTut.getText()) < 0)
					this.txtHTut.setText("0");
				this.sommeHTut = Integer.parseInt(this.txtHTut.getText());
			}
		}
		catch(NumberFormatException ex) {
			System.out.println("Erreur de saisie, veuillez entrer un nombre entier");
		}
		}

		this.totalSomme = this.sommeHSae + this.sommeHTut;
		this.txtSomme.setText(Integer.toString(this.totalSomme));
		this.txtSomme.repaint();
		this.txtSomme.revalidate();
	}
	@Override
	public void focusGained(FocusEvent e) {}


	public boolean estValide() {
		return this.checkValid.isSelected();
	}

	public int getSommePN() {
		return this.sommeAction;
	}
}
