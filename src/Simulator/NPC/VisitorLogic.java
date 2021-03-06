package Simulator.NPC;

import Simulator.SpriteBatch;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class VisitorLogic extends JPanel implements ActionListener
{

    private double distances[][][];
    private SpriteBatch spriteBatch;
    private String destinationName = null;

    private ArrayList<VisitorObject> visitors = new ArrayList<>();

    public VisitorLogic(double distances[][][], int path[][])
    {
        this.distances = distances;
        spriteBatch = new SpriteBatch();
        int count = 0;
        while (visitors.size() < 100)
        {
            VisitorObject visitor = new VisitorObject(spriteBatch);
            count++;
            if (visitor.getPosition().getX() > 0 && visitor.getPosition().getY() > 0)
            {
                visitors.add(visitor);
                //update();
            }
        }

        update();

        new Timer(1000 / 60, this).start();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for (VisitorObject visitor : visitors)
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
            VisitorObject visitor = visitors.get(i);
            int posX = (int) visitor.getPosition().getX() / 32;
            int posY = (int) visitor.getPosition().getY() / 32;

            int x = 0;
            int y = 0;

            for (int xx = -1; xx <= 1; xx++)
            {
                for (int yy = -1; yy <= 1; yy++)
                {
                    if (posX + xx < 0 || posX + xx >= 50 || posY + yy < 0 || posY + yy >= 50 || y + posX < 0 || x + posX < 0)
                        continue;
//                    System.out.println(posX + xx);
                    if (distances[visitor.getFavouriteStage()][posX + xx][posY + yy] > 1000)
                        continue;

                    if (distances[visitor.getFavouriteStage()][xx + posX][yy + posY] < distances[visitor.getFavouriteStage()][x + posX][y + posY])
                    {
                        x = xx;
                        y = yy;
                        continue;
                    }
                }
            }
            Point2D olddir = visitor.getPosition();
            visitor.setPosition(new Point2D.Double(visitor.getPosition().getX() + x * 3, visitor.getPosition().getY() + y * 3));
            visitor.direction();

            //if (destinationName != null)
            {

                try
                {
                    if (/*destinationName.equals("ingang") && */distances[3][x + posX][y + posY] < 1 /*|| distances[3][x + posX][y + posY] < 1*/)
                    {
                        visitors.remove(i);
                        return;
                    }
                }catch (ArrayIndexOutOfBoundsException e)
                {
                    visitors.set(i, new VisitorObject(spriteBatch));
                    return;
                }
            }

            for (VisitorObject collisionCheckVisitor : visitors)
            {
                double dist = collisionCheckVisitor.getPosition().distance(visitor.getPosition());
                if (collisionCheckVisitor != visitor && dist < 20)
                {
                    Point2D oldPos = visitor.getPosition();
                    Point2D diff = new Point2D.Double((collisionCheckVisitor.getPosition().getX() - visitor.getPosition().getX()) / dist, (collisionCheckVisitor.getPosition().getY() - visitor.getPosition().getY()) / dist);
                    visitor.setPosition(new Point2D.Double(visitor.getPosition().getX() + (dist - 32) * diff.getX(), visitor.getPosition().getY() + (dist - 32) * diff.getY()));
                    posX = (int) visitor.getPosition().getX() / 32;
                    posY = (int) visitor.getPosition().getY() / 32;
                    try
                    {
                        if (distances[collisionCheckVisitor.getFavouriteStage()][posX][posY] > 1000)
                            visitor.setPosition(oldPos);
                    }catch (ArrayIndexOutOfBoundsException e)
                    {
                        visitors.set(i, new VisitorObject(spriteBatch));
                        return;
                    }
                }
            }
        }
    }

    public ArrayList<VisitorObject> getVisitors()
    {
        return visitors;
    }

    public void setTargets(Point e)
    {
        for (VisitorObject visitor : visitors)
        {
            visitor.setTarget(e);
        }
    }

    public void setDestinationName(String destinationName)
    {
        this.destinationName = destinationName;
    }
}
