package AgendaV2;

import AgendaData.Schedule;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AgendaGUI extends JFrame
{
    private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    private ArrayList<Schedule> schedule = new ArrayList <>();

    private TableModel tableModelModel;
    private ButtonControl buttonControl;
    private JTable table;
    private JScrollPane scrollPane;

    public AgendaGUI()
    {
        super("Agenda Test");
        super.setSize(800,600);
        JPanel panel = new JPanel(new BorderLayout());

        this.table = new JTable(new TableModel());
        this.table.setFillsViewportHeight(true);
        this.scrollPane = new JScrollPane(this.table);
        this.buttonControl = new ButtonControl();
        panel.add(this.scrollPane,BorderLayout.CENTER);
        panel.add(this.buttonControl, BorderLayout.SOUTH);


        super.setContentPane(panel);
        super.setLocation((screenWidth / 2) - (getWidth() / 2), (screenHeight / 2) - (getHeight() / 2));
        super.setResizable(false);
        super.setVisible(true);
    }
}
