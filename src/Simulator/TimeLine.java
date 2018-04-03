package Simulator;

import AgendaData.Act;

import javax.swing.*;
import java.awt.*;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Area;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class TimeLine extends JPanel implements MouseListener, MouseMotionListener
{
    private double tijdVerloop = 1.0;
    private List<Act> acts;
    private ArrayList<Area> areas;

    public TimeLine(List<Act> acts)
    {
        addMouseListener(this);
        addMouseMotionListener(this);
        this.acts = acts;
        areas = new ArrayList<>();
        setPreferredSize(new Dimension(500, 210));
        setBackground(new Color(0, 0, 0, 0));
    }

    public TimeLine(double tijdVerloop)
    {
        this.tijdVerloop = tijdVerloop;
        JFrame frame = new JFrame();
        frame.setContentPane(this);
        frame.setSize(500, 210);
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        double scrollTijd = tijdVerloop / 24 * 400;

        g2.setStroke(new BasicStroke(4.0f));
        Shape box = new Rectangle2D.Double(0, 0, 400, 150);
        g2.draw(box);

        Shape background = new Rectangle2D.Double(0, 0, 400, 210);
        g2.setColor(new Color(150, 150, 150, 125));
        g2.fill(background);
        g2.setColor(Color.BLACK);

        g2.draw(new Line2D.Double(0, 50, 400, 50));
        g2.draw(new Line2D.Double(0, 100, 400, 100));

        for (Act act : acts)
        {
            double startTime = act.getStartTime().getHours() + (act.getStartTime().getMinutes() / 100.0d);
            double endTime = act.getEndTime().getHours() + (act.getEndTime().getMinutes() / 100.0d);
            String podium = act.getPodium().getName();

            int startY = 0;
            int endY = 150;

            if (podium.equals("Podium 1"))
            {
                startY = 0;
                endY = 50;
            }
            if (podium.equals("Podium 2"))
            {
                startY = 50;
                endY = 100;
            }
            if (podium.equals("Podium 3"))
            {
                startY = 100;
                endY = 150;
            }

            Area area = new Area(new Rectangle2D.Double(startTime / 24 * 400, startY, (endTime - startTime) / 24 * 400, 50));
            if(!areas.contains(area))
                areas.add(area);
            g2.draw(area);
            g2.setColor(new Color(250, 30, 30, 100));
            g2.fill(area);
            g2.setColor(Color.BLACK);

            if(scrollTijd > startTime/ 24 * 400)
            {
//                Shape fillBox = new Rectangle2D.Double(startTime/24*400, startY, scrollTijd - startTime/ 24 * 400, 50);
//                g2.setColor(new Color(0, 0, 0, 100));
//                g2.fill(fillBox);
//                g2.setColor(Color.BLACK);
            }
        }

        g2.setColor(Color.red);
        Shape line = new Line2D.Double(scrollTijd, 0, scrollTijd, 150);
        g2.draw(line);

        Font font = new Font("Arial", Font.PLAIN, 30);
        g2.setColor(Color.BLACK);
        g2.setFont(font);
        String tijd = tijdVerloop + "";
        String[] tijd2 = tijd.split("\\.");
        if (tijd2[1].length() == 1)
            tijd2[1] += "0";
        tijd = "Tijd: " + tijd2[0] + ":" + tijd2[1];
        g2.drawString(tijd, 0, 200);
    }

    public void update(double tijdVerloop)
    {
        this.tijdVerloop = tijdVerloop;
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        int count = 0;
        for (int i = 0; i < areas.size()-1; i++)
        {
            Area area = areas.get(i);
            Point2D point = e.getPoint();
            if(area.contains(point))
            {
                showMessageDialog(null,
                        acts.get(i).printDetails(1)
                        , "Act info", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e)
    {

    }

    @Override
    public void mouseReleased(MouseEvent e)
    {

    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }

    @Override
    public void mouseDragged(MouseEvent e)
    {

    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
//        for(int i = 0; i < areas.size(); i++)
//        {
//            Area area = areas.get(i);
//            if(area.contains(e.getPoint()));
//            {
//                JToolTip jToolTip = new JToolTip();
//                jToolTip.setTipText("Test");
//                jToolTip.setLocation(e.getPoint());
//                add(jToolTip);
//            }
//        }
    }
}