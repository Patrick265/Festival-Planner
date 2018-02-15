package FileIO;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader
{
    public static void main(String[] args)
    {
        List<Star> stars = readStarFromCSV("C:\\Users\\Patrick de Jong\\Documents\\JavaProjects\\School\\FestivalPlanner\\Festival-Planner\\src\\FileIO\\26BrightestStars.csv");
        for(Star star : stars)
        {
            System.out.println(star);
        }
    }

    public static List<Star> readStarFromCSV(String filename)
    {
        File file = new File(filename);
        List<Star> stars = new ArrayList<>();

        try(Scanner input = new Scanner(file))
        {
            String line = input.nextLine();
            while(input.hasNext())
            {
                String[] attributes = line.split(",");
                Star star = addStar(attributes);
                stars.add(star);
                line = input.nextLine();
            }
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return stars;
    }

    public static Star addStar(String[] data)
    {
        String commonName = data[0];
        String scientifcName = data[1];
        double distance = Double.parseDouble(data[2]);
        double apparantMagnitude = Double.parseDouble(data[3]);
        double absoluteMagnitude = Double.parseDouble(data[4]);

        return new Star(commonName, scientifcName, distance, apparantMagnitude, absoluteMagnitude);
    }
}
