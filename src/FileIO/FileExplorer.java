package FileIO;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
//import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class FileExplorer extends JPanel
{
    private ObjectMapper objectMapper = new ObjectMapper();
    private JFileChooser fc;
    private static String path = "Festival-Planner\\resources\\Schedules\\Schedule1.json";

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
