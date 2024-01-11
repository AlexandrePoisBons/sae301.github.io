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
import metier.Heure;
import metier.Intervenant;
import metier.Module;
import metier.TypeHeure;

public class PRCentre extends JPanel {
	private PanelRessources  panelMere;
	private PanelRepartition panelRepartition;
	private ProgNat          pProgNat;
	private Module           module;

	public PRCentre(PanelRessources panelMere, Module m) {
		this.panelMere = panelMere;
		this.module = m;
		this.setLayout(new BorderLayout());

		this.panelRepartition = new PanelRepartition(this, this.module);
		this.pProgNat         = new ProgNat(this, this.module);

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

	public void setLabelErreur(String message) { this.pProgNat.setLabelErreur(message); }

	public List<Heure> getHeures() { return this.panelRepartition.getHeures(); }

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
		this.panelRepartition.setHeures(this.module.getHeures());
	}

	public HashMap<TypeHeure, HashMap<String,Integer>> getDataHeuresTypesHeures() {

		HashMap<TypeHeure, HashMap<String,Integer>> map = new HashMap<>();

		HashMap<String, Integer> mapNat = this.pProgNat.getDataHeuresTypesHeures();
		HashMap<String, HashMap<String,Integer>> mapRep = this.panelRepartition.getDataHeuresTypesHeures();


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


	public List<Intervenant> getIntervenants()  { return this.panelMere.getIntervenants(); }
	public List<TypeHeure>   getTypesHeures()   { return this.panelMere.getTypesHeures();  }
	public List<Heure>       getDeletedHeures() { return this.panelRepartition.getDeletedHeures(); }
	public HashMap<TypeHeure, HashMap<String,Integer>> getHeuresParTypesHeures(Module module) { return this.panelMere.getHeuresParTypesHeures(module); }

	public int getNbGpTd() { return this.panelMere.getNbGpTdMaj(); }
	public int getNbGpTp() { return this.panelMere.getNbGpTpMaj(); }

	public boolean estValide() { return this.pProgNat.estValide(); }
	public void fermerFrameFormulaire() { this.panelRepartition.fermerFrameFormulaire(); }


}
