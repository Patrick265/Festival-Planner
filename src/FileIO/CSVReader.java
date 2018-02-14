package FileIO;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVReader
{
    public static void main(String[] args)
    {
        List<Star> stars = readStarFromCSV("C:\\Users\\Patrick de Jong\\Documents\\JavaProjects\\School\\FestivalPlanner\\Festival-Planner\\src\\FileIO\\26BrightestStars.csv");

        for(int i = 0; i < stars.size(); i++)
        {
            System.out.println(stars.toString());
        }
    }

    public static List<Star> readStarFromCSV(String filename)
    {
        List<Star> stars = new ArrayList<>();
        Path pathToFile = Paths.get(filename);

        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename),"Cp1252")))
        {
            String line = br.readLine();

            while(line != null)
            {
                String[] attributes = line.split(",");
                Star star = addStar(attributes);
                stars.add(star);
                line = br.readLine();
            }

        } catch (IOException e)
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
