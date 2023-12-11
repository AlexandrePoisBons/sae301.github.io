package ihm.Panels;
import ihm.FrameAccueil;

import java.awt.*;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class PanelCenterPrevi extends JPanel {
	private FrameAccueil frame;

	private JTextField[] tabTextFieldsS1;
	private JTextField[] tabTextFieldsS2;
	private JTextField[] tabTextFieldsS3;
	private JTextField[] tabTextFieldsS4;
	private JTextField[] tabTextFieldsS5;
	private JTextField[] tabTextFieldsS6;
	private JTable[] tabModules;	

	public PanelCenterPrevi(FrameAccueil frameAccueil) {
		this.frame = frameAccueil;
		this.frame.setSize(800, 300);
        this.setSize(400, 300);

		//Instanciation des tableaux de JTextField
		this.tabTextFieldsS1 = new JTextField[4];
		this.tabTextFieldsS2 = new JTextField[4];
		this.tabTextFieldsS3 = new JTextField[4];
		this.tabTextFieldsS4 = new JTextField[4];
		this.tabTextFieldsS5 = new JTextField[4];
		this.tabTextFieldsS6 = new JTextField[4];
		this.tabModules = new JTable[6];

        JTabbedPane modules = new JTabbedPane(JTabbedPane.TOP);
		modules.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

		 // Changer la taille du JTabbedPane
        modules.setPreferredSize(new Dimension(550, 200));

        // Créer des panneaux pour chaque onglet
        JPanel semestre1 =  remplissagePanel(new JPanel(), this.tabTextFieldsS1[0], this.tabTextFieldsS1[1], this.tabTextFieldsS1[2], this.tabTextFieldsS1[3], this.tabModules[0]);
    
        JPanel semestre2 =  remplissagePanel(new JPanel(), this.tabTextFieldsS2[0], this.tabTextFieldsS2[1], this.tabTextFieldsS2[2], this.tabTextFieldsS2[3], this.tabModules[1]);

        JPanel semestre3 =  remplissagePanel(new JPanel(), this.tabTextFieldsS3[0], this.tabTextFieldsS3[1], this.tabTextFieldsS3[2], this.tabTextFieldsS3[3], this.tabModules[2]);

		JPanel semestre4 =  remplissagePanel(new JPanel(), this.tabTextFieldsS4[0], this.tabTextFieldsS4[1], this.tabTextFieldsS4[2], this.tabTextFieldsS4[3], 	this.tabModules[3]);

		JPanel semestre5 =  remplissagePanel(new JPanel(), this.tabTextFieldsS5[0], this.tabTextFieldsS5[1], this.tabTextFieldsS5[2], this.tabTextFieldsS5[3], this.tabModules[4]);

		JPanel semestre6 = remplissagePanel(new JPanel(), this.tabTextFieldsS6[0], this.tabTextFieldsS6[1], this.tabTextFieldsS6[2], this.tabTextFieldsS6[3], this.tabModules[5]);

        // Ajouter les onglets au JTabbedPanes
        modules.addTab("S1", semestre1);
        modules.addTab("S2", semestre2);
        modules.addTab("S3", semestre3);
		modules.addTab("S4", semestre4);
		modules.addTab("S5", semestre5);
		modules.addTab("S6", semestre6);

        // Ajouter le JTabbedPane à la fenêtre
        this.add(modules);

        setVisible(true);
	}

	public JPanel remplissagePanel(JPanel global, JTextField textFieldGpTD, JTextField textFieldGpTP, JTextField textFieldNbEt, JTextField textFieldNbSemaine, JTable tabModule){
		
		//Layout
		global.setLayout(new BorderLayout());
		//JPanels
		JPanel panelNord = new JPanel();
		JPanel panelCentre = new JPanel();
		panelCentre.setLayout(new BorderLayout());
		tabModule = new JTable();

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

		panelCentre.add(new JLabel("Liste des modules :"), BorderLayout.NORTH);
		panelCentre.add(tabModule, BorderLayout.CENTER);

		global.add(panelNord, BorderLayout.NORTH);
		global.add(panelCentre, BorderLayout.CENTER);
		
		return global;
	}
}