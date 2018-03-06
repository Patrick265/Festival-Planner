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
    private Point2D target;
    private BufferedImage imageNPC;

    private double angle;
    private int imageWidth;
    private int imageHeight;

    public NPCLogic()
    {
        this.position = new Point2D.Double(Math.random() * 1980, Math.random() * 1080 );
        this.angle = Math.random() * 2 * Math.PI;
        try
        {
            this.imageNPC = ImageIO.read(getClass().getResource("/NPC/TestNPC.png"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        this.imageWidth = this.imageNPC.getWidth();
        this.imageHeight = this.imageNPC.getHeight();
    }

    public void draw(Graphics2D g2d)
    {
        AffineTransform tx = new AffineTransform();
        tx.translate(this.position.getX() - this.imageNPC.getWidth()/2, position.getY() - this.imageNPC.getHeight()/2);
        tx.rotate(this.angle, this.imageNPC.getWidth()/2, this.imageNPC.getHeight()/2);
        g2d.drawImage(this.imageNPC, tx, null);
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
