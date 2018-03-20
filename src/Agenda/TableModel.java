package Agenda;

import AgendaData.Act;
import AgendaData.Schedule;
import FileIO.JSONManager;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TableModel extends AbstractTableModel
{

    private String[] headerNames = {"Artist(s)", "Genre", "Popularity", "Podum", "Start Time", "End Time"};
    private List<Act> acts;
    private Schedule schedules;
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");


    public TableModel()
    {
        this.acts = new ArrayList <>();
        readJSON();
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
                return sdf.format(this.acts.get(row).getStartTime());
            case 5:
                return sdf.format(this.acts.get(row).getEndTime());
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

    @Override
    public void fireTableDataChanged()
    {
        super.fireTableDataChanged();
    }

    public void readJSON()
    {
        try
        {
            this.schedules = JSONManager.readFile();
            System.out.println("Reading JSON for agenda");
            this.acts = schedules.getActs();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void deleteAct(JPanel tablePanel, JTable table)
    {
        try
        {
            this.schedules = JSONManager.readFile();
            this.schedules.getActs().remove(table.getSelectedRow());
            JSONManager.writeToFile(schedules);
            JOptionPane.showMessageDialog(tablePanel, "Act deleted.");
        }
        catch (Exception e1)
        {
            e1.printStackTrace();
        }
    }

    public void printActs()
    {
        for(int i = 0; i < acts.size(); i++)
        {
            System.out.print(acts.get(i).getArtists().get(0).getName());
        }
        System.out.println("\n");
    }

    public List getActs()
    {
        return acts;
    }
}