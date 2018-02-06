package AgendaData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Schedule
{
    private List<Act> acts;

    public Schedule()
    {
        this.acts = new ArrayList<>();
    }

    public void addAct(Act newAct)
    {
        boolean isEligable = true;
        Date nst = newAct.getStartTime();
        Date net = newAct.getEndTime();
        Podium npd = newAct.getPodium();
        for(Act act : acts)
        {
            Podium pd = act.getPodium();
            Date st = act.getStartTime();
            Date et = act.getEndTime();
            if(st.compareTo(net) != et.compareTo(nst))
            {
                if(pd.equals(npd))
                {
                    isEligable = false;
                    return;
                }

                for(Artist artist : act.getArtists())
                {
                    for (Artist newArtist : newAct.getArtists())
                    {
                        if (artist.equals(newArtist))
                        {
                            isEligable = false;
                            return;
                        }
                    }
                }
            }
        }

        if(isEligable)
        {
            acts.add(newAct);
        }
    }
}