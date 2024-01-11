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


public class PCentreSae extends JPanel {
	private PanelSae            panelMere;
	private PanelRepartitionSae panelRepartitionSae;
	private ProgNatSae          panelProgNatSae;
	private Module              module;

	public PCentreSae( PanelSae panelMere, Module m ) {
		this.panelMere = panelMere;
		this.module = m;

		this.setLayout(new BorderLayout());

		this.panelRepartitionSae = new PanelRepartitionSae(this, this.module);
		this.panelProgNatSae     = new ProgNatSae(this, this.module);

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

	public void setLabelErreur(String message) { this.panelProgNatSae.setLabelErreur(message); }


	private void setData() { this.panelRepartitionSae.setHeures(this.module.getHeures()); }

	public void setErreur(String message){
		this.panelMere.setErreur(message);
	}

	public HashMap<TypeHeure, HashMap<String,Integer>> getDataHeuresTypesHeures() {

		HashMap<TypeHeure, HashMap<String,Integer>> map = new HashMap<>();

		HashMap<String, Integer> mapNat = this.panelProgNatSae.getDataHeuresTypesHeures();
		HashMap<String, HashMap<String,Integer>> mapRep = this.panelRepartitionSae.getDataHeuresTypesHeures();


		HashMap<String, Integer> temp;
		for (String typeHeure : mapRep.keySet()) {
			TypeHeure typeHeureTemp = this.panelMere.getTypeHeureParNom(typeHeure);

			temp = new HashMap<>();

			for (String nom : mapRep.get(typeHeure).keySet()) {
				// if ( typeHeure.equals("TD") && nom.equals("pn") )
				temp.put(nom,mapRep.get(typeHeure).get(nom));
				temp.put("pn", mapNat.get(nom));
			}

			if ( !temp.containsKey("pn") )
				temp.put("pn",0);
			else {
				temp.remove("pn");
				temp.put("pn",mapNat.get(typeHeure));
			}
			if ( !temp.containsKey("nb_semaines") )
				temp.put("nb_semaines",0);
			if ( !temp.containsKey("nb_heures") )
				temp.put("nb_heures",0);


			map.put(typeHeureTemp, temp);

		}

		List<TypeHeure> typesHeures = this.panelMere.getTypesHeures();

		for (TypeHeure typeHeure : typesHeures) {
			temp = new HashMap<>();
			if ( !map.containsKey(typeHeure) ) {

				temp.put("pn",0);
				temp.put("nb_semaines",0);
				temp.put("nb_heures",0);

				map.put(typeHeure, temp);
			}
		}


		return map;

		// combiner gros les 2 et hop
	}

	public List<Intervenant> getIntervenants() { return this.panelMere.getIntervenants(); }
	public List<TypeHeure>   getTypesHeures()  { return this.panelMere.getTypesHeures();  }
	public List<Heure> getDeletedHeures() { return this.panelRepartitionSae.getDeletedHeures(); }
	public HashMap<TypeHeure, HashMap<String,Integer>> getHeuresParTypesHeures(Module module) { return this.panelMere.getHeuresParTypesHeures(module); }

	public boolean estValide() { return this.panelProgNatSae.estValide(); }

	public int getSommeAffecte() {
		return this.panelRepartitionSae.getSommeAffecte();
	}

	public int getSommePN() {
		return this.panelProgNatSae.getSommePN();
	}

	public void fermerFrameFormulaire() { this.panelRepartitionSae.fermerFrameFormulaire(); }

}
