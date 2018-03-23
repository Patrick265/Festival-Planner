package Simulator.NPC;

import Simulator.SpriteBatch;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class VisitorObject
{
    private String type;
    private String interest;

    private Point2D position;
    private Point2D target = new Point2D.Double(500,500);
    private SpriteBatch spriteBatch;
    private BufferedImage[] imageNPC;

    private int frame = 0;
    private int count = 0;

    private double angle;
    private double speed;
    private int imageWidth;
    private String action;


    public VisitorObject(SpriteBatch spriteBatch, int path[][])
    {
        do
        {
            this.position = new Point2D.Double(Math.random() * 1280, Math.random() * 720 );
        }while(path[(int)this.getPosition().getX()/32][(int)this.getPosition().getY()/32] != 0);
        this.angle = Math.random() * 2 * Math.PI;
        this.speed = 3;
        String[] paths = {"bandana male1.png", "bandana male2.png", "bandana male3.png"};
        this.spriteBatch = spriteBatch;

        Random random = new Random();

        int x = random.nextInt(paths.length);
        this.imageNPC = this.spriteBatch.getCutImage(paths[x]);
        this.imageWidth = this.imageNPC[0].getWidth();
    }

    public void draw(Graphics2D g2d)
    {
        AffineTransform tx = new AffineTransform();
        tx.translate(this.position.getX() - this.imageNPC[0].getWidth()/2, position.getY() - this.imageNPC[0].getHeight()/2);
        g2d.drawImage(this.imageNPC[frame], tx, null);
    }


//    public void update(ArrayList<VisitorObject> npcs)
//    {
//        Point2D diff = new Point2D.Double(
//                target.getX() - position.getX(),
//                target.getY() - position.getY()
//        );
//
//
//
//        double targetAngle = Math.atan2(diff.getY(), diff.getX());
//
//        double angleDiff = angle - targetAngle;
//        while(angleDiff < -Math.PI)
//            angleDiff += 2*Math.PI;
//        while(angleDiff > Math.PI)
//            angleDiff -= 2*Math.PI;
//
//        if(angleDiff < 0)
//            angle += 0.1;
//        else if(angleDiff > 0)
//            angle -= 0.1;
//
//
////        Point2D lastPosition = position;
//////        position = new Point2D.Double(
//////                position.getX() + speed * Math.cos(angle),
//////                position.getY() + speed * Math.sin(angle));
//
//        System.out.println(position.getX());
//        boolean hasCollision = hasCollision(npcs);
//
//        if(hasCollision)
//        {
////            position = lastPosition;
////            angle += 0.2;
//        }
//        this.count ++;
//        this.frame++;
//        direction();
//    }

    public boolean hasCollision(ArrayList<VisitorObject> npcs) {
        boolean hasCollision = false;
        for(VisitorObject npc : npcs)
        {
            if(npc== this)
                continue;
            double distance = position.distance(npc.position);
            if(distance < imageWidth / 2 + 5)
                hasCollision = true;
        }
        return hasCollision;
    }

    public void direction()
    {

        //if (count > 8)
        //{
        //    count = 0;
            if (position.getX() > target.getX())
            {
                this.frame = 9;
                if (position.getY() > target.getY())
                {
                    this.frame = 0;
                }
            } else if (position.getX() < target.getX())
            {
                this.frame = 27;
                if (position.getY() < target.getY())
                {
                    this.frame = 18;
                }
            }
        //}
    }

    public void setTarget(Point2D targetPosition)
    {
        this.target = targetPosition;
    }

    public Point2D getPosition()
    {
        return position;
    }

    public void setPosition(Point2D position)
    {
        this.position = position;
    }
}