import AgendaData.Act;
import AgendaData.Schedule;
import FileIO.JSONManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class deleteAct extends JPanel implements ActionListener
{
    private int index;
    private JButton yesButton;
    private JButton noButton;
    private JFrame frame;
    public deleteAct(int index)
    {
        super(new BorderLayout());
        this.index = index;
        frame = new JFrame();
        affirmation();
        frame.setContentPane(this);
//        frame.addWindowListener(new WindowAdapter()
//        {
//            @Override
//            public void windowClosing(WindowEvent e)
//            {
//                super.windowClosing(e);
//
//            }
//        });
        frame.pack();
        frame.setVisible(true);
    }

    public void affirmation()
    {
        //JPanel panel = new J
        JLabel warningLabel = new JLabel("Are you sure you want to delete this act?");
        yesButton = new JButton("Yes");
        noButton = new JButton("No");
        add(warningLabel, BorderLayout.CENTER);
        add(yesButton, BorderLayout.SOUTH);
        add(noButton, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == yesButton)
        {
            try
            {
                Schedule schedule = JSONManager.readFile();
                schedule.getActs().remove(index);
                JSONManager.writeToFile(schedule);
            }
            catch (Exception ex) {}
        }

    }
}
