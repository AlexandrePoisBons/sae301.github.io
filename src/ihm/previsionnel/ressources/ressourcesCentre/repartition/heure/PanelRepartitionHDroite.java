package ihm.previsionnel.ressources.ressourcesCentre.repartition.heure;

import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;


public class PanelRepartitionHDroite extends JPanel{
	//4,3

	private ArrayList<JTextField> ensTxtFld;

	public PanelRepartitionHDroite(){
		this.ensTxtFld = new ArrayList<JTextField>();
		for(int i = 0; i < 15; i++){
            JTextField textField = new JTextField(3);
            this.ensTxtFld.add(textField); 
        }
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		// Ajout des composants avec GridBagLayout
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 3, 5, 0);

		//Ajout des éléments
		this.add(new JLabel("CM"), gbc);
		gbc.gridx = 1;
		this.add(new JLabel("TD"), gbc);
		gbc.gridx = 2;
		this.add(new JLabel("TP"), gbc);
		gbc.insets = new Insets(0, 7, 5, 0);
		gbc.gridx = 3;
		this.add(new JLabel("HP"), gbc);
		gbc.gridx = 4;
		this.add(new JLabel("OE"), gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(0, 3, 5, 0);

		this.add(this.ensTxtFld.get(0), gbc);
		gbc.gridx = 1;
		this.add(this.ensTxtFld.get(1), gbc);
		gbc.gridx = 2;
		this.add(this.ensTxtFld.get(2), gbc);
		gbc.insets = new Insets(0, 7, 5, 0);
		gbc.gridx = 3;
		this.add(this.ensTxtFld.get(3), gbc);
		gbc.gridx = 4;
		this.add(this.ensTxtFld.get(4), gbc);
		gbc.insets = new Insets(0, 3, 5, 0);
		gbc.gridx = 0;
		gbc.gridy = 2;

		this.add(this.ensTxtFld.get(5), gbc);
		gbc.gridx = 1;
		this.add(this.ensTxtFld.get(6), gbc);
		gbc.gridx = 2;
		this.add(this.ensTxtFld.get(7), gbc);
		gbc.insets = new Insets(0, 7, 5, 0);
		gbc.gridx = 3;
		this.add(this.ensTxtFld.get(8), gbc);
		gbc.gridx = 4;
		this.add(this.ensTxtFld.get(9), gbc);
		gbc.insets = new Insets(0, 3, 5, 0);
		gbc.gridx = 0;
		gbc.gridy = 3;

		this.add(this.ensTxtFld.get(10), gbc);
		gbc.gridx = 1;
		this.add(this.ensTxtFld.get(11), gbc);
		gbc.gridx = 2;
		this.add(this.ensTxtFld.get(12), gbc);
		gbc.insets = new Insets(0, 7, 5, 0);
		gbc.gridx = 3;
		this.add(this.ensTxtFld.get(13), gbc);
		gbc.gridx = 4;
		this.add(this.ensTxtFld.get(14), gbc);


	}
}
