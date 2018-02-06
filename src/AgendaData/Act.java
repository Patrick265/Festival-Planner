package AgendaData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * the logic behind the activity for the festival planner
 * @author Anastasia Hellemons,
 * @version 0.1
 */
public class Act
{
    private int popularity;
    private Date startTime;
    private Date endTime;
    private List<Artist> artists;
    private Podium podium;



    /**
     * @param popularity the popularity of the activity in int.
     * @param startTime  the starting time of the activity in Double.
     * @param endTime    the ending time of the activity in Double.
     * @param artists    the artists who will be on stage during the activity.
     * @param podium     the podium on which the activity wil be held.
     */
    public Act(int popularity, Date startTime, Date endTime, ArrayList artists, Podium podium)
    {
        this.artists = artists;
        this.popularity = popularity;
        this.startTime = startTime;
        this.endTime = endTime;
        this.podium = podium;
    }

    public int getPopularity()
    {
        return popularity;
    }
    public void setPopularity(int popularity)
    {
        this.popularity = popularity;
    }

    public Date getStartTime()
    {
        return startTime;
    }
    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }
    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }

    public List<Artist> getArtists()
    {
        return artists;
    }
    public void setArtists(List<Artist> artists)
    {
        this.artists = artists;
    }

    public Podium getPodium()
    {
        return podium;
    }
    public void setPodium(Podium podium)
    {
        this.podium = podium;
    }
}