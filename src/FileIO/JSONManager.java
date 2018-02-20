package FileIO;

import AgendaData.Schedule;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.io.File;


public class JSONManager
{
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static File file = new File("Festival-Planner/src/FileIO/schedule.json");

    public Schedule readFile() throws Exception
    {
        return objectMapper.readValue(file, Schedule.class);
    }

    public void writeToFile(Schedule schedule) throws Exception
    {
        objectMapper.writeValue(file, schedule.getActs());
    }
}
//    public static void bla() throws Exception
//    {
//        Artist test = new Artist("bob", "adfads", "afdsaf");
//        ArrayList<Artist> biem = new ArrayList<>();
//        biem.add(test);
//        Act a = new Act(10, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), biem, new Podium("Podium1"));
//        Act b = new Act(21, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), biem, new Podium("Podium3"));
//        List<Act> actList = new ArrayList<>();
//        actList.add(a);
//        actList.add(b);
//        objectMapper.writeValue(file, actList);
//    }
//}
