package ihm.previsionnel.ppp.pppCentre;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;

import javax.swing.*;

import ihm.previsionnel.ppp.PanelPpp;
import ihm.previsionnel.ppp.pppCentre.progNat.ProgNat;
import ihm.previsionnel.ppp.pppCentre.repartition.PanelRepartition;

public class PanelPppCentre extends JPanel{
	private PanelPpp 	panelMere;
	private PanelRepartition 	panelRepartition;
	private ProgNat 			pProgNat;

	public PanelPppCentre(PanelPpp panelMere){
		this.panelMere = panelMere;
		this.setLayout(new BorderLayout());

		this.panelRepartition 	= new PanelRepartition(this);
		this.pProgNat 			= new ProgNat();

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

		panelC.add(this.panelRepartition, BorderLayout.CENTER);
		panelC.add(this.pProgNat, BorderLayout.WEST);

		this.add(panelC, BorderLayout.CENTER);
		this.add(panelN, BorderLayout.NORTH);
	}

	public HashMap<String,Integer> getData() {
		HashMap<String,Integer> map = new HashMap<>();
		HashMap<String,Integer> mapH = this.pProgNat.getHeuresTot();
		HashMap<String,Integer> mapS = this.panelRepartition.getNbSemaines();

		for ( String type : mapH.keySet() )
			if ( mapH.get(type) != 0 || mapS.get(type) != 0 )
				map.put(type, mapS.get(type));

		return map;
	}

}
