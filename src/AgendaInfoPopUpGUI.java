package Agenda;

import AgendaData.Act;
import AgendaData.Artist;
import AgendaData.Podium;

import javax.swing.*;
import java.util.Date;
import java.util.List;

public class AgendaInfoPopUpGUI extends JPanel
{
    Act act;
    int index;

    public AgendaInfoPopUpGUI(int index, java.util.List<Act> acts)
    {
        act = acts.get(index);
        JFrame frame = new JFrame();
        frame.setSize(400, 300);
        frame.setContentPane(this);
        makeContent(this);
        frame.setVisible(true);
    }


    public void showInfo()
    {

    }
    public void makeContent(JPanel panel)
    {
        List<Artist> artists;
        artists = act.getArtists();
        JLabel info = new JLabel("Artists: " + "\n");
        JTextField textField = new JTextField();
        panel.add(info);

        String photoArtist;
        String content;

        for(Artist artist : artists)
        {
            content ="name: " + artist.getName() + "\n";


            content = content + "     Genre: " + artist.getGenre() + "\n";
            textField = new JTextField();
            textField.setText(content);
            panel.add(textField);

            photoArtist = artist.getPhoto();
            info = new JLabel(photoArtist);
            panel.add(info);
            info = new JLabel("");
            panel.add(info);
        }
        Podium podium = act.getPodium();
        info = new JLabel( "\nPodium:   " + podium.getName());
        panel.add(info);

        info = new JLabel( "Popularity:   " + act.getPopularity());
        panel.add(info);

        Date time = act.getStartTime();
        info = new JLabel( "Starting time:   " + time.getHours() + ":"+ time.getMinutes());
        panel.add(info);

        time = act.getEndTime();
        info = new JLabel( "Ending time:   " + time.getHours() + ":"+ time.getMinutes());
        panel.add(info);
    }
}
