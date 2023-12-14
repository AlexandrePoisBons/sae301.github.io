package ihm.previsionnel.sae.saeCentre;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import ihm.previsionnel.sae.PanelSae;
import ihm.previsionnel.sae.saeCentre.progNatSae.ProgNatSae;
import ihm.previsionnel.sae.saeCentre.repartition.PanelRepartitionSae;


public class PCentreSae extends JPanel{
		private PanelSae 			panelMere;
		private PanelRepartitionSae panelRepartitionSae;
		private ProgNatSae 			pProgNatSae;

		public PCentreSae(PanelSae panelMere){
		this.panelMere = panelMere;
		this.setLayout(new BorderLayout());

		this.panelRepartitionSae 	= new PanelRepartitionSae(this);
		this.pProgNatSae 			= new ProgNatSae();

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

		panelC.add(this.panelRepartitionSae, BorderLayout.CENTER);
		panelC.add(this.pProgNatSae, BorderLayout.WEST);

		this.add(panelC, BorderLayout.CENTER);
		this.add(panelN, BorderLayout.NORTH);
	}

	public HashMap<String, Integer> getData() {

		HashMap<String,Integer> map = new HashMap<>();
		HashMap<String,Integer> mapN = this.pProgNatSae.getHeuresTot();
		HashMap<String,Integer> mapR = this.panelRepartitionSae.getRepartitionHeures();

		for ( String type : mapN.keySet() )
			if ( mapN.get(type) != 0 && mapR.get(type) != 0 )
				map.put(type, mapR.get(type));

		return map;
	}

	// public String getSemestre() { return this.pProgNatSae.getSemestre(); }
	// public String getNbEtd() { return this.pCentreSae.getNbEtd(); }
	// public String getNbGpTd() { return this.pCentreSae.getNbGpTd(); }
	// public String getNbGpTp() { return this.pCentreSae.getNbGpTp(); }

}
