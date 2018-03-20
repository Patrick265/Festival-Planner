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
    private BufferedImage[] imageNPC;

    private int frame = 0;
    private int count = 0;
    private String dir;
    private boolean dancing;
    boolean drunk;

    private double angle;
    private double speed;
    private int imageWidth;


    public VistorLogic(SpriteBatch spriteBatch)
    {
        this.position = new Point2D.Double(Math.random() * 1980, Math.random() * 1080 );
        this.angle = Math.random() * 2 * Math.PI;
        this.speed = 3;
        String[] paths = {"bandana male1.png", "bandana male2.png", "bandana male3.png","blonde female1.png",
                "blonde female2.png", "blonde male1.png", "blonde male2.png", "blonde male3.png", "blonde male4.png",
                "blue female1.png", "blue female2.png", "blue female3.png", "blue male1.png", "brown female1.png",
                "brown female2.png", "brown male1.png", "brown male2.png", "brown male3.png", "green female1.png",
                "green female2.png", "green male1.png", "green male2.png", "green male3.png", "green male4.png",
                "green male5.png", "green male6.png", "pink female1.png", "pink female2.png","pink male1.png",
                "pink male2.png", "raven female1.png", "raven female2.png", "raven male1.png", "raven male2.png",
                "raven male3.png", "raven male4.png", "raven male5.png", "raven male6.png", "raven male7.png",
                "raven male8.png", "red female1.png", "red female2.png", "red female3.png", "red female4.png",
                "silver female1.png", "silver female2.png",
                "white male1.png", "white male2.png", "white male3.png", "white male4.png", "white male5.png",
                "white male6.png", "white male7.png", "white male8.png", "white male9.png"};
        this.spriteBatch = spriteBatch;
;
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
        if (dancing == true && count > 5)
            direction();
        if (drunk != true && count > 5)
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
            if(distance < imageWidth / 2 + 5)
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
                this.dir = "W";
                if (position.getY() > target.getY())
                {
                    this.frame = 0;
                    this.dir = "N";
                }
            } else if (position.getX() < target.getX())
            {
                this.frame = 27;
                this.dir = "E";
                if (position.getY() < target.getY())
                {
                    this.frame = 18;
                    this.dir = "S";
                }
            }
        }
    }

    public void setTarget(Point2D targetPosition)
    {
        this.target = targetPosition;
    }

    public void dance()
    {
        this.dancing = true;
        if (dir == "N")
            this.frame = 36;
        else if (dir == "W")
            this.frame = 45;
        else if (dir == "S")
            this.frame = 54;
        else
            this.frame = 63;

    }

    public void getDrunk()
    {
        this.drunk = true;
        this.frame = 72;
    }



}
