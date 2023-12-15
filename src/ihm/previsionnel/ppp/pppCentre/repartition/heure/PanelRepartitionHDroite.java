package ihm.previsionnel.ppp.pppCentre.repartition.heure;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JLabel;


public class PanelRepartitionHDroite extends JPanel implements FocusListener{
	//4,3
	private JPanel panelN;
	private JPanel panelC;
	private ArrayList<JTextField> ensTxtFld;

	public PanelRepartitionHDroite(){
		this.panelN = new JPanel();
		this.panelC = new JPanel();
		this.ensTxtFld = new ArrayList<JTextField>();
		for(int i = 0; i < 18; i++){
            JTextField textField = new JTextField(3);
            this.ensTxtFld.add(textField); 
        }
		
		this.setLayout(new BorderLayout());
		this.panelN.setLayout(new GridBagLayout());
		GridBagConstraints gbcN = new GridBagConstraints();
		this.panelC.setLayout(new GridBagLayout());
		GridBagConstraints gbcC = new GridBagConstraints();

		gbcN.gridy = 2;
		gbcN.gridx = 0;
		this.panelC.add(new JLabel("Total promo (eqtd)", JLabel.RIGHT	), gbcN);
		gbcN.gridy = 3;
		gbcN.gridx = 0;
		this.panelC.add(new JLabel("Total affect (eqtd)", JLabel.RIGHT	), 		gbcN);


		gbcN.gridy = 0;
		gbcN.gridx = 4;
		this.panelN.add(new JLabel("      heures"), gbcN);
		gbcN.gridx = 5;
		this.panelN.add(new JLabel("     heures"), gbcN);

		// Ajout des composants avec GridBagLayout
        gbcC.gridx = 1;
        gbcC.gridy = 0;
        gbcC.insets = new Insets(0, 3, 5, 0);

		//Ajout des éléments
		this.panelC.add(new JLabel("CM"), gbcC);
		gbcC.gridx = 2;
		this.panelC.add(new JLabel("TD"), gbcC);
		gbcC.gridx = 3;
		this.panelC.add(new JLabel("TP"), gbcC);
		gbcC.insets = new Insets(0, 3, 5, 0);
		gbcC.gridx = 4;
		this.panelC.add(new JLabel("tutorées"), gbcC);
		gbcC.gridx = 5;
		this.panelC.add(new JLabel("ponctuelles"), gbcC);
		gbcC.gridx = 6;
		gbcC.insets = new Insets(0, 150, 5, 0);
		this.panelC.add(new JLabel("OE"), gbcC);
		gbcC.gridx = 1;
		gbcC.gridy = 1;
		gbcC.insets = new Insets(0, 3, 5, 0);

		this.panelC.add(this.ensTxtFld.get(0), gbcC);
		gbcC.gridx = 2;
		this.panelC.add(this.ensTxtFld.get(1), gbcC);
		gbcC.gridx = 3;
		this.panelC.add(this.ensTxtFld.get(2), gbcC);
		gbcC.insets = new Insets(0, 9, 8, 0);
		gbcC.gridx = 4;
		this.panelC.add(this.ensTxtFld.get(3), gbcC);
		//
		gbcC.gridx = 5;
		this.panelC.add(this.ensTxtFld.get(4), gbcC);
		//
		gbcC.gridx = 6;
		gbcC.insets = new Insets(0, 150, 5, 0);
		this.panelC.add(this.ensTxtFld.get(5), gbcC);
		gbcC.insets = new Insets(0, 3, 8, 0);
		gbcC.gridx = 1;
		gbcC.gridy = 2;

		this.panelC.add(this.ensTxtFld.get(6), gbcC);
		gbcC.gridx = 2;
		this.panelC.add(this.ensTxtFld.get(7), gbcC);
		gbcC.gridx = 3;
		this.panelC.add(this.ensTxtFld.get(8), gbcC);
		gbcC.insets = new Insets(0, 9, 8, 0);
		gbcC.gridx = 4;                                                                              
		this.panelC.add(this.ensTxtFld.get(9), gbcC);
		//
		gbcC.gridx = 5;                                                                              
		this.panelC.add(this.ensTxtFld.get(10), gbcC);
		//
		gbcC.gridx = 6;
		gbcC.insets = new Insets(0, 150, 5, 0);
		this.panelC.add(this.ensTxtFld.get(11), gbcC);
		gbcC.insets = new Insets(0, 3, 8, 0);
		gbcC.gridx = 1;
		gbcC.gridy = 3;

		this.panelC.add(this.ensTxtFld.get(12), gbcC);
		gbcC.gridx = 2;
		this.panelC.add(this.ensTxtFld.get(13), gbcC);
		gbcC.gridx = 3;
		this.panelC.add(this.ensTxtFld.get(14), gbcC);
		gbcC.insets = new Insets(0, 9, 8, 0);
		gbcC.gridx = 4;
		this.panelC.add(this.ensTxtFld.get(15), gbcC);
		//
		gbcC.gridx = 5;
		this.panelC.add(this.ensTxtFld.get(16), gbcC);
		//
		gbcC.insets = new Insets(0, 150, 5, 0);
		gbcC.gridx = 6;
		this.panelC.add(this.ensTxtFld.get(17), gbcC);

		this.add(this.panelN, BorderLayout.NORTH);
		this.add(this.panelC, BorderLayout.CENTER);
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'focusGained'");
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'focusLost'");
	}

	public HashMap<String, Integer> getRepartitionHeures() {
		HashMap<String, Integer> map = new HashMap<>();
	
		try { map.put("CM", Integer.parseInt(this.ensTxtFld.get(0).getText())); }
		catch (NumberFormatException e) { map.put("CM", 0); }

		try { map.put("TD", Integer.parseInt(this.ensTxtFld.get(1).getText())); }
		catch (NumberFormatException e) { map.put("TD", 0); }

		try { map.put("TP", Integer.parseInt(this.ensTxtFld.get(2).getText())); }
		catch (NumberFormatException e) { map.put("TP", 0); }

		try { map.put("TUT", Integer.parseInt(this.ensTxtFld.get(3).getText())); }
		catch (NumberFormatException e) { map.put("TUT", 0); }

		try { map.put("HP", Integer.parseInt(this.ensTxtFld.get(4).getText())); }
		catch (NumberFormatException e) { map.put("HP", 0); }

		return map;
	}


}
