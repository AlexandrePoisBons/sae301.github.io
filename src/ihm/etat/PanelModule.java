package ihm.etat;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

import ihm.accueil.FrameAccueil;

import java.util.List;

import metier.Heure;
import metier.Intervenant;
import metier.Module;

public class PanelModule extends JPanel implements ActionListener {
	private FrameAccueil       frame;
	private PanelEtats         panelMere;
	private JPanel             panelPrincipal;
	private JPanel             panelSud;
	private JButton            btnGenererHtml;
	private JButton            btnRetour;
	private JComboBox<String>  ddlstModule;
	private List<Module>       listModule;


	public PanelModule(FrameAccueil frame, PanelEtats panelMere) {
		this.frame = frame;
		this.panelMere = panelMere;
		this.setLayout(new BorderLayout());

		//Définition de la taille et la position de la fenêtre
		int hauteur = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()  - (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.05);
		int largeur = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int xSize = (int)(largeur*0.6);
		int ySize = (int)(hauteur*0.35);
		this.frame.setSize(xSize, ySize);
		this.frame.setLocation((int)(largeur*0.5-xSize*0.5),(int)(hauteur*0.5-ySize*0.5));

		this.panelPrincipal  = new JPanel();
		this.panelSud        = new JPanel();
		this.btnGenererHtml  = new JButton("Genérer html");
		this.btnRetour       = new JButton("Retour");
		this.ddlstModule      = this.init();

		this.panelPrincipal.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridy = 0;
		gbc.gridx = 0;
		gbc.insets = new Insets(5, 5, 5, 5);
		this.panelPrincipal.add(new JLabel("Génération d'un fichier html pour un module choisi"), gbc);
		gbc.gridy = 1;
		this.panelPrincipal.add(this.btnGenererHtml, gbc);

		this.panelSud.add(this.btnRetour);

		this.add(this.panelPrincipal, BorderLayout.CENTER);
		this.add(this.panelSud, BorderLayout.SOUTH);

		this.btnGenererHtml.addActionListener(this);
		this.btnRetour     . addActionListener(this);

	}

	public void genererHtml(Module module) {
		StringBuilder html = new StringBuilder();
		System.out.println("test");

		// Entête de la page HTML avec le titre
		html.append("<html>\n<head>\n<title>Récapitulatif de ").append(module.getCode()).append(" ").append(module.getTypeModule()).append("</title>\n</head>\n<body>\n");

		// Titre de la page
		html.append("<h1>Récapitulatif de ").append(module.getTypeModule()).append("</h1>\n");

		// Tableau HTML
		html.append("<table border=\"1\">\n");
		// En-tête du tableau
		html.append("<tr>\n<th>Nom Intervenant</th>\n<th>Type d'heure</th>\n<th>Nombre d'heures</th>\n</tr>\n");

		// Remplissage du tableau avec les modules et les heures associées
		for (Intervenant i : module.getIntervenants()) {
			for (Heure heure : module.getHeures()) {
				html.append("<tr>\n<td>").append(i.getNom() + "_" + i.getPrenom()).append("</td>\n<td>").append(heure.getTypeHeure().getNomTypeHeure()).append("</td>\n<td>").append(heure.getDuree()).append("</td>\n</tr>\n");
			}
		}
		// Fin du tableau et de la page HTML
		html.append("</table>\n</body>\n</html>");
		this.ecrireFichierHTML(html.toString(), "./Etats/Modules/" +  module.getCode()  + "_" + module.getTypeModule() + ".html");
	}

	private void ecrireFichierHTML(String htmlContent, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Écriture de la chaîne HTML dans le fichier
            writer.write(htmlContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public JComboBox<String> init() {
		this.listModule = this.panelMere.getControleur().getCtrl().metier().getModules();
		List<Module> listModule = this.panelMere.getControleur().getCtrl().metier().getModules();
		JComboBox<String> ddlst = new JComboBox<String>();

		for (Module module : listModule) {
			ddlst.addItem(module.getCode() + " " + module.getLibelleCourt());
		}
		return ddlst;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btnGenererHtml){
			for (Module module : this.listModule) {
					if((module.getCode() + " " + module.getLibelleCourt()).equals(this.ddlstModule.getSelectedItem())) {
					this.genererHtml(module);
				}
			}
		}
		if(e.getSource() == this.btnRetour) {
			this.panelMere.changerPanel(new PanelEtats(this.frame));
		}
	}
}