package simulator.NPC;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class NPCLogic
{
    private String type;
    private String interest;

    private Point2D position;
    private Point2D target = new Point2D.Double(500,500);
    private BufferedImage imageNPC;

    private double angle;
    private double speed;
    private int imageWidth;


    public NPCLogic()
    {
        this.position = new Point2D.Double(Math.random() * 1980, Math.random() * 1080 );
        this.angle = Math.random() * 2 * Math.PI;
        this.speed = 3 + 4 * Math.random();

        try
        {
            this.imageNPC = ImageIO.read(getClass().getResource("/NPC/TestNPC.png"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        this.imageWidth = this.imageNPC.getWidth();

    }

    public void draw(Graphics2D g2d)
    {
        AffineTransform tx = new AffineTransform();
        tx.translate(this.position.getX() - this.imageNPC.getWidth()/2, position.getY() - this.imageNPC.getHeight()/2);
        g2d.drawImage(this.imageNPC, tx, null);
    }


    public void update(ArrayList<NPCLogic> npcs)
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
    }
    public boolean hasCollision(ArrayList<NPCLogic> npcs) {
        boolean hasCollision = false;
        for(NPCLogic npc : npcs)
        {
            if(npc== this)
                continue;
            double distance = position.distance(npc.position);
            if(distance < imageWidth)
                hasCollision = true;
        }
        return hasCollision;
    }

    public void setTarget(Point2D targetPosition)
    {
        this.target = targetPosition;
    }
}
