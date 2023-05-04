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
        setSize(800,350);
        setLayout(new BorderLayout());
        setVisible(true);

        // Create top panel with title label
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.WHITE);
        titleLabel = new JLabel("Registration Form: ");
        topPanel.add(titleLabel);
        add(topPanel, BorderLayout.NORTH);

        // Create registration panel with input fields
        JPanel registrationPanel = new JPanel();
        registrationPanel.setLayout(new GridLayout(8, 2, 10, 10));
        registrationPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        firstNameLabel = new JLabel("First Name:");
        firstNameTextField = new JTextField();
        lastNameLabel = new JLabel("Last Name:");
        lastNameTextField = new JTextField();
        emailLabel = new JLabel("Email:");
        emailTextField = new JTextField();
        addressLabel = new JLabel("Address:");
        addressTextField = new JTextField();
        ageLabel = new JLabel("Age:");
        ageTextField = new JTextField();
        residencyLabel = new JLabel("Residency:");
        String[] residencyOptions = {"Resident", "Non-Resident"};
        residencyComboBox = new JComboBox<>(residencyOptions);
        districtLabel = new JLabel("District:");
        districtTextField = new JTextField();
        genderLabel = new JLabel("Gender:");
        String[] genderOptions = {"Male", "Female"};
        genderComboBox = new JComboBox<>(genderOptions);
        registrationPanel.add(firstNameLabel);
        registrationPanel.add(firstNameTextField);
        registrationPanel.add(lastNameLabel);
        registrationPanel.add(lastNameTextField);
        registrationPanel.add(emailLabel);
        registrationPanel.add(emailTextField);
        registrationPanel.add(addressLabel);
        registrationPanel.add(addressTextField);
        registrationPanel.add(ageLabel);
        registrationPanel.add(ageTextField);
        registrationPanel.add(residencyLabel);
        registrationPanel.add(residencyComboBox);
        registrationPanel.add(districtLabel);
        registrationPanel.add(districtTextField);
        registrationPanel.add(genderLabel);
        registrationPanel.add(genderComboBox);
        add(registrationPanel, BorderLayout.CENTER);

        // Create button panel with add button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        addButton = new JButton("Add");
        addButton.addActionListener(e->{
            MyProgramUtility myProgramUtility = new MyProgramUtility();
            ArrayList<Citizen> addCitizen = myProgramUtility.readDataFromCSV();
            try (FileWriter writer = new FileWriter("res/data.csv")) {
                if (e.getSource() == addButton) {
                    // Get the values from the text fields
                    String fullName =  lastNameTextField.getText() + " " + firstNameTextField.getText();
                    String email = emailTextField.getText();
                    String address = addressTextField.getText();
                    int district = Integer.parseInt(districtTextField.getText());

                    char gender;
                    int genderComboBoxSelectedIndex = genderComboBox.getSelectedIndex();
                    if (genderComboBoxSelectedIndex == 0){
                        gender = 'M';
                    }else
                        gender = 'F';

                    boolean residency;
                    int residencyComboBoxSelectedIndex = residencyComboBox.getSelectedIndex();
                    if (residencyComboBoxSelectedIndex == 0){
                        residency = true;
                    }else
                        residency=false;



                    int age = Integer.parseInt(ageTextField.getText());

                    // Create a new Citizen object with the values
                    Citizen newCitizen = new Citizen(fullName, email, address, age, residency, district, gender);
                    addCitizen.add(newCitizen);

                    for (int i = 0; i<addCitizen.size(); i++){
                        writer.write(addCitizen.get(i).toString());
                        writer.write("\n");
                    }

                    // Display a message to the user that the new citizen was added
                    JOptionPane.showMessageDialog(this, "New citizen added: " + newCitizen);

                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        buttonPanel.add(addButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}




