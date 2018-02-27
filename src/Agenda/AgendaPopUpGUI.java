package Agenda;

import AgendaData.Act;
import AgendaData.Artist;
import AgendaData.Podium;
import AgendaData.Schedule;
import FileIO.FileExplorer;
import FileIO.JSONManager;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EventListener;
import java.util.List;

public class AgendaPopUpGUI extends JFrame
{
    private Schedule schedule;
    private Act act;
    private JTable table;
    private JPanel mainPanel;

    public AgendaPopUpGUI()
    {
        super("Agenda");
        //JFrame frame = new JFrame("Agenda");
        //act = new Act;
        setSize(800, 600);
        mainPanel = new JPanel(new BorderLayout());
        JPanel panel = new JPanel();
        table = makeContent(panel);
        mainPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        mainPanel.add(buttonPanel(), BorderLayout.SOUTH);

        setContentPane(mainPanel);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public JPanel buttonPanel()
    {
        JPanel panel = new JPanel(new FlowLayout());

        JButton addButton = new JButton("Add");
        addButton.addActionListener( e -> new MakeAct() );

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e ->
        {
            new DeleteAct(table.getSelectedRow(), mainPanel);

        });

        panel.add(addButton);
        panel.add(deleteButton);

        return panel;
    }

    public JTable makeContent(JPanel panel)
    {
        table = new JTable();
        Object[] name = {"Artist(s)", "Genre", "Popularity", "Podium", "Start time", "End time" };
        try
        {
            schedule = JSONManager.readFile();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        Date start;
        Date end;
        String startTime = "";
        String endTime = "";
        String artistName = "";
        Podium podium;
        String genre;
        String popularity;
        String namePodium;


        List<Act> acts = schedule.getActs();
        Object[][] allInfo = new Object[acts.size()][6];

        int index = acts.size();
        for (int i = 0; i < index; i++)
        {
            act = acts.get(i);
            start = act.getStartTime();
            end = act.getEndTime();

            startTime = "";
            if(start.getHours() < 10) { startTime += 0; }
            startTime += start.getHours() + ":";
            if(start.getMinutes() < 10) { startTime += 0; }
            startTime += start.getMinutes();

            endTime = "";
            if(end.getHours() < 10) { endTime += 0; }
            endTime += end.getHours() + ":";
            if(end.getMinutes() < 10) { endTime += 0; }
            endTime += end.getMinutes();

            for(int j = 0; j < act.getArtists().size(); j++)
            {
                if(j < act.getArtists().size() - 1)
                {
                    artistName += act.getArtists().get(j).getName() + ", ";
                }
                else
                {
                    artistName += act.getArtists().get(j).getName();
                }
            }

            genre = "";

            for(int j = 0; j < act.getArtists().size(); j++)
            {
                if(j > 0)
                {
                    genre += ", ";
                }
                genre += act.getArtists().get(j).getGenre();
            }

            popularity = "" + act.getPopularity();
            podium = act.getPodium();
            namePodium = podium.getName();

            Object[] info = {artistName, genre, popularity, namePodium, startTime, endTime};

            for (int j = 0; j < info.length; j++)
                allInfo[i][j] = info[j];

            artistName = "";
        }

        table = new JTable(allInfo, name);
        panel.add(table);

        table.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent evt)
            {
                if (evt.getButton() == 3)
                {
                    int row = table.rowAtPoint(evt.getPoint());
                    int col = table.columnAtPoint(evt.getPoint());
                    if (row >= 0 && col >= 0)
                    {
                        int index = row;
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
                        }
                        catch (Exception ex) {}
                        break;
                    case 3:
                        try
                        {
                            changedAct.setStartTime(simpleDateFormat.parse((String) tcl.getNewValue()));
                        }
                        catch (Exception ex) {}
                        break;
                }
                acts.set(tcl.getRow() - 1, changedAct);
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