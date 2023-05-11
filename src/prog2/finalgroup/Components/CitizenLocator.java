package prog2.finalgroup.Components;

import prog2.finalgroup.Citizen;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CitizenLocator extends JPanel{
    private JPanel topPanel;
    private JComboBox<String> ageComboBox;
    private JComboBox<String> districtComboBox;
    private JComboBox<String> residencyComboBox;
    private JComboBox<String> genderComboBox;
    private JTable citizenTable;
    private DefaultTableModel tableModel;

    private List<Citizen> citizens; // Assume you have an ArrayList named "citizens" with Citizen objects

    public CitizenLocator(ArrayList<Citizen> citizens) {
        this.citizens=citizens;

        setSize(new Dimension(800, 370));
        setVisible(true);

        topPanel = new JPanel(new GridLayout(4, 4,15,5));
        topPanel.setBackground(new Color(0,0,0,0));

        JLabel filterLabel = new JLabel("Filter by:");
        filterLabel.setFont(new Font("Century Gothic", Font.ITALIC | Font.BOLD, 15));
        topPanel.add(filterLabel);
        topPanel.add(new JLabel()); //dummy
        topPanel.add(new JLabel()); //dummy
        topPanel.add(new JLabel()); //dummy

        ageComboBox = new JComboBox<>(new String[]{"11-20", "21-30", "31-40", "41-50", "51-60", "Above 60"});
        ageComboBox.setFont(new Font("Century Gothic", Font.BOLD, 12));
        ageComboBox.setBackground(Color.decode("#5A78FF"));
        ageComboBox.setForeground(Color.WHITE);
        ageComboBox.setFocusable(false);
        topPanel.add(ageComboBox);

        districtComboBox = new JComboBox<>(new String[]{"District 1-5", "District 6-10", "District 11-15", "District 16-20"});
        districtComboBox.setFont(new Font("Century Gothic", Font.BOLD, 12));
        districtComboBox.setBackground(Color.decode("#5A78FF"));
        districtComboBox.setForeground(Color.WHITE);
        districtComboBox.setFocusable(false);
        topPanel.add(districtComboBox);

        residencyComboBox = new JComboBox<>(new String[]{"Resident", "Non-Resident"});
        residencyComboBox.setFont(new Font("Century Gothic", Font.BOLD, 12));
        residencyComboBox.setBackground(Color.decode("#5A78FF"));
        residencyComboBox.setForeground(Color.WHITE);
        residencyComboBox.setFocusable(false);
        topPanel.add(residencyComboBox);

        genderComboBox = new JComboBox<>(new String[]{"Male", "Female"});
        genderComboBox.setFont(new Font("Century Gothic", Font.BOLD, 12));
        genderComboBox.setBackground(Color.decode("#5A78FF"));
        genderComboBox.setForeground(Color.WHITE);
        genderComboBox.setFocusable(false);
        topPanel.add(genderComboBox);


        topPanel.add(new JLabel()); //dummy
        topPanel.add(new JLabel()); //dummy
        topPanel.add(new JLabel()); //dummy
        topPanel.add(new JLabel()); //dummy

        JLabel possibleCitizensLabel = new JLabel("Possible filtered results:           ");
        possibleCitizensLabel.setFont(new Font("Century Gothic", Font.ITALIC , 13));

        topPanel.add(possibleCitizensLabel);
        topPanel.add(new JLabel()); //dummy
        topPanel.add(new JLabel()); //dummy
        topPanel.add(new JLabel()); //dummy

        // Creating the table
        String[] columnNames = {"Full Name", "Address"};
        tableModel = new DefaultTableModel(columnNames, 0);
        citizenTable = new JTable(tableModel);
        citizenTable.setPreferredScrollableViewportSize(new Dimension(650, 208)); // Set preferred size for the table
        citizenTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        citizenTable.setDefaultEditor(Object.class, null);
        citizenTable.setFont(new Font("Century Gothic", Font.PLAIN, 14));
        citizenTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = citizenTable.getSelectedRow();
                if (selectedRow >= 0) {
                    String address = tableModel.getValueAt(selectedRow, 1).toString();
                    CitizenDetails citizenDetails = new CitizenDetails(e, address);
                }
            }
        });

        // Adding the table to a scroll pane
        JScrollPane tableScrollPane = new JScrollPane(citizenTable);
        tableScrollPane.setSize(citizenTable.getWidth(), citizenTable.getHeight()); // Set preferred size for the scroll pane

        // Adding the panels to the main panel
        add(topPanel);
        add(tableScrollPane);

        // ActionListener for the combo boxes to update the table content
        ageComboBox.addActionListener(e -> updateTable());
        districtComboBox.addActionListener(e -> updateTable());
        residencyComboBox.addActionListener(e -> updateTable());
        genderComboBox.addActionListener(e -> updateTable());
    }

    private void updateTable() {
        // Clearing the table
        tableModel.setRowCount(0);

        // Filtering the citizens based on the selected options
        List<Citizen> filteredCitizens = citizens.stream()
                .filter(citizen -> {
                    String selectedAgeRange = (String) ageComboBox.getSelectedItem();

                    int selectedDistrict = 0;
                    if (districtComboBox.getSelectedItem() == "District 1-5"){
                        selectedDistrict = 5;
                    }else if (districtComboBox.getSelectedItem() == "District 6-10"){
                        selectedDistrict = 10;
                    }else if (districtComboBox.getSelectedItem() == "District 11-15"){
                        selectedDistrict = 15;
                    }else if (districtComboBox.getSelectedItem() == "District 16-20"){
                        selectedDistrict = 20;
                    }

                    boolean selectedResidency = residencyComboBox.getSelectedItem().equals("Resident");
                    String temp = (String) genderComboBox.getSelectedItem();

                    char selectedGender = temp.charAt(0);

                    boolean ageMatch = selectedAgeRange.equals("Above 60")
                            ? citizen.getAge() >= 61
                            : citizen.getAge() >= Integer.parseInt(selectedAgeRange.split("-")[0]) &&
                            citizen.getAge() <= Integer.parseInt(selectedAgeRange.split("-")[1]);

                    boolean districtMatch = citizen.getDistrict() <= selectedDistrict;
                    boolean residencyMatch = citizen.isResident() == selectedResidency;
                    boolean genderMatch = citizen.getGender() == (selectedGender);

                    return ageMatch && districtMatch && residencyMatch && genderMatch;
                })
                .collect(Collectors.toList());

        // Populating the table with the filtered citizens
        filteredCitizens.forEach(citizen -> {
            Object[] rowData = {citizen.getFullName(), citizen.getAddress()};
            tableModel.addRow(rowData);
        });
    }
}
