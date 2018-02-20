import AgendaData.Act;
import AgendaData.Artist;
import AgendaData.Podium;
import AgendaData.Schedule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
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
        JPanel panel = new JPanel();
        JTable table = makeContent(panel);
       // panel.add(table);
        setContentPane(table);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       // makeContent(panel);
        setVisible(true);
    }

    public JTable makeContent(JPanel panel) {
        JTable tabel= new JTable();
        Object[] name = {"Artist", "Podium", "Start time", "EndTime"};
        schedule = testData();

        Date start;
        Date end;
        String nameArtist;
        Artist artist;
        java.util.List<Artist> artists;
        Podium podium;
        String namePodium;


        int amoutOfTimes =0;

        java.util.List<Act> acts = schedule.getActs();
        Object[][] allInfo = new Object[acts.size()+1][4];
        allInfo[0][0] = name[0];
        allInfo[0][1] = name[1];
        allInfo[0][2] = name[2];
        allInfo[0][3] = name[3];
        int index = acts.size();
        for (int x = 0; x < index; x++)
        {
            act = acts.get(x);
            start = act.getStartTime();
            end = act.getEndTime();

            artists = act.getArtists();
            artist = artists.get(0);
            nameArtist = artist.getName();

            podium = act.getPodium();
            namePodium = podium.getName();

            Object[] info = {nameArtist, namePodium, start.toString(), end.toString()};
            allInfo[x+1][0] = info[0];
            allInfo[x+1][1] = info[1];
            allInfo[x+1][2] = info[2];
            allInfo[x+1][3] = info[3];

            if (amoutOfTimes == 0)
            {
                tabel = new JTable(allInfo, name);
            }
            amoutOfTimes ++;

        }
        panel.add(tabel);

        return tabel;

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
        Act testAct2 = new Act(10, new Date(2018, 7, 2, 20, 30, 00),
                new Date(2018, 7, 2, 22, 30, 00),artistss, testPodium);
        testAgenda.addAct(testAct);
        testAgenda.addAct(testAct2);
        return testAgenda;
    }


}
