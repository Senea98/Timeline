package project.Timeline;

import javax.swing.*;
import java.awt.*;

public class Graphic extends JFrame {
    public Graphic() {
        setLayout(null);
        setSize(1100, 500);
        getContentPane().setBackground(Color.decode("#bdb76b"));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void addPanel(Panel panel){
        add(panel.getCountry());
        add(panel.getPanel());
        add(panel.getPopulation());
    }
}
