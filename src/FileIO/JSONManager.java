package FileIO;

import AgendaData.Schedule;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;


public class JSONManager
{
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static File file = new File(FileExplorer.getPath());

    public static Schedule readFile() throws Exception
    {
        return objectMapper.readValue(file, Schedule.class);
    }

    public static void writeToFile(Schedule schedule) throws Exception
    {
        file.delete();
        objectMapper.writeValue(file, schedule);
    }
}
