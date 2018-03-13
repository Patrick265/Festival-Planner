package AgendaV2;

import AgendaData.Act;
import AgendaData.Schedule;
import FileIO.JSONManager;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TableModel extends AbstractTableModel
{

    private String[] headerNames = {"Artist(s)", "Genre", "Popularity", "Podum", "Start Time", "End Time"};
    private List<Act> acts;
    private Schedule schedules;


    public TableModel()
    {
        this.acts = new ArrayList <>();
        try
        {
            this.schedules = JSONManager.readFile();
            this.acts = schedules.getActs();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        for(Act act : acts)
        {
            System.out.println(acts);
        }

    }

    @Override
    public Object getValueAt(int row, int column)
    {


        switch (column)
        {
            case 0:
                return this.acts.get(row).getArtists().get(0).getName();
            case 1:
                return this.acts.get(row).getArtists().get(0).getGenre();
            case 2:
                return this.acts.get(row).getPopularity();
            case 3:
                return this.acts.get(row).getPodium().getName();
            case 4:
                return this.acts.get(row).getStartTime().toString();
            case 5:
                return this.acts.get(row).getEndTime().toString();
        }
        return "";
    }

    @Override
    public int getRowCount()
    {
        return acts.size();
    }

    @Override
    public int getColumnCount()
    {
        return headerNames.length;
    }


    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return true;
    }

    @Override
    public String getColumnName(int column)
    {
        switch(column)
        {
            case 0:
                return headerNames[0];
            case 1:
                return headerNames[1];
            case 2:
                return headerNames[2];
            case 3:
                return headerNames[3];
            case 4:
                return headerNames[4];
            case 5:
                return headerNames[5];
        }
        return "";
    }
}
