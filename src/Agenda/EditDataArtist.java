package Agenda;

import AgendaData.Artist;
import AgendaData.Schedule;
import FileIO.JSONManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

public class EditDataArtist implements KeyListener
{
    private JFrame frame;
    private JPanel mainPanel;
    private JPanel searchPanel;
    private JPanel infoPanel;
    private JPanel editButtonsPanel;
    private TableModel model;

    private JTextField searchBar;
    private JButton search;
    private JButton editGenre;


    private String artist;
    private JLabel genreLabel;
    private String genre;


    private Schedule schedule;

    private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

    public EditDataArtist()
    {
        this.frame = new JFrame("Edit Artists");
        this.mainPanel = new JPanel(new BorderLayout());
        this.searchPanel = new JPanel(new FlowLayout());
        this.infoPanel = new JPanel(new FlowLayout());
        this.editButtonsPanel = new JPanel(new FlowLayout());
        this.model = new TableModel();

        fillSchedule();
        buildSearchPanel();
        buildEditButtons();

        this.genreLabel = new JLabel("Genre: ");
        this.infoPanel.add(this.genreLabel);

        updateInfo();
        this.search.addActionListener(e ->
        {
            searchForArtist();
            updateInfo();
        });

        this.mainPanel.setFocusable(true);
        this.mainPanel.requestFocusInWindow();
        this.mainPanel.add(this.searchPanel, BorderLayout.NORTH);
        this.mainPanel.add(this.infoPanel, BorderLayout.CENTER);
        this.mainPanel.add(this.editButtonsPanel, BorderLayout.SOUTH);
        this.frame.setContentPane(this.mainPanel);
        this.frame.setSize(400,200);
        this.frame.setLocation( (this.screenWidth / 2) - (this.frame.getWidth() / 2),
                                (this.screenHeight / 2) - (this.frame.getWidth() / 2));
        this.frame.setResizable(false);
        this.frame.setVisible(true);
    }


    private void buildSearchPanel()
    {
        this.searchBar = new JTextField();

        this.searchBar.setFont(new Font("Arial", Font.PLAIN, 16));
        this.searchBar.setPreferredSize(new Dimension(300, 35));
        this.searchBar.addKeyListener(this);

        this.search = new JButton("Search");
        this.search.setPreferredSize(new Dimension(75, 35));
        this.searchPanel.add(this.searchBar);
        this.searchPanel.add(this.search);
    }

    private void buildEditButtons()
    {
        this.editGenre = new JButton("Edit Genre");
        this.editGenre.setPreferredSize(new Dimension(100,35));
        this.editGenre.addActionListener(e -> editGenre());
        this.editButtonsPanel.add(editGenre);
    }

    private void editGenre()
    {
        JFrame frame = new JFrame("Edit Genre");
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel textFieldPanel = new JPanel(new FlowLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JTextField editGenre = new JTextField();
        editGenre.setPreferredSize(new Dimension(375, 25));
        editGenre.setFont(new Font("Arial", Font.PLAIN, 16));

        JButton saveButton = new JButton("Save");
        saveButton.setPreferredSize(new Dimension(100,35));
        saveButton.addActionListener(e -> {
            writeToFileGenre(editGenre);
            frame.dispose();
        });


        JButton cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(new Dimension(100,35));
        cancelButton.addActionListener(e -> frame.dispose());


        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        textFieldPanel.add(editGenre);
        mainPanel.add(textFieldPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        frame.setContentPane(mainPanel);
        frame.setSize(400,150);
        frame.setLocation( (this.screenWidth / 2) - (frame.getWidth() / 2),
                (this.screenHeight / 2) - (frame.getWidth() / 2));
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private void writeToFileGenre(JTextField genreField)
    {
        for (Map.Entry<String, Artist> entry : this.schedule.getArtists().entrySet())
        {
            if(this.artist.equals(entry.getValue().getName().toLowerCase()))
            {
                entry.getValue().setGenre(genreField.getText());
                try
                {
                    JSONManager.writeToFile(this.schedule);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            this.search.doClick();
        }

        for(int i = 0; i < this.schedule.getActs().size(); i++)
        {
            if(this.schedule.getActs().get(i).getArtists().get(0).getName().toLowerCase().equals(this.artist))
            {
                this.schedule.getActs().get(i).getArtists().get(0).setGenre(genreField.getText());
                try
                {
                    JSONManager.writeToFile(this.schedule);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        AgendaGUI.updateTable();
    }

    private void fillSchedule()
    {
        try
        {
            this.schedule = this.model.getSchedule();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void searchForArtist()
    {
        this.artist = this.searchBar.getText().toLowerCase();
        for (Map.Entry<String, Artist> entry : this.schedule.getArtists().entrySet())
        {
            if(this.artist.equals(entry.getValue().getName().toLowerCase()))
            {
                this.genre = entry.getValue().getGenre();
            }
        }
    }

    private void updateInfo()
    {
        this.genreLabel.setText("Genre: " + this.genre);
        this.genreLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        this.infoPanel.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            this.search.doClick();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }
}