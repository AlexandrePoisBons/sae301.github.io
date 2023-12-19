package ihm.accueil						;
//Imports classes externes
import ihm.previsionnel.*				;
import ihm.parametrage.PanelParam		;
import ihm.etat.PanelEtats				;
import ihm.intervenants.PanelInter		;

//Imports classes Java
import javax.swing.*					;
import java.awt.GridLayout				;
import java.awt.Toolkit;
import java.awt.BorderLayout			;
import java.awt.event.ActionListener	;
import java.awt.event.ActionEvent		;



public class PanelAcceuil extends JPanel implements ActionListener {
	//Attributs
	private FrameAccueil frame;
	private JButton btnParam;
	private JButton btnPrevi;
	private JButton btnInter;
	private JButton btnEtats;

	//Constructeur
	public PanelAcceuil(FrameAccueil frame){
		//Synchronisation des pages
		this.frame = frame;
		JPanel panelCentre;
		JPanel panelHaut;
		JPanel panelBas;
		JPanel panelGauche;
		JPanel panelDroite;

		//Layout
		this.setLayout(new BorderLayout(60, 0));

		//Positionnement de la frame
		int hauteur = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()  - (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.05);
		int largeur = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int xSize = (int)(largeur*0.3);
		int ySize = (int)(hauteur*0.3);
		this.frame.setSize(xSize, ySize);
		this.frame.setLocation((int)(largeur*0.5-xSize*0.5),(int)(hauteur*0.5-ySize*0.5));
		//Composants
		panelCentre   = new JPanel();	
		panelHaut     = new JPanel();
		panelBas      = new JPanel();
		panelGauche   = new JPanel();
		panelDroite   = new JPanel();
		this.btnParam = new JButton("Paramètres		");
		this.btnPrevi = new JButton("Prévisionnel	");
		this.btnInter = new JButton("Intervenants	");
		this.btnEtats = new JButton("Etats			");

		panelCentre.setLayout(new GridLayout(4, 1, 10, 5));

		//Ajout des composants
		panelCentre.add(this.btnParam);
		panelCentre.add(this.btnPrevi);
		panelCentre.add(this.btnInter);
		panelCentre.add(this.btnEtats);

		this.add(panelCentre, BorderLayout.CENTER);
		this.add(panelHaut  , BorderLayout.NORTH);
		this.add(panelBas, BorderLayout.SOUTH);
		this.add(panelGauche, BorderLayout.WEST);
		this.add(panelDroite, BorderLayout.EAST);
	
		//ActionListeners
		this.btnParam.addActionListener(this);
		this.btnInter.addActionListener(this);
		this.btnPrevi.addActionListener(this);
		this.btnEtats.addActionListener(this);
	}


	//Permets de changer de panel en fonction du bouton cliqué 
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == this.btnParam){this.frame.changerPanel(new PanelParam(this.frame)	);}
		if(e.getSource() == this.btnPrevi){this.frame.changerPanel(new PanelPrevi(this.frame)	);}
		if(e.getSource() == this.btnInter){this.frame.changerPanel(new PanelInter(this.frame)   );}
		if(e.getSource() == this.btnEtats){this.frame.changerPanel(new PanelEtats(this.frame)	);}
	}
}
