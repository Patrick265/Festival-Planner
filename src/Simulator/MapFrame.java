package Simulator;

import AgendaData.Act;
import AgendaData.Schedule;
import FileIO.JSONManager;
import Simulator.MAP.MapLogica;
import Simulator.NPC.VisitorLogic;
import Simulator.NPC.VisitorObject;

import javax.json.JsonObject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

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
    int timer;

    private boolean ctrlPressed = false;
    private double dist = 0;
    private Graphics2D g2d;
    private MapLogica logic = new MapLogica(map.getTargets(), map.getPaths());
    private double matrix[][][] = logic.getDistances();

    private int clickTarget = 0;

    private int lastTotalPopularity = 0;
    private Schedule schedule;
    private Date currentTime;
    private VisitorLogic animation = new VisitorLogic(matrix, map.getPaths());

    public MapFrame(JFrame frame)
    {
        currentTime = new Date(2018, 1, 1, 9,50,0);
        try
        {
            schedule = JSONManager.readFile(); //new Schedule();
            //todo: update when schedule changes
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        timer = 0;

        frame.setContentPane(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
        addKeyListener(this);
        setFocusable(true);
        new Timer(1000/60, this).start();
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
                dist = matrix[clickTarget][i][j];

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
        //if (ctrlPressed)
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
            Area area = new Area(new Rectangle2D.Double(objects.get(i).getInt("x")*scale, objects.get(i).getInt("y")*scale, objects.get(i).getInt("width")*scale, objects.get(i).getInt("height")*scale));
            if(area.contains(new Point2D.Double((e.getX()-x), (e.getY()-y))))
            {
                clickTarget = i;
                //logic.reCalcDistance(objects.get(i), 0);
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
        timer++;
        animation.update();
        repaint();

        if(timer % 30 != 0)
            return;

        int minutes = currentTime.getMinutes();
        if (minutes + 1 == 60)
        {
            currentTime.setHours(currentTime.getHours()+1);
            currentTime.setMinutes(0);
        }
        else
            currentTime.setMinutes(minutes + 1);

        double curTime = currentTime.getHours() + (currentTime.getMinutes() / 100.0d);
        System.out.println(curTime);

        if (currentTime.getMinutes() % 15 == 0)
        {
            System.out.println("ding");
            checkAct(curTime);
        }
    }

    public void checkAct(double curTime)
    {
        java.util.List<Act> activeActs = new ArrayList<>();

        for (Act act : schedule.getActs())
        {
            double startTime = act.getStartTime().getHours() + (act.getStartTime().getMinutes() / 100.0d);
            double endTime = act.getEndTime().getHours() + (act.getEndTime().getMinutes() / 100.0d);

            if (startTime <= curTime && endTime >= curTime)
            {
                activeActs.add(act);
            }
        }

        int p1Pop = 0;
        int p2Pop = 0;
        int p3Pop = 0;

        for(Act act : activeActs)
        {
            switch(act.getPodium().getName())
            {
                case "Podium 1":
                    p1Pop = act.getPopularity();
                    break;
                case "Podium 2":
                    p2Pop = act.getPopularity();
                    break;
                case "Podium 3":
                    p3Pop = act.getPopularity();
                    break;
            }
        }


        int rndSize = 10 + p1Pop + p2Pop + p3Pop; // +5 per uitgang
        Random rnd = new Random();
        if(rndSize - 10 != lastTotalPopularity)
            for(VisitorObject visitor : animation.getVisitors())
            {
                System.out.println("Nieuwe podia aangewezen");

                int rndNum = rnd.nextInt(rndSize);
                if(rndNum <= p1Pop)
                    visitor.setFavouriteStage(0);
                else if (rndNum <= p1Pop + p2Pop)
                    visitor.setFavouriteStage(1);
                else if (rndNum <= p1Pop + p2Pop + p3Pop)
                    visitor.setFavouriteStage(2);
                else if (rndNum <= rndSize - 5)
                    visitor.setFavouriteStage(3);
                else
                    visitor.setFavouriteStage(4);
            }

        lastTotalPopularity = p1Pop + p2Pop + p3Pop;
    }
}