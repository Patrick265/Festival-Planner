
package AgendaData;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * the logics behind the festival planner
 * @author Anastasia Hellemons,
 * @version 0.1
 */

public class Artist
{
    private String name;
    private BufferedImage photo;
    private String genre;

    public Artist(String name, String photoPath, String genre)
    {
        this.name = name;
        try
        {
            this.photo = ImageIO.read(new FileInputStream(photoPath));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        this.genre = genre;
    }
    public Artist(){}

    public String getName()
    {
        return name;
    }
    public void setName(String name){this.name = name;}
    public String getGenre()
    {
        return genre;
    }

    public BufferedImage getPhoto()
    {
        return photo;
    }

    public void changePhoto(String newPhotoPath)
    {
        try
        {
            this.photo = ImageIO.read(new FileInputStream(newPhotoPath));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

