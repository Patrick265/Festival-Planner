package Agenda;


import AgendaData.Schedule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AgendaGUI extends JFrame
{
    private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

    private static TableModel tableModel;
    private JTable table;
    private JScrollPane scrollPane;
    private JPanel mainPanel;
    private JPanel tablePanel;
    private JPanel buttonPanel;
    private JButton addAct;
    private JButton deleteAct;
    private String[] buttonText = {"Add Act", "Delete Act"};

    public AgendaGUI()
    {

        super("Agenda");
        this.mainPanel = new JPanel(new BorderLayout());
        this.tablePanel = new JPanel(new BorderLayout());
        this.buttonPanel = new JPanel(new FlowLayout());

        tableModel = new TableModel();
        this.table = new JTable(this.tableModel);
        this.table.setFillsViewportHeight(true);
        this.table.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
            if (e.getButton() == 3)
            {
                int row = table.rowAtPoint(e.getPoint());
                int col = table.columnAtPoint(e.getPoint());
                if (row >= 0 && col >= 0)
                {
                    int index = row;
                    AgendaInfoPopUpGUI popup = new AgendaInfoPopUpGUI(index, tableModel.getActs());
                }
            }
            }
        });
        this.scrollPane = new JScrollPane(this.table);

        this.tablePanel.add(this.scrollPane);
        createButtons();
        buttonListener();
        this.mainPanel.add(this.tablePanel,BorderLayout.CENTER);
        this.mainPanel.add(this.buttonPanel, BorderLayout.SOUTH);


        super.setContentPane(mainPanel);
        super.setSize(800,600);
        super.setLocation((screenWidth / 2) - (getWidth() / 2), (screenHeight / 2) - (getHeight() / 2));
        super.setResizable(false);
        super.setVisible(true);
    }

    public void createButtons()
    {
        this.addAct = new JButton(buttonText[0]);

        this.deleteAct = new JButton(buttonText[1]);
        this.buttonPanel.add(addAct);
        this.buttonPanel.add(deleteAct);
    }

    public void buttonListener()
    {
        this.addAct.addActionListener(e ->
        {
            NewActGUI newActGUI = new NewActGUI();
            tableModel.printActs();
            updateTable();
        });

        this.deleteAct.addActionListener(e ->
        {
            tableModel.deleteAct(this.tablePanel, this.table);
            updateTable();
        });

    }

    public static void updateTable()
    {
        tableModel.readJSON();
        tableModel.fireTableDataChanged();
        tableModel.fireTableRowsInserted(0, tableModel.getRowCount());
    }
}