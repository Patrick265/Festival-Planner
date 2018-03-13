package AgendaV2;


import Agenda.NewActGUI;
import javax.swing.*;
import java.awt.*;

public class ButtonControl extends JPanel
{
    private JButton addAct;
    private JButton deleteAct;
    private String[] buttonText = {"Add Act", "Delete Act"};
    public ButtonControl()
    {
        super(new FlowLayout());
        createButtons(this);
        buttonListener();
    }

    private void createButtons(JPanel panel)
    {
        this.addAct = new JButton(buttonText[0]);
        this.deleteAct = new JButton(buttonText[1]);

        panel.add(addAct);
        panel.add(deleteAct);
    }

    private void buttonListener()
    {
        this.addAct.addActionListener(e ->
        {
            NewActGUI newActGUI = new NewActGUI();

        });
    }
}
