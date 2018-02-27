import javax.swing.*;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @Author Thomas Mandemaker, Patrick de Jong, Yannick van Dolen , Sergen Peker , Anastasia Hellemons
 * @version 1.0
 */
public class Main
{
    public static void main(String[] args)
    {
        JFrame.setDefaultLookAndFeelDecorated(true);
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception ex)
        {

        }
        GUI gui = new GUI();
    }

}