package ihm.etat;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.nio.charset.StandardCharsets;

import javax.swing.*;

import ihm.accueil.FrameAccueil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import metier.Heure;
import metier.Intervenant;
import metier.Module;

public class PanelInter extends JPanel implements ActionListener {
	private FrameAccueil                 frame;
	private PanelEtats                   panelMere;
	private JPanel                       panelPrincipal;
	private JPanel                       panelSud;
	private JButton                      btnGenererHtml;
	private JButton                      btnGenererCSV;
	private JLabel                       lblMessage;
	private JButton                      btnRetour;
	private JComboBox<String>            ddlstInter;
	private List<Intervenant>            listInter;
	private List<Heure>                  listVerif;
	private HashMap<String, List<Heure>> hashSemestre;


	public PanelInter(FrameAccueil frame, PanelEtats panelMere) {
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
		this.btnGenererCSV   = new JButton("Genérer CSV");
		this.lblMessage      = new JLabel("");
		this.btnRetour       = new JButton("Retour");
		this.ddlstInter      = this.init();
		this.listVerif 		 = new ArrayList<Heure>();
		this.hashSemestre    = new HashMap<String, List<Heure>>();

		this.panelSud.setLayout(new GridLayout(2, 1, 0, 10));
		this.panelPrincipal.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();


		gbc.gridy = 0;
		gbc.gridx = 0;
		gbc.insets = new Insets(5, 5, 5, 5);
		this.panelPrincipal.add(new JLabel("Génération d'un fichier html pour un intervenant choisi"), gbc);
		gbc.gridy = 1;
		this.panelPrincipal.add(this.btnGenererHtml, gbc);
		gbc.gridy = 2;
		this.panelPrincipal.add(this.ddlstInter, gbc);
		gbc.gridy = 0;
		gbc.gridx = 1;
		this.panelPrincipal.add(new JLabel("Génération d'un fichier CSV pour tous les intervenants"), gbc);
		gbc.gridy = 1;
		this.panelPrincipal.add(this.btnGenererCSV, gbc);

		this.panelSud.add(this.lblMessage);
		this.panelSud.add(this.btnRetour );

		this.add(this.panelPrincipal, BorderLayout.CENTER);
		this.add(this.panelSud, BorderLayout.SOUTH);

		this.btnGenererHtml.addActionListener(this);
		this.btnGenererCSV .addActionListener(this);
		this.btnRetour     . addActionListener(this);

		this.frame.setTitle("États - Intervenant");
	}

	public void genererHtml(Intervenant intervenant) {
		StringBuilder html = new StringBuilder();

		// Entête de la page HTML avec le titre
		html.append("<html>\n<head>\n<title>Récapitulatif de ").append(intervenant.getPrenom()).append(" ").append(intervenant.getNom()).append("</title>\n</head>\n<body>\n");

		// Titre de la page
		html.append("<h1>Récapitulatif de ").append(intervenant.getPrenom()).append(" ").append(intervenant.getNom()).append("</h1>\n");

		// Tableau HTML
		html.append("<table border=\"1\">\n");
		// En-tête du tableau
		html.append("<tr>\n<th>Code</th>\n<th>Module</th>\n<th>Type d'heure</th>\n<th>Nombre d'heures</th>\n<th>Semestre</th>\n</tr>\n");

		// Remplissage du tableau avec les modules et les heures associées
		for ( Module module : intervenant.getModules() ) {
			// Pour chaque module, on parcourt les heures associées
			for ( Heure heure : module.getHeures() ) {
				if(heure.getIntervenants().contains(intervenant) && !(this.listVerif.contains(heure))) {
					html.append("<tr>\n");
					html.append("<td>").append(module.getCode()).append("</td>\n");
					html.append("<td>").append(module.getLibelle()).append("</td>\n");
					html.append("<td>").append(heure.getTypeHeure().getNomTypeHeure()).append("</td>\n");
					html.append("<td>").append(heure.getDuree()).append("</td>\n");
					html.append("<td>").append(module.getSemestre()).append("</td>\n");
					html.append("</tr>\n");
					this.listVerif.add(heure);
				}
			}

			if ( this.hashSemestre.containsKey(module.getSemestre()) ) {
				this.hashSemestre.get(module.getSemestre()).addAll(this.listVerif);
			} else {
				this.hashSemestre.put(module.getSemestre(), new ArrayList<Heure>(this.listVerif));
			}

			// On vide la liste des heures pour le prochain semestre
			this.listVerif.clear();
		}

		// Fin du tableau et de la page HTML
		html.append("</table>");
		html.append("<br>");
		html.append("<br>");
		html.append("<br>");
		html.append("<br>");
		// Tableau semestre
		html.append("<table border=\"1\">\n");
		// En-tête du tableau
		html.append("<tr>\n<th>S1</th>\n<th>s3</th>\n <th>S5</th>\n<th>TotImpair</th>\n<th>S2</th>\n<th>S4</th>\n<th>S6</th>\n<th>TotPair</th>\n<th>Tot</th>\n</tr>\n");
		html.append("<tr>\n");
		html.append("<td>").append(this.getNbHeuresParSemestre("S1")).append("</td>\n");
		html.append("<td>").append(this.getNbHeuresParSemestre("S3")).append("</td>\n");
		html.append("<td>").append(this.getNbHeuresParSemestre("S5")).append("</td>\n");
		html.append("<td>").append(this.getNbHeuresParSemestre("S1") + this.getNbHeuresParSemestre("S3") + this.getNbHeuresParSemestre("S5")).append("</td>\n");
		html.append("<td>").append(this.getNbHeuresParSemestre("S2")).append("</td>\n");
		html.append("<td>").append(this.getNbHeuresParSemestre("S4")).append("</td>\n");
		html.append("<td>").append(this.getNbHeuresParSemestre("S6")).append("</td>\n");
		html.append("<td>").append(this.getNbHeuresParSemestre("S2") + this.getNbHeuresParSemestre("S4") + this.getNbHeuresParSemestre("S6")).append("</td>\n");
		html.append("<td>").append(this.getNbHeuresParSemestre("S1") + this.getNbHeuresParSemestre("S3") + this.getNbHeuresParSemestre("S5") + this.getNbHeuresParSemestre("S2") + this.getNbHeuresParSemestre("S4") + this.getNbHeuresParSemestre("S6")).append("</td>\n");

		html.append("</tr>\n");
		html.append("</table>");
		//fin body et html
		html.append("</body>\n</html>");
		this.ecrireFichierHTML(html.toString(), "./Etats/Intervenants/Html/" + intervenant.getNom() + "_" + intervenant.getPrenom() + ".html");
	}

	private int getNbHeuresParSemestre( String semestre ) {
		int nbHeures = 0;
		//verif si le semestre est null
		if(this.hashSemestre.get(semestre) == null) {
			return 0;
		}
		for (Heure heure : this.hashSemestre.get(semestre)) {
			if (heure.getModule().getSemestre().equals(semestre)) {
				nbHeures += heure.getDuree();
			}
		}

		return nbHeures;
	}

	private void ecrireFichierHTML( String htmlContent, String filePath ) {
		try ( BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filePath), StandardCharsets.UTF_8)) ) {
			// Écriture de la chaîne HTML dans le fichier
			writer.write(htmlContent);
			this.lblMessage.setText("Fichier HTML généré avec succès à l'emplacement : " + filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public JComboBox<String> init() {
		this.listInter = this.panelMere.getControleur().getIntervenants();
		List<Intervenant> listInter = this.panelMere.getControleur().getIntervenants();
		JComboBox<String> ddlst = new JComboBox<String>();

		for (Intervenant intervenant : listInter) {
			ddlst.addItem(intervenant.getNom() + " " + intervenant.getPrenom());
		}
		return ddlst;
	}

	public void generateIntervenantsCSV(List<Intervenant> intervenants, String filePath) {
		try ( BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filePath), StandardCharsets.UTF_8)) ) {
			// Entête du CSV
			writer.write("Catégorie;Nom;Prénom;Service Dû;Max Heures Autorisé;Coeff TP;Total Heures Impairs;Total Heures Pairs;Total Heures");

			// Écriture des données pour chaque intervenant
			for ( Intervenant intervenant : intervenants ) {
				writer.newLine();
				writer.write(
						intervenant.getStatut().getNomStatut()                        + ";" +
						intervenant.getNom()                                          + ";" +
						intervenant.getPrenom()                                       + ";" +
						intervenant.getNbEqTD()                                       + ";" +
						getMaxHeuresAutorisees(intervenant)                           + ";" +
						getCoefficientTP(intervenant)                                 + ";" +
						getTotalHeuresParSemestre(intervenant, "impair") + ";" +
						getTotalHeuresParSemestre(intervenant, "pair")   + ";" +
						intervenant.getNbHeures()
				);
			}

			this.lblMessage.setText("Fichier CSV généré avec succès à l'emplacement : " + filePath);
			this.repaint();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private float getMaxHeuresAutorisees(Intervenant intervenant) {
		// À compléter avec la logique appropriée pour obtenir le maximum d'heures autorisé
		// Par exemple, vous pourriez avoir une méthode dans la classe Intervenant qui retourne cette valeur.
		return 0.0f; // Exemple, à remplacer avec la logique réelle
	}

	private float getCoefficientTP(Intervenant intervenant) {
		// À compléter avec la logique appropriée pour obtenir le coefficient TP
		// Par exemple, vous pourriez avoir une méthode dans la classe Intervenant qui retourne cette valeur.
		return 0.0f; // Exemple, à remplacer avec la logique réelle
	}

	private float getTotalHeuresParSemestre(Intervenant intervenant, String semestreType) {
		Map<String, Float> heuresParSemestre = intervenant.getNbHeuresParSemestre();
		float totalHeures = 0.0f;

		for ( Map.Entry<String, Float> entry : heuresParSemestre.entrySet() ) {
			String semestre = entry.getKey();
			float heures = entry.getValue();

			if ( (semestreType.equals("impair") && semestre.endsWith("1")) ||
				 (semestreType.equals("pair")   && semestre.endsWith("2")) ) {
				totalHeures += heures;
			}
		}

		return totalHeures;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ( e.getSource() == this.btnGenererHtml ) {
			for ( Intervenant intervenant : this.listInter ) {
				if ( (intervenant.getNom() + " " + intervenant.getPrenom()).equals(this.ddlstInter.getSelectedItem()) ) {
					this.genererHtml(intervenant);
				}
			}
		} else if ( e.getSource() == this.btnGenererCSV ) {
			this.generateIntervenantsCSV(this.listInter, "./Etats/Intervenants/CSV/Intervenants.csv");
		} else if ( e.getSource() == this.btnRetour ) {
			this.panelMere.changerPanel(new PanelEtats(this.frame));
		}
	}

}
