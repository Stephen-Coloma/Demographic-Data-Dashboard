package prog2.finalgroup;

import prog2.finalgroup.Components.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MyFrame extends JFrame {
    MyProgramUtility myProgramUtility = new MyProgramUtility();

    private JPanel rightPanel;

    private JPanel searchBar;
    private JPanel countStats;
    private JPanel citizenSorting;
    private JPanel addCitizen;


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
        leftPanel.setBackground(new Color(136, 191, 255));
        leftPanel.setSize(80, getHeight()-35);


        //icons
        ImageIcon icon1 = new ImageIcon("res/images/icon1.png");
        ImageIcon icon2 = new ImageIcon("res/images/icon2.png");
        ImageIcon icon3 = new ImageIcon("res/images/icon3.png");


        // Create 5 buttons with the given names
        JButton btn1 = new JButton(icon1);
        JButton btn2 = new JButton(icon2);
        JButton btn3 = new JButton(icon3);
        JButton btn4 = new JButton("4");

        //setting up the focusable and the background
        btn1.setFocusable(false);
        btn2.setFocusable(false);
        btn3.setFocusable(false);
        btn4.setFocusable(false);
        btn1.setBackground(new Color(82, 113, 255));
        btn2.setBackground(new Color(82, 113, 255));
        btn3.setBackground(new Color(82, 113, 255));
        btn4.setBackground(new Color(82, 113, 255));




        //Create the 2 vertical JSeparator
        JSeparator v1 = new JSeparator(SwingConstants.VERTICAL);
//        JSeparator v2 = new JSeparator(SwingConstants.VERTICAL);

        // Set the layout of the left panel to a GridLayout with 5 rows and 1 column
        GridLayout layout = new GridLayout(7, 1);
        layout.setVgap(10);
        leftPanel.setLayout(layout);

        // Add the buttons to the left panel
//        leftPanel.add(v1);
        leftPanel.add(new JLabel());
        leftPanel.add(new JLabel());
        leftPanel.add(btn1);
        leftPanel.add(btn2);
        leftPanel.add(btn3);
        leftPanel.add(btn4);
        leftPanel.add(new JLabel());
//        leftPanel.add(v2);

        // Set the horizontal alignment of the buttons to center
        btn1.setHorizontalAlignment(JButton.CENTER);
        btn2.setHorizontalAlignment(JButton.CENTER);
        btn3.setHorizontalAlignment(JButton.CENTER);
        btn4.setHorizontalAlignment(JButton.CENTER);

        // Create a right panel with a width of 650 and color it blue
        rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setSize(getWidth()-80, getHeight() - 30); //right panel height: 920; width: 570
        rightPanel.setLocation(80, 0); //location x:80 ; y:0
        // Set the layout of the frame to null so that we can manually position the panels
        rightPanel.setLayout(null);
        rightPanel.setBackground(Color.decode("#EFEFEF"));


        // Create a label with the text "DEMOGRAPHIC DATA DASHBOARD" and a red border


        ImageIcon icon = new ImageIcon("res/images/title.png");
        JLabel titleLabel = new JLabel(icon);
        titleLabel.setBounds(50,5, 800,80);

        //------SEARCH PANEL----------------------------------------------------------
        searchBar = createSearchbar();
        searchBar.setVisible(true);

        //--------COUNT STATS----------------------------------------------------------------------

        countStats = createCountStats();
        countStats.setVisible(true);

        //------CITIZEN SORT--------------------------------------------------------------------------

        citizenSorting = createCitizenSorting();
        citizenSorting.setVisible(false);

        //------ADD CITIZEN--------------------------------------------------------------------------

        addCitizen = createAddCitizen();
        addCitizen.setVisible(false);

        //------------------------------------------------------------------------------------------



        //adding elements to right panel
        rightPanel.add(countStats);
        rightPanel.add(addCitizen);
        rightPanel.add(citizenSorting);
        rightPanel.add(searchBar);
        rightPanel.add(titleLabel);


        setUpComponentZIndex(); // z index refresh


        //----------BUTTON LISTENER--------------------------------------
        btn1.addActionListener(e->{

            //removing the JPanel component in the right panel
            rightPanel.remove(countStats);
            countStats = null; //nullifying it

            //creating a new object of the countStats JPanel with the same identifier
            countStats = createCountStats();
            rightPanel.add(countStats);

            setUpSearchBar(); //search bar refresh


            searchBar.setVisible(true);
            citizenSorting.setVisible(false);
            addCitizen.setVisible(false);
            countStats.setVisible(true);



            setUpComponentZIndex(); // z index refresh
        });

        btn2.addActionListener(e->{
            rightPanel.remove(citizenSorting);
            citizenSorting = null; //nullifying it

            //creating a new object of the countStats JPanel with the same identifier
            citizenSorting = createCitizenSorting();
            rightPanel.add(citizenSorting);

            setUpSearchBar(); //search bar refresh


            searchBar.setVisible(true);
            citizenSorting.setVisible(true);
            addCitizen.setVisible(false);
            countStats.setVisible(false);

            setUpComponentZIndex(); // z index refresh

        });
        btn3.addActionListener(e->{
            setUpSearchBar(); //search bar refresh


            searchBar.setVisible(true);
            citizenSorting.setVisible(false);
            addCitizen.setVisible(true);
            countStats.setVisible(false);

            setUpComponentZIndex(); // z index refresh


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
    } // END OF CONSTRUCTOR







    /**
     * Method to add the JPanel for count stats object*/
    private JPanel createCountStats(){
        CountStats countStats = new CountStats();
        countStats.setLocation(25,120);
        countStats.setVisible(false);

        return countStats;
    }

    /**
     * Method to add the JPanel for search bar object*/
    private JPanel createSearchbar(){
        CitizenSearchPanel searchBar = new CitizenSearchPanel(myProgramUtility.readDataFromCSV());
        searchBar.setBounds(225,80,450,90);
        searchBar.setVisible(false);

        return searchBar;
    }

    /**
     * Method to add the JPanel for citizen sort object*/
    private JPanel createCitizenSorting(){
        CitizenSorting citizenSorting = new CitizenSorting(myProgramUtility.readDataFromCSV());
        citizenSorting.setLocation(40,140);
        citizenSorting.setVisible(false);

        return citizenSorting;
    }

    /**
     * Method to add the JPanel for add citizen object*/
    private JPanel createAddCitizen(){
        AddCitizen addCitizen = new AddCitizen();
        addCitizen.setLocation(45,160);
        addCitizen.setVisible(false);

        return addCitizen;
    }









    /**Setting up the search bar*/
    private void setUpSearchBar(){
        //for the search bar
        rightPanel.remove(searchBar);
        searchBar = null; //nullifying it
        searchBar = createSearchbar(); // creating new search bar
        rightPanel.add(searchBar);
    }


    /**Setting up the Component z index in the right panel*/
    private void setUpComponentZIndex() {
        //search bar Component Z Index
        rightPanel.setComponentZOrder(searchBar,0);
        rightPanel.setComponentZOrder(citizenSorting, 1);
        rightPanel.setComponentZOrder(addCitizen, 1);
        rightPanel.setComponentZOrder(countStats, 1);
    }

    public static void main(String[] args) {
        new MyFrame();
    }
}