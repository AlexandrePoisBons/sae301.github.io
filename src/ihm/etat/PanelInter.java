package ihm.etat;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PanelInter extends JPanel implements ActionListener {
	private PanelEtats panelMere;
	private JPanel     panel;
	private JButton    btnGenerer;


	public PanelInter(PanelEtats panelMere) {
		this.panelMere = panelMere;
		this.setLayout(new BorderLayout());

		this.btnGenerer = new JButton("Genérer html");
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btnGenerer) {
			this.generer();
		}
	}

	public void generer() {

	}
}