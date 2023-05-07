package prog2.finalgroup.Components;

import prog2.finalgroup.Citizen;
import prog2.finalgroup.MyProgramUtility;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class AddCitizen extends JPanel {
    private JLabel titleLabel, firstNameLabel, lastNameLabel, emailLabel, addressLabel, ageLabel, residencyLabel, districtLabel, genderLabel;
    private JTextField firstNameTextField, lastNameTextField, emailTextField, addressTextField, ageTextField, districtTextField;
    private JComboBox<String> residencyComboBox, genderComboBox;
    private JButton addButton;

    public AddCitizen() {
        setSize(800,370);
        setLayout(new BorderLayout());
        setVisible(true);

        // Create top panel with title label
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titleLabel = new JLabel("Registration Form: ");
        titleLabel.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 19));
        topPanel.add(titleLabel);
        add(topPanel, BorderLayout.NORTH);


        // Create registration panel with input fields
        JPanel registrationPanel = new JPanel();
        registrationPanel.setLayout(null);
        registrationPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        registrationPanel.setVisible(true);

        RoundedPanel holder = new RoundedPanel(20, 20, new Color(99, 126, 255), new GridLayout(8, 2, 10, 5));
        holder.setVisible(true);

        //for the font
        Font font = new Font("Century Gothic", Font.PLAIN , 12);

        firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setFont(new Font("Century Gothic", Font.ITALIC | Font.BOLD, 13));
        firstNameLabel.setForeground(Color.WHITE);
        firstNameTextField = new JTextField();
        firstNameTextField.setFont(font);
        lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setFont(new Font("Century Gothic", Font.ITALIC | Font.BOLD, 13));
        lastNameLabel.setForeground(Color.WHITE);
        lastNameTextField = new JTextField();
        lastNameTextField.setFont(font);        emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Century Gothic", Font.ITALIC | Font.BOLD, 13));
        emailLabel.setForeground(Color.WHITE);
        emailTextField = new JTextField();
        emailTextField.setFont(font);
        addressLabel = new JLabel("Address:");
        addressLabel.setFont(new Font("Century Gothic", Font.ITALIC | Font.BOLD, 13));
        addressLabel.setForeground(Color.WHITE);
        addressTextField = new JTextField();
        addressTextField.setFont(font);
        ageLabel = new JLabel("Age:");
        ageLabel.setFont(new Font("Century Gothic", Font.ITALIC | Font.BOLD, 13));
        ageLabel.setForeground(Color.WHITE);
        ageTextField = new JTextField();
        ageTextField.setFont(font);
        residencyLabel = new JLabel("Residency:");
        residencyLabel.setFont(new Font("Century Gothic", Font.ITALIC | Font.BOLD, 13));
        residencyLabel.setForeground(Color.WHITE);
        String[] residencyOptions = {"Resident", "Non-Resident"};
        residencyComboBox = new JComboBox<>(residencyOptions);
        districtLabel = new JLabel("District:");
        districtLabel.setFont(new Font("Century Gothic", Font.ITALIC | Font.BOLD, 13));
        districtLabel.setForeground(Color.WHITE);
        districtTextField = new JTextField();
        districtTextField.setFont(font);
        genderLabel = new JLabel("Gender:");
        genderLabel.setFont(new Font("Century Gothic", Font.ITALIC | Font.BOLD, 13));
        genderLabel.setForeground(Color.WHITE);
        genderLabel.setForeground(Color.WHITE);
        String[] genderOptions = {"Male", "Female"};
        genderComboBox = new JComboBox<>(genderOptions);
        holder.add(firstNameLabel);
        holder.add(firstNameTextField);
        holder.add(lastNameLabel);
        holder.add(lastNameTextField);
        holder.add(emailLabel);
        holder.add(emailTextField);
        holder.add(addressLabel);
        holder.add(addressTextField);
        holder.add(ageLabel);
        holder.add(ageTextField);
        holder.add(districtLabel);
        holder.add(districtTextField);
        holder.add(residencyLabel);
        holder.add(residencyComboBox);
        holder.add(genderLabel);
        holder.add(genderComboBox);


        holder.setBounds(150, 10, 500, 250);
        holder.setBackground(Color.CYAN);
        holder.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        registrationPanel.add(holder);

        add(registrationPanel, BorderLayout.CENTER);

        // Create button panel with add button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        addButton = new JButton("Add Citizen");
        addButton.setBackground(new Color(0x5A78FF));
        addButton.setPreferredSize(new Dimension(130, 40));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusable(false);

        //button text font
        Font font1 = new Font("Century Gothic", Font.PLAIN | Font.BOLD, 15);
        addButton.setFont(font1);

        addButton.addActionListener(e->{
            // Get the values from the text fields
            String firstName = firstNameTextField.getText();
            String lastName = lastNameTextField.getText();
            String email = emailTextField.getText();
            String address = addressTextField.getText();
            String ageStr = ageTextField.getText();
            String districtStr = districtTextField.getText();

            // Validate required fields
            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || ageStr.isEmpty() || districtStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all required fields");
                return;
            }

            // Parse integer fields
            int age = 0;
            int district = 0;
            try {
                age = Integer.parseInt(ageStr);
                district = Integer.parseInt(districtStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Age and district must be a number");
                return;
            }

            // Get values from combo boxes
            String genderStr = (String) genderComboBox.getSelectedItem();
            String residencyStr = (String) residencyComboBox.getSelectedItem();

            // Validate optional fields
            if (genderStr == null || residencyStr == null) {
                JOptionPane.showMessageDialog(this, "Please select a value for all fields");
                return;
            }

            // Create a new Citizen object with the values
            String fullName =  lastName + " " + firstName;
            char gender = genderStr.equals("Male") ? 'M' : 'F';
            boolean residency = residencyStr.equals("Resident");
            Citizen newCitizen = new Citizen(fullName, email, address, age, residency, district, gender);

            // Write the new Citizen to the file
            MyProgramUtility myProgramUtility = new MyProgramUtility();
            ArrayList<Citizen> addCitizen = myProgramUtility.readDataFromCSV();
            try (FileWriter writer = new FileWriter("res/data.csv")) {
                addCitizen.add(newCitizen);
                for (int i = 0; i < addCitizen.size(); i++){
                    writer.write(addCitizen.get(i).toString());
                    writer.write("\n");
                }

                // Display a message to the user that the new citizen was added
                JOptionPane.showMessageDialog(this, "New citizen added:  " + newCitizen.getFullName());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            firstNameTextField.setText("");
            lastNameTextField.setText("");
            ageTextField.setText("");
            addressTextField.setText("");
            emailTextField.setText("");
            districtTextField.setText("");
        });
        buttonPanel.add(addButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}




