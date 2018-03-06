package simulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;

public class MapFrame extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener
{
    private MapLoader map = new MapLoader("FesivalPlannermap.json");

    private int y = 0;
    private int initY = 0;

    private Graphics2D g2d;

    public MapFrame()
    {
        JFrame frame = new JFrame("Tilemap demo");
        frame.setSize(new Dimension(1600, 1600));
        frame.setMinimumSize(new Dimension(1600, 1600));
        frame.setMaximumSize(new Dimension(1600, 1600));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(this);
        frame.setVisible(true);
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g2d = (Graphics2D)g;

        g2d.setTransform(AffineTransform.getTranslateInstance(0, y));
        map.draw(g2d);

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int a = (initY - e.getY())*-1;
        y += a;
        if(g2d.getClip().getBounds().y - y > 0 && g2d.getClip().getBounds().y - y < 1061)
        {
            repaint();
        }
        else
        {
            y -= a;
        }
        initY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        initY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e)
    {
        int a = e.getWheelRotation()*-100;
        y += a;
        if(g2d.getClip().getBounds().y - y > 0 && g2d.getClip().getBounds().y - y < 1061)
        {
            invalidate();
            revalidate();
            repaint();
        }
        else
        {
            y-=a;
        }
    }
}