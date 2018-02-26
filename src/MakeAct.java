import AgendaData.Act;
import AgendaData.Artist;
import AgendaData.Podium;
import AgendaData.Schedule;
import FileIO.JSONManager;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MakeAct extends JPanel
{
    private JTextField artistField = new JTextField();
    private JTextField genreField = new JTextField();
    private JTextField popularityField = new JTextField();
    private JTextField podiumField = new JTextField();
    private JTextField startTimeField = new JTextField();
    private JTextField endTimeField = new JTextField();

    public MakeAct()
    {
        super(new BorderLayout());
        JFrame frame = new JFrame();
        frame.setSize(400, 400);
        headPanel();
        frame.setContentPane(this);
        frame.setVisible(true);
    }

    public void headPanel()
    {
        JPanel panel = new JPanel(new GridLayout(6, 2, 0, 5));
        JLabel artistLabel = new JLabel("Artist(s):");
        JLabel genreLabel = new JLabel("Genre:");
        JLabel popularityLabel = new JLabel("Popularity: ");
        JLabel podiumLabel = new JLabel("Podium:");
        JLabel startLabel = new JLabel("StartTime:");
        JLabel endLabel = new JLabel("EndTime: ");

        panel.add(artistLabel);
        panel.add(artistField);
        panel.add(genreLabel);
        panel.add(genreField);
        panel.add(popularityLabel);
        panel.add(popularityField);
        panel.add(podiumLabel);
        panel.add(podiumField);
        panel.add(startLabel);
        panel.add(startTimeField);
        panel.add(endLabel);
        panel.add(endTimeField);

        JButton saveButton = new JButton("Save Act");
        saveButton.addActionListener(e -> {
            try
            {
                saveInput();
            }catch (Exception ex)
            {
                ex.printStackTrace();
            }
        });

        add(panel, BorderLayout.CENTER);
        add(saveButton, BorderLayout.SOUTH);
    }

    public void saveInput() throws Exception
    {
        Schedule schedule = JSONManager.readFile();
        ArrayList<Artist> artists = new ArrayList<>();

        String artistInput = artistField.getText();
        String genreInput = genreField.getText();
        String popularityInput = popularityField.getText();
        String podiumInput = podiumField.getText();
        String startTimeInput = startTimeField.getText();
        String endTimeInput = endTimeField.getText();

        if(artistInput.contains(","))
        {
            String[] multipleArtists = artistInput.split(", ");
            for(int i = 0; i < multipleArtists.length; i++)
                artists.add(new Artist(multipleArtists[i], "", genreInput));
        }
        else
        {
            artists.add(new Artist(artistInput, "", genreInput));
        }
        Podium podium = new Podium(podiumInput);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date startTime = simpleDateFormat.parse(startTimeInput);
        Date endTime = simpleDateFormat.parse(endTimeInput);
        try
        {
            Act act = new Act(Integer.parseInt(popularityInput), startTime, endTime, artists, podium);
        }catch(Exception e){}
        schedule.addAct(act);
        JSONManager.writeToFile(schedule);
    }
}