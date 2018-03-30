package Simulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;

public class Camera implements MouseListener, MouseMotionListener, MouseWheelListener
{

    private JPanel panel;
    private double zoomfactor = 0.05;


    public double zoom = 1.25;
    public int y  = 0;
    public int initY = 0;
    public int x = 0;
    public int initX = 0;

    Camera(JPanel panel)
    {
        this.panel = panel;

        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
        panel.addMouseWheelListener(this);
    }



    public AffineTransform getTransform(JPanel panel, MapLoader map)
    {
        double width = panel.getWidth();
        double height = panel.getHeight();

        double zoomWidth = width * zoom;
        double zoomHeight = height * zoom;

        double anchorx = (width - zoomWidth) / 2;
        double anchory = (height - zoomHeight) / 2;


        AffineTransform tx = new AffineTransform();
        tx.translate(anchorx, anchory);
        tx.scale(zoom, zoom);
        tx.translate(x, y);


        return tx;

    }



    @Override
    public void mouseClicked(MouseEvent e) {    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        if(SwingUtilities.isMiddleMouseButton(e))
        {
            this.zoom = 1.25;
            this.x = 0;
            this.y = 0;
        }
        initX = e.getX();
        initY = e.getY();
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
            int a = (initY - e.getY()) * -1;
            int b = (initX - e.getX()) * -1;
            y += a;
            x += b;
            initY = e.getY();
            initX = e.getX();
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
            if(this.zoom > 0.5)
            {
                this.zoom -= zoomfactor;
            }
        }
        this.panel.repaint();
    }
}