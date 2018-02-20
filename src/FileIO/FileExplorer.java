package FileIO;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;

public class FileExplorer extends JPanel implements ActionListener{
    //JTextArea log;
    JFileChooser fc;

    public FileExplorer(){
//        log = new JTextArea(5, 20);
//        log.setMargin(new Insets(5, 5, 5, 5));
//        log.setEditable(false);
//        JScrollPane logScrollPane = new JScrollPane(log);

        fc = new JFileChooser();
        fc.showOpenDialog(FileExplorer.this);
        fc.addActionListener(this);
        fc.setFileFilter(new FileNameExtensionFilter("JSON file", ".json"));


        //this.add(fc);
        createAndShowGui();
    }

    public void createAndShowGui(){
        JFrame frame = new JFrame();
        frame.setContentPane(this);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
