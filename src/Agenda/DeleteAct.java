package Agenda;

import AgendaData.Schedule;
import FileIO.JSONManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteAct extends JPanel
{
    private int index;
    private JButton yesButton;
    private JButton noButton;
    private JFrame frame;
    private JPanel mainPanel;

    public DeleteAct(int index, JPanel mainPanel)
    {
        super(new BorderLayout());
        this.index = index;
        frame = new JFrame();
        this.mainPanel = mainPanel;
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
        yesButton.addActionListener(e ->
        {

        });
        noButton = new JButton("No");
        noButton.addActionListener(e ->
        {

        });
        add(warningLabel, BorderLayout.CENTER);
        panel.add(yesButton);
        panel.add(noButton);
        add(panel, BorderLayout.SOUTH);
    }
}