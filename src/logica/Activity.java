package logica;

/**
 * the logic behind the activity for the festival planner
 * @author Anastasia Hellemons,
 * @version 0.1
 */
public class Activity
{
    private int popularity;
    private Double startTime;
    private Double endTime;
    private Artist artist;
    private Podium podium;

    /**
     *
     * @param popularity the popularity of the activity in int.
     * @param startTime the starting time of the activity in Double.
     * @param endTime the ending time of the activity in Double.
     * @param artist the artist who will be on stage during the activity.
     * @param podium the podium on which the activity wil be held.
     */
    public Activity(int popularity, Double startTime, Double endTime, Artist artist, Podium podium)
    {
        this.popularity = popularity;
        this.startTime = startTime;
        this.endTime = endTime;
        this.artist = artist;
        this.podium = podium;
    }

    /**
     *
     * @return the popularity of the activity in int.
     */
    public int getPopularity()
    {
        return popularity;
    }

    /**
     *
     * @return the starting time of the activity in Double.
     */
    public Double getStartTime()
    {
        return startTime;
    }

    /**
     *
     * @return the ending time of the activity in Double.
     */
    public Double getEndTime()
    {
        return endTime;
    }

    /**
     *
     * @return the artist who wil be on stage during the activity.
     */
    public Artist getArtist()
    {
        return artist;
    }

    /**
     *
     * @return the podium on which the activity wil be held.
     */
    public Podium getPodium() {
        return podium;
    }


    /**
     * changes value of the popularity.
     * @param newPop the new value of the activity's popularity in int.
     */
    public void setPopularity(int newPop)
    {
        popularity = newPop;
    }

    /**
     * changes the starting time of the activity.
     * @param newTime the new starting time of the activity in Double.
     */
    public void setStartTime(Double newTime)
    {
        startTime = newTime;
    }

    /**
     * changes the ending time of the activity.
     * @param newTime the new ending time of the activity in Double.
     */
    public void setEndTime(Double newTime)
    {
        endTime = newTime;
    }

    /**
     * changes the artist who will be on stage during the activity.
     * @param newArtist the new artist who will be on stage during te activity.
     */
    public void setArtist(Artist newArtist)
    {
        artist = newArtist;
    }

    /**
     * changes the podium on which the activity will be held.
     * @param newPodium the new podium on which the activity will be held.
     */
    public void setPodium(Podium newPodium)
    {
        podium = newPodium;
    }
}
