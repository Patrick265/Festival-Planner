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
        Date date1 = new Date();
        date1.setTime(System.currentTimeMillis());

        Date date2 = new Date();
        date2.setHours(13);
        date2.setMinutes(46);
        date2.setTime(System.currentTimeMillis() - 10000000);

        System.out.println(date1);
        System.out.println(date2);
        System.out.println(date1.compareTo(date2));

        GUI gui = new GUI();
    }

}