package prog2.finalgroup.Components;

import prog2.finalgroup.Citizen;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class AddCitizenGui extends JFrame implements ActionListener {
    private JTextField firstNameField,lastNameField,emailField, addressField, districtField, genderField,residencyField, ageField;
    private JLabel firstNameLabel,lastNameLabel, emailLabel, addressLabel, districtLabel, genderLabel, residencyLabel, ageLabel;
    private JButton addButton;

    public AddCitizenGui() {
        // Set up the JFrame
        setTitle("Add New Citizen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);

        // Set up the JPanel and layout
        JPanel panel = new JPanel(new GridLayout(9, 2));

        // Set up the text fields and labels
//        fullNameLabel = new JLabel("Full Name:");
//        fullNameField = new JTextField(20);
        firstNameLabel= new JLabel("FirstName:");
        firstNameField = new JTextField(20);
        lastNameLabel = new JLabel("LastName:");
        lastNameField = new JTextField(20);
        emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);
        addressLabel = new JLabel("Address:");
        addressField = new JTextField(20);
        districtLabel = new JLabel("District:");
        districtField = new JTextField(20);
        genderLabel = new JLabel("Gender:");
        genderField = new JTextField(20);
        residencyLabel = new JLabel("Residency Status:");
        residencyField = new JTextField(20);
        ageLabel = new JLabel("Age:");
        ageField = new JTextField(20);

        // Add the labels and text fields to the panel
//        panel.add(fullNameLabel);
//        panel.add(fullNameField);
        panel.add(firstNameLabel);
        panel.add(firstNameField);
        panel.add(lastNameLabel);
        panel.add(lastNameField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(addressLabel);
        panel.add(addressField);
        panel.add(districtLabel);
        panel.add(districtField);
        panel.add(genderLabel);
        panel.add(genderField);
        panel.add(residencyLabel);
        panel.add(residencyField);
        panel.add(ageLabel);
        panel.add(ageField);

        // Set up the add button and add it to the panel
        addButton = new JButton("Add");
        addButton.addActionListener(this);
        panel.add(addButton);

        // Add the panel to the JFrame
        add(panel);

        // Make the JFrame visible
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        ArrayList<Citizen> addCitizen = readDataFromCSV();
        try (FileWriter writer = new FileWriter("res/data.csv")) {
            if (e.getSource() == addButton) {
                // Get the values from the text fields
                String fullName =  lastNameField.getText() + " " + firstNameField.getText();
                String email = emailField.getText();
                String address = addressField.getText();
                int district = Integer.parseInt(districtField.getText());
                char gender = genderField.getText().toLowerCase().charAt(0);
                boolean residency = residencyField.getText().toLowerCase().equals("resident");
                int age = Integer.parseInt(ageField.getText());

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
    }





    public ArrayList<Citizen> readDataFromCSV() {
        ArrayList<Citizen> arrayList = new ArrayList<>(); //instantiation of the ArrayList<Citizen>

        //change the src to res
        try (BufferedReader reader = new BufferedReader(new FileReader("res/data.csv"))) { //try-with-resource

            String line;
            while ((line = reader.readLine()) != null) { //returns a boolean value
                if (!line.contains("\"")) {
                    String[] citizenDetails = line.split(",");    // use comma as separator

                    String fullName = citizenDetails[0] + " " + citizenDetails[1];
                    String email = citizenDetails[2];
                    String address = citizenDetails[3];
                    int age = Integer.parseInt(citizenDetails[4]);
                    boolean resident = citizenDetails[5].equals("Resident");
                    int district = Integer.parseInt(citizenDetails[6]);
                    char gender = Character.toUpperCase(citizenDetails[7].charAt(0));

                    //object instantiation
                    Citizen citizen = new Citizen(fullName, email, address, age, resident, district, gender);
                    arrayList.add(citizen);

                } else {
                    String[] citizenDetails = new String[8]; //final template for the citizenDetails

                    String[] myArr; //temporary array that will hold the substring of the text na erase na yung "P.O. Box 621, 5231 Eros Av."

                    //Given String Ramona,Bowman,dui.Cum@adipiscingnonluctus.co.uk,"P.O. Box 621, 5231 Eros Av.",62,Non-Resident,5,Female
                    String temporaryDescriptiveTitle = line.substring((line.indexOf('\"') + 1), line.lastIndexOf('\"'));
                    //kukunin ko as substring yung occurence ng "P.O. Box 621, 5231 Eros Av." sa line
                    //result nito: temporaryDescriptiveTitle = P.O. Box 621, 5231 Eros Av.


                    //Ramona,Bowman,dui.Cum@adipiscingnonluctus.co.uk,"P.O. Box 621, 5231 Eros Av.",62,Non-Resident,5,Female
                    String descriptiveTitleToBeRemoved = "\"" + temporaryDescriptiveTitle + "\",";
                    //this line naman, inconcat ko yung temporaryDescriptiveTitle variable sa " at ",
                    //result nito: descriptiveTitleToBeRemoved = "P.O. Box 621, 5231 Eros Av.",


                    String template = line.replaceAll(descriptiveTitleToBeRemoved, "");
                    //this code replaces the occurrence of the  descriptiveTitleToBeRemoved variable sa line
                    //result neto: template = "Ramona,Bowman,dui.Cum@adipiscingnonluctus.co.uk,62,Non-Resident,5,Female"
                    myArr = template.split(",");
                    //eto na yung kukuha sa array na ma s split as substring base sa template variable

                    //populate the citizenDetails Array using the items inside the myArr array and the temporaryDescriptiveTitle variable
                    citizenDetails[0] = myArr[0];
                    citizenDetails[1] = myArr[1];
                    citizenDetails[2] = myArr[2];
                    citizenDetails[3] = temporaryDescriptiveTitle;
                    citizenDetails[4] = myArr[3];
                    citizenDetails[5] = myArr[4];
                    citizenDetails[6] = myArr[5];
                    citizenDetails[7] = myArr[6];

                    String fullName = citizenDetails[0] + " " + citizenDetails[1];
                    String email = citizenDetails[2];
                    String address = citizenDetails[3];
                    int age = Integer.parseInt(citizenDetails[4]);
                    boolean resident = citizenDetails[5] == "Resident";
                    int district = Integer.parseInt(citizenDetails[6]);
                    char gender = Character.toUpperCase(citizenDetails[7].charAt(0));

                    //object instantiation
                    Citizen citizen = new Citizen(fullName, email, address, age, resident, district, gender);
                    arrayList.add(citizen);
                }
            }//end of while

        } catch (FileNotFoundException fnf) {
            System.out.println("File is not found");
        } catch (IOException e) {
            System.out.println("Input output exception");
        }
        return arrayList;
    }// end of readDataFromCSV()

    public static void main(String[] args) {
        // Create a new AddCitizen object
        AddCitizenGui addCitizen = new AddCitizenGui();
    }
}




