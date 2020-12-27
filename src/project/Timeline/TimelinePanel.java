package project.Timeline;
import java.awt.Color;
import javax.swing.*;

public class TimelinePanel extends JFrame {
    String[][] excel;
    Timer tm1;
    JButton start;
    JPanel panel, panel1, panel2, panel3, panel4, panel5;
    JLabel label, label1, label2, label3, label4, label5;
    String[][] colors= new String[][] {
            {"#bada55","#7fe5f0","#ff0000","#ff80ed","#696969","#133337", "#065535"},
            {"unused","unused","unused","unused","unused","unused","unused"}
    };

    public TimelinePanel(String[][] excelData) {
        super("Timeline");
        excel = excelData;
        createElements();

        tm1 = new Timer(10, e -> {

            panel.setBounds(60, 30, panel.getWidth() + 5, 40);

            if (panel.getWidth() == 350) {
                tm1.stop();
                start.setEnabled(true);
            }
        });
        //Timer1 Start
        start.addActionListener(e -> tm1.start());

        addElements();
    }

    private void addElements() {
        add(start);
        add(label1);
        add(panel);
        add(panel1);
        setLayout(null);
        setSize(1500, 500);
        getContentPane().setBackground(Color.decode("#bdb76b"));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setColor(JPanel panel1) {
        for(int i=0;i<colors.length;i++)
            if(colors[1][i]=="unused"){
                panel1.setBackground(Color.decode(colors[0][i]));
                colors[1][i]="used";
            }
    }

    private void createElements() {
        start = new JButton("start");
        start.setBounds(260, 400, 100, 20);
        label1 = new JLabel(excel[2][1]);
        label1.setBounds(10,30,50,40);
        label1.setFont (label1.getFont ().deriveFont (14.0f));
        panel = new JPanel();
        setColor(panel);
        panel.setBounds(45, 30, 5, 40);
        panel1 = new JPanel();
        setColor(panel1);
        panel1.setBounds(30, 80, 5, 40);
    }

    void increment(int i){
        i++;
    }
    void reset(int i){
        i=0;
    }
}
