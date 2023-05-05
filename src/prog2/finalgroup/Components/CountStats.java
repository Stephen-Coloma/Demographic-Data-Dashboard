package prog2.finalgroup.Components;

import prog2.finalgroup.Citizen;
import prog2.finalgroup.MyProgramUtility;
import java.util.Date;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class CountStats extends JPanel {
    JPanel mainPanel = new JPanel();
    Panel panel1 = new Panel();
    Panel panel2 = new Panel();
    Panel panel3 = new Panel();
    Panel panel4 = new Panel();
    RoundedPanel panel5 = new RoundedPanel(20, 20, new Color(34,	52,	134));
    RoundedPanel panel6 = new RoundedPanel(20, 20, new Color(82,	113, 255));
    RoundedPanel panel7 = new RoundedPanel(20, 20, new Color(136, 191, 255));
    RoundedPanel panel8 = new RoundedPanel(20, 20, new Color(144, 217, 255));
    JPanel panel5a = new JPanel(new GridLayout(2,1));
    Label populationLabel;
    Label totalPopulationLabel;
    JPanel panel6a = new JPanel(new GridLayout(2, 2));
    Label teenagerPopulationLabel;
    Label teenagerPopulationCount;
    Label adultPopulationLabel;
    Label adultPopulationCount;
    JPanel panel7a = new JPanel(new GridLayout(2, 1));
    Label residentLabel;
    Label residentCount;
    JPanel panel8a = new JPanel(new GridLayout(2, 1));
    Label nonResidentLabel;
    Label nonResidentCount;
    Label lbl1 = new Label("Data as of "+ java.time.LocalDateTime.now()+"." );
    Label lbl2 = new Label("Data as of 05/02/2023");
    Label lbl3 = new Label("Data as of 05/02/2023");
    Label lbl4 = new Label("Data as of 05/02/2023");

    public CountStats(){

        setSize(800, 350);
        setVisible(true);
        mainPanel.setVisible(true);

        lbl1.setFont(new Font("Century Gothic", Font.BOLD, 12));
        lbl2.setForeground(Color.decode("#EFEFEF"));
        lbl3.setForeground(Color.decode("#EFEFEF"));
        lbl4.setForeground(Color.decode("#EFEFEF"));

        MyProgramUtility myProgramUtility = new MyProgramUtility();
        String populationCount = String.valueOf(myProgramUtility.totalPopulation());

        populationLabel = new Label("Total Population: ");
        populationLabel.setAlignment(Label.CENTER);
        populationLabel.setFont(new Font("Century Gothic",Font.CENTER_BASELINE, 16));
        populationLabel.setBackground(new Color(34,	52,	134));
        populationLabel.setForeground(Color.white);

        totalPopulationLabel = new Label(populationCount);
        totalPopulationLabel.setAlignment(Label.CENTER);
        totalPopulationLabel.setFont(new Font("Century Gothic",Font.CENTER_BASELINE, 25));
        totalPopulationLabel.setBackground(new Color(34,	52,	134));
        totalPopulationLabel.setForeground(Color.white);

        panel5.setBorder(BorderFactory.createEmptyBorder(32, 0, 0, 0));
        panel5a.add(populationLabel);
        panel5a.add(totalPopulationLabel);
        panel5.add(panel5a);

        teenagerPopulationLabel = new Label("Number of Teenagers: ");
        teenagerPopulationLabel.setAlignment(Label.CENTER);
        teenagerPopulationLabel.setFont(new Font("Century Gothic",Font.CENTER_BASELINE, 18));
        teenagerPopulationLabel.setBackground(new Color(82,	113,	255));
        teenagerPopulationLabel.setForeground(Color.white);

        String teenagerCount = String.valueOf(myProgramUtility.teenageCount());
        teenagerPopulationCount = new Label(teenagerCount);
        teenagerPopulationCount.setAlignment(Label.CENTER);
        teenagerPopulationCount.setFont(new Font("Century Gothic",Font.CENTER_BASELINE, 25));
        teenagerPopulationCount.setBackground(new Color(82,	113,	255));
        teenagerPopulationCount.setForeground(Color.white);

        adultPopulationLabel = new Label("Number of Adults: ");
        adultPopulationLabel.setAlignment(Label.CENTER);
        adultPopulationLabel.setFont(new Font("Century Gothic",Font.CENTER_BASELINE, 18));
        adultPopulationLabel.setBackground(new Color(82,	113,	255));
        adultPopulationLabel.setForeground(Color.white);

        String adultCount = String.valueOf(myProgramUtility.adultCount());
        adultPopulationCount = new Label(adultCount);
        adultPopulationCount.setAlignment(Label.CENTER);
        adultPopulationCount.setFont(new Font("Century Gothic",Font.CENTER_BASELINE, 25));
        adultPopulationCount.setBackground(new Color(82,	113,	255));
        adultPopulationCount.setForeground(Color.white);

        panel6.setBorder(BorderFactory.createEmptyBorder(35, 0, 0, 0));
        panel6a.add(teenagerPopulationLabel);
        panel6a.add(adultPopulationLabel);
        panel6a.add(teenagerPopulationCount);
        panel6a.add(adultPopulationCount);
        panel6.add(panel6a);

        residentLabel = new Label("Residents: ");
        residentLabel.setAlignment(Label.CENTER);
        residentLabel.setFont(new Font("Century Gothic",Font.CENTER_BASELINE, 31));
        residentLabel.setBackground(new Color(136, 191, 255));
        residentLabel.setForeground(Color.white);

        String residents = String.valueOf(myProgramUtility.countResidents());
        residentCount = new Label(residents);
        residentCount.setAlignment(Label.CENTER);
        residentCount.setFont(new Font("Century Gothic",Font.CENTER_BASELINE, 30));
        residentCount.setBackground(new Color(136, 191, 255));
        residentCount.setForeground(Color.white);

        panel7.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
        panel7a.add(residentLabel);
        panel7a.add(residentCount);
        panel7.add(panel7a);

        nonResidentLabel = new Label("Non-Residents: ");
        nonResidentLabel.setAlignment(Label.CENTER);
        nonResidentLabel.setFont(new Font("Century Gothic",Font.CENTER_BASELINE, 30));
        nonResidentLabel.setBackground(new Color(144, 217, 255));
        nonResidentLabel.setForeground(Color.white);

        String nonResidents = String.valueOf(myProgramUtility.countNonResidents());
        nonResidentCount = new Label(nonResidents);
        nonResidentCount.setAlignment(Label.CENTER);
        nonResidentCount.setFont(new Font("Century Gothic",Font.CENTER_BASELINE, 30));
        nonResidentCount.setBackground(new Color(144, 217, 255));
        nonResidentCount.setForeground(Color.white);

        panel8.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
        panel8a.add(nonResidentLabel);
        panel8a.add(nonResidentCount);
        panel8.add(panel8a);

        panel1.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel1.add(lbl1);

        panel2.add(lbl2);

        panel3.add(lbl3);

        panel4.add(lbl4);


        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        mainPanel.setLayout(gbl);

        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 50;
        gbc.ipady = 10;
        mainPanel.add(panel1, gbc);

        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.ipadx = 50;
        gbc.ipady = 10;
        mainPanel.add(panel2, gbc);

        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.ipadx = 50;
        gbc.ipady = 10;
        mainPanel.add(panel3, gbc);

        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.ipadx = 50;
        gbc.ipady = 10;
        mainPanel.add(panel4, gbc);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5,5,5,5);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipadx = 50;
        gbc.ipady = 20;
        mainPanel.add(panel5, gbc);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5,5,5,5);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.ipady = 20;
        gbc.gridwidth = 3;
        mainPanel.add(panel6, gbc);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5,5,5,5);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.ipady = 50;
        gbc.gridwidth =2;
        mainPanel.add(panel7, gbc);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5,5,5,5);
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.ipady = 50;
        gbc.gridwidth = 2;
        mainPanel.add(panel8, gbc);


        add(mainPanel);
    }


    class RoundedPanel extends JPanel {
        private int arcWidth;
        private int arcHeight;
        private Color backgroundColor;

        public RoundedPanel(int arcWidth, int arcHeight, Color backgroundColor) {
            this.arcWidth = arcWidth;
            this.arcHeight = arcHeight;
            this.backgroundColor = backgroundColor;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(backgroundColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);
        }
    }
}





