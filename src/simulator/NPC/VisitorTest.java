package simulator.NPC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class VisitorTest extends JPanel implements ActionListener
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("NPC-Demo");
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(new VisitorTest());
        frame.setVisible(true);
    }

    ArrayList<VistorLogic> visitors = new ArrayList<>();

    public VisitorTest()
    {
        while(visitors.size() < 10)
        {
            VistorLogic visitor = new VistorLogic();
            if(!visitor.hasCollision(visitors))
            {
                visitors.add(visitor);
            }
        }


        new Timer(1000/60, this).start();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e)
            {
                for(VistorLogic visitor : visitors)
                {
                    visitor.setTarget(e.getPoint());
                }
            }
        });
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for(VistorLogic visitor : visitors)
        {
            visitor.draw(g2d);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
    }

    public void update()
    {
        for(VistorLogic visitor : visitors)
        {
            visitor.update(visitors);

        }

        repaint();
    }

    public void setTargets(Point e)
    {
        for(VistorLogic visitor : visitors)
        {
            visitor.setTarget(e);
        }
    }

}
