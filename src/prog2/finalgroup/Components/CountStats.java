package prog2.finalgroup.Components;

import prog2.finalgroup.MyProgramUtility;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import java.awt.*;

public class CountStats extends JPanel {
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    JPanel panel4 = new JPanel();
    RoundedPanel panel5 = new RoundedPanel(20, 20, new Color(34, 52, 134));
    RoundedPanel panel6 = new RoundedPanel(20, 20, new Color(82, 113, 255));
    RoundedPanel panel7 = new RoundedPanel(20, 20, new Color(136, 191, 255));
    RoundedPanel panel8 = new RoundedPanel(20, 20, new Color(144, 217, 255));
    JPanel panel5a = new JPanel(new GridLayout(2,1));
    JLabel populationLabel;
    JLabel totalPopulationLabel;
    JPanel panel6a = new JPanel(new GridLayout(2, 3));
    JLabel teenagerPopulationLabel;
    JLabel teenagerPopulationCount;
    JLabel adultPopulationLabel;
    JLabel adultPopulationCount;
    JPanel panel7a = new JPanel(new GridLayout(2, 1));
    JLabel residentLabel;
    JLabel residentCount;
    JPanel panel8a = new JPanel(new GridLayout(2, 1));
    JLabel nonResidentLabel;
    JLabel nonResidentCount;
    LocalDate currentDate = LocalDate.now();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy");
    JLabel lbl1 = new JLabel("Data as of "+ currentDate.format(formatter)+"." );
    JLabel lbl2 = new JLabel("Data as of 05/02/2023");
    JLabel lbl3 = new JLabel("Data as of 05/02/2023");

    public CountStats(){
        setSize(850, 420);
        setVisible(true);

        lbl1.setFont(new Font("Century Gothic", Font.ITALIC | Font.BOLD, 15));
        lbl2.setForeground(Color.decode("#EFEFEF"));
        lbl3.setForeground(Color.decode("#EFEFEF"));

        MyProgramUtility myProgramUtility = new MyProgramUtility();
        String populationCount = String.valueOf(myProgramUtility.totalPopulation());

        populationLabel = new JLabel("Population: ");
        populationLabel.setAlignmentX(Label.CENTER);
        populationLabel.setFont(new Font("Century Gothic",Font.CENTER_BASELINE, 26));
        populationLabel.setBackground(new Color(34, 52, 134));
        populationLabel.setForeground(Color.white);

        totalPopulationLabel = new JLabel(populationCount);
        totalPopulationLabel.setAlignmentX(Label.CENTER);
        totalPopulationLabel.setFont(new Font("Century Gothic",Font.CENTER_BASELINE, 30)); //steph
        totalPopulationLabel.setBackground(new Color(34, 52, 134));
        totalPopulationLabel.setForeground(Color.white);

        panel5.setBorder(BorderFactory.createEmptyBorder(32, 0, 0, 0));
        panel5a.add(populationLabel);
        panel5a.add(totalPopulationLabel);
        panel5a.setBackground(new Color(0,0,0,0));
        panel5.add(panel5a);

        teenagerPopulationLabel = new JLabel("Teenagers count: ");
        teenagerPopulationLabel.setAlignmentX(Label.CENTER);
        teenagerPopulationLabel.setFont(new Font("Century Gothic",Font.CENTER_BASELINE, 20));
        teenagerPopulationLabel.setBackground(new Color(82, 113, 255));
        teenagerPopulationLabel.setForeground(Color.white);

        String teenagerCount = String.valueOf(myProgramUtility.teenageCount());
        teenagerPopulationCount = new JLabel(teenagerCount);
        teenagerPopulationCount.setAlignmentX(Label.CENTER);
        teenagerPopulationCount.setFont(new Font("Century Gothic",Font.CENTER_BASELINE, 27));
        teenagerPopulationCount.setBackground(new Color(82, 113, 255));
        teenagerPopulationCount.setForeground(Color.white);

        adultPopulationLabel = new JLabel("Adults count: ");
        adultPopulationLabel.setAlignmentX(Label.CENTER);
        adultPopulationLabel.setFont(new Font("Century Gothic",Font.CENTER_BASELINE, 20));
        adultPopulationLabel.setBackground(new Color(82, 113, 255));
        adultPopulationLabel.setForeground(Color.white);

        String adultCount = String.valueOf(myProgramUtility.adultCount());
        adultPopulationCount = new JLabel(adultCount);
        adultPopulationCount.setAlignmentX(Label.CENTER);
        adultPopulationCount.setFont(new Font("Century Gothic",Font.CENTER_BASELINE, 27));
        adultPopulationCount.setBackground(new Color(82, 113, 255));
        adultPopulationCount.setForeground(Color.white);

        panel6.setBorder(BorderFactory.createEmptyBorder(35, 0, 0, 0));
        panel6a.add(teenagerPopulationLabel);
        panel6a.add(new JLabel());
        panel6a.add(adultPopulationLabel);
        panel6a.add(teenagerPopulationCount);
        panel6a.add(new JLabel());
        panel6a.add(adultPopulationCount);
        panel6a.setBackground(new Color(0,0,0,0));
        panel6.add(panel6a);

        residentLabel = new JLabel("Non-Residents: ");
        residentLabel.setAlignmentX(Label.CENTER);
        residentLabel.setFont(new Font("Century Gothic",Font.CENTER_BASELINE, 25));
        residentLabel.setBackground(new Color(136, 191, 255));
        residentLabel.setForeground(Color.white);

        String residents = String.valueOf(myProgramUtility.countNonResidents());
        residentCount = new JLabel(residents);
        residentCount.setAlignmentX(Label.CENTER);
        residentCount.setFont(new Font("Century Gothic",Font.CENTER_BASELINE, 27));
        residentCount.setBackground(new Color(136, 191, 255));
        residentCount.setForeground(Color.white);

        panel7.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
        panel7a.add(residentLabel);
        panel7a.add(residentCount);
        panel7a.setBackground(new Color(0,0,0,0));
        panel7.add(panel7a);

        nonResidentLabel = new JLabel("Residents: ");
        nonResidentLabel.setAlignmentX(Label.CENTER);
        nonResidentLabel.setFont(new Font("Century Gothic",Font.CENTER_BASELINE, 26));
        nonResidentLabel.setBackground(new Color(144, 217, 255));
        nonResidentLabel.setForeground(Color.white);

        String nonResidents = String.valueOf(myProgramUtility.countResidents());
        nonResidentCount = new JLabel(nonResidents);
        nonResidentCount.setAlignmentX(Label.CENTER);
        nonResidentCount.setFont(new Font("Century Gothic",Font.CENTER_BASELINE, 27));
        nonResidentCount.setBackground(new Color(144, 217, 255));
        nonResidentCount.setForeground(Color.white);

        panel8.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
        panel8a.add(nonResidentLabel);
        panel8a.add(nonResidentCount);
        panel8a.setBackground(new Color(0,0,0,0));
        panel8.add(panel8a);

        panel1.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel1.add(lbl1);

        panel2.add(lbl2);

        panel3.add(lbl3);

        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        setLayout(gbl);

        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 50;
        gbc.ipady = 10;
        add(panel1, gbc);

        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.ipadx = 50;
        gbc.ipady = 10;
        add(panel2, gbc);

        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.ipadx = 50;
        gbc.ipady = 10;
        add(panel3, gbc);

        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.ipadx = 50;
        gbc.ipady = 10;
        add(panel4, gbc);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5,5,5,5);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipadx = 50;
        gbc.ipady = 20;
        add(panel5, gbc);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5,5,5,5);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.ipady = 20;
        gbc.gridwidth = 3;
        add(panel6, gbc);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5,5,5,5);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.ipady = 50;
        gbc.gridwidth =2;
        add(panel7, gbc);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5,5,5,5);
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.ipady = 50;
        gbc.gridwidth = 2;
        add(panel8, gbc);
    }
}





