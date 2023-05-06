package prog2.finalgroup;

import prog2.finalgroup.Components.AddCitizen;
import prog2.finalgroup.Components.CitizenSorting;
import prog2.finalgroup.Components.CitizenSearchPanel;
import prog2.finalgroup.Components.CountStats;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MyFrame extends JFrame {

    /**
     * Header size: 30
     * FRAME SIZE: 1000 by 600
     * Left Panel: 80 by 570
     * Right panel: 920 by 570*/

    public MyFrame() {
        // Set the size of the frame
        setSize(1000, 600);

        // Create a left panel with a width of 50 and color it yellow
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.YELLOW);
        leftPanel.setSize(80, getHeight()-30);

        // Create 5 buttons with the given names
        JButton btn1 = new JButton("1");
        JButton btn2 = new JButton("2");
        JButton btn3 = new JButton("3");
        JButton btn4 = new JButton("4");

        //Create the 2 vertical JSeparator
        JSeparator v1 = new JSeparator(SwingConstants.VERTICAL);
        JSeparator v2 = new JSeparator(SwingConstants.VERTICAL);

        // Set the layout of the left panel to a GridLayout with 5 rows and 1 column
        GridLayout layout = new GridLayout(6, 1);
        layout.setVgap(10);
        leftPanel.setLayout(layout);

        // Add the buttons to the left panel
        leftPanel.add(v1);
        leftPanel.add(btn1);
        leftPanel.add(btn2);
        leftPanel.add(btn3);
        leftPanel.add(btn4);
        leftPanel.add(v2);

        // Set the horizontal alignment of the buttons to center
        btn1.setHorizontalAlignment(JButton.CENTER);
        btn2.setHorizontalAlignment(JButton.CENTER);
        btn3.setHorizontalAlignment(JButton.CENTER);
        btn4.setHorizontalAlignment(JButton.CENTER);

        // Create a right panel with a width of 650 and color it blue
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setSize(getWidth()-80, getHeight() - 30); //right panel height: 920; width: 570
        rightPanel.setLocation(80, 0); //location x:80 ; y:0
        // Set the layout of the frame to null so that we can manually position the panels
        rightPanel.setLayout(null);
        rightPanel.setBackground(Color.decode("#EFEFEF"));


        // Create a label with the text "DEMOGRAPHIC DATA DASHBOARD" and a red border
        JLabel titleLabel = new JLabel("DEMOGRAPHIC DATA DASHBOARD");
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Century Gothic", Font.BOLD, 35));
        titleLabel.setBounds(150,30, 600,35);

        //------SEARCH PANEL----------------------------------------------------------
        MyProgramUtility myProgramUtility = new MyProgramUtility();
        CitizenSearchPanel searchBar = new CitizenSearchPanel(myProgramUtility.readDataFromCSV());
        searchBar.setBounds(225,80,450,90);

        //------CITIZEN SORT--------------------------------------------------------------------------

        CitizenSorting citizenSorting = new CitizenSorting(myProgramUtility.readDataFromCSV());
        citizenSorting.setLocation(55,140);
        citizenSorting.setVisible(true); /**CHANGE THIS TO TRUEEEEE AFTER ALL*/

        //--------COUNT STATS----------------------------------------------------------------------

        CountStats countStats = new CountStats();
        countStats.setLocation(55,165);
        countStats.setVisible(false);
        //------ADD CITIZEN--------------------------------------------------------------------------

        AddCitizen addCitizen = new AddCitizen();
        addCitizen.setLocation(55,170);
        addCitizen.setVisible(false);

        //------------------------------------------------------------------------------------------



        //adding elements to right panel
        rightPanel.add(countStats);
        rightPanel.add(addCitizen);
        rightPanel.add(citizenSorting);
        rightPanel.add(searchBar);
        rightPanel.add(titleLabel);


        //search bar Component Z Index
        rightPanel.setComponentZOrder(searchBar,0);
        rightPanel.setComponentZOrder(citizenSorting, 1);
        rightPanel.setComponentZOrder(addCitizen, 1);
        rightPanel.setComponentZOrder(countStats, 1);





        //----------BUTTON LISTENER--------------------------------------
        btn1.addActionListener(e->{
            citizenSorting.setVisible(true);
            addCitizen.setVisible(false);
            countStats.setVisible(false);
        });

        btn2.addActionListener(e->{
            citizenSorting.setVisible(false);
            addCitizen.setVisible(false);
            countStats.setVisible(true);
        });
        btn3.addActionListener(e->{
            citizenSorting.setVisible(false);
            addCitizen.setVisible(true);
            countStats.setVisible(false);
        });
        //----------BUTTON LISTENER--------------------------------------











        // Add the left and right panels to the frame
        add(leftPanel);
        add(rightPanel);

        // Set the layout of the frame to null so that we can manually position the panels
        setLayout(null);

        // Set the frame to be visible & position relative to null
        setVisible(true);
        setLocationRelativeTo(null);
        setTitle("DEMOGRAPHIC DATA DASHBOARD");


        //set the frame to not resizable
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new MyFrame();
    }
}