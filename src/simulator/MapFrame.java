package simulator;

import simulator.NPC.VisitorTest;
import simulator.NPC.VistorLogic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class MapFrame extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener, ActionListener
{
    private MapLoader map = new MapLoader("/Map/FesivalPlannermap.json");
    private VisitorTest animation = new VisitorTest();

    private int y = 0;
    private int initY = 0;

    private int x = 0;
    private int initX = 0;

    private boolean ctrlPressed = false;

    private Graphics2D g2d;

    public MapFrame(JFrame frame)
    {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(this);
        frame.setVisible(true);
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
        addKeyListener(this);
        setFocusable(true);
        new Timer(1000/35, this).start();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g2d = (Graphics2D) g;

        g2d.setTransform(AffineTransform.getTranslateInstance(x, y));
        map.draw(g2d);
        animation.paintComponent(g2d);

    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        if (ctrlPressed)
        {
            int a = (initY - e.getY()) * -1;
            int b = (initX - e.getX()) * -1;
            y += a;
            x += b;
            int divY = g2d.getClip().getBounds().y - y;
            int divX = g2d.getClip().getBounds().x - x;
            if (divY > 0 && divY < 1061 && divX > 0 && divX < 539)
            {
                repaint();
            } else
            {
                x -= b;
                y -= a;
            }
            initY = e.getY();
            initX = e.getX();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {

    }

    @Override
    public void mouseClicked(MouseEvent e)
    {

    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        initX = e.getX();
        initY = e.getY();
        animation.update();
        repaint();
        int divX = g2d.getClip().getBounds().x + e.getX();
        int divY = g2d.getClip().getBounds().y + e.getY();
        animation.setTargets(new Point(divX, divY));

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
    public void mouseWheelMoved(MouseWheelEvent e)
    {
        
        int a = e.getWheelRotation() * -100;
        y += a;
        if (g2d.getClip().getBounds().y - y > 0 && g2d.getClip().getBounds().y - y < 1061)
        {
            invalidate();
            revalidate();
            repaint();
        } else
        {
            y -= a;
        }
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
        System.out.println(e.getID());
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == 17)
            ctrlPressed = true;
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_CONTROL)
            ctrlPressed = false;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        animation.update();
        repaint();
    }
}