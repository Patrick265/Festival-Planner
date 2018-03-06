package simulator.NPC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class NPCDemo extends JPanel implements ActionListener
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("NPC-Demo");
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(new NPCDemo());
        frame.setVisible(true);
    }

    ArrayList<NPCLogic> npc = new ArrayList<>();

    public NPCDemo()
    {
        while(npc.size() < 10)
        {
            NPCLogic visitor = new NPCLogic();
            if(!visitor.hasCollision(npc))
                npc.add(visitor);
        }


        new Timer(1000/60, this).start();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e)
            {
                for(NPCLogic visitor : npc)
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

        for(NPCLogic visitor : npc)
            visitor.draw(g2d);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        for(NPCLogic visitor : npc)
            visitor.update(npc);
        repaint();
    }
}
