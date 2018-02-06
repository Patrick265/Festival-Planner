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
            if(pd.equals(npd))
            {
                if(st.compareTo(net) < 0  || et.compareTo(nst) > 0)
                {

                }
                if()
                {

                }
            }

            if ((act.getStartTime() != newAct.getStartTime() || act.getEndTime() != newAct.getEndTime()) && act.getPodium() != newAct.getPodium())
            {

            }
        }


    }
}
