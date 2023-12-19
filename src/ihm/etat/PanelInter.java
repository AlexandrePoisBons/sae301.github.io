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
import java.util.Map;

import metier.Heure;
import metier.Intervenant;
import metier.Module;

public class PanelInter extends JPanel implements ActionListener {
	private FrameAccueil       frame;
	private PanelEtats         panelMere;
	private JPanel             panelPrincipal;
	private JPanel             panelSud;
	private JButton            btnGenererHtml;
	private JButton            btnGenererCSV;
	private JButton            btnRetour;
	private JComboBox<String>  ddlstInter;
	private List<Intervenant>  listInter;


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
		this.btnRetour       = new JButton("Retour");
		this.ddlstInter      = this.init();

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

		this.panelSud.add(this.btnRetour);

		this.add(this.panelPrincipal, BorderLayout.CENTER);
		this.add(this.panelSud, BorderLayout.SOUTH);

		this.btnGenererHtml.addActionListener(this);
		this.btnGenererCSV .addActionListener(this);
		this.btnRetour     . addActionListener(this);

	}

	public void genererHtml(Intervenant intervenant) {
		StringBuilder html = new StringBuilder();
		System.out.println("test");

		// Entête de la page HTML avec le titre
		html.append("<html>\n<head>\n<title>Récapitulatif de ").append(intervenant.getPrenom()).append(" ").append(intervenant.getNom()).append("</title>\n</head>\n<body>\n");

		// Titre de la page
		html.append("<h1>Récapitulatif de ").append(intervenant.getPrenom()).append(" ").append(intervenant.getNom()).append("</h1>\n");

		// Tableau HTML
		html.append("<table border=\"1\">\n");
		// En-tête du tableau
		html.append("<tr>\n<th>Code</th>\n<th>Module</th>\n<th>Type d'heure</th>\n<th>Nombre d'heures</th>\n</tr>\n");

		// Remplissage du tableau avec les modules et les heures associées
		for (Module module : intervenant.getModules()) {
			for (Heure heure : module.getHeures()) {
				html.append("<tr>\n<td>").append(module.getCode()).append("</td>\n<td>").append(module.getLibelle()).append("</td>\n<td>").append(heure.getTypeHeure().getNomTypeHeure()).append("</td>\n<td>").append(heure.getDuree()).append("</td>\n</tr>\n");
			}
		}
		// Fin du tableau et de la page HTML
		html.append("</table>\n</body>\n</html>");
		this.ecrireFichierHTML(html.toString(), "./Etats/Intervenants/Html/" + intervenant.getNom() + "_" + intervenant.getPrenom() + ".html");
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
		this.listInter = this.panelMere.getControleur().getCtrl().metier().getIntervenants();
		List<Intervenant> listInter = this.panelMere.getControleur().getCtrl().metier().getIntervenants();
		JComboBox<String> ddlst = new JComboBox<String>();

		for (Intervenant intervenant : listInter) {
			ddlst.addItem(intervenant.getNom() + " " + intervenant.getPrenom());
		}
		return ddlst;
	}

	public void generateIntervenantsCSV(List<Intervenant> intervenants, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Entête du CSV
            writer.write("Catégorie;Nom;Prénom;Service Dû;Max Heures Autorisé;Coeff TP;Total Heures Impairs;Total Heures Pairs;Total Heures");

            // Écriture des données pour chaque intervenant
            for (Intervenant intervenant : intervenants) {
                writer.newLine();
                writer.write(
                        intervenant.getStatut() + ";" +
                        intervenant.getNom() + ";" +
                        intervenant.getPrenom() + ";" +
                        intervenant.getNbEqTD() + ";" +
                        getMaxHeuresAutorisees(intervenant) + ";" +
                        getCoefficientTP(intervenant) + ";" +
                        getTotalHeuresParSemestre(intervenant, "impair") + ";" +
                        getTotalHeuresParSemestre(intervenant, "pair") + ";" +
                        intervenant.getNbHeures()
                );
            }

            System.out.println("Fichier CSV généré avec succès à l'emplacement : " + filePath);
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

        for (Map.Entry<String, Float> entry : heuresParSemestre.entrySet()) {
            String semestre = entry.getKey();
            float heures = entry.getValue();

            if ((semestreType.equals("impair") && semestre.endsWith("1")) ||
                (semestreType.equals("pair") && semestre.endsWith("2"))) {
                totalHeures += heures;
            }
        }

        return totalHeures;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btnGenererHtml){
			for (Intervenant intervenant : this.listInter) {
				if((intervenant.getNom() + " " + intervenant.getPrenom()).equals(this.ddlstInter.getSelectedItem())) {
					this.genererHtml(intervenant);
				}
			}
		}
		if(e.getSource() == this.btnGenererCSV) {
			this.generateIntervenantsCSV(this.listInter, "./Etats/Intervenants/CSV/Intervenants.csv");
		}
		if(e.getSource() == this.btnRetour) {
			this.panelMere.changerPanel(new PanelEtats(this.frame));
		}
	}
}