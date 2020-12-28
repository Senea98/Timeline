package project.Timeline;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.*;

public class TimelinePanel extends JFrame {
    String[][] excel;
    Timer tm1;
    JButton start;
    ArrayList<JPanel> jPanels = new ArrayList<>();
    ArrayList<JLabel> jLabels = new ArrayList<>();
    ArrayList<JLabel> popLabels = new ArrayList<>();
    JLabel world, thYear;
    String[][] colors= new String[][] {
            {"#bada55","#7fe5f0","#ff0000","#ff80ed","#696969", "#065535","#133337"},
            {"unused","unused","unused","unused","unused","unused","unused"}
    };
    int year, top;

    public TimelinePanel(String[][] excelData, int topd) {
        super("Timeline");
        excel = excelData;
        top = topd;
        createElements();
        year =0;
        tm1 = new Timer(100, e -> {

            year++;
            for(JPanel panel:jPanels){
                long countryPop = Long.parseLong(excel[jPanels.indexOf(panel)+2][year+1]);
                long worldPop = Long.parseLong(excel[1][60]);
                int wid = Math.round(countryPop*5000/worldPop);
                panel.setBounds(panel.getX(), panel.getY(), wid, 40);

                int index = jPanels.indexOf(panel);
                JLabel label = popLabels.get(index);
                label.setBounds(panel.getX()+panel.getWidth()+10, panel.getY(), 100, 40);
                label.setText(String.valueOf(countryPop));

                world.setText(excel[1][year+1]);
                thYear.setText(excel[0][year+1]);
            }
            if (year == excel[0].length-2) {
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
        world.setText(excel[1][2]);
        world.setBounds(560, 350, 150, 30);
        world.setFont(world.getFont().deriveFont(18.0f));
        thYear = new JLabel();
        thYear.setText(excel[0][2]);
        thYear.setBounds(560, 300, 150, 30);
        thYear.setFont(thYear.getFont().deriveFont(24.0f));
        for(int i=0;i<top;i++){
            JPanel panel = new JPanel();
            setColor(panel);
            panel.setBounds(15, 50*(i+1), 5, 40);
            jPanels.add(panel);
        }
        for(int i=0;i<top;i++) {
            JLabel label = new JLabel(excel[i+2][0]);
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

}
