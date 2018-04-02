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

        if (deleting)
        {
            frame = new JFrame("Delete act");
            frame.setSize(400, 110);
            this.deleting = true;
        }
        else
        {
            frame = new JFrame("New Act");
            frame.setSize(400, 150);
        }

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

        frame.setLocation((screenWidth / 2) - (frame.getWidth() / 2), (screenHeight / 2) - (frame.getHeight() / 2));
        frame.setContentPane(this);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private void buildPanel()
    {
        JPanel panel;
        JButton saveButton;
        if (deleting)
        {
            panel = new JPanel(new GridLayout(1, 2, 0, 32));
            panel.add(new JLabel("  Select artist you want to delete:"));
            panel.add(artistField);
            saveButton = new JButton("Delete Act");
        }
        else
        {
            panel = new JPanel(new GridLayout(2, 2, 0, 32));
            panel.add(new JLabel("        Artist:"));
            panel.add(artistTextField);
            panel.add(new JLabel("        Genre:"));
            panel.add(genreField);
            saveButton = new JButton("Save Act");
        }

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

    private void saveInput() throws Exception
    {
        String artistInput;
        String genreInput = null;
        if (deleting)
        {
            artistInput = (String) artistField.getSelectedItem();
            schedule.deleteArtist(artistInput);
            JSONManager.writeToFile(schedule);
            frame.dispose();
            JOptionPane.showMessageDialog(frame, "Artist deleted!");
        }
        else
        {
            artistInput = artistTextField.getText();
            genreInput = genreField.getText();
            if (!artistInput.isEmpty() && !genreInput.isEmpty())
            {
                Artist artist = new Artist(artistInput, "Festival-Planner\\Resources\\Schedules\\defaultpicture.png", genreInput);
                schedule.addArtist(artist);
                JSONManager.writeToFile(schedule);
                frame.dispose();
                JOptionPane.showMessageDialog(frame, "Artist added!");
            }
            else
            {
                JOptionPane.showMessageDialog(frame, "Artist and/or genre input was empty. Artist not added.");
            }
        }
    }
}
