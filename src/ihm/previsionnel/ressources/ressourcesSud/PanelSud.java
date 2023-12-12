package ihm.previsionnel.ressources.ressourcesSud;
//Import des classes externes au package
import ihm.previsionnel.*;
import ihm.acceuil.*;

//Import des classes Java
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;

public class PanelSud extends JPanel implements ActionListener{
	private FrameAccueil frame;
	private PanelPrevi panelPrevi;
	private JButton boutonEnregistrer;
	private JButton boutonAnnuler;
	private JPanel panelWest;

	public PanelSud(FrameAccueil frame, PanelPrevi panelPrevi) {
		this.frame = frame;
		this.panelPrevi = panelPrevi;
		this.setLayout(new BorderLayout());

		this.panelWest = new JPanel();
		this.boutonEnregistrer = new JButton("Enregistrer");
		this.boutonAnnuler = new JButton("Annuler");
		this.panelWest.add(this.boutonEnregistrer);
		this.panelWest.add(this.boutonAnnuler);

		this.add(this.panelWest, BorderLayout.WEST);

		this.boutonEnregistrer.addActionListener(this);
		this.boutonAnnuler.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.boutonEnregistrer) {
			// à faire avec la bado
		}
		else if(e.getSource() == this.boutonAnnuler) {
			this.frame.changerPanel(this.panelPrevi);
		}
	}
}