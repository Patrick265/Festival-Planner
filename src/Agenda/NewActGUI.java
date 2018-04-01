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

    private JTextField startTimeField = new JTextField();
    private JTextField endTimeField = new JTextField();

    private JFrame frame;

    private Schedule schedule;
    private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

    public NewActGUI()
    {
        super(new BorderLayout());
        frame = new JFrame();
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

    public void buildPanel()
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
        panel.add(startTimeField);
        panel.add(new JLabel("        End Time: "));
        panel.add(endTimeField);

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

    public void saveInput() throws Exception
    {
        ArrayList<Artist> artists = new ArrayList<>();

        String artistInput = (String) artistField.getSelectedItem();
        String genreInput = genreField.getText();
        String popularityInput = popularityField.getText();
        String podiumInput = (String) podiumField.getSelectedItem();
        String startTimeInput = startTimeField.getText();
        String endTimeInput = endTimeField.getText();

        if(artistInput.contains(","))
        {
            String[] multipleArtists = artistInput.split(", ");
            for(int i = 0; i < multipleArtists.length; i++)
                artists.add(new Artist(multipleArtists[i], "Festival-Planner\\Resources\\Schedules\\defaultpicture.png", genreInput));
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