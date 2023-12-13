package ihm.previsionnel.ressources.ressourcesCentre;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

import ihm.previsionnel.ressources.PanelRessources;
import ihm.previsionnel.ressources.ressourcesCentre.progNat.ProgNat;
import ihm.previsionnel.ressources.ressourcesCentre.repartition.PanelRepartition;

public class PRCentre extends JPanel{
	private PanelRessources 	panelMere;
	private PanelRepartition 	panelRepartition;
	private ProgNat 			pProgNat;

	public PRCentre(PanelRessources panelMere){
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
	
}
