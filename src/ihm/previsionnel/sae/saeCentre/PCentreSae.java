package ihm.previsionnel.sae.saeCentre;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

import ihm.previsionnel.sae.PanelSae;
import ihm.previsionnel.sae.saeCentre.progNatSae.ProgNatSae;
import ihm.previsionnel.sae.saeCentre.repartition.PanelRepartitionSae;


public class PCentreSae extends JPanel{
		private PanelSae 	panelMere;
		private PanelRepartitionSae 	panelRepartitionSae;
		private ProgNatSae 			pProgNat;

		public PCentreSae(PanelSae panelMere){
		this.panelMere = panelMere;
		this.setLayout(new BorderLayout());

		this.panelRepartitionSae 	= new PanelRepartitionSae(this);
		this.pProgNat 			= new ProgNatSae();

		JPanel panelN = new JPanel();
		panelN.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		JPanel panelC = new JPanel();
		panelC.setLayout(new BorderLayout());

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 0, 200);
		panelN.add(new JLabel("PN local (nb h tot/etd)"), gbc);
		gbc.gridx = 2;
		panelN.add(new JLabel("Répartition"), gbc);

		panelC.add(this.panelRepartitionSae, BorderLayout.CENTER);
		panelC.add(this.pProgNat, BorderLayout.WEST);

		this.add(panelC, BorderLayout.CENTER);
		this.add(panelN, BorderLayout.NORTH);
	}

	public Object[] getHeuresProgNat(){
		return this.pProgNat.getHeuresTot();
	}

}