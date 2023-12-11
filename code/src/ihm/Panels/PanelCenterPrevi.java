package ihm.Panels;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class PanelCenterPrevi extends JPanel {
	private JTextField textFieldGpTD;
	private JTextField textFieldGpTP;
	private JTextField textFieldNbEt;
	private JTextField textFieldNbSemaine;
	

	public PanelCenterPrevi() {
        setSize(400, 300);

        JTabbedPane modules = new JTabbedPane();

        // Créer des panneaux pour chaque onglet
        JPanel semestre1 = new JPanel();
        semestre1.add(new JLabel("Contenu de l'onglet 1"));
        
        JPanel semestre2 = new JPanel();
        semestre2.add(new JLabel("Contenu de l'onglet 2"));

        JPanel semestre3 = new JPanel();
        semestre3.add(new JLabel("Contenu de l'onglet 3"));

		JPanel semestre4 = new JPanel();
		semestre4.add(new JLabel("Contenu de l'onglet 4"));

		JPanel semestre5 = new JPanel();
		semestre5.add(new JLabel("Contenu de l'onglet 5"));

		JPanel semestre6 = new JPanel();
		semestre6.add(new JLabel("Contenu de l'onglet 6"));

        // Ajouter les onglets au JTabbedPane
        modules.addTab("Onglet 1", semestre1);
        modules.addTab("Onglet 2", semestre2);
        modules.addTab("Onglet 3", semestre3);
		modules.addTab("Onglet 4", semestre4);
		modules.addTab("Onglet 5", semestre5);
		modules.addTab("Onglet 6", semestre6);

        // Ajouter le JTabbedPane à la fenêtre
        add(modules);

        setVisible(true);
	}

	public JPanel remplissagPanel(JPanel global){
		//Layout
		global.setLayout(new BorderLayout());

		//JPanels
		JPanel panelNord = new JPanel();
		JPanel panelCentre = new JPanel();

		panelNord.add(new JLabel("nb gp TD"));
		textFieldGpTD = new JTextField(2);
		panelNord.add(textFieldGpTD);

		panelNord.add(new JLabel("nb gp TP"));
		textFieldGpTP = new JTextField(2);
		panelNord.add(textFieldGpTP);

		panelNord.add(new JLabel("nb Etd"));
		textFieldNbEt = new JTextField(2);
		panelNord.add(textFieldNbEt);

		panelNord.add(new JLabel("nb semaines"));
		textFieldNbSemaine = new JTextField(2);
		panelNord.add(textFieldNbSemaine);

		global.add(panelNord, BorderLayout.NORTH);
		
		return global;
	}
}