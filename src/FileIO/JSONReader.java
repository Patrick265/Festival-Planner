package FileIO;

import AgendaData.Artist;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;

import jdk.nashorn.internal.parser.JSONParser;


public class JSONReader {
    //<<<<<<< Updated upstream
    public static void main(String[] args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Artist[] a = objectMapper.readValue(new File("Festival-Planner/src/FileIO/artists.json"), Artist[].class);
        //System.out.println(objectMapper.readValue(new File("Festival-Planner/src/FileIO/artists.json"), Artiest.class));
        //System.out.println(a.get(0).getName());

        for(Artist b : a){
            System.out.println(b.getName());
            System.out.println(b.getPhoto());
            System.out.println(b.getGenre()+ "\n");
        }
    }
