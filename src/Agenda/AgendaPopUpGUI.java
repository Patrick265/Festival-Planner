package Agenda;

import AgendaData.Act;
import AgendaData.Artist;
import AgendaData.Podium;
import AgendaData.Schedule;
import FileIO.JSONManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
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
        mainPanel.add(buttonPanel(), BorderLayout.SOUTH);

        setContentPane(mainPanel);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public JPanel buttonPanel()
    {
        JPanel panel = new JPanel(new FlowLayout());

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e ->
        {
            new MakeAct();
        });

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e ->
        {
            new DeleteAct(table.getSelectedRow());
        });

        panel.add(addButton);
        panel.add(deleteButton);

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
                        Agenda.AgendaInfoPopUpGUI popup = new Agenda.AgendaInfoPopUpGUI(index, acts);
                    }
                }
            }
        });
        Action action = new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                TableCellListener tcl = (TableCellListener) e.getSource();
                Act changedAct = acts.get(tcl.getRow() - 1);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                switch (tcl.getColumn())
                {
                    case 0: String artistString = (String)tcl.getNewValue();
                            if(artistString.contains(","))
                            {
                                String[] artistsChanged = artistString.split(", ");
                                for(int i = 0; i < artistsChanged.length; i++)
                                {
                                    changedAct.getArtists().get(i).setName(artistsChanged[i]);
                                }
                            }
                            else
                            {
                                changedAct.getArtists().get(0).setName(artistString);
                            }
                        break;
                    case 1:
                        changedAct.getPodium().setName((String) tcl.getNewValue());
                        break;
                    case 2:
                        try
                        {
                            changedAct.setStartTime(simpleDateFormat.parse((String) tcl.getNewValue()));
                        } catch (Exception ex) {}
                        break;
                    case 3:
                        try
                        {
                            changedAct.setStartTime(simpleDateFormat.parse((String) tcl.getNewValue()));
                        } catch (Exception ex) {}
                        break;
                }
                acts.set(tcl.getRow() - 1, changedAct);
                schedule.setActs(acts);
                try
                {
                    JSONManager.writeToFile(schedule);
                } catch (Exception ex)
                {

                }
            }
        };

        TableCellListener tcl = new TableCellListener(table, action);
        return table;


    }
}