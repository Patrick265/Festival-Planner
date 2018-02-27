import AgendaData.Act;
import AgendaData.Artist;
import AgendaData.Podium;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * a Test class for the logic of the agenda
 */
public class AgendaTest
{
    private ArrayList<Act> acts;
    private ArrayList<Artist> artistsMain;
    private ArrayList<Artist> artistSecondMain;
    private ArrayList<Artist> artistsSec;
    private ArrayList<Artist> artistsThird;

    private Podium podiumMain;
    private Podium podiumSec;
    private Podium podiumThird;

    @Test
    public void addingAct()
    {
        makeTestScenario();
        //ADDING A NEW ACT
        Date startTime2 = new Date();
        Date endTime2 = new Date();
        startTime2.setHours(16);
        startTime2.setMinutes(0);
        endTime2.setHours(17);
        endTime2.setMinutes(0);
        Artist rtsArtist = new Artist("Recode the Subliminal","path","HardRock");
        this.artistSecondMain = new ArrayList <>();
        this.artistSecondMain.add(rtsArtist);
        acts.add(new Act(10, startTime2, endTime2, this.artistSecondMain,this.podiumMain));


        //COMPARING IT TO A HARDCODED ARRAYLIST
        ArrayList<Act> hardcodedArray = new ArrayList();
        ArrayList<Artist> artistsMainHard = new ArrayList();
        ArrayList<Artist> artistsMainHard2 = new ArrayList();
        ArrayList<Artist> artistsSecHard = new ArrayList();
        ArrayList<Artist> artistsThirdHard = new ArrayList();

        Artist vsArtistHard = new Artist("Vince Staples", "path","Hip-Hop");
        Artist abArtistHard = new Artist("Ali - B", "path", "Hip-Hop");
        Artist stygArtistHard = new Artist("Stick To Your Guns", "path", "HardRock");
        Artist llArtistHard = new Artist("Letlive.", "path", "Rock");
        Artist rtsArtistHard = new Artist("Recode the Subliminal","path","HardRock");

        Podium podiumMainHard = new Podium("MainStage");
        Podium podiumSecHard = new Podium("Secondary Stage");
        Podium podiumThirdHard = new Podium("Third Stage");


        Date beginTime = new Date();
        Date endingTime = new Date();
        beginTime.setHours(13);
        beginTime.setMinutes(35);
        endingTime.setHours(15);
        endingTime.setMinutes(0);

        artistsMainHard.add(vsArtistHard);
        artistsMainHard.add(abArtistHard);
        artistsMainHard2.add(rtsArtistHard);
        artistsSecHard.add(stygArtistHard);
        artistsThirdHard.add(llArtistHard);

        Act vsActHard = new Act(75, beginTime, endingTime, artistsMainHard ,podiumMainHard);
        Act vsActHard2 = new Act(75, beginTime, endingTime, artistsMainHard2 ,podiumMainHard);
        Act stygActHard = new Act(50, beginTime, endingTime, artistsSecHard ,podiumSecHard);
        Act llActHard = new Act(35, beginTime, endingTime, artistsThirdHard,podiumThirdHard);
        hardcodedArray.add(vsActHard);
        hardcodedArray.add(vsActHard2);
        hardcodedArray.add(stygActHard);
        hardcodedArray.add(llActHard);

        assertEquals(printDetailsArrayList(hardcodedArray),printDetailsArrayList(acts));
    }

    @Test
    public void testingPrintDetails()
    {
        makeTestScenario();
        String expected ="\n" + "Popularity: 75\n" +
                "StartTime: 13:35\n" +
                "EndTime: 15:0\n" +
                "Name: Vince Staples, Ali - B, \n" +
                "Podium name: MainStage\n" +
                "----------------------------------\n" +
                "Popularity: 50\n" +
                "StartTime: 13:35\n" +
                "EndTime: 15:0\n" +
                "Name: Stick To Your Guns, \n" +
                "Podium name: Secondary Stage\n" +
                "----------------------------------\n" +
                "Popularity: 35\n" +
                "StartTime: 13:35\n" +
                "EndTime: 15:0\n" +
                "Name: Letlive., \n" +
                "Podium name: Third Stage\n" +
                "----------------------------------";

        System.out.println(printDetails());
        assertEquals(printDetails(), expected);
    }

    public void makeTestScenario()
    {
        this.acts = new ArrayList();
        this.artistsMain = new ArrayList();
        this.artistsSec = new ArrayList <>();
        this.artistsThird = new ArrayList <>();

        Artist vsArtist = new Artist("Vince Staples", "path","Hip-Hop");
        Artist abArtist = new Artist("Ali - B", "path", "Hip-Hop");
        Artist stygArtist = new Artist("Stick To Your Guns", "path", "HardRock");
        Artist llArtist = new Artist("Letlive.", "path", "Rock");

        this.podiumMain = new Podium("MainStage");
        this.podiumSec = new Podium("Secondary Stage");
        this.podiumThird = new Podium("Third Stage");

        Date startTime = new Date();
        Date endTime = new Date();
        startTime.setHours(13);
        startTime.setMinutes(35);
        endTime.setHours(15);
        endTime.setMinutes(0);

        artistsMain.add(vsArtist);
        artistsMain.add(abArtist);
        artistsSec.add(stygArtist);
        artistsThird.add(llArtist);

        Act vsAct = new Act(75, startTime, endTime, this.artistsMain ,this.podiumMain);
        Act stygAct = new Act(50, startTime, endTime, this.artistsSec ,this.podiumSec);
        Act llAct = new Act(35, startTime, endTime, this.artistsThird,this.podiumThird);
        acts.add(vsAct);
        acts.add(stygAct);
        acts.add(llAct);
    }

    public String printDetails()
    {
        String details = "";
        for (int i = 0; i < acts.size(); i++)
        {
            details +=  "\n" + "Popularity: "  + acts.get(i).getPopularity() + "\n" +
                        "StartTime: "   + acts.get(i).getStartTime().getHours() + ":" + acts.get(i).getStartTime().getMinutes() + "\n" +
                        "EndTime: "     + acts.get(i).getEndTime().getHours()   + ":" + acts.get(i).getEndTime().getMinutes()   + "\n";
            details +=  "Name: ";

            for (int artistindex = 0; artistindex < acts.get(i).getArtists().size(); artistindex++)
            {
                details += acts.get(i).getArtists().get(artistindex).getName() + ", ";
            }

            details += "\n" + "Podium name: " + acts.get(i).getPodium().getName() + "\n" +
                    "----------------------------------";
        }
        return details;
    }

    public String printDetailsArrayList(ArrayList<Act> list)
    {
        String details = "";
        for (int i = 0; i < list.size(); i++)
        {
            details +=  "\n" + "Popularity: "  + list.get(i).getPopularity() + "\n" +
                    "StartTime: "   + list.get(i).getStartTime().getHours() + ":" + acts.get(i).getStartTime().getMinutes() + "\n" +
                    "EndTime: "     + list.get(i).getEndTime().getHours()   + ":" + acts.get(i).getEndTime().getMinutes()   + "\n";
            details +=  "Name: ";

            for (int artistindex = 0; artistindex < list.get(i).getArtists().size(); artistindex++)
            {
                details += list.get(i).getArtists().get(artistindex).getName() + ", ";
            }

            details += "\n" + "Podium name: " + list.get(i).getPodium().getName() + "\n" +
                    "----------------------------------";
        }
        return details;
    }
}