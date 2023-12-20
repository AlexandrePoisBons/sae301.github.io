package ihm.previsionnel.sae.saeCentre;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.List;

import javax.swing.*;

import ihm.previsionnel.sae.PanelSae;
import ihm.previsionnel.sae.saeCentre.progNatSae.ProgNatSae;
import ihm.previsionnel.sae.saeCentre.repartition.PanelRepartitionSae;
import metier.Heure;
import metier.Intervenant;
import metier.Module;
import metier.TypeHeure;


public class PCentreSae extends JPanel{
	private PanelSae            panelMere;
	private PanelRepartitionSae panelRepartitionSae;
	private ProgNatSae          panelProgNatSae;
	private Module              module;

	public PCentreSae(PanelSae panelMere, Module m) {
		this.panelMere = panelMere;
		this.module = m;
		this.setLayout(new BorderLayout());

		this.panelRepartitionSae 	= new PanelRepartitionSae(this, this.module);
		this.panelProgNatSae 		= new ProgNatSae();

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
		panelC.add(this.panelProgNatSae, BorderLayout.WEST);

		this.add(panelC, BorderLayout.CENTER);
		this.add(panelN, BorderLayout.NORTH);

		if ( this.module != null )
			this.setData();
	}

	public List<Heure> getHeures() { return this.panelRepartitionSae.getHeures(); }

	public HashMap<String, Integer> getData() {

		HashMap<String,Integer> map = new HashMap<>();
		HashMap<String,Integer> mapN = this.panelProgNatSae.getHeuresTot();
		HashMap<String,Integer> mapR = this.panelRepartitionSae.getNbSemaines();

		for ( String type : mapN.keySet() )
			if ( mapN.get(type) != 0 && mapR.get(type) != 0 )
				map.put(type, mapR.get(type));

		return map;
	}

	public void setData() { this.panelRepartitionSae.setHeures(this.module.getHeures()); }

	public void setErreur(String message){
		this.panelMere.setErreur(message);
	}

	public List<Intervenant> getIntervenants() { return this.panelMere.getIntervenants(); }
	public List<TypeHeure>   getTypesHeures()  { return this.panelMere.getTypesHeures();  }

	public boolean estValide() {
		return this.panelProgNatSae.estValide();
	}

	public int getSommeAffecte() {
		return this.panelRepartitionSae.getSommeAffecte();
	}

	public int getSommePN() {
		return this.panelProgNatSae.getSommePN();
	}

}
