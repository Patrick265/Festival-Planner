package Simulator;

import Simulator.MAP.MapLogica;
import Simulator.NPC.VisitorTest;

import javax.json.JsonObject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class MapFrame extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener, ActionListener
{
    private MapLoader map = new MapLoader("/Map/FesivalPlannermap.json");

    private int y = 0;
    private int initY = 0;

    private int x = 0;
    private int initX = 0;

    private int mousePosX = 0;
    private int mousePosY = 0;

    private double scale = 1;

    private boolean ctrlPressed = false;
    private double dist = 0;
    private Graphics2D g2d;
    private MapLogica logic = new MapLogica(map.getTargets(), map.getPaths());
    private double matrix[][] = logic.getMatrix();

    private VisitorTest animation = new VisitorTest(matrix);

    public MapFrame(JFrame frame)
    {
        frame.setContentPane(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
        addKeyListener(this);
        setFocusable(true);
        new Timer(1000 / 75, this).start();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g2d = (Graphics2D) g;

        g2d.setTransform(AffineTransform.getTranslateInstance(x, y));
        g2d.translate(mousePosX, mousePosY);
        g2d.scale(scale, scale);
        map.draw(g2d);
        animation.paintComponent(g2d);
        for (int i = 0; i < 50; i++)
        {
            for (int j = 0; j < 50; j++)
            {
                dist = matrix[i][j];

                if(dist == 0.0)
                {
                    animation.setTargets(new Point(i * 32, j * 32));
                }
                if (dist < 1000)
                {
                    String dist2 = "" + dist;
                    g2d.drawString(dist2, i * 32, j * 32);
                }
            }
        }
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
        ArrayList<JsonObject> objects = map.getTargets();
        for(int i = 0; i < map.getTargets().size(); i++)
        {
            Area area = new Area(new Rectangle2D.Double(objects.get(i).getInt("x")*scale, objects.get(i).getInt("y")*scale, objects.get(i).getInt("width"), objects.get(i).getInt("height")));
            if(area.contains(new Point2D.Double((e.getX()-x), (e.getY()-y))))
            {
                logic.reCalcDistance(objects.get(i));
                animation.setDestinationName(objects.get(i).getString("name"));
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        initX = e.getX();
        initY = e.getY();
        //animation.update();
        //repaint();
        int divX = g2d.getClip().getBounds().x + e.getX();
        int divY = g2d.getClip().getBounds().y + e.getY();
        //animation.setTargets(new Point((int) (divX / scale), (int) (divY / scale)));
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        initX = 0;
        initY = 0;
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
        int c = e.getWheelRotation();

        //mousePosX = e.getX();
        //mousePosY = e.getY();

        scale -= c * 0.1;

        //x = getWidth()/2 - e.getPoint().x;
        //y = getWidth()/2 - e.getPoint().y;
        repaint();
        //setAlignmentX(0);
        //setAlignmentY(0);

//        int a = e.getWheelRotation() * -100;
//        y += a;
//        if (g2d.getClip().getBounds().y - y > 0 && g2d.getClip().getBounds().y - y < 1061)
//        {
//            invalidate();
//            revalidate();
//            repaint();
//        } else
//        {
//            y -= a;
//        }
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == 17)
            ctrlPressed = true;
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_CONTROL)
            ctrlPressed = false;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        animation.update();
        repaint();
    }
}