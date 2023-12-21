package ihm.previsionnel.ppp.pppCentre;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.List;

import javax.swing.*;

import ihm.previsionnel.ppp.PanelPpp;
import ihm.previsionnel.ppp.pppCentre.progNat.ProgNat;
import ihm.previsionnel.ppp.pppCentre.repartition.PanelRepartition;
import metier.Heure;
import metier.Intervenant;
import metier.Module;
import metier.TypeHeure;

public class PanelPppCentre extends JPanel {
	private PanelPpp panelMere;
	private PanelRepartition panelRepartition;
	private ProgNat pProgNat;
	private Module module;

	public PanelPppCentre(PanelPpp panelMere, Module m) {
		this.panelMere = panelMere;
		this.module = m;

		this.setLayout(new BorderLayout());

		this.panelRepartition = new PanelRepartition(this, this.module);
		this.pProgNat = new ProgNat();

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


		if ( this.module != null )
			this.setData();

	}

	public List<Heure> getHeures() { return this.panelRepartition.getHeures(); }

	public HashMap<String, Integer> getData() {

		HashMap<String,Integer> map = new HashMap<>();
		HashMap<String,Integer> mapN = this.pProgNat.getHeuresTot();
		HashMap<String,Integer> mapR = this.panelRepartition.getNbSemaines();

		for ( String type : mapN.keySet() )
			if ( mapN.get(type) != 0 && mapR.get(type) != 0 )
				map.put(type, mapR.get(type));

		return map;
	}

	private void setData() { this.panelRepartition.setHeures(this.module.getHeures()); }

	public void setErreur(String message){
		this.panelMere.setErreur(message);
	}

	public List<Intervenant> getIntervenants() { return this.panelMere.getIntervenants(); }
	public List<TypeHeure>   getTypesHeures()  { return this.panelMere.getTypesHeures();  }

	public boolean isValide() {
		return this.pProgNat.isValide();
	}

	public int getSommeAffecte() {
		return this.panelRepartition.getSommeAffecte();
	}

	public int getSommePN() {
		return this.pProgNat.getSommePN();
	}
}
