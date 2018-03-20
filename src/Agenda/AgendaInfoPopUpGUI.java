package Agenda;

import AgendaData.Act;
import AgendaData.Artist;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class AgendaInfoPopUpGUI extends JPanel
{
    private Act act;
    private int index;
    private int amount;

    public AgendaInfoPopUpGUI(int index, List<Act> acts)
    {
        act = acts.get(index);
        JFrame frame = new JFrame();
        this.setLayout(new GridLayout(5, 2));
        makeContent(this);
        frame.setSize(500, 200*this.amount);
        frame.setContentPane(this);
        frame.setVisible(true);
    }

    private void makeContent(JPanel panel)
    {
        SimpleDateFormat std = new SimpleDateFormat("HH:mm");
        panel.add(new JLabel("Artists:"));
        JPanel panel1 = new JPanel(new GridLayout(act.getArtists().size(), 1));

        for(Artist artist : act.getArtists())
        {
            panel1.add(new JLabel("Name: " + artist.getName() + "\n"));
            panel1.add(new JLabel("Genre: " + artist.getGenre() + "\n"));
            panel1.add(new JLabel("Photo: " + artist.getPhoto() + "\n\n"));
            amount++;
        }

        panel.add(panel1);
        panel.add(new JLabel("Podium:"));
        panel.add(new JLabel(act.getPodium().getName()));
        panel.add(new JLabel("Popularity:"));
        panel.add(new JLabel("" + act.getPopularity()));
        panel.add(new JLabel("Starting time:"));
        panel.add(new JLabel(std.format(act.getStartTime())));
        panel.add(new JLabel("Ending time:"));
        panel.add(new JLabel(std.format(act.getEndTime())));
    }
}
