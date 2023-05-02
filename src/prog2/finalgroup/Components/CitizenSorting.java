package prog2.finalgroup.Components;

import prog2.finalgroup.Citizen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CitizenSorting extends JPanel {
    private final ArrayList<Citizen> citizens;
    private final JTable table;
    private final JComboBox<String> sortMenu;
    private int startIndex;

    public CitizenSorting(ArrayList<Citizen> citizens) {
        super(new BorderLayout());

        //your JPanel size
        setSize(800, 350);
        setVisible(true);
        this.citizens = citizens;
        this.startIndex = 0;

        // Create top panel with sort label and sort menu
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel sortLabel = new JLabel("Sort by:");
        topPanel.add(sortLabel);
        sortMenu = new JComboBox<>(new String[]{"Name", "Email", "Address", "Age", "Residency", "District", "Gender"});
        sortMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startIndex = 0;
                refreshTable();
            }
        });
        topPanel.add(sortMenu);
        add(topPanel, BorderLayout.NORTH);

        // Create table with 7 columns
        table = new JTable(new DefaultTableModel(new Object[]{"Name", "Email", "Address", "Age", "Residency", "District", "Gender"}, 0));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Create bottom panel with previous and next buttons
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton prevButton = new JButton("Previous");

        prevButton.addActionListener(e -> {
            startIndex = Math.max(startIndex - 10, 0);
            refreshTable();
        });


        bottomPanel.add(prevButton);
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startIndex += 10;
                refreshTable();
            }
        });
        bottomPanel.add(nextButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // Initialize table with default sort
        refreshTable();
    }

    private void refreshTable() {
        List<Citizen> filteredCitizens = citizens.stream()
                .sorted(getComparator())
                .collect(Collectors.toList());
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (int i = startIndex; i < Math.min(startIndex + 10, filteredCitizens.size()); i++) {
            Citizen citizen = filteredCitizens.get(i);

            String resident;
            if (citizen.isResident()){
                resident = "Resident";
            }else {
                resident = "Non-Resident";
            }
            model.addRow(new Object[]{citizen.getFullName(), citizen.getEmail(), citizen.getAddress(), citizen.getAge(), resident, citizen.getDistrict(), citizen.getGender()});
        }
    }

    private Comparator<Citizen> getComparator() {
        String sortField = (String) sortMenu.getSelectedItem();
        Comparator<Citizen> comparator = Comparator.comparing(Citizen::getFullName);
        switch (sortField) {
            case "Name":
                comparator = Comparator.comparing(Citizen::getFullName);
                break;
            case "Email":
                comparator = Comparator.comparing(Citizen::getEmail);
                break;
            case "Address":
                comparator = Comparator.comparing(Citizen::getAddress);
                break;
            case "Age":
                comparator = Comparator.comparing(Citizen::getAge);
                break;
            case "Residency":
                comparator = Comparator.comparing(Citizen::isResident);
                break;
            case "District":
                comparator = Comparator.comparing(Citizen::getDistrict);
                break;
            case "Gender":
                comparator = Comparator.comparing(Citizen::getGender);
                break;
        }
        return comparator;
    }
}
