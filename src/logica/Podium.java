package logica;

/**
 * the logic behind the Podium for the festival planner
 * @author Anastasia Hellemons
 * @version 0.1
 */
public class Podium
{
    private String namePodium;
    // location is the coordinates of the locaton on the map
    private Double location;

    public void Podium(String namePodium, Double location)
    {
       this.namePodium = namePodium;
       this.location = location;
    }

    public String getNamePodium()
    {
        return namePodium;
    }

    public Double getLocation()
    {
        return location;
    }
}
