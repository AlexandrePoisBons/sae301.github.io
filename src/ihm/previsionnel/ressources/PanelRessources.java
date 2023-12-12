package ihm.previsionnel.ressources;

import ihm.previsionnel.PanelPrevi;
import javax.swing.*;
import java.awt.*;

import ihm.previsionnel.ressources.ressourcesNord.PRNord;

public class PanelRessources extends JPanel {
    private PanelPrevi frame;
    private JPanel panelPrincipalNord;
    private PRNord pRNord;

    public PanelRessources(PanelPrevi frame) {
        this.frame = frame;
        this.setLayout(new BorderLayout());

        this.panelPrincipalNord = new JPanel();
        this.pRNord = new PRNord(this);

        // Utiliser BoxLayout pour organiser les composants horizontalement
        this.panelPrincipalNord.setLayout(new BoxLayout(this.panelPrincipalNord, BoxLayout.X_AXIS));
        this.panelPrincipalNord.add(this.pRNord);
        this.panelPrincipalNord.add(Box.createHorizontalGlue()); // Ajouter de l'espace horizontal flexible
        this.add(this.panelPrincipalNord, BorderLayout.NORTH);
    }
}
