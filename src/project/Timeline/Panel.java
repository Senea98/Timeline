package project.Timeline;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Panel {

    private JLabel country, population;
    private JPanel panel = new JPanel();

    private static HashMap<Color, Boolean> colors = new HashMap<>();

    public Panel(String country, long population) {
        this.country = new JLabel(country);
        this.country.setFont(this.country.getFont().deriveFont(14.0f));
        this.population = new JLabel(String.valueOf(population));
        this.population.setFont(this.population.getFont().deriveFont(18.0f));
    }
    public static void addPanelColor(Color color){
        colors.put(color, true);
    }
    private void setColor(JPanel panel1) {
        boolean found = false;
        if (colors.size()>0)
            for (Color color: colors.keySet()){
                if (colors.get(color) == true){
                    panel1.setBackground(color);
                    colors.put(color, false);
                    found = true;
                    break;
                }
            }
        if (!found)panel1.setBackground(new Color((int)(Math.random() * 0x1000000)));
    }

    public JLabel getCountry() {
        return country;
    }

    public JLabel getPopulation() {
        return population;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPosition(int y, int width){
        panel.setBounds(15, y, width, 40);
        setColor(panel);
        country.setBounds(panel.getX()+10, y, panel.getWidth()-10, 40);
        country.setFont(country.getFont().deriveFont(14.0f));
        population.setBounds(panel.getX()+panel.getWidth()+10, y, 100, 40);
        population.setFont( population.getFont().deriveFont(14.0f));
    }
    public void changeWidth(int toAdd){
        panel.setBounds(panel.getX(), panel.getY(), toAdd, panel.getHeight());
        population.setBounds(panel.getX()+panel.getWidth()+10, panel.getY(), 100, 40);
    }
    public void changeY(int toAdd){
        panel.setBounds(panel.getX(), panel.getY()+toAdd, panel.getWidth(), panel.getHeight());
        country.setBounds(panel.getX()+10, panel.getY(), 100, 40);
        population.setBounds(panel.getX()+panel.getWidth()+10, panel.getY(), 100, 40);
    }
    public void changePop( long population){
        this.population.setText(String.valueOf(population));
    }

}
