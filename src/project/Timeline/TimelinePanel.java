package project.Timeline;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

class TopCountry{
    String name;
    long population;
    private long[] popArray;
    public int next;

    public TopCountry(String name, long population) {
        this.name = name;
        this.population = population;
    }
    public void setPopAbstractArray(long prevPop, int between){
        popArray = new long[between];
        float pace = (float) (population- prevPop)/between;
        for (int i=0;i<between; i++){
            popArray[i] = (long) (prevPop+i*pace);
        }
    }
    public long getPopAbstractArrayByIndex(int index){

        return popArray[index];
    }
}

public class TimelinePanel extends JFrame {
    ArrayList<Country> countries;
    Timer tm1, tm2;
    JButton start;
    ArrayList<JPanel> jPanels = new ArrayList<>();
    ArrayList<JLabel> jLabels = new ArrayList<>();
    ArrayList<JLabel> popLabels = new ArrayList<>();
    JLabel world, thYear;
    long worldPop;
    int year, top;

    public TimelinePanel(ArrayList<Country> countries, int topd) {
        super("Timeline");
        this.countries = countries;
        top = topd;
        createElements();
        year =1;
        worldPop = countries.get(1).getLastPop();
        tm1 = new Timer(1000, e -> {

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
            panel.setBackground(Color.getHSBColor(new Random().nextFloat(), 0.9f, 1.0f));
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
            {
                TopCountry country = new TopCountry(countries.get(i).getName(), countries.get(i).getPopByIndex(index));
                if (index>1)country.setPopAbstractArray(countries.get(i).getPopByIndex(index-1), 10);
                else country.setPopAbstractArray(countries.get(i).getPopByIndex(index), 10);
                list.add(country);
            }
        for (int i = top+2; i < countries.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).population < countries.get(i).getPopByIndex(index)) {
                    TopCountry country = new TopCountry(countries.get(i).getName(), countries.get(i).getPopByIndex(index));
                    if (index>1)country.setPopAbstractArray(countries.get(i).getPopByIndex(index-1), 10);
                    else country.setPopAbstractArray(countries.get(i).getPopByIndex(index), 10);
                    country.next = j;
                    list.add(j, country);
                    list.remove(list.get(list.size() - 1));
                    break;
                }


            }
        }
        return list;
    }
    private int cnt=0;
    private void cycle(int ind){

        ArrayList<TopCountry> list = topCountries(countries, ind);

        System.out.println(list.get(0).population);
        tm2 = new Timer(90, e -> {
//            for(JPanel panel:jPanels){
//                int index = jPanels.indexOf(panel);
//                long countryPop = list.get(index).getPopAbstractArrayByIndex(cnt);
//
//                int wid = Math.round(countryPop*5000/worldPop);
//                panel.setBounds(panel.getX(), panel.getY(), wid, 40);
//
//                JLabel label = popLabels.get(index);
//                label.setBounds(panel.getX()+panel.getWidth()+10, panel.getY(), 100, 40);
//                label.setText(String.valueOf(countryPop));
//
//                label = jLabels.get(index);
//                label.setText(list.get(index).name);
//                cnt++;}

                System.out.print(list.get(0).getPopAbstractArrayByIndex(cnt)+ " ");
                cnt++;
                if (cnt == 10){
                    tm2.stop();
                    cnt=0;
                    System.out.println();
                }
            });
        tm2.start();
        world.setText(String.valueOf(countries.get(1).getPopByIndex(ind)));
        thYear.setText(String.valueOf(countries.get(0).getPopByIndex(ind)));



//
//        for (TopCountry country: list){
//            for(int i=0;i<10;i++)System.out.print(country.getPopAbstractArrayByIndex(i)+ " ");
//            System.out.println();
//        }
    }

}
