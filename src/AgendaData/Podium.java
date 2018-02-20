package AgendaData;

/**
 * the logic behind the Podium for the festival planner
 * @author Anastasia Hellemons
 * @version 0.1
 */
public class Podium
{
    private String name;

    public Podium(String name)
    {
       this.name = name;
    }

    public String getName() {return name;}

    public void setName(String name){this.name = name;}
}
