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

        this.pRNord = new PRNord(this);

        // Utiliser BoxLayout pour organiser les composants horizontalement
        this.add(this.pRNord, BorderLayout.NORTH);
    }
}
