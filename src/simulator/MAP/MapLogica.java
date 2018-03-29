package Simulator.MAP;

import javax.json.JsonObject;
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

    private double distances[][][];

    private int paths[][];
    private int path[][];
    private String destinationName = null;

    private Queue<Point2D> queue;

    public MapLogica(ArrayList objects, int path[][])
    {
        this.path = path;
        this.distanceMaps = objects;
        queue = new LinkedList<>();
        distances = new double[5][50][50];
        paths = new int[50][50];
//        posX = distanceMaps.get(0).getInt("x");
//        posY = distanceMaps.get(0).getInt("y");
//        width = distanceMaps.get(0).getInt("width");
//        height = distanceMaps.get(0).getInt("height");


        for (int i = 0; i < 5; i++)
        {

            reCalcDistance(distanceMaps.get(i), i);
        }
    }

    public void reCalcDistance(JsonObject object, int target)
    {
        System.out.println("reCalcDistance is called");

        posX = object.getInt("x");
        posY = object.getInt("y");
        width = object.getInt("width");
        height = object.getInt("height");
        destinationName = object.getString("name");
        calcDistance(target);
    }

    private void calcDistance(int target)
    {
        System.out.println("calcDistance is called");

        int count = 0;
        Point2D source = new Point2D.Double((posX + width/2) / 32, (posY + height/2) / 32);
        for (int x = 0; x < 50; x++)
        {
            for (int y = 0; y < 50; y++)
            {
                distances[target][x][y] = Integer.MAX_VALUE;
                paths[x][y] = path[y][x];
                count++;
            }
        }
        queue.offer(source);
        distances[target][(posX + width/2) / 32][(posY + height/2) / 32] = 0;

        while (!queue.isEmpty())
        {
            Point2D p = queue.poll();
            if(paths[(int)p.getY()][(int)p.getX()] != 0)
                continue;
            for (int x = -1; x <= 1; x++)
            {
                for (int y = -1; y <= 1; y++)
                {
                    if ( p.getX() + x < 0 || p.getX() + x >= 50 || p.getY() + y < 0 || p.getY() + y >= 50 || Math.abs(x) == Math.abs(y))
                        continue;
                    double d = distances[target][(int) p.getX()][(int) p.getY()] + Math.sqrt(x*x+y*y);
                    if (d < distances[target][(int) p.getX()+x][(int) p.getY()+y])
                    {
                        distances[target][(int) p.getX()+x][(int) p.getY()+y] = d;
                        queue.offer(new Point2D.Double(p.getX()+x, p.getY()+y));
                    }
                }
            }
        }
    }

    public double[][][] getDistances()
    {
        return distances;
    }

    public int[][] getPaths()
    {
        return paths;
    }

    public String getDestinationName()
    {
        return destinationName;
    }
}