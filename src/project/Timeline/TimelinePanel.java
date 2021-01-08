package project.Timeline;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

class TopCountry{
    String name;
    long population;

    public TopCountry(String name, long population) {
        this.name = name;
        this.population = population;
    }
}

public class TimelinePanel extends JFrame {

    private Timer tm1;
    private JButton start;
    private int year, top, count;
    private JLabel world, thYear;
    private ArrayList<Country> countries;
    private Country worldPop, years;
    private ArrayList<TopCountry> list;
    private ArrayList<Panel> panels = new ArrayList<>();
    private HashMap<Panel, Integer> newPosition = new HashMap<>();

    private String[][] colors= new String[][] {
            {"#bada55","#7fe5f0","#ff0000","#ff80ed","#696969", "#065535","#133337"},
            {"unused","unused","unused","unused","unused","unused","unused"}
    };
    // Timeline Constructor
    public TimelinePanel(ArrayList<Country> countries, int top) {
        super("Timeline");
        this.years = countries.get(0);
        this.worldPop = countries.get(1);
        this.countries = new ArrayList<>(countries.subList(2, countries.size()));
        this.top = top;
        list = topCountries(this.countries, 0);
        createElements();
        setTimer1();
        addElements();
    }

    private void setTimer1() {
        year =0;
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

    }

    private int calculateWidth(long population){
        return Math.round(population*5000/worldPop.getLastPop());
    }
    private void createElements() {
        start = new JButton("start");
        start.setBounds(560, 400, 100, 20);
        world = new JLabel();
        world.setText(String.valueOf(worldPop.getPopByIndex(0)));
        world.setBounds(560, 350, 150, 30);
        world.setFont(world.getFont().deriveFont(18.0f));
        thYear = new JLabel();
        thYear.setText(String.valueOf(years.getPopByIndex(0)));
        thYear.setBounds(560, 300, 150, 30);
        thYear.setFont(thYear.getFont().deriveFont(24.0f));
        for (int i=0;i<top;i++){
            Panel panel = new Panel(list.get(i).name, list.get(i).population);
            panel.setPosition(50*(i+1), calculateWidth(list.get(i).population));
            panels.add(panel);
        }
    }

    private void addElements() {
        add(start);
        add(world);
        add(thYear);
        for (Panel panel:panels)addPanel(panel);
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

    private ArrayList<TopCountry> topCountries(ArrayList<Country> countries, int index) {
        ArrayList<TopCountry> list = new ArrayList<>();
        for (int i = 0; i < top; i++)
            list.add(new TopCountry(countries.get(i).getName(), countries.get(i).getPopByIndex(index)));

        for (int i = top; i < countries.size(); i++) {
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
    private void movePanel(){

        Timer tm2 = new Timer(90, e->{

            for (Panel panel : newPosition.keySet()){
                int to = newPosition.get(panel);
                if(to<=top+1) {
                    int from = panels.indexOf(panel);
                    if (from != to) {
                        System.out.println(from + " " + to);
                        int y = (to - from) * 5;
                        panel.changeY(y);
                    }
                }
            }
            count++;
            if (count==10){
                count=0;
                updatePanels();
                ((Timer)e.getSource()).stop();

            }

        });
        tm2.start();
    }

    private void updatePanels(){
        boolean ok = true;
        while (ok){
            ok=false;
            for (Panel panel : newPosition.keySet()) {
                int from = panels.indexOf(panel), to = newPosition.get(panel);
                if (to==top+1) {
                    newPosition.put(panel, top+2);
                    System.out.println(panel.getCountry().getText() + " Added value 8 !!!!!!!!!!");
                }
                else if (from != to && to!=top+2) {
                        Panel panel1 = panels.get(from);
                        panels.remove(from);
                        addPanel(panel1);
                        panels.add(to, panel1);
                        System.out.println(panel1.getCountry().getText() + " to " + to);
                        ok = true;
                    }

            }
        }
        for (Panel panel : panels)System.out.println(panel.getCountry().getText());

    }

    private void removePosition() {

        Iterator<Panel> iterator = newPosition.keySet().iterator();
        while(iterator.hasNext()){
            Panel panel = iterator.next();
            if (panel.getPanel().getY()>(top+2)*50){
                iterator.remove();
                panels.remove(panel);
            }

        }
    }

    private void addPanel(Panel panel){
        add(panel.getCountry());
        add(panel.getPanel());
        add(panel.getPopulation());
    }
    private void cycle(int ind){

        list = topCountries(countries, ind);
        System.out.println(years.getPopByIndex(ind));

        // check wich panels must remain inside the frame - compare topCountry list with panels. country list
        for (Panel panel: panels){
            boolean exists = false;
            for(int i=0;i<list.size();i++){
                if (panel.getCountry().getText() == list.get(i).name){
                    newPosition.put(panel, i);// if current panel is still inside top list, it's position is set to updated position
                    exists = true;

                    panel.changePop(list.get(i).population);
                    panel.changeWidth(calculateWidth(list.get(i).population));
                    break;
                }
            }
            if (exists == false){
                newPosition.put(panel, top+1); // if current panel is not inside top list anymore, it's position is set to top+1
            }
        }
        // check wich countries must be added to the frame
        for(TopCountry country:list){
            boolean exist = false;
            for(Panel panel: panels){
                if (country.name == panel.getCountry().getText()){
                    exist = true;
                    break;
                }
            }
            if (exist == false){
                Panel panel = new Panel(country.name, country.population);
                panel.setPosition(50*(panels.size()+1), calculateWidth(country.population));
                addPanel(panel);
                panels.add(panel);

                newPosition.put(panel, list.indexOf(country));

            }
        }
        System.out.println();
        for (Panel panel : newPosition.keySet())
            System.out.println(panel.getCountry().getText()+" -> " + newPosition.get(panel));
        System.out.println();

        movePanel();

        removePosition();
        world.setText(String.valueOf(worldPop.getPopByIndex(ind)));
        thYear.setText(String.valueOf(years.getPopByIndex(ind)));
    }

}
