package Simulator;

import javax.imageio.ImageIO;
import javax.json.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class MapLoader
{
    private int width;
    private int height;

    private int tileHeight;
    private int tileWidth;

    private ArrayList<BufferedImage> tiles = new ArrayList<>();

    private JsonArray layers2;

    private ArrayList<JsonObject> targets = new ArrayList<>();

    private ArrayList<int[]> mapList = new ArrayList<>();

    public MapLoader(String fileName)
    {
        JsonReader reader = Json.createReader(getClass().getResourceAsStream(fileName));
        JsonObject root = (JsonObject) reader.read();

        width = root.getInt("width");
        height = root.getInt("height");

        JsonArray tileSets = root.getJsonArray("tilesets");

        try
        {
            for (int i = 0; i < tileSets.size(); i++)
            {
                JsonObject tileSet = tileSets.getJsonObject(i);
                String imageSource = tileSet.getString("image");

                BufferedImage image = ImageIO.read(getClass().getResource(imageSource));

                tileHeight = root.getInt("tileheight");
                tileWidth = root.getInt("tilewidth");

                JsonArray terrains = tileSet.getJsonArray("");

                for (int y = 0; y < image.getHeight(); y += tileHeight)
                {
                    for (int x = 0; x < image.getWidth(); x += tileWidth)
                    {
                        if (image.getHeight() - y > tileHeight)
                            tiles.add(image.getSubimage(x, y, tileWidth, tileHeight));
                    }
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        layers2 = root.getJsonArray("layers");

        for(int i = 0; i < 5; i++)
        {
            targets.add(layers2.getJsonObject(0).getJsonArray("objects").getJsonObject(i));
        }

        for (int i = 1; i < layers2.size(); i++)
        {
            int count = 0;
            int[] map = new int[height * width];
            for (int y = 0; y < height; y++)
            {
                for (int x = 0; x < width; x++)
                {
                    map[count] = layers2.getJsonObject(i).getJsonArray("data").getInt(count);
                    count++;
                }
            }
            mapList.add(map);
        }
    }

    public ArrayList getTargets()
    {
        return targets;
    }
    public JsonArray getPaths()
    {
        return layers2.getJsonObject(1).getJsonArray("data");
    }
    void draw(Graphics2D g2)
    {
        AffineTransform tx = new AffineTransform();
        for (int[] map : mapList)
        {
            int count = -1;
            for (int y = 0; y < height; y++)
            {
                for (int x = 0; x < width; x++)
                {
                    tx.setToTranslation(x * tileWidth, y * tileHeight);
                    count++;
                    if (map[count] != 0)
                        g2.drawImage(tiles.get(map[count]- 1), tx, null);
                }
            }
        }
    }
}