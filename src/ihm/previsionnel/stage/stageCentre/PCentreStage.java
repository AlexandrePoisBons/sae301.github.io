package ihm.previsionnel.stage.stageCentre;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import metier.Intervenant;
import metier.Module;
import metier.TypeHeure;
import metier.Heure;
import java.util.List;

import ihm.previsionnel.stage.stageCentre.repartition.PanelRepartitionStage;
import ihm.previsionnel.stage.stageCentre.progNatStage.ProgNatStage;
import ihm.previsionnel.stage.PanelStage;

public class PCentreStage extends JPanel {
	private PanelStage panelMere;
	private PanelRepartitionStage panelRepartitionStage;
	private ProgNatStage pProgNatStage;

	private Module module;

	public PCentreStage(PanelStage panelMere, Module m){
		this.panelMere = panelMere;
		this.module = m;

		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(0, 5, 0, 10));

		this.panelRepartitionStage = new PanelRepartitionStage(this,this.module);
		this.pProgNatStage         = new ProgNatStage(this, this.module);

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

		if ( this.module != null )
			this.setData();

	}

	public List<Heure> getHeures() { return this.panelRepartitionStage.getHeures(); }

	public HashMap<String, Integer> getData() {

		HashMap<String,Integer> map = new HashMap<>();
		HashMap<String,Integer> mapN = this.pProgNatStage.getHeuresTot();
		HashMap<String,Integer> mapR = this.panelRepartitionStage.getNbSemaines();

		for ( String type : mapN.keySet() )
			if ( mapN.get(type) != 0 && mapR.get(type) != 0 )
				map.put(type, mapR.get(type));

		return map;
	}

	public void setLabelErreur(String message) { this.pProgNatStage.setLabelErreur(message); }

	private void setData() { this.panelRepartitionStage.setHeures(this.module.getHeures()); }

	public void setErreur(String message) {
		this.panelMere.setErreur(message);
	}

	public List<Intervenant> getIntervenants() { return this.panelMere.getIntervenants(); }
	public List<TypeHeure>   getTypesHeures()  { return this.panelMere.getTypesHeures();  }

	public boolean estValide() { return this.pProgNatStage.estValide(); }

	public int getSommeAffecte() {
		return this.panelRepartitionStage.getSommeAffecte();
	}

	public int getSommePN() {
		return this.pProgNatStage.getSommePN();
	}


}
