package Simulator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpriteBatch
{
    private Map<String, BufferedImage[]> cutImages;

    public SpriteBatch()
    {
        cutAllImages();
    }

    private void cutAllImages()
    {
        cutImages = new HashMap<>();

        try
        {
            for (String pathname : getImagePaths())
            {
                BufferedImage image = ImageIO.read(new FileInputStream(pathname));
                BufferedImage[] cutImage = new BufferedImage[66];

                for (int i = 0; i < 66; i++)
                    cutImage[i] = image.getSubimage(64 * (i % 9), 64 * (i / 9), 64, 64);

                cutImages.put(new File(pathname).getName(), cutImage);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private List<String> getImagePaths()
    {
        List<String> imagePaths = new ArrayList<>();
        File NPCImageMap = new File("Festival-Planner\\resources\\NPC");
        File[] files = NPCImageMap.listFiles();
        for (int i = 0; files != null && i < files.length; i++)
        {
            imagePaths.add(files[i].getPath());
        }

        return imagePaths;
    }

    public Map getCutImages()
    {
        return cutImages;
    }

    public BufferedImage[] getCutImage(String key)
    {
        return cutImages.get(key);
    }

    public String getPath()
    {
        return getImagePaths().get((int)(Math.random() * 55) + 1);
    }
}
