package Agenda;

import AgendaData.Artist;
import AgendaData.Schedule;
import FileIO.JSONManager;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class ArtistHandlerGUI extends JPanel
{
    private JComboBox<String> artistField;
    private JTextField artistTextField = new JTextField();
    private JTextField genreField = new JTextField();

    private JFrame frame;

    private Schedule schedule;
    private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    private boolean deleting = false;

    public ArtistHandlerGUI(boolean deleting)
    {
        super(new BorderLayout());
        frame = new JFrame();
        frame.setSize(200, 100);
        frame.setLocation((screenWidth / 2) - (frame.getWidth() / 2), (screenHeight / 2) - (frame.getHeight() / 2));

        if (deleting)
        {
            frame.setSize(100, 100);
            this.deleting = true;
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
        }
        buildPanel();

        frame.setContentPane(this);
        frame.setVisible(true);
    }

    public void buildPanel()
    {
        JPanel panel;
        if (deleting)
        {
            panel = new JPanel(new GridLayout(1, 2, 0, 32));
            panel.add(new JLabel("        Select artist you want to delete:"));
            panel.add(artistField);
        }
        else
        {
            panel = new JPanel(new GridLayout(2, 2, 0, 32));
            panel.add(new JLabel("        Artist:"));
            panel.add(artistTextField);
            panel.add(new JLabel("        Genre:"));
            panel.add(genreField);
        }

        JButton saveButton = new JButton("Save Act");
        saveButton.addActionListener(e -> {
            try
            {
                saveInput();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        });

        add(panel, BorderLayout.CENTER);
        add(saveButton, BorderLayout.SOUTH);
    }

    public void saveInput() throws Exception
    {
        String artistInput;
        String genreInput = null;
        if (deleting)
        {
            artistInput = (String) artistField.getSelectedItem();
            schedule.deleteArtist(artistInput);
        }
        else
        {
            artistInput = artistTextField.getText();
            genreInput = genreField.getText();
            Artist artist = new Artist(artistInput, "Festival-Planner\\Resources\\Schedules\\defaultpicture.png", genreInput);
            schedule.addArtist(artist);
        }

        JSONManager.writeToFile(schedule);
        frame.dispose();
    }
}
