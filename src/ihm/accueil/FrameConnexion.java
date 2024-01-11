package ihm.accueil;

import controleur.Controleur;
import controleur.Infos;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Insets;
import java.awt.Toolkit;

import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.*;


public class FrameConnexion extends JFrame implements ActionListener{

	private JTextField     txtDatabase;
	private JTextField     txtLogin;
	private JPasswordField pswMdp;
	private JButton        btnConnexion;
	private JLabel         lblErreur;

	private Infos infos;

	public FrameConnexion() {

		this.infos = new Infos();

		//Positionnement de la frame
		int hauteur = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.05);
		int largeur = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int xSize   = (int)(largeur*0.3);
		int ySize   = (int)(hauteur*0.3);
		this.setSize(xSize, ySize);
		this.setLocation((int)(largeur*0.5-xSize*0.5),(int)(hauteur*0.5-ySize*0.5));
		this.setTitle("Accueil");

		this.setResizable(false);

		this.txtDatabase       = new JTextField(10);
		this.txtLogin          = new JTextField(10);
		this.pswMdp            = new JPasswordField(10);
		this.txtDatabase.setText(infos.getDatabase());
		this.txtLogin.setText(infos.getLogin());
		this.pswMdp.setText(infos.getPassword());
		this.btnConnexion      = new JButton("Connexion");
		JPanel panelPrincipal  = new JPanel();
	
		panelPrincipal.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 10, 5);
		panelPrincipal.add(new JLabel("Database : "), gbc);
		gbc.gridy = 1;
		panelPrincipal.add(new JLabel("Login : "), gbc);
		gbc.gridy = 2;
		panelPrincipal.add(new JLabel("Mdp : "), gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 5, 10, 0);
		panelPrincipal.add(this.txtDatabase, gbc);
		gbc.gridy = 1;
		panelPrincipal.add(this.txtLogin, gbc);
		gbc.gridy = 2;
		panelPrincipal.add(this.pswMdp, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;

		this.lblErreur = new JLabel("");
		this.lblErreur.setForeground(java.awt.Color.RED);
		panelPrincipal.add(this.lblErreur, gbc);

		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.insets = new Insets(0, 0, 0, 0);
		panelPrincipal.add(this.btnConnexion, gbc);

		this.add(panelPrincipal);

		this.btnConnexion.addActionListener(this);


		//Permet la fermeture de la fenetre quand on appuie sur la croix
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.setVisible(true);
	}



	private void connexion() {
		String u;
		String login;
		String password;

		u = this.txtDatabase.getText();
		login = this.txtLogin.getText();
		password = new String(this.pswMdp.getPassword());

		try {
			Class.forName("org.postgresql.Driver");
			// System.out.println ("CHARGEMENT DU PILOTE OK");
		} catch ( ClassNotFoundException e ) {
			this.txtDatabase.setText("");
			this.txtLogin.setText("");
			this.pswMdp.setText("");
			this.lblErreur.setText("Connexion à la bado impossible");
			this.repaint();
			return;
		}

		try {
			String url = Infos.URL_DATABASE + u;

			DriverManager.getConnection(url,login,password);

			// System.out.println("CONNEXION A LA BADO: REUSSIE");
		} catch ( SQLException e ) {
			this.txtDatabase.setText("");
			this.txtLogin.setText("");
			this.pswMdp.setText("");
			this.lblErreur.setText("Connexion à la bado impossible");
			this.repaint();
			return;
		}

		Infos.ecrire(u, login, password);
		this.dispose();
		new Controleur();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Faire en sorte que ça passe au prochain textfield quand on clique sur entre avec .transferFocus();
		if(e.getSource() == this.btnConnexion) {
			this.connexion();
		}
	}
}
