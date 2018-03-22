package Simulator.NPC;

import Simulator.SpriteBatch;

import javax.swing.*;
import java.util.List;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;

//class Visitor
//{
//    public Point2D position;
//    public double direction;
//    public Visitor(Point2D position, double direction)
//    {
//        this.position = position;
//        this.direction = direction;
//    }
//}

public class VisitorTest extends JPanel implements ActionListener
{
    //List<Visitor> sims = new ArrayList<>();

    private double directions[][];
    private SpriteBatch spriteBatch;
    private String destinationName = null;

    private ArrayList<VistorLogic> visitors = new ArrayList<>();

    public VisitorTest(double directions[][])
    {
        this.directions = directions;
        spriteBatch = new SpriteBatch();
        while (visitors.size() < 100)
        {
            VistorLogic visitor = new VistorLogic(spriteBatch);
            if (!visitor.hasCollision(visitors))
            {
                visitors.add(visitor);
            }
        }

        update();

        new Timer(1000 / 60, this).start();

//        addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent e)
//            {
//                for(VistorLogic visitor : visitors)
//                {
//                    visitor.setTarget(e.getPoint());
//                }
//            }
//        });
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for (VistorLogic visitor : visitors)
        {
            visitor.draw(g2d);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

    }

    public void update()
    {
        for (int i = 0; i < visitors.size(); i++)
        {
            VistorLogic npc = visitors.get(i);
            int posX = (int) npc.getPosition().getX() / 32;
            int posY = (int) npc.getPosition().getY() / 32;

            int x = 0;
            int y = 0;

            for (int xx = -1; xx <= 1; xx++)
            {
                for (int yy = -1; yy <= 1; yy++)
                {
                    if (posX + xx < 0 || posX + xx >= 50 || posY + yy < 0 || posY + yy >= 50 || y + posX < 0 || x + posX < 0)
                        continue;
                    System.out.println(posX + xx);
                    if (directions[posX + xx][posY + yy] > 1000)
                        continue;

                    if (directions[xx + posX][yy + posY] < directions[x + posX][y + posY])
                    {
                        x = xx;
                        y = yy;
                        continue;
                    }
                }
            }
            Point2D olddir = npc.getPosition();
            npc.setPosition(new Point2D.Double(npc.getPosition().getX() + x, npc.getPosition().getY() + y));
            npc.direction();
//            if(npc.hasCollision(visitors))
//                npc.setPosition(new Point2D.Double(olddir.getX()-32, olddir.getY()-32));
            if (destinationName != null)
            {
                if (destinationName.equals("ingang") && directions[x + posX][y + posY] < 1)
                {
                    visitors.remove(i);
                    return;
                }
            }

            for (VistorLogic npc2 : visitors)
            {
                double dist = npc2.getPosition().distance(npc.getPosition());
                if (npc2 != npc && dist < 20)
                {
                    Point2D oldPos = npc.getPosition();
                    Point2D diff = new Point2D.Double((npc2.getPosition().getX() - npc.getPosition().getX()) / dist, (npc2.getPosition().getY() - npc.getPosition().getY()) / dist);
                    npc.setPosition(new Point2D.Double(npc.getPosition().getX() + (dist - 32) * diff.getX(), npc.getPosition().getY() + (dist - 32) * diff.getY()));
                    posX = (int) npc.getPosition().getX() / 32;
                    posY = (int) npc.getPosition().getY() / 32;
                    if (directions[posX][posY] > 1000)
                        npc.setPosition(oldPos);
                }
            }
        }
    }

    public void setTargets(Point e)
    {
        for (VistorLogic visitor : visitors)
        {
            visitor.setTarget(e);
        }
    }

    public void setDestinationName(String destinationName)
    {
        this.destinationName = destinationName;
    }
}
