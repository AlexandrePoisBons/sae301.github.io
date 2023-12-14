package ihm.previsionnel.stage.stageCentre;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import ihm.previsionnel.stage.stageCentre.repartition.PanelRepartitionStage;
import ihm.previsionnel.stage.stageCentre.progNatStage.ProgNatStage;
import ihm.previsionnel.stage.PanelStage;

public class PCentreStage extends JPanel {
	private PanelStage panelMere;
	private PanelRepartitionStage panelRepartitionStage;
	private ProgNatStage pProgNatStage;

	public PCentreStage(PanelStage panelMere){
		this.panelMere = panelMere;
		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(0, 5, 0, 10));

		this.panelRepartitionStage 	= new PanelRepartitionStage(this);
		this.pProgNatStage 			= new ProgNatStage();

		JPanel panelN = new JPanel();
		panelN.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		JPanel panelC = new JPanel();
		panelC.setLayout(new BorderLayout());

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 0, 300);
		panelN.add(new JLabel("PN local (nb h tot/etd)"), gbc);
		gbc.gridx = 2;
		panelN.add(new JLabel("Répartition"), gbc);

		panelC.add(this.panelRepartitionStage, BorderLayout.CENTER);
		panelC.add(this.pProgNatStage, BorderLayout.WEST);

		this.add(panelC, BorderLayout.CENTER);
		this.add(panelN, BorderLayout.NORTH);
	}
	/* A DECOMENTER 
	public Object[] getHeuresProgNat() {
		return this.pProgNatStage.getHeuresTot();
	}*/
}
