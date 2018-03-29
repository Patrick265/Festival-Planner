import AgendaData.Act;
import AgendaData.Schedule;
import Agenda.AgendaGUI;
import FileIO.FileExplorer;
import FileIO.JSONManager;
import Simulator.MapFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author Thomas Mandemaker, Patrick de Jong, Yannick van Dolen , Sergen Peker , Anastasia Hellemons
 * @version 1.0
 */
public class GUI implements ActionListener
{
//    private Date currentTime;
    private JFrame frame;
//    private Schedule schedule;
    private Timer timer;
    private boolean event;

    public GUI()
    {
        event = false;

//        currentTime = new Date(2018, 1, 1, 9,50,0);
        makeFrame();
//        try
//        {
//            schedule = JSONManager.readFile(); //new Schedule();
//            //todo: update when schedule changes
//        } catch (Exception e)
//        {
//            e.printStackTrace();
//        }
        timer = new Timer(1000/2, this);
        timer.start();
        //testAgenda();
    }

    /**
     * creates Swing frame and its contents.
     */
    private void makeFrame()
    {
        frame = new JFrame("Festival Planner");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        makeMenuBar(frame);
        makeContent(frame);
        makeSimulator(frame);

        frame.setSize(1280, 720);
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }

    /**
     * creates the menu bar and its contents.
     * @param frame the frame wherein the menu bar is added to.
     */
    private void makeMenuBar(JFrame frame)
    {

        JMenuBar menu = new JMenuBar();
        frame.setJMenuBar(menu);

        //making file menu
        JMenu file = new JMenu("File");
        JMenuItem open = new JMenuItem("Open");
        try
        {
            open.setIcon(new ImageIcon(ImageIO.read(new FileInputStream("Festival-Planner\\Resources\\GUI\\MenuBarIcons\\16x16_OpenFileMenuBar.png"))));
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        file.add(open).addActionListener(e -> {
            try
            {
                new FileExplorer(0);
            } catch (Exception ex)
            {
                ex.printStackTrace();
            }
        });
        menu.add(file);

        //making agenda menu
        JMenu agenda = new JMenu("Agenda");
        JMenuItem viewAgenda = new JMenuItem("View agenda");
        agenda.add(viewAgenda);
        viewAgenda.addActionListener(e ->
        {
            AgendaGUI agendaGUI = new AgendaGUI();
            //AgendaPopUpGUI agendaPopUpGUI = new AgendaPopUpGUI();
        });
        menu.add(agenda);




        //making info menu
        JMenu info = new JMenu("Info");


        /**
         * Dit is voor de INFO kop
         */
        JMenuItem about = new JMenuItem("About");
        info.add(about);
        about.addActionListener(e ->
        {
            JOptionPane.showMessageDialog(frame,"Dit is een simulatie van een festival. \n" +
                                                        "Dit project is gemaakt door: B5", "Informatie", JOptionPane.INFORMATION_MESSAGE);
        });
        JMenuItem version = new JMenuItem("Version");
        version.addActionListener(e ->
        {
            JOptionPane.showMessageDialog(frame,"Versie 1.0", "Versie", JOptionPane.INFORMATION_MESSAGE);
        });

        info.add(version);
        menu.add(info);
    }

    /**
     * creates the content of the GUI
     * @param frame the frame wherein the content is added to.
     */
    private void makeContent(JFrame frame)
    {
        JPanel content = new JPanel();
        frame.add(content);
    }

    private void makeSimulator(JFrame frame)
    {
        new MapFrame(frame);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
//        int minutes = currentTime.getMinutes();
//        if (minutes + 1 == 60)
//        {
//            currentTime.setHours(currentTime.getHours()+1);
//            currentTime.setMinutes(0);
//        }
//        else
//            currentTime.setMinutes(minutes + 1);
//
//        double curTime = currentTime.getHours() + (currentTime.getMinutes() / 100.0d);
//        System.out.println(curTime);
//
//        if (curTime % 0.15 == 0)
//        {
//            checkAct(curTime);
//        }
//
//    }
//
//    public void checkAct(double curTime)
//    {
//        List<Act> activeActs = new ArrayList<>();
//
//        for (Act act : schedule.getActs())
//        {
//            double startTime = act.getStartTime().getHours() + (act.getStartTime().getMinutes() / 100.0d);
//            double endTime = act.getEndTime().getHours() + (act.getEndTime().getMinutes() / 100.0d);
//
//            if (startTime <= curTime && endTime >= curTime)
//            {
//                activeActs.add(act);
//            }
//        }
//
////        for(int i = 0; i <)
//    }
    }
}
