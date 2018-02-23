package FileIO;

import AgendaData.Act;
import AgendaData.Schedule;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.List;


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
