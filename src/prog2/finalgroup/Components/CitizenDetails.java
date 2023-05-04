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
            JPanel detailsPanel = new JPanel(new FlowLayout());//set to none

            JPanel left = new JPanel(new GridLayout(7, 1,0,10));
            JPanel right = new JPanel(new GridLayout(7, 1,0,10));

            detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//            detailsFrame.getContentPane().add(detailsPanel);


            //--------------------------------------------------------------------------
            JLabel firstName = new JLabel("Full Name: ");
            JLabel firstName1 = new JLabel(selectedCitizen.getFullName());

            firstName.setFont(new Font("Century Gothic", Font.BOLD, 15));
            firstName1.setFont(new Font("Century Gothic", Font.PLAIN, 15));


            left.add(firstName);
            right.add(firstName1);
            //--------------------------------------------------------------------------
            JLabel address = new JLabel("Address: ");
            JLabel address1 = new JLabel(selectedCitizen.getAddress());

            address.setFont(new Font("Century Gothic", Font.BOLD, 15));
            address1.setFont(new Font("Century Gothic", Font.PLAIN, 15));


            left.add(address);
            right.add(address1);
            //--------------------------------------------------------------------------
            JLabel email = new JLabel("Email: ");
            JLabel email1 = new JLabel(selectedCitizen.getEmail());

            email.setFont(new Font("Century Gothic", Font.BOLD, 15));
            email1.setFont(new Font("Century Gothic", Font.PLAIN, 15));


            left.add(email);
            right.add(email1);
            //--------------------------------------------------------------------------
            JLabel age = new JLabel("Age: ");
            JLabel age1 = new JLabel(String.valueOf(selectedCitizen.getAge()));

            age.setFont(new Font("Century Gothic", Font.BOLD, 15));
            age1.setFont(new Font("Century Gothic", Font.PLAIN, 15));


            left.add(age);
            right.add(age1);
            //--------------------------------------------------------------------------
            JLabel district = new JLabel("District: ");
            JLabel district1 = new JLabel(String.valueOf(selectedCitizen.getDistrict()));

            district.setFont(new Font("Century Gothic", Font.BOLD, 15));
            district1.setFont(new Font("Century Gothic", Font.PLAIN, 15));

            left.add(district);
            right.add(district1);
            //--------------------------------------------------------------------------
            JLabel resident = new JLabel("Residency: ");

            String residency = "";
            if (selectedCitizen.isResident()){
                residency = "Resident";
            }else
                residency = "Non-Resident";
            JLabel resident1 = new JLabel(residency);

            resident.setFont(new Font("Century Gothic", Font.BOLD, 15));
            resident1.setFont(new Font("Century Gothic", Font.PLAIN, 15));

            left.add(resident);
            right.add(resident1);
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
            gender1.setFont(new Font("Century Gothic", Font.PLAIN, 15));

            left.add(gender);
            right.add(gender1);
            //--------------------------------------------------------------------------


            detailsPanel.add(left);
            detailsPanel.add(right);




            detailsFrame.add(detailsPanel);
            //THIS IS IN SHOWING THE DETAILS OF THE CITIZEN IN THE LIST
            // Create a new empty border with 10 pixels of padding on the left side

            detailsFrame.setVisible(true);
            detailsFrame.pack();
            detailsFrame.setLocationRelativeTo(null);
            detailsFrame.setResizable(false);
        }

    }
}
