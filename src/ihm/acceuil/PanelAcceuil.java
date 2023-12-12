package ihm.acceuil						;
//Imports classes externes
import ihm.previsionnel.*				;
import ihm.parametrage.*				;
import ihm.etat.PanelEtats				;
import ihm.intervenants.PanelInter		;

//Imports classes Java
import javax.swing.*					;
import java.awt.GridLayout				;
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

		//Layout
		this.setLayout(new GridLayout(4, 1, 0, 5));

		//Composants
		this.btnParam = new JButton("Paramètres		");
		this.btnPrevi = new JButton("Prévisionnel	");
		this.btnInter = new JButton("Intervenants	");
		this.btnEtats = new JButton("Etats			");

		//Ajout des composants
		this.add(this.btnParam);
		this.add(this.btnPrevi);
		this.add(this.btnInter);
		this.add(this.btnEtats);
	
		//ActionListeners
		this.btnParam.addActionListener(this);
		this.btnInter.addActionListener(this);
		this.btnPrevi.addActionListener(this);
		this.btnEtats.addActionListener(this);
	}


	//Permets de changer de panel en fonction du bouton cliqué 
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == this.btnParam){this.frame.changerPanel(new PanelParam()				);}
		if(e.getSource() == this.btnPrevi){this.frame.changerPanel(new PanelPrevi(this.frame)	);}
		if(e.getSource() == this.btnInter){this.frame.changerPanel(new PanelInter()				);}
		if(e.getSource() == this.btnEtats){this.frame.changerPanel(new PanelEtats()				);}
	}
}
