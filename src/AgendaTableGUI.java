import javax.swing.*;
import java.awt.*;

public class AgendaTableGUI extends JFrame
{
    public AgendaTableGUI()
    {
        super("Week 1 - 2D-Computergraphics");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel panel = new JPanel(new BorderLayout());
        setContentPane(panel);

        setMinimumSize(new Dimension(400,800));
        setVisible(true);
    }
}
