package ihm;
import java.awt.*;
import javax.swing.*;
import ihm.Panels.*;

public class FrameAccueil extends JFrame
{
    private JPanel panel;

    public FrameAccueil()
    {
        this.setLayout(new BorderLayout());
        this.panel = new PanelFrameAcceuil(this);

        this.setTitle("Accueil");
        this.setLocation(100, 100);
        this.setSize(350, 200); 

        this.add(new JPanel(), BorderLayout.NORTH);
        this.add(this.panel, BorderLayout.CENTER);
        this.add(new JPanel(), BorderLayout.SOUTH);
        
        JPanel eastPanel = new JPanel();
        eastPanel.setPreferredSize(new Dimension(100, 1)); 
        this.add(eastPanel, BorderLayout.EAST);

        JPanel westPanel = new JPanel();
        westPanel.setPreferredSize(new Dimension(100, 1));
        this.add(westPanel, BorderLayout.WEST);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void changerPanel(JPanel panel)
    {
        this.remove(this.panel);
        this.panel = panel;
        this.add(this.panel);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new FrameAccueil();
    }
}
