package ihm.previsionnel.sae.saeCentre.progNatSae;
//import classes java
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.HashMap;
import java.awt.Color;
import java.awt.event.FocusListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;

public class ProgNatSae extends JPanel implements FocusListener, ActionListener {
	private JPanel panelPrincipal;
	private JPanel panelValidation;
	private JTextField txtHSae;
	private JTextField txtHTut;
	private JTextField txtSomme;
	private JCheckBox checkValid;
	private int sommeAction;

	public ProgNatSae() {
		this.setLayout(new BorderLayout());

		//Initialisation des composants
		this.sommeAction = 0;
		this.panelPrincipal = new JPanel();
		this.panelValidation = new JPanel();
		this.txtHSae = new JTextField(2);
		this.txtHTut = new JTextField(2);
		this.txtSomme = new JTextField(4);
		this.checkValid = new JCheckBox();
		this.panelPrincipal.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

		//Rendre certain champ de sasie non modifiable
		this.txtSomme.setEditable(false);



		//Layouts
		//this.setLayout(new BorderLayout());
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

		this.add(this.panelPrincipal, BorderLayout.NORTH);
		this.add(this.panelValidation, BorderLayout.CENTER);

		//implémentation des listener
		this.txtHSae.addFocusListener(this);
		this.txtHTut.addFocusListener(this);
		this.txtSomme.addFocusListener(this);

		this.txtHSae.addActionListener(this);
		this.txtHTut.addActionListener(this);
		this.txtSomme.addActionListener(this);

		this.setVisible(true);
	}


	public HashMap<String, Integer> getHeuresTot() {

		HashMap<String,Integer> map = new HashMap<>();

		try { map.put("SAE", Integer.parseInt(this.txtHSae.getText())); }
		catch(NumberFormatException e) { map.put("SAE", 0); }

		try { map.put("TUT", Integer.parseInt(this.txtHTut.getText())); }
		catch (NumberFormatException e) { map.put("TUT",0); }

		return map;
	}


	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == this.txtHSae) {
			//Vérification que l'utilsateur rentre un nombre entier supérieur ou égal à 0
			/*try {
				int val = Integer.parseInt(this.txtHSae.getText());
				if(val < 0) {
					this.txtHSae.setText("0");
				}
			}
			catch(NumberFormatException ex) {
				System.out.println("Erreur de saisie, veuillez entrer un nombre entier supérieur ou égal à 0");
			}
			//addition des heures saisies dans les champs
			try {
				//Vérification que la saisie de cette valeur n'a pas déjas été enregistrée dans somme
				if(this.txtHSae.getText().equals(Integer.toString(this.sommeAction)) && this.txtHTut.getText().equals("")) {
					System.out.println("Cette valeur a déjas été prise en compte");
				}
				else
					this.sommeAction += Integer.parseInt(this.txtHSae.getText());

			}
			catch(NumberFormatException ex) {
				System.out.println("Erreur de saisie, veuillez entrer un nombre entier");
			}*/
			//pour que le focus passe au champ suivant quand  l'utilisteur clique sur "entrée"
			this.txtHSae.transferFocus();
		}


		else if(e.getSource() == this.txtHTut) {
			//Vérification que l'utilsateur rentre un nombre entier supérieur ou égal à 0
			/*try {
				int val = Integer.parseInt(this.txtHTut.getText());
				if(val < 0) {
					this.txtHTut.setText("0");
				}
			}
			catch(NumberFormatException ex) {
				System.out.println("Erreur de saisie, veuillez entrer un nombre entier supérieur ou égal à 0");
			}
			//addition des heures saisies dans les champs
			try {
				//Vérification que la saisie de cette valeur n'a pas déjas été enregistrée dans somme
				if(this.txtHTut.getText().equals(Integer.toString(this.sommeAction)) && this.txtHSae.getText().equals("")) {
					System.out.println("Cette valeur a déjas été prise en compte");
				}
				else
					this.sommeAction += Integer.parseInt(this.txtHTut.getText());

			}
			catch(NumberFormatException ex) {
				System.out.println("Erreur de saisie, veuillez entrer un nombre entier");
			}*/
			//pour que le focus passe au champ d'avant quand  l'utilisteur clique sur "entrée"
			this.txtHTut.transferFocusBackward();
		}

		this.txtSomme.setText(Integer.toString(this.sommeAction));

		this.txtSomme.repaint();
		this.txtSomme.revalidate();
	}



	@Override
	public void focusLost(FocusEvent e) {
		//Addition des heures saisies dans les champs
		int somme = 0;
		
		try {
			//Vérification que la saisie de cette valeur n'a pas déjas été enregistrée dans somme
			if(this.txtHSae.getText().equals(Integer.toString(somme)) && this.txtHTut.getText().equals("")){
				System.out.println("Cette valeur a déjas été prise en compte");
			}
			else
				somme += Integer.parseInt(this.txtHSae.getText()); 
		}
		catch(NumberFormatException ex) {
			System.out.println("Erreur de saisie, veuillez entrer un nombre entier");
		}
		try {
			//Vérification que la saisie de cette valeur n'a pas déjas été enregistrée dans somme
			if(this.txtHTut.getText().equals(Integer.toString(somme)) && this.txtHSae.getText().equals("")){
				System.out.println("Cette valeur a déjas été prise en compte");
			}
			else
				somme += Integer.parseInt(this.txtHTut.getText());
		}
		catch(NumberFormatException ex) {
			System.out.println("Erreur de saisie, veuillez entrer un nombre entier");
		}
		this.txtSomme.setText(Integer.toString(somme));
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