import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class GUI {
    private JFrame frame;

    public GUI(){
        makeFrame();

    }

    /**
     * creates Swing frame and its contents.
     */
    private void makeFrame(){
        frame = new JFrame("Festival Planner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       makeMenuBar(frame);
       makeContent(frame);

        frame.setSize(640, 480);
        frame.setVisible(true);
    }

    /**
     * creates the menu bar and its contents.
     * @param frame the frame wherein the menu bar is added to.
     */
    private void makeMenuBar(JFrame frame){

        JMenuBar menu = new JMenuBar();
        frame.setJMenuBar(menu);

        //making file menu
        JMenu file = new JMenu("File");
        JMenuItem open = new JMenuItem("Open");
        file.add(open);
        menu.add(file);

        //making agenda menu
        JMenu agenda = new JMenu("Agenda");
        menu.add(agenda);

        //making info menu
        JMenu info = new JMenu("Info");

        JMenuItem about = new JMenuItem("About");
        info.add(about);
        JMenuItem help = new JMenuItem("Help");
        info.add(help);
        JMenuItem version = new JMenuItem("Version");
        info.add(version);
        menu.add(info);
    }

    private void makeContent(JFrame frame) {
        JPanel content = new JPanel();
        frame.add(content);
    }
}
