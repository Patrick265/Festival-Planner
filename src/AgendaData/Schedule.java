package AgendaData;

import java.util.*;

public class Schedule
{
    private List<Act> acts;
    private Set<Artist> artists;

    public Schedule()
    {
        this.acts = new ArrayList<>();
        this.artists = new LinkedHashSet<>();
    }

    public void addAct(Act newAct)
    {
        boolean isEligable;
        Date startTime, endTime, newStartTime, newEndTime;
        Podium podium, newPodium;

        isEligable = true;
        this.artists.addAll(newAct.getArtists());
        newStartTime = newAct.getStartTime();
        newEndTime = newAct.getEndTime();
        newPodium = newAct.getPodium();
        for(Act act : this.acts)
        {
            podium = act.getPodium();
            startTime = act.getStartTime();
            endTime = act.getEndTime();
            if(startTime.compareTo(newEndTime) != endTime.compareTo(newStartTime))
            {
                if(podium.equals(newPodium))
                {
                    isEligable = false;
                    System.out.println("Something with time is wrong");
                    return;
                }

                for(Artist artist : act.getArtists())
                {
                    for (Artist newArtist : newAct.getArtists())
                    {
                        if (artist.equals(newArtist))
                        {
                            isEligable = false;
                            System.out.print("Something with artist is wrong: ");
                            System.out.println(newArtist.getName() + " is already busy. ");

                            System.out.print("Conflicting act - Start: " + act.getStartTime().toString());
                            System.out.print(" End: " + act.getEndTime().toString());
                            System.out.print(" Current Artist: " + artist.getName());
                            System.out.println(" Stage: " + act.getPodium().getName());

                            System.out.print("New act - Start: " + newAct.getStartTime().toString());
                            System.out.print(" End: " + newAct.getEndTime().toString());
                            System.out.print(" Current Artist: " + newArtist.getName());
                            System.out.println(" Stage: " + newAct.getPodium().getName());

                            System.out.println(act.getStartTime().equals(newAct.getStartTime()));
                            return;
                        }
                    }
                }
            }
        }

        if(isEligable)
        {
            acts.add(newAct);
            System.out.println("Act added!");
        }
        else
        {
            throw new Error("Test");
        }
    }

    public void setActs(List<Act> acts)
    {
        this.acts = acts;
    }
    public List<Act> getActs()
    {
        return this.acts;
    }

    public void setArtists(Set<Artist> artists)
    {
        this.artists = artists;
    }
    public Set<Artist> getArtists()
    {
        return this.artists;
    }
}