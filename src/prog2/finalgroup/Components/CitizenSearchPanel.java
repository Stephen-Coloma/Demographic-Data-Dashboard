package prog2.finalgroup.Components;

import prog2.finalgroup.Citizen;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.swing.*;

public class CitizenSearchPanel extends JPanel {

    private JTextField searchField;
    private JScrollPane resultPane;
    private JList<String> resultList;
    private DefaultListModel<String> resultModel;
    private ArrayList<Citizen> citizens;
    private ArrayList<Citizen> filteredCitizens;

    public CitizenSearchPanel(ArrayList<Citizen> citizens) {
        setBackground(Color.BLACK);
        setOpaque(false);
        this.citizens = citizens;
        setLayout(new BorderLayout());
//        setBackground(Color.BLUE); //SUBBJECT TO CHANGE

        // Perform filtering operation on the list of citizens
        filteredCitizens = new ArrayList<>(citizens.stream()
                .sorted((c1, c2) -> c1.getFullName().compareToIgnoreCase(c2.getFullName()))
                .collect(Collectors.toList()));

        searchField = new JTextField("Search People", 20) {
            private int arcWidth = 20;
            private int arcHeight = 20;

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setPaint(getBackground());
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), arcWidth, arcHeight));
                super.paintComponent(g2);
                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                // Remove the black borderline
                g.setColor(getForeground());
                g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcWidth, arcHeight);
            }
        };

        searchField.setBorder(BorderFactory.createCompoundBorder(
                searchField.getBorder(),
                BorderFactory.createEmptyBorder(5, 8, 5, 10)
        ));
        searchField.setFont(new Font("Century Gothic", Font.BOLD, 13));
        searchField.setBackground(new Color(236,240,241));
        searchField.setText("Search People");
        searchField.setForeground(new Color(35, 35, 35, 255));
        searchField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Search People")) {
                    searchField.setText("");
                    searchField.setForeground(new Color(0, 0, 0));
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Search People");
                    searchField.setForeground(new Color(35, 35, 35));
                }
            }
        });


        add(searchField, BorderLayout.NORTH);

        resultModel = new DefaultListModel<>();
        resultList = new JList<>(resultModel);
        resultList.setFont(new Font("Century Gothic", Font.BOLD, 13));
        resultPane = new JScrollPane(resultList);
        resultPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        resultPane.setVisible(true); // Initially hide the pane

        add(resultPane, BorderLayout.CENTER);

        searchField.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                // Not needed for this implementation
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // Not needed for this implementation
            }

            @Override
            public void keyReleased(KeyEvent e) {
                String searchTerm = searchField.getText().toLowerCase();
                resultPane.setVisible(false);

                if (searchTerm.isEmpty()) {
                    resultPane.setVisible(false);
                    resultModel.clear();
                    resultPane.setOpaque(false);

                } else {
                    ArrayList<String> matches = filteredCitizens.stream()
                            .filter(c -> c.getFullName().toLowerCase().contains(searchTerm))
                            .map(Citizen::getFullName)
                            .collect(Collectors.toCollection(ArrayList::new));


                    resultModel.clear();
                    matches.forEach(resultModel::addElement);
                    resultPane.setVisible(true);
                    resultPane.setBackground(Color.BLUE);
                    resultPane.setOpaque(true);
                    resultPane.setBackground(new Color(0,0,0,0));

                }
                resultPane.setOpaque(false);
            }

        });


        resultList.addMouseListener(new MouseAdapter()
        {

            @Override
            public void mouseClicked(MouseEvent e) {
                CitizenDetails citizenDetails = new CitizenDetails(e,resultList);
            }
        });

    }


}
