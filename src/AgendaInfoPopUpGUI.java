import AgendaData.Act;
import AgendaData.Artist;
import AgendaData.Podium;
import AgendaData.Schedule;

import javax.swing.*;
import javax.tools.JavaCompiler;
import java.awt.*;
import java.util.Date;

public class AgendaInfoPopUpGUI extends JFrame
{
    Act act;
    int index;

    public AgendaInfoPopUpGUI(int index, java.util.List<Act> acts)
    {
        super("Info");

       act = acts.get(index);
        //act = new Act;
        setSize(400,200);
        JPanel panel = new JPanel(new GridLayout(0,1));
        makeContent(panel);
        setContentPane(panel);
       // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void makeContent(JPanel panel)
    {
        java.util.List<Artist> artists;
        artists = act.getArtists();
        JLabel info = new JLabel("Artists: " + "\n");
        panel.add(info);
        info = new JLabel("");
        panel.add(info);

        String photoArtist;
        String content;

        for(Artist artist : artists)
        {
            content ="name: " + artist.getName() + "\n";


            content = content + "     Genre: " + artist.getGenre() + "\n";
            info = new JLabel(content);
            panel.add(info);

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
        info = new JLabel( "Starting time:   " + time.toString());
        panel.add(info);

        time = act.getEndTime();
        info = new JLabel( "Ending time:   " + time.toString());
        panel.add(info);
    }
}
