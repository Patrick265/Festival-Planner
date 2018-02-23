
package AgendaData;

/**
 * the logics behind the festival planner
 * @author Anastasia Hellemons,
 * @version 0.1
 */

public class Artist
{
    private String name;
    private String photo;
    private String genre;

    public Artist(String name, String photo, String genre)
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

    public String getPhoto()
    {
        return photo;
    }

    public void changePhoto(String newPhotoPath)
    {
        this.photo = newPhotoPath;
    }
}

