package Simulator.NPC;

import Simulator.SpriteBatch;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class VistorLogic
{
    private String type;
    private String interest;

    private Point2D position;
    private Point2D target = new Point2D.Double(500,500);
    private SpriteBatch spriteBatch;
    private BufferedImage imageNPC;
    private java.util.List<String> sprites;

    private int frame = 0;
    private int count = 0;

    private double angle;
    private double speed;
    private int imageWidth;
    private String action;


    public VistorLogic(SpriteBatch spriteBatch)
    {
        this.position = new Point2D.Double(Math.random() * 1980, Math.random() * 1080 );
        this.angle = Math.random() * 2 * Math.PI;
        this.speed = 3;

        this.spriteBatch = spriteBatch;
        this.sprites = spriteBatch.getImagePaths();
        this.imageNPC = this.spriteBatch.getCutImage(sprites.get(setSprite() + 1))[frame];
//        this.imageNPC = ImageIO.read(getClass().getResource("/NPC/skeleton easteregg.png"));
        this.imageWidth = this.imageNPC.getWidth();

    }

    public void draw(Graphics2D g2d)
    {
        AffineTransform tx = new AffineTransform();
        tx.translate(this.position.getX() - this.imageNPC.getWidth()/2, position.getY() - this.imageNPC.getHeight()/2);
        g2d.drawImage(this.spriteBatch.getCutImage("bandana male1.png")[frame], tx, null);
    }


    public void update(ArrayList<VistorLogic> npcs)
    {

        Point2D diff = new Point2D.Double(
                target.getX() - position.getX(),
                target.getY() - position.getY()
        );

        double targetAngle = Math.atan2(diff.getY(), diff.getX());



        double angleDiff = angle - targetAngle;
        while(angleDiff < -Math.PI)
            angleDiff += 2*Math.PI;
        while(angleDiff > Math.PI)
            angleDiff -= 2*Math.PI;

        if(angleDiff < 0)
            angle += 0.1;
        else if(angleDiff > 0)
            angle -= 0.1;


        Point2D lastPosition = position;
        position = new Point2D.Double(
                position.getX() + speed * Math.cos(angle),
                position.getY() + speed * Math.sin(angle));


        boolean hasCollision = hasCollision(npcs);

        if(hasCollision)
        {
            position = lastPosition;
            angle += 0.2;
        }
        this.count ++;
        this.frame++;
        direction();
    }

    public boolean hasCollision(ArrayList<VistorLogic> npcs) {
        boolean hasCollision = false;
        for(VistorLogic npc : npcs)
        {
            if(npc== this)
                continue;
            double distance = position.distance(npc.position);
            if(distance < imageWidth)
                hasCollision = true;
        }
        return hasCollision;
    }

    public void direction()
    {
        if (count > 8)
        {
            count = 0;
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
        }
    }

    public void setTarget(Point2D targetPosition)
    {
        this.target = targetPosition;
    }

    private int setSprite()
    {
        return (int)(Math.random() * 55);
    }
}
