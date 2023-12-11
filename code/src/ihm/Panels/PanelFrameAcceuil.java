package ihm.Panels;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import ihm.*;

public class PanelFrameAcceuil extends JPanel implements ActionListener {
	private FrameAccueil frame;
	private JButton btnParam;
	private JButton btnPrevi;
	private JButton btnInter;
	private JButton btnEtats;

	public PanelFrameAcceuil(FrameAccueil frame){
		this.frame = frame;
		//GridLayout
		this.setLayout(new GridLayout(4, 1, 0, 5));

		this.btnParam = new JButton("Paramètres");
		this.btnPrevi = new JButton("Prévisionnel");
		this.btnInter = new JButton("Intervenants");
		this.btnEtats = new JButton("Etats");

		this.add(this.btnParam);
		this.add(this.btnPrevi);
		this.add(this.btnInter);
		this.add(this.btnEtats);
	
	
		this.btnParam.addActionListener(this);
		this.btnInter.addActionListener(this);
		this.btnPrevi.addActionListener(this);
		this.btnEtats.addActionListener(this);
	}


	public void actionPerformed(ActionEvent e){
		if(e.getSource() == this.btnParam){
			this.frame.changerPanel(new PanelParam());
		}
		if(e.getSource() == this.btnPrevi){
			this.frame.changerPanel(new PanelPrevi(this.frame));
		}
		if(e.getSource() == this.btnInter){
			this.frame.changerPanel(new PanelInter());
		}
		if(e.getSource() == this.btnEtats){
			this.frame.changerPanel(new PanelEtats());
		}
	}
}
