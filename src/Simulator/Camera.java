package Simulator;


import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;

public class Camera implements MouseListener, MouseMotionListener, MouseWheelListener
{

    private JPanel panel;
    private MapLoader map;

    private double zoomfactor = 0.05;


    public double zoom = 1;
    public int y  = 0;
    public int initY = 0;
    public int x = 0;
    public int initX = 0;

    Camera(JPanel panel, MapLoader map)
    {
        this.panel = panel;
        this.map = map;
        this.panel.addMouseListener(this);
        this.panel.addMouseMotionListener(this);
        this.panel.addMouseWheelListener(this);
    }



    public AffineTransform getTransform(JPanel panel)
    {
        AffineTransform tx = new AffineTransform();

        double width = panel.getWidth();
        double height = panel.getHeight();

        double zoomWidth = width * this.zoom;
        double zoomHeight = height * this.zoom;

        double anchorx = (width - zoomWidth) / 2;
        double anchory = (height - zoomHeight) / 2;


        tx.translate(anchorx, anchory);
        tx.scale(this.zoom, this.zoom);
        tx.translate(this.x, this.y);
        return tx;
    }

    @Override
    public void mouseClicked(MouseEvent e) {    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        if (SwingUtilities.isMiddleMouseButton(e))
        {
            this.zoom = 1.25;
            this.x = 0;
            this.y = 0;
        }

            this.initX = e.getX();
            this.initY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) {    }

    @Override
    public void mouseExited(MouseEvent e) {    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        if(SwingUtilities.isLeftMouseButton(e))
        {
                if(x < panel.getWidth() || x < 0)
                {
                    int incrementX = (this.initX - e.getX()) * -1;
                    int incrementY = (this.initY - e.getY()) * -1;
                    this.y += incrementY;
                    this.x += incrementX;
                    this.initY = e.getY();
                    this.initX = e.getX();
                }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) { }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e)
    {
        if (e.getWheelRotation() < 0)
        {
            if (this.zoom < 2)
            {
                this.zoom += zoomfactor;
            }
        }
        if(e.getWheelRotation() > 0)
        {
            if(this.zoom > 0.75)
            {
                this.zoom -= zoomfactor;
            }
        }
        this.panel.repaint();
    }
}