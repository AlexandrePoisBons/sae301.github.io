package ihm.previsionnel.ressources.ressourcesCentre.repartition.heure;

import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;

import javax.swing.JLabel;


public class PanelRepartitionHDroite extends JPanel implements FocusListener, ActionListener{
	private JPanel panelN;
	private JPanel panelC;
	private ArrayList<JTextField> ensTxtFld;

	private int sommeActionTD, sommeActionTP, sommeActionCM;

	public PanelRepartitionHDroite(){
		this.sommeActionTD = this.sommeActionTP = this.sommeActionCM = 0;
		this.panelN = new JPanel();
		this.panelC = new JPanel();
		this.ensTxtFld = new ArrayList<JTextField>();
		for(int i = 0; i < 15; i++){
			JTextField textField = new JTextField(3);
			if(i != 3){textField.setEditable(false);}
			this.ensTxtFld.add(textField); 
		}
		
		this.setLayout(new BorderLayout());
		this.panelN.setLayout(new GridBagLayout());
		GridBagConstraints gbcN = new GridBagConstraints();
		this.panelC.setLayout(new GridBagLayout());
		GridBagConstraints gbcC = new GridBagConstraints();

		gbcN.gridy = 0;
		gbcN.gridx = 4;
		this.panelN.add(new JLabel("                    heures"), gbcN);

		// Ajout des composants avec GridBagLayout
		gbcC.gridx = 0;
		gbcC.gridy = 0;
		gbcC.insets = new Insets(0, 3, 5, 0);

		//Ajout des éléments
		this.panelC.add(new JLabel("CM"), gbcC);
		gbcC.gridx = 1;
		this.panelC.add(new JLabel("TD"), gbcC);
		gbcC.gridx = 2;
		this.panelC.add(new JLabel("TP"), gbcC);
		gbcC.insets = new Insets(0, 8, 5, 0);
		gbcC.gridx = 3;
		this.panelC.add(new JLabel("ponctuelles"), gbcC);
		gbcC.gridx = 4;
		this.panelC.add(new JLabel("OE"), gbcC);
		gbcC.gridx = 0;
		gbcC.gridy = 1;
		gbcC.insets = new Insets(0, 3, 10, 0);

		this.panelC.add(this.ensTxtFld.get(0), gbcC);
		gbcC.gridx = 1;
		this.panelC.add(this.ensTxtFld.get(1), gbcC);
		gbcC.gridx = 2;
		this.panelC.add(this.ensTxtFld.get(2), gbcC);
		gbcC.insets = new Insets(0, 9, 8, 0);
		gbcC.gridx = 3;
		this.panelC.add(this.ensTxtFld.get(3), gbcC);
		gbcC.gridx = 4;
		this.panelC.add(this.ensTxtFld.get(4), gbcC);
		gbcC.insets = new Insets(0, 3, 8, 0);
		gbcC.gridx = 0;
		gbcC.gridy = 2;

		this.panelC.add(this.ensTxtFld.get(5), gbcC);
		gbcC.gridx = 1;
		this.panelC.add(this.ensTxtFld.get(6), gbcC);
		gbcC.gridx = 2;
		this.panelC.add(this.ensTxtFld.get(7), gbcC);
		gbcC.insets = new Insets(0, 9, 8, 0);
		gbcC.gridx = 3;                                                                              
		this.panelC.add(this.ensTxtFld.get(8), gbcC);
		gbcC.gridx = 4;
		this.panelC.add(this.ensTxtFld.get(9), gbcC);
		gbcC.insets = new Insets(0, 3, 8, 0);
		gbcC.gridx = 0;
		gbcC.gridy = 3;

		this.panelC.add(this.ensTxtFld.get(10), gbcC);
		gbcC.gridx = 1;
		this.panelC.add(this.ensTxtFld.get(11), gbcC);
		gbcC.gridx = 2;
		this.panelC.add(this.ensTxtFld.get(12), gbcC);
		gbcC.insets = new Insets(0, 9, 8, 0);
		gbcC.gridx = 3;
		this.panelC.add(this.ensTxtFld.get(13), gbcC);
		gbcC.gridx = 4;
		this.panelC.add(this.ensTxtFld.get(14), gbcC);

		this.add(this.panelN, BorderLayout.NORTH);
		this.add(this.panelC, BorderLayout.CENTER);

		this.ensTxtFld.get(3).addActionListener(this);
		this.ensTxtFld.get(3).addFocusListener(this);
	}



	public void setSommeTD(int somme) {
		this.ensTxtFld.get(0).setText("" + somme);
		this.sommeActionTD = somme;
		this.repaint();
		this.revalidate();
	}

	public void setSommeTP(int somme) {
		this.ensTxtFld.get(1).setText("" + somme);
		this.sommeActionTP = somme;
		this.repaint();
		this.revalidate();
	}

	public void setSommeCM(int somme) {
		this.ensTxtFld.get(2).setText("" + somme);
		this.sommeActionCM = somme;
		this.repaint();
		this.revalidate();
	}

	

	public void setSommeTotal(){
		int somme = this.sommeActionTD + this.sommeActionTP + this.sommeActionCM;
		this.ensTxtFld.get(4).setText("" + somme);
		this.repaint();
		this.revalidate();
	}

	//Ajout de la valeur de jtxtfld3 dans jtxtfld4
	public void setSommeTotal(int val){
		int somme = this.sommeActionTD + this.sommeActionTP + this.sommeActionCM+val;
		this.ensTxtFld.get(4).setText("" + somme);
		this.repaint();
		this.revalidate();
	}

	public void setHeureAffecte(int hSae, int hTut) {
		this.ensTxtFld.get(2).setText("" + hSae);
		this.ensTxtFld.get(3).setText("" + hTut);
		this.repaint();
		this.revalidate();
	}

	@Override
	public void actionPerformed(java.awt.event.ActionEvent e) {
		if(e.getSource() == this.ensTxtFld.get(3)){
			this.setSommeTotal(Integer.parseInt(this.ensTxtFld.get(3).getText()));
			this.repaint();
			this.revalidate();
		}
	}



	@Override
	public void focusLost(java.awt.event.FocusEvent e) {
		//vérification que la saisie est bien un positif
		try {
			if(Integer.parseInt(this.ensTxtFld.get(3).getText()) > 0){
				this.setSommeTotal(Integer.parseInt(this.ensTxtFld.get(3).getText()));
				this.repaint();
				this.revalidate();
			}
			else{
				this.ensTxtFld.get(3).setText("0");
				this.setSommeTotal(Integer.parseInt(this.ensTxtFld.get(3).getText()));
				this.repaint();
				this.revalidate();
			}
		} catch (Exception er) {
			System.err.println("Erreur de saisie, veuillez entrer un nombre entier");
		}
	}

	@Override
	public void focusGained(java.awt.event.FocusEvent e) {}
}
