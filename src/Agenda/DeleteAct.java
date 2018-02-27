package Agenda;

import AgendaData.Schedule;
import FileIO.JSONManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteAct extends JPanel implements ActionListener
{
    private int index;
    private JButton yesButton;
    private JButton noButton;
    private JFrame frame;
    public DeleteAct(int index)
    {
        super(new BorderLayout());
        this.index = index;
        frame = new JFrame();
        affirmation();
        frame.setContentPane(this);
        frame.pack();
        frame.setVisible(true);
    }

    public void affirmation()
    {
        JPanel panel = new JPanel();
        JLabel warningLabel = new JLabel("Are you sure you want to delete this act?");
        yesButton = new JButton("Yes");
        noButton = new JButton("No");
        add(warningLabel, BorderLayout.CENTER);
        panel.add(yesButton);
        panel.add(noButton);
        add(panel, BorderLayout.SOUTH);
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
