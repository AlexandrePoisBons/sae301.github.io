package ihm.previsionnel.ressources.ressourcesCentre;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.List;

import javax.swing.*;

import ihm.previsionnel.ressources.PanelRessources;
import ihm.previsionnel.ressources.ressourcesCentre.progNat.ProgNat;
import ihm.previsionnel.ressources.ressourcesCentre.repartition.PanelRepartition;
import metier.Intervenant;
import metier.Module;

public class PRCentre extends JPanel {
	private PanelRessources  panelMere;
	private PanelRepartition panelRepartition;
	private ProgNat          pProgNat;
	private Module           module;

	public PRCentre(PanelRessources panelMere, Module m) {
		this.panelMere = panelMere;
		this.module = m;
		this.setLayout(new BorderLayout());

		this.panelRepartition = new PanelRepartition(this);
		this.pProgNat         = new ProgNat();

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


		if ( this.module != null ){
			this.setData();
			System.out.println("!= null");
		} else {
			System.out.println("== null");
		}

	}

	public HashMap<String,Integer> getData() {
		HashMap<String,Integer> map = new HashMap<>();
		HashMap<String,Integer> mapH = this.pProgNat.getHeuresTot();
		HashMap<String,Integer> mapS = this.panelRepartition.getNbSemaines();

		for ( String type : mapH.keySet() )
			if ( mapH.get(type) != 0 && mapS.get(type) != 0 )
				map.put(type, mapS.get(type));

		return map;
	}

	public void setData() {
		System.out.println("coucou");
		this.panelRepartition.setHeures(this.module.getHeures());
	}

	public List<Intervenant> getIntervenants() { return this.panelMere.getIntervenants(); }


}
