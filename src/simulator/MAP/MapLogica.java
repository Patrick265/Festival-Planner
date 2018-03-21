package Simulator.MAP;

import Simulator.MapLoader;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.*;

public class MapLogica
{
    private ArrayList<JsonObject> distanceMaps;
    private Point2D startPos;
    private int posX;
    private int posY;
    private int width;
    private int height;

    private int[] distances;
    private Set<Integer> settled;
    private double matrix[][];

    private int paths[];
    private JsonArray path;

    private Queue<Point2D> queue;

    public MapLogica(ArrayList objects, JsonArray path)
    {
        this.path = path;
        this.distanceMaps = objects;
        distances = new int[path.size() + 1];
        settled = new HashSet<>();
        queue = new LinkedList<>();
        matrix = new double[50][50];
        posX = distanceMaps.get(0).getInt("x");
        posY = distanceMaps.get(0).getInt("y");
        width = distanceMaps.get(0).getInt("width");
        height = distanceMaps.get(0).getInt("height");

//        for ()
//        {
//            //calc all distances
//        }

        calcDistance();
    }

    private void calcDistance()
    {
        int count = 0;
        Point2D source = new Point2D.Double((posX + width/2) / 32, (posY + height/2) / 32);
        for (int x = 0; x < 50; x++)
        {
            for (int y = 0; y < 50; y++)
            {
                matrix[x][y] = Integer.MAX_VALUE;
                paths[count] = path.getInt(count);
                count++;
            }
        }
        queue.offer(source);
        matrix[(posX + width/2) / 32][(posY + height/2) / 32] = 0;

        while (!queue.isEmpty())
        {
            Point2D p = queue.poll();

            for (int x = -1; x <= 1; x++)
            {
                for (int y = -1; y <= 1; y++)
                {
                    if ( p.getX() + x < 0 || p.getX() + x >= 50 || p.getY() + y < 0 || p.getY() + y >= 50 || Math.abs(x) == Math.abs(y))
                        continue;
                    double d = matrix[(int) p.getX()][(int) p.getY()] + Math.sqrt(x*x+y*y);
                    if (d < matrix[(int) p.getX()+x][(int) p.getY()+y])
                    {
                        matrix[(int) p.getX()+x][(int) p.getY()+y] = d;
                        queue.offer(new Point2D.Double(p.getX()+x, p.getY()+y));
                    }
                }
            }
        }
    }

    public double[][] getMatrix()
    {
        return matrix;
    }
}