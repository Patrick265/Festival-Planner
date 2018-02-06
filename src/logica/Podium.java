package logica;

public class Podium {
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
