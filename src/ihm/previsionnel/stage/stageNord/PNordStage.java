package ihm.previsionnel.stage.stageNord;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

import ihm.previsionnel.stage.PanelStage;
import metier.Module;

public class PNordStage extends JPanel{
	private PanelStage panelMere;
	private JPanel panelCentre;

	private JTextField txtTypeModule;
	private JTextField txtSemestre;
	private JTextField txtCode;
	private JTextField txtLibLong;
	private JTextField txtLibCourt;

	private JTextField txtNbEtd;
	private JTextField txtNbGpTd;
	private JTextField txtNbGpTp;

	private Module module;

	public PNordStage(PanelStage panelMere, Module m) {
		this.panelMere = panelMere;
		this.module = m;

		this.setLayout(new BorderLayout());

		this.panelCentre   = new JPanel();
		this.txtTypeModule = new JTextField("Stage/suivi", 10);
		this.txtSemestre   = new JTextField(10);
		this.txtCode       = new JTextField(10);
		this.txtLibLong    = new JTextField(20);
		this.txtLibCourt   = new JTextField(15);
		this.txtNbEtd      = new JTextField(2);
		this.txtNbGpTd     = new JTextField(2);
		this.txtNbGpTp     = new JTextField(2);

		this.txtNbEtd.setEditable(false);
		this.txtNbGpTd.setEditable(false);
		this.txtNbGpTp.setEditable(false);

		this.panelCentre.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;

		// Ajout des composants avec GridBagLayout
		gbc.gridx  = 0;
		gbc.gridy  = 0;
		gbc.insets = new Insets(0, 0, 5, 5);

		// Ajouts des éléments
		this.panelCentre.add( new JLabel("type module")  , gbc );
		gbc.gridx = 1;
		this.panelCentre.add( new JLabel("semestre")     , gbc );
		gbc.gridx = 2;
		this.panelCentre.add( new JLabel("code")         , gbc );
		gbc.gridx = 3;
		this.panelCentre.add( new JLabel("libellé long") , gbc );
		gbc.gridx = 4;
		this.panelCentre.add( new JLabel("libellé court"), gbc );
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.panelCentre.add( this.txtTypeModule , gbc );
		gbc.gridx = 1;
		this.panelCentre.add( this.txtSemestre   , gbc );
		gbc.gridx = 2;
		this.panelCentre.add( this.txtCode       , gbc );
		gbc.gridx = 3;
		gbc.insets = new Insets(0, 0, 5, 10);
		this.panelCentre.add(this.txtLibLong, gbc);
		gbc.gridx = 4;
		this.panelCentre.add(this.txtLibCourt, gbc);
		// Pour empêcher la modification des champs de saisies
		this.txtTypeModule.setEditable(false);
		this.txtSemestre.setEditable(false);

		// pnlHeureModule
		// Ajout des composants avec GridBagLayout
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.insets = new Insets(0, 10, 5, 0);
		this.panelCentre.add(new JLabel("nb Etd"), gbc);
		gbc.gridx = 1;
		this.panelCentre.add(new JLabel("nb gp TD"), gbc);
		gbc.gridx = 2;
		this.panelCentre.add(new JLabel("nb gp TP"), gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		this.panelCentre.add(this.txtNbEtd, gbc);
		gbc.gridx = 1;
		this.panelCentre.add(this.txtNbGpTd, gbc);
		gbc.gridx = 2;
		this.panelCentre.add(this.txtNbGpTp, gbc);

		this.add(this.panelCentre, BorderLayout.WEST);

		this.setModuleData();

		this.setVisible(true);
	}

	private void setModuleData() {
		this.txtTypeModule.setText("Stage");
		this.txtSemestre.setText(this.panelMere.getSemestre());
		this.txtNbEtd.setText(this.panelMere.getNbEtd()+"");
		this.txtNbGpTd.setText(this.panelMere.getNbGpTd()+"");
		this.txtNbGpTp.setText(this.panelMere.getNbGpTp()+"");
		this.txtCode.setText(this.module.getCode());
		this.txtLibCourt.setText(this.module.getLibelleCourt());
		this.txtLibLong.setText(this.module.getLibelle());
	}

	public void setValues() {
		this.module.setTypeModule("Stage");
		this.module.setSemestre(this.panelMere.getSemestre());
		this.module.setNbEtudiants(this.panelMere.getNbEtd());
		this.module.setNbGpTD(this.panelMere.getNbGpTd());
		this.module.setNbGpTP(this.panelMere.getNbGpTp());
		this.module.setCode(this.txtCode.getText());
		this.module.setLibelleCourt(this.txtLibCourt.getText());
		this.module.setLibelle(this.txtLibLong.getText());
	}

	public String getLibelle()      { return this.txtLibLong.getText();  }
	public String getLibelleCourt() { return this.txtLibCourt.getText(); }
	public String getCode()         { return this.txtCode.getText();     }
	public int getTxtNbEtd()     { return Integer.parseInt(this.txtNbEtd.getText() );}
	public int getTxtNbGpTd()    { return Integer.parseInt(this.txtNbGpTd.getText()) ;}
	public int getTxtNbGpTp()    { return Integer.parseInt(this.txtNbGpTp.getText()) ;}


}
