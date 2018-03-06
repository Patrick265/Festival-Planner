package NPC;

import javax.imageio.ImageIO;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class NPCLogic
{
    private String type;
    private String interest;
    private Point2D position;
    private double angle;
    private BufferedImage imageNPC;

    public NPCLogic()
    {
        this.position = new Point2D.Double(Math.random() * 1980, Math.random() * 1080 );
        this.angle = ;
        this.imageNPC = ImageIO.read(getClass().getResource("/"));
    }
}
