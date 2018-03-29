package Simulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class Camera implements MouseListener, MouseMotionListener, MouseWheelListener {
    Point2D centerPoint = new Point2D.Double(0,0);
    double scale = 1;
    double zoomfactor = 0.05;
    double rotation = 0;

    int y = 0;
    int initY = 0;

    int x = 0;
    int initX = 0;
    int mousePosX = 0;
    int mousePosY = 0;

    Point2D lastMousePos;
    JPanel panel;
    Graphics2D g2d;
    Camera(JPanel panel, Graphics2D g2d)
    {
        this.panel = panel;
        this.g2d = g2d;
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
        panel.addMouseWheelListener(this);
    }



    public AffineTransform getTransform(MapLoader map, JPanel panel)
    {
        AffineTransform tx = new AffineTransform();

        tx.scale(scale, scale);
        tx.translate((panel.getWidth()/2) - (map.getWidth()*(scale))/2,
                (panel.getHeight()/2) - (map.getHeight()*(scale))/2);

        //tx.translate(centerPoint.getX(), centerPoint.getY());
        tx.rotate(rotation);
        return tx;
    }



    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        lastMousePos = e.getPoint();
        if(SwingUtilities.isMiddleMouseButton(e))
        {
            this.scale = 1;
        }

        initX = e.getX();
        initY = e.getY();
//        int divX = g2d.getClip().getBounds().x + e.getX();
//        int divY = g2d.getClip().getBounds().y + e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int a = (initY - e.getY()) * -1;
        int b = (initX - e.getX()) * -1;
        y += a;
        x += b;
        initY = e.getY();
        initX = e.getX();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.getWheelRotation() < 0)
        {
            this.scale += zoomfactor;
        }
        if(e.getWheelRotation() > 0)
        {
            this.scale -= zoomfactor;
        }
        this.panel.repaint();
    }
}