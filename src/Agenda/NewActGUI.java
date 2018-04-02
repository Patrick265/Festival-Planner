package Agenda;

import AgendaData.Act;
import AgendaData.Artist;
import AgendaData.Podium;
import AgendaData.Schedule;
import FileIO.JSONManager;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class NewActGUI extends JPanel
{
    private JComboBox<String> artistField;
    private JTextField genreField = new JTextField();
    private JTextField popularityField = new JTextField();
    private String[] podiumText = {"Podium 1", "Podium 2", "Podium 3"};
    private JComboBox<String> podiumField = new JComboBox<>(podiumText);


    private String[] times = {
                    "1:00", "1:15", "1:30", "1:45", "2:00", "2:15", "2:30", "2:45","3:00", "3:15", "3:30", "3:45",
                    "4:00", "4:15", "4:30", "4:45", "5:00", "5:15", "5:30", "5:45","6:00", "6:15", "6:30", "6:45",
                    "7:00", "7:15", "7:30", "7:45", "8:00", "8:15", "8:30", "8:45","9:00", "9:15", "9:30", "9:45",
                    "10:00", "10:15", "10:30", "10:45", "11:00", "11:15", "11:30", "11:45","12:00", "12:15", "12:30", "12:45",
                    "13:00", "13:15", "13:30", "13:45", "14:00", "14:15", "14:30", "14:45","15:00", "15:15", "15:30", "15:45",
                    "16:00", "16:15", "16:30", "16:45", "17:00", "17:15", "17:30", "17:45","18:00", "18:15", "18:30", "18:45",
                    "19:00", "19:15", "19:30", "19:45", "20:00", "20:15", "20:30", "20:45","21:00", "21:15", "21:30", "21:45",
                    "22:00", "22:15", "22:30", "22:45", "23:00", "23:15", "23:30", "23:45","24:00"};
    private JComboBox<String> startTime = new JComboBox <>(times);
    private JComboBox<String> endTime = new JComboBox <>(times);
    private JTextField startTimeField = new JTextField();
    private JTextField endTimeField = new JTextField();

    private JFrame frame;

    private Schedule schedule;
    private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

    public NewActGUI()
    {
        super(new BorderLayout());
        frame = new JFrame("Add an Act");
        frame.setSize(400, 400);
        frame.setLocation((screenWidth / 2) - (frame.getWidth() / 2), (screenHeight / 2) - (frame.getHeight() / 2));

        try
        {
            this.schedule = JSONManager.readFile();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        String[] artistText = new String[this.schedule.getArtists().size()];
        int index = 0;
        for (Map.Entry<String, Artist> entry : this.schedule.getArtists().entrySet())
        {
            artistText[index] = entry.getKey();
            index++;
        }

        this.artistField = new JComboBox<>(artistText);
        buildPanel();


        frame.setContentPane(this);
        frame.setVisible(true);
    }

    private void buildPanel()
    {
        JPanel panel = new JPanel(new GridLayout(6, 2, 0, 32));

        panel.add(new JLabel("        Artist(s):"));
        panel.add(artistField);
        panel.add(new JLabel("        Genre:"));
        panel.add(genreField);
        panel.add(new JLabel("        Popularity: "));
        panel.add(popularityField);
        panel.add(new JLabel("        Podium:"));
        panel.add(podiumField);
        panel.add(new JLabel("        Start Time:"));
        this.startTime.setSelectedIndex(29);
        panel.add(startTime);
        panel.add(new JLabel("        End Time: "));
        this.endTime.setSelectedIndex(30);
        panel.add(endTime);

        JButton saveButton = new JButton("Save Act");
        saveButton.addActionListener(e -> {
            try
            {
                saveInput();
                AgendaGUI.updateTable();
            }catch (Exception ex)
            {
                ex.printStackTrace();
            }
        });

        add(panel, BorderLayout.CENTER);
        add(saveButton, BorderLayout.SOUTH);
    }

    private void saveInput() throws Exception
    {
        ArrayList<Artist> artists = new ArrayList<>();

        String artistInput = (String) artistField.getSelectedItem();
        String genreInput = genreField.getText();
        String popularityInput = popularityField.getText();
        String podiumInput = (String) podiumField.getSelectedItem();
        String startTimeInput = String.valueOf(startTime.getSelectedItem());
        String endTimeInput = String.valueOf(endTime.getSelectedItem());



        if(artistInput.contains(","))
        {
            String[] multipleArtists = artistInput.split(", ");
            for (String artistName : multipleArtists)
                artists.add(new Artist(artistName, "Festival-Planner\\Resources\\Schedules\\defaultpicture.png", genreInput));
        }
        else
        {
            artists.add(new Artist(artistInput, "Festival-Planner\\Resources\\Schedules\\defaultpicture.png", genreInput));
        }
        Podium podium = new Podium(podiumInput);

        try
        {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
            Date startTime = simpleDateFormat.parse(startTimeInput);
            Date endTime = simpleDateFormat.parse(endTimeInput);
            Act act = new Act(Integer.parseInt(popularityInput), startTime, endTime, artists, podium);
            schedule.addAct(act);
            JSONManager.writeToFile(schedule);
            frame.dispose();
        }
        catch(NumberFormatException e)
        {
            JOptionPane.showMessageDialog(frame, "Inserted popularity is too big.");
        }
        catch(ParseException e)
        {
            JOptionPane.showMessageDialog(frame, "Inserted data either invalid or empty.");
        }
    }
}