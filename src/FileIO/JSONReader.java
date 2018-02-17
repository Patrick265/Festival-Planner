package FileIO;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class JSONReader {
    public static void main(String[] args) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("data/artists.json");
        Artiest artist = objectMapper.readValue(file, Artiest.class);
        System.out.println(artist.getName());
        System.out.println(artist.getGenre());
        System.out.println(artist.getPhoto());
    }
}
