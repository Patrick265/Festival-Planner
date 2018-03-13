package simulator;

import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 * Created by johan on 15-2-2017.
 */
public class Camera implements MouseListener, MouseMotionListener, MouseWheelListener {
    Point2D centerPoint = new Point2D.Double(0,0);
    double zoom = 1;
    double rotation = 0;


    Point2D lastMousePos;
    JPanel panel;
    Camera(JPanel panel)
    {
        this.panel = panel;
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
        panel.addMouseWheelListener(this);
    }



    public AffineTransform getTransform(int windowWidth, int windowHeight)
    {
        AffineTransform tx = new AffineTransform();
        tx.translate(windowWidth/2, windowHeight/2);
        tx.scale(zoom, zoom);
        tx.translate(centerPoint.getX(), centerPoint.getY());
        tx.rotate(rotation);
        return tx;
    }



    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        lastMousePos = e.getPoint();
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
        if(SwingUtilities.isMiddleMouseButton(e))
        {
            centerPoint = new Point2D.Double(
                    centerPoint.getX() - (lastMousePos.getX() - e.getX()) / zoom,
                    centerPoint.getY() - (lastMousePos.getY() - e.getY()) / zoom
            );
            lastMousePos = e.getPoint();
            panel.repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        zoom *= (1 - e.getWheelRotation()/25.0f);
        panel.repaint();
    }
}