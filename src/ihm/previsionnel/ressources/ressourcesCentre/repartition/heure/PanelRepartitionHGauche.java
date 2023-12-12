package ihm.previsionnel.ressources.ressourcesCentre.repartition.heure;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.*;

public class PanelRepartitionHGauche extends JPanel{
	private PanelRepartitionHeure panelMere;
	private JPanel panelHeure;
	private JPanel panelTotal;
	private JTextField txtNbSemCM;
	private JTextField txtNbHCM;
	private JTextField txtNbSemTD;
	private JTextField txtNbHTD;
	private JTextField txtNbSemTP;
	private JTextField txtNbHTP;

	public PanelRepartitionHGauche(PanelRepartitionHeure panelMere){
		this.panelMere = panelMere;

		this.setLayout(new BorderLayout());

		JPanel panelN 	= new JPanel();
		JPanel panelC 	= new JPanel();
		panelN.setLayout(new GridLayout(1, 3));
		panelC.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints(); 
		
		this.panelHeure 	= new JPanel();
		this.panelTotal		= new JPanel();
		this.panelTotal.setLayout(new GridLayout(2, 1));
		this.txtNbSemCM 	= new JTextField("A completer");
		this.txtNbHCM	 	= new JTextField("A completer");
		this.txtNbSemTD 	= new JTextField("A completer");
		this.txtNbHTD 		= new JTextField("A completer");
		this.txtNbSemTP 	= new JTextField("A completer");
		this.txtNbHTP		= new JTextField("A completer");
		
		panelN.add(new JLabel("CM"));
		panelN.add(new JLabel("TD"));
		panelN.add(new JLabel("TP"));
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 3, 5, 0);
		panelC.add(new JLabel("nb Sem"), gbc);
		gbc.gridx = 1;
		panelC.add(new JLabel("nb h/sem"), gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelC.add(this.txtNbSemCM, gbc);
		gbc.gridx = 1;
		panelC.add(this.txtNbHCM, gbc);
		gbc.insets = new Insets(0, 0, 5, 8);
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 5, 5, 0);
		panelC.add(new JLabel("nb Sem"));
		gbc.gridx = 3;
		panelC.add(new JLabel("nb h/Sem"));
		gbc.gridx = 2;
		gbc.gridy = 1;
		panelC.add(this.txtNbSemTD, gbc);
		gbc.gridx = 3;
		panelC.add(this.txtNbHTD, gbc);
		gbc.insets = new Insets(0, 0, 5, 8);
		gbc.gridx = 4;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 5, 5, 0);
		panelC.add(new JLabel("nb Sem"), gbc);
		gbc.gridx = 5;
		panelC.add(new JLabel("nb h/Sem"), gbc);
		gbc.gridx = 4;
		gbc.gridy = 1;
		panelC.add(this.txtNbSemTP, gbc);
		gbc.gridx = 5;
		panelC.add(this.txtNbHTP, gbc);
		gbc.insets = new Insets(0, 0, 5, 8);

		this.panelTotal.add(new JLabel("total promo (eqtd)"));
		this.panelTotal.add(new JLabel("total affecte (eqtd)"));

		this.panelHeure.add(panelN, BorderLayout.NORTH);
		this.panelHeure.add(panelC, BorderLayout.CENTER);
		this.add(this.panelHeure, BorderLayout.NORTH);
		this.add(this.panelTotal, BorderLayout.CENTER);
	}
}
