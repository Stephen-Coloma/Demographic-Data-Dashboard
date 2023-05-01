package prog2.finalgroup.Components;

import prog2.finalgroup.Citizen;
import prog2.finalgroup.MyProgramUtility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class CitizenDetails {
    public CitizenDetails(MouseEvent e, JList<String> resultList){
        MyProgramUtility myProgramUtility = new MyProgramUtility();
        ArrayList<Citizen> citizenArrayList = myProgramUtility.readDataFromCSV();
        if (e.getClickCount() == 2) {
            String selectedString = resultList.getSelectedValue();

            Citizen selectedCitizen = citizenArrayList.stream()
                    .filter(citizen -> citizen.getFullName().equalsIgnoreCase(selectedString))
                    .findFirst()
                    .orElse(null); // If no matching citizen is found

            JFrame detailsFrame = new JFrame(selectedCitizen.getFullName());
            JPanel detailsPanel = new JPanel(new GridLayout(7, 2));
            detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            detailsFrame.getContentPane().add(detailsPanel);

            //--------------------------------------------------------------------------
            JLabel firstName = new JLabel("Full Name: ");
            JLabel firstName1 = new JLabel(selectedCitizen.getFullName());

            firstName.setFont(new Font("Century Gothic", Font.BOLD, 15));
            firstName1.setFont(new Font("Century Gothic", Font.BOLD, 15));


            detailsPanel.add(firstName);
            detailsPanel.add(firstName1);
            //--------------------------------------------------------------------------
            JLabel address = new JLabel("Address: ");
            JLabel address1 = new JLabel(selectedCitizen.getAddress());

            address.setFont(new Font("Century Gothic", Font.BOLD, 15));
            address1.setFont(new Font("Century Gothic", Font.BOLD, 15));


            detailsPanel.add(address);
            detailsPanel.add(address1);
            //--------------------------------------------------------------------------
            JLabel email = new JLabel("Email: ");
            JLabel email1 = new JLabel(selectedCitizen.getEmail());

            email.setFont(new Font("Century Gothic", Font.BOLD, 15));
            email1.setFont(new Font("Century Gothic", Font.BOLD, 15));


            detailsPanel.add(email);
            detailsPanel.add(email1);
            //--------------------------------------------------------------------------
            JLabel age = new JLabel("Age: ");
            JLabel age1 = new JLabel(String.valueOf(selectedCitizen.getAge()));

            age.setFont(new Font("Century Gothic", Font.BOLD, 15));
            age1.setFont(new Font("Century Gothic", Font.BOLD, 15));


            detailsPanel.add(age);
            detailsPanel.add(age1);
            //--------------------------------------------------------------------------
            JLabel district = new JLabel("District: ");
            JLabel district1 = new JLabel(String.valueOf(selectedCitizen.getDistrict()));

            district.setFont(new Font("Century Gothic", Font.BOLD, 15));
            district1.setFont(new Font("Century Gothic", Font.BOLD, 15));

            detailsPanel.add(district);
            detailsPanel.add(district1);
            //--------------------------------------------------------------------------
            JLabel resident = new JLabel("Residency: ");

            String residency = "";
            if (selectedCitizen.isResident()){
                residency = "Resident";
            }else
                residency = "Non-Resident";
            JLabel resident1 = new JLabel(residency);

            resident.setFont(new Font("Century Gothic", Font.BOLD, 15));
            resident1.setFont(new Font("Century Gothic", Font.BOLD, 15));

            detailsPanel.add(resident);
            detailsPanel.add(resident1);
            //--------------------------------------------------------------------------
            String myGender = "";
            if (selectedCitizen.getGender()=='M'){
                myGender = "Male";
            }else {
                myGender = "Female";
            }

            JLabel gender = new JLabel("Gender: ");
            JLabel gender1 = new JLabel(myGender);

            gender.setFont(new Font("Century Gothic", Font.BOLD, 15));
            gender1.setFont(new Font("Century Gothic", Font.BOLD, 15));

            detailsPanel.add(gender);
            detailsPanel.add(gender1);
            //--------------------------------------------------------------------------


            detailsFrame.add(detailsPanel);
            //THIS IS IN SHOWING THE DETAILS OF THE CITIZEN IN THE LIST
            detailsFrame.setVisible(true);
            detailsFrame.setSize(500,300);
            detailsFrame.setLocationRelativeTo(null);

        }

    }
}
