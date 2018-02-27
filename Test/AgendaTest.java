import AgendaData.Act;
import AgendaData.Artist;
import AgendaData.Podium;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AgendaTest
{
    @Test
    public void testAddingAndDeleting()
    {
        List<Act> acts = new ArrayList();
        ArrayList<Artist> artistsMain = new ArrayList();
        ArrayList<Artist> artistsSec = new ArrayList <>();
        ArrayList<Artist> artistsThird = new ArrayList <>();

        Artist vsArtist = new Artist("Vince Staples", "path","Hip-Hop");
        Artist stygArtist = new Artist("Stick To Your Guns", "path", "HardRock");
        Artist llArtist = new Artist("Letlive.", "path", "Rock");

        Podium podiumMain = new Podium("MainStage");
        Podium podiumSec = new Podium("Secondary Stage");
        Podium podiumThird = new Podium("Third Stage");
        Date startTime = new Date();
        Date endTime = new Date();

        artistsMain.add(vsArtist);
        artistsSec.add(stygArtist);
        artistsThird.add(llArtist);



        Act vsAct = new Act(75, startTime, endTime, artistsMain,podiumMain);
        Act stygAct = new Act(50,startTime,endTime,artistsSec,podiumSec);
        Act llAct = new Act(35,startTime,endTime,artistsThird,podiumThird);
        acts.add(vsAct);
        acts.add(stygAct);
        acts.add(llAct);

        System.out.println(acts.toString());
//        for(int i = 0; i < acts.size(); i++)
//        {
//            System.out.print(   "Popularity: "  + acts.get(i).getPopularity()               + "\n"    +
//                                "StartTime: " + acts.get(i).getStartTime().getHours()      + ":" + acts.get(i).getStartTime().getMinutes() + "\n"    +
//                                "EndTime: "   + acts.get(i).getEndTime().getHours()        + ":" + acts.get(i).getEndTime().getMinutes()   + "\n"    +
//                                "Name: "       + acts.get(i).getArtists().get(0).getName() + "\n"    +
//                                "Podium name: "+ acts.get(i).getPodium().getName()         + "\n"    +
//                                "----------------------------------"                        + "\n");
//        }

    }
}
