import AgendaData.Act;
import AgendaData.Artist;
import AgendaData.Podium;
import AgendaData.Schedule;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

public class AgendaPopUpGUI extends JFrame
{

    Schedule schedule;
    Act act;

    public static void main(String[]args)
    {
        new AgendaPopUpGUI();
    }

    public AgendaPopUpGUI()
    {
    super("");

    schedule = new Schedule();
    //act = new Act;
        setSize(800,600);
        JPanel panel = new JPanel(new GridLayout(0, 4));
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        makeContent(panel);
        setVisible(true);
    }

    public void makeContent(JPanel panel) {
        schedule = testData();
        JPanel artistPanel = new JPanel();
        artistPanel.add(new JLabel("Artist"));
        panel.add(artistPanel);

        JPanel stagePanel = new JPanel();
        stagePanel.add(new JLabel("Podium"));
        panel.add(stagePanel);

        JPanel startPanel = new JPanel();
        stagePanel.add(new JLabel("Start time"));
        panel.add(startPanel);

        JPanel endPanel = new JPanel();
        stagePanel.add(new JLabel("End time"));
        panel.add(endPanel);


        Date start;
        Date end;
        String nameArtist;
        Artist artist;
        java.util.List<Artist> artists;
        Podium podium;
        String namePodium;

        java.util.List<Act> acts = schedule.getActs();
        int index = acts.size();
        for (int x = 0; x < index; x++) {
            act = acts.get(x);
            start = act.getStartTime();
            end = act.getEndTime();

            artists = act.getArtists();
            artist = artists.get(0);
            nameArtist = artist.getName();

            podium = act.getPodium();
            namePodium = podium.getName();

            artistPanel = new JPanel();
            artistPanel.add(new JLabel(nameArtist));
            panel.add(artistPanel);

            stagePanel = new JPanel();
            stagePanel.add(new JLabel(namePodium));
            panel.add(stagePanel);

            startPanel = new JPanel();
            startPanel.add(new JLabel(start.toString()));
            panel.add(startPanel);

            endPanel = new JPanel();
            endPanel.add(new JLabel(end.toString()));
            panel.add(endPanel);


        }

    }

    public Schedule testData()
    {
        Schedule testAgenda = new Schedule();
        Artist testArtist = new Artist("Makulka", "photo.jpg", "game Arrangements");
        Artist testArtist2 = new Artist("JulesX","photo.png","metal");
        ArrayList<Artist> artistss = new ArrayList<>();
        artistss.add(testArtist);
        artistss.add(testArtist2);
        Podium testPodium = new Podium("EastNewCoaster");
        Act testAct = new Act(10, new Date(2018, 7, 2, 17, 30, 00),
                new Date(2018, 7, 2, 19, 30, 00),artistss, testPodium);
        testAgenda.addAct(testAct);
        return testAgenda;
    }

}
