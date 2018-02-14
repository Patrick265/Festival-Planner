package FileIO;

public class Star
{
    private String commonName;
    private String scientificName;
    private double distance;
    private double apparentMagnitude;
    private double absoluteMagnitude;

    public Star(String commonName, String scientificName, double distance, double apparentMagnitude, double absoluteMagnitude)
    {
        this.commonName = commonName;
        this.scientificName = scientificName;
        this.distance = distance;
        this.apparentMagnitude = apparentMagnitude;
        this.absoluteMagnitude = absoluteMagnitude;
    }

    public String toString()
    {
        return  "Common name: "         +   commonName          +   "\n"    +
                "Scientific name: "     +   scientificName      +   "\n"    +
                "Distance: "            +   distance            +   "\n"    +
                "Apparent Magnitude: "  +   apparentMagnitude   +   "\n"    +
                "Absolute Magnitude: "  +   absoluteMagnitude   +   "\n"    +
                "--------------------------------------------"  +   "\n" ;
    }
    public String getCommonName()
    {
        return commonName;
    }

    public void setCommonName(String commonName)
    {
        this.commonName = commonName;
    }

    public String getScientificName()
    {
        return scientificName;
    }

    public void setScientificName(String scientificName)
    {
        this.scientificName = scientificName;
    }

    public double getDistance()
    {
        return distance;
    }

    public void setDistance(double distance)
    {
        this.distance = distance;
    }

    public double getApparentMagnitude()
    {
        return apparentMagnitude;
    }

    public void setApparentMagnitude(double apparentMagnitude)
    {
        this.apparentMagnitude = apparentMagnitude;
    }

    public double getAbsoluteMagnitude()
    {
        return absoluteMagnitude;
    }

    public void setAbsoluteMagnitude(double absoluteMagnitude)
    {
        this.absoluteMagnitude = absoluteMagnitude;
    }
}
