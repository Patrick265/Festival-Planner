package FileIO;

import AgendaData.Artist;
import AgendaData.Schedule;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
//import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;

public class FileExplorer extends JPanel
{
    private ObjectMapper objectMapper = new ObjectMapper();
    private JFileChooser fc;
    private static String path = "Festival-Planner/src/FileIO/schedule.json";

    public FileExplorer(int saveOrOpen) throws Exception
    {
        fc = new JFileChooser();
        if (saveOrOpen == 0)
        {
            int reValue = fc.showSaveDialog(this);
            if (reValue == JFileChooser.APPROVE_OPTION)
            {
                path = fc.getSelectedFile().getPath();
            }
        }
        else
        {
            int reValue = fc.showSaveDialog(this);
            if(reValue == JFileChooser.APPROVE_OPTION)
            {
                path = fc.getSelectedFile().getPath();
                File file = new File(path);
            }
        }
    }

    public static String getPath()
    {
        return path;
    }
}
