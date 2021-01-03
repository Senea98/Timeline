package project.Timeline;

import javax.swing.*;

public class Panel {

    private JLabel country, population;
    private JPanel panel = new JPanel();


    public Panel(String country, long population) {
        this.country = new JLabel(country);
        this.country.setFont(this.country.getFont().deriveFont(14.0f));
        this.population = new JLabel(String.valueOf(population));
        this.population.setFont(this.population.getFont().deriveFont(18.0f));
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

    public void setPosition(int x, int y, int width){
        panel.setBounds(x, y, width, 40);
        country.setBounds(x+10, y, 100, 40);
        population.setBounds(x+panel.getWidth()+10, y, 100, 40);
    }
}
