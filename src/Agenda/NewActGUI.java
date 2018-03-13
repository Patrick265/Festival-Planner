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

public class NewActGUI extends JPanel
{
    private JTextField artistField = new JTextField();
    private JTextField genreField = new JTextField();
    private JTextField popularityField = new JTextField();
    private String[] podiumText = {"Podium 1", "Podium 2", "Podium 3"};
    private JComboBox<String> podiumField = new JComboBox(podiumText);


    private JTextField startTimeField = new JTextField();
    private JTextField endTimeField = new JTextField();

    private JFrame frame;

    private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

    public NewActGUI()
    {
        super(new BorderLayout());
        frame = new JFrame();
        frame.setSize(400, 400);
        frame.setLocation((screenWidth / 2) - (frame.getWidth() / 2), (screenHeight / 2) - (frame.getHeight() / 2));
        headPanel();
        frame.setContentPane(this);
        frame.setVisible(true);
    }

    public void headPanel()
    {
        JPanel panel = new JPanel(new GridLayout(6, 2, 0, 32));
        JLabel artistLabel = new JLabel("        Artist(s):");
        JLabel genreLabel = new JLabel("        Genre:");
        JLabel popularityLabel = new JLabel("        Popularity: ");
        JLabel podiumLabel = new JLabel("        Podium:");
        JLabel startLabel = new JLabel("        Start Time:");
        JLabel endLabel = new JLabel("        End Time: ");

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
        String podiumInput = (String) podiumField.getSelectedItem();
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