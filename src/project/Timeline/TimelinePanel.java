package project.Timeline;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
class TopCountry{
    String name;
    long population;

    public TopCountry(String name, long population) {
        this.name = name;
        this.population = population;
    }
}

public class TimelinePanel extends JFrame {
    ArrayList<Country> countries;
    Timer tm1;
    JButton start;
    ArrayList<JPanel> jPanels = new ArrayList<>();
    ArrayList<JLabel> jLabels = new ArrayList<>();
    ArrayList<JLabel> popLabels = new ArrayList<>();
    JLabel world, thYear;
    long worldPop;
    String[][] colors= new String[][] {
            {"#bada55","#7fe5f0","#ff0000","#ff80ed","#696969", "#065535","#133337"},
            {"unused","unused","unused","unused","unused","unused","unused"}
    };
    int year, top;

    public TimelinePanel(ArrayList<Country> countries, int topd) {
        super("Timeline");
        this.countries = countries;
        top = topd;
        createElements();
        year =0;
        worldPop = countries.get(1).getLastPop();
        tm1 = new Timer(100, e -> {

            year++;
            cycle(year);

            if (year == countries.get(0).getPop().size()-1) {
                tm1.stop();
                year=0;
                start.setEnabled(true);
            }

        });
        //Timer1 Start
        start.addActionListener(e -> tm1.start());

        addElements();
    }

    private void addElements() {
        add(start);
        add(world);
        add(thYear);
        for (JLabel label:jLabels)add(label);
        for (JLabel label:popLabels)add(label);
        for (JPanel panel:jPanels)add(panel);
        setLayout(null);
        setSize(1100, 500);
        getContentPane().setBackground(Color.decode("#bdb76b"));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setColor(JPanel panel1) {
        for(int i=0;i<colors[0].length;i++) {
            if (colors[1][i] == "unused") {
                panel1.setBackground(Color.decode(colors[0][i]));
                colors[1][i] = "used";
                break;
            }
        }
    }

    private void createElements() {
        start = new JButton("start");
        start.setBounds(560, 400, 100, 20);
        world = new JLabel();
        world.setText(String.valueOf(countries.get(1).getPopByIndex(0)));
        world.setBounds(560, 350, 150, 30);
        world.setFont(world.getFont().deriveFont(18.0f));
        thYear = new JLabel();
        thYear.setText(String.valueOf(countries.get(0).getPopByIndex(0)));
        thYear.setBounds(560, 300, 150, 30);
        thYear.setFont(thYear.getFont().deriveFont(24.0f));
        for(int i=0;i<top;i++){
            JPanel panel = new JPanel();
            panel.setBackground(new Color((int)(Math.random() * 0x1000000)));
            panel.setBounds(15, 50*(i+1), 5, 40);
            jPanels.add(panel);
        }
        for(int i=0;i<top;i++) {
            JLabel label = new JLabel(countries.get(i+2).getName());
            label.setBounds(25, 50*(i+1), 100, 40);
            label.setFont(label.getFont().deriveFont(14.0f));
            jLabels.add(label);
        }
        for(int i=0;i<top;i++) {
            JLabel label = new JLabel();
            label.setBounds(100, 50*(i+1), 100, 40);
            label.setFont(label.getFont().deriveFont(14.0f));
            popLabels.add(label);
        }
    }
    private ArrayList<TopCountry> topCountries(ArrayList<Country> countries, int index) {
        ArrayList<TopCountry> list = new ArrayList<>();
        for (int i = 2; i < top+2; i++)
            list.add(new TopCountry(countries.get(i).getName(), countries.get(i).getPopByIndex(index)));

        for (int i = top+2; i < countries.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).population < countries.get(i).getPopByIndex(index)) {
                    list.add(j, new TopCountry(countries.get(i).getName(), countries.get(i).getPopByIndex(index)));
                    list.remove(list.get(list.size() - 1));
                    break;
                }


            }
        }
        return list;
    }
    private void cycle(int ind){

        ArrayList<TopCountry> list = topCountries(countries, ind);
        for(JPanel panel:jPanels){
            int index = jPanels.indexOf(panel);
            long countryPop = list.get(index).population;

            int wid = Math.round(countryPop*5000/worldPop);
            panel.setBounds(panel.getX(), panel.getY(), wid, 40);

            JLabel label = popLabels.get(index);
            label.setBounds(panel.getX()+panel.getWidth()+10, panel.getY(), 100, 40);
            label.setText(String.valueOf(countryPop));

            label = jLabels.get(index);
            label.setText(list.get(index).name);


            world.setText(String.valueOf(countries.get(1).getPopByIndex(ind)));
            thYear.setText(String.valueOf(countries.get(0).getPopByIndex(ind)));
        }
    }

}
