import AgendaData.Act;
import AgendaData.Artist;
import AgendaData.Podium;
import AgendaData.Schedule;
import FileIO.JSONManager;
import com.sun.org.apache.bcel.internal.generic.SWITCH;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;

public class AgendaPopUpGUI extends JFrame
{
    Schedule schedule;
    Act act;
    JTable table;


    public AgendaPopUpGUI()
    {
        super("Agenda");
        //act = new Act;
        setSize(800, 600);
        JPanel panel = new JPanel();
        JPanel mainPanel = new JPanel(new BorderLayout());
        table = makeContent(panel);
        mainPanel.add(table, BorderLayout.CENTER);

        setContentPane(table);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public JPanel buttonPanel()
    {
        JPanel panel = new JPanel(new FlowLayout());

        JButton saveButton = new JButton("save");
        saveButton.addActionListener(e -> {

        });

        return panel;
    }

    public JTable makeContent(JPanel panel)
    {
        table = new JTable();
        Object[] name = {"Artist", "Podium", "Start time", "EndTime"};
        try
        {
            schedule = JSONManager.readFile();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        Date start;
        Date end;
        String startTime;
        String endTime;
        String nameArtist = "";
        Artist artist;
        java.util.List<Artist> artists;
        Podium podium;
        String namePodium;


        int amoutOfTimes = 0;

        java.util.List<Act> acts = schedule.getActs();
        Object[][] allInfo = new Object[acts.size() + 1][4];
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
            startTime = "" + start.getHours() + ":" + start.getMinutes();
            endTime = end.getHours() + ":" + end.getMinutes();

            for (Artist a : act.getArtists())
                nameArtist += a.getName() + " ";

//            artists = act.getArtists();
//            nameArtist = artists.getName();

            podium = act.getPodium();
            namePodium = podium.getName();

            Object[] info = {nameArtist, namePodium, startTime, endTime};
            allInfo[x + 1][0] = info[0];
            allInfo[x + 1][1] = info[1];
            allInfo[x + 1][2] = info[2];
            allInfo[x + 1][3] = info[3];

            nameArtist = "";


        }
        table = new JTable(allInfo, name);
        panel.add(table);

        table.addMouseListener(new java.awt.event.MouseAdapter()
        {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                if (evt.getClickCount() == 2)
                {
                    int row = table.rowAtPoint(evt.getPoint());
                    int col = table.columnAtPoint(evt.getPoint());
                    if (row >= 1 && col >= 0)
                    {
                        int index = row - 1;
                        AgendaInfoPopUpGUI popup = new AgendaInfoPopUpGUI(index, acts);
                    }
                }
            }
        });
        Action action = new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                TableCellListener tcl = (TableCellListener)e.getSource();
                Act changedAct = acts.get(tcl.getRow()-1);
                switch (tcl.getColumn())
                {
                    case 0: changedAct.getArtists().get(0).setName((String)tcl.getNewValue());
//                        String a = (String)tcl.getNewValue();
//                            String[] artistList = a.
//                            for(int i = 0; i < changedAct.getArtists().size(); i++)
//                                changedAct.getArtists().get(i).setName(artistList[i]);
                    break;
                    case 1: changedAct.getPodium().setName((String)tcl.getNewValue());
                    break;
                }
                acts.set(tcl.getRow()-1, changedAct);
                schedule.setActs(acts);
                try
                {
                    JSONManager.writeToFile(schedule);
                }
                catch (Exception ex)
                {

                }
            }
        };

        TableCellListener tcl = new TableCellListener(table, action);
        return table;


    }
}
