package logica;

/**
 * the logics behind the festival planner
 * @author Anastasia Hellemons,
 * @version 0.1
 */

import java.io.File;

public class Artist
{
    private String name;
    private File photo;
    private String genre;

    public void Artist(String name, File photo, String genre)
    {
        this.name = name;
        this.photo = photo;
        this.genre = genre;
    }

    public String getName()
    {
        return name;
    }

    public String getGenre()
    {
        return genre;
    }

    public File getPhoto()
    {
        return photo;
    }

    public void changePhoto(File newPhoto)
    {
        photo = newPhoto;
    }
}
