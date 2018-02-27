import javax.swing.*;

import AgendaData.Act;
import AgendaData.Artist;
import AgendaData.Podium;
import AgendaData.Schedule;
import Agenda.AgendaPopUpGUI;
import FileIO.*;

import java.util.ArrayList;
import java.util.Date;

/**
 * @Author Thomas Mandemaker, Patrick de Jong, Yannick van Dolen , Sergen Peker , Anastasia Hellemons
 * @version 1.0
 */
public class GUI
{
    private JFrame frame;
    private Schedule schedule;

    public GUI()
    {
        makeFrame();
        schedule = new Schedule();
        testAgenda();
    }

    /**
     * creates Swing frame and its contents.
     */
    private void makeFrame()
    {
        frame = new JFrame("Festival Planner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        makeMenuBar(frame);
        makeContent(frame);

        frame.setSize(1280, 720);
        frame.setVisible(true);
    }

    /**
     * creates the menu bar and its contents.
     * @param frame the frame wherein the menu bar is added to.
     */
    private void makeMenuBar(JFrame frame)
    {

        JMenuBar menu = new JMenuBar();
        frame.setJMenuBar(menu);

        //making file menu
        JMenu file = new JMenu("File");
        JMenuItem open = new JMenuItem("Open");
        file.add(open).addActionListener(e -> {
            try
            {
                new FileExplorer(0);
            } catch (Exception ex)
            {
                ex.printStackTrace();
            }
        });
        menu.add(file);

        //making agenda menu
        JMenu agenda = new JMenu("Agenda");
        JMenuItem viewAgenda = new JMenuItem("View agenda");
        agenda.add(viewAgenda);
        viewAgenda.addActionListener(e ->
        {
            //AgendaTableGUI agendaTableGUI = new AgendaTableGUI();
            AgendaPopUpGUI agendaPopUpGUI = new AgendaPopUpGUI();
        });
        menu.add(agenda);




        //making info menu
        JMenu info = new JMenu("Info");


        /**
         * Dit is voor de INFO kop
         */
        JMenuItem about = new JMenuItem("About");
        info.add(about);
        about.addActionListener(e ->
        {
            JOptionPane.showMessageDialog(frame,"Dit is een simulatie van een festival. \n" +
                                                        "Dit project is gemaakt door: B5", "Informatie", JOptionPane.INFORMATION_MESSAGE);
        });
        JMenuItem version = new JMenuItem("Version");
        version.addActionListener(e ->
        {
            JOptionPane.showMessageDialog(frame,"Versie 1.0", "Versie", JOptionPane.INFORMATION_MESSAGE);
        });

        info.add(version);
        menu.add(info);
    }

    /**
     * creates the content of the GUI
     * @param frame the frame wherein the content is added to.
     */
    private void makeContent(JFrame frame)
    {
        JPanel content = new JPanel();
        frame.add(content);
    }

    private void testAgenda()
    {
        Podium stageOne = new Podium("Stage One");
        Podium stageTwo = new Podium("Stage Two");

        Artist artJustinBieber = new Artist("Justin Bieber", "artists/justin_bieber.jpg", "pop");
        Artist artNickelback = new Artist("Nickelback", "artists/nickelback.jpg", "rock");
        Artist artFiftyCent = new Artist("50 Cent", "artists/fifty_cent.jpg", "hiphop");
        Artist artAvicii = new Artist("Avicii", "artists/avicii.jpg", "edm");

        Date actStart = new Date();
        Date actEnd = new Date();
        ArrayList tempArtists = new ArrayList<Artist>();


        // Adding a first act
        actStart.setTime(170000);
        actEnd.setTime(170100);
        tempArtists.add(artFiftyCent);
        tempArtists.add(artNickelback);
        Act actFortyFiveCent = new Act(15, actStart, actEnd, tempArtists, stageOne);

        // Adding a working act behind it
        tempArtists.remove(artNickelback);
        actStart.setTime(170150);
        actEnd.setTime(170300);
        Act actFollowupWorking = new Act(75, actStart, actEnd, tempArtists, stageOne);

        // Adding a broken act overlapping the second one
        tempArtists.clear();
        tempArtists.remove(artNickelback);
        tempArtists.add(artJustinBieber);
        actStart.setTime(170200);
        actEnd.setTime(170400);
        Act actFollowupBroken = new Act(30, actStart, actEnd, tempArtists, stageOne);

        // Adding an overlapping act on another podium
        tempArtists.clear();
        tempArtists.add(artAvicii);
        actStart.setTime(170002000);
        actEnd.setTime(170003000);
        Act actOtherPodium = new Act(2, actStart, actEnd, tempArtists, stageTwo);

        //act firstAct = new Act()
        System.out.println("Start adding actFortyFiveCent...");
        schedule.addAct(actFortyFiveCent);
        System.out.println("Start adding actFollowupWorking...");
        schedule.addAct(actFollowupWorking);
        System.out.println("Start adding actFollowupBroken...");
        schedule.addAct(actFollowupBroken);
        System.out.println("Start adding actOtherPodium...");
        schedule.addAct(actOtherPodium);
    }
}
