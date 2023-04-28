/**
 * may error na etoh diko alm ayusin hehe
 * Exception in thread "main" java.lang.NumberFormatException: For input string: "Resident"
 * */


package prog2.finalgroup;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MyProgramUtility {
    public static void main(String[] args) {
        MyProgramUtility program = new MyProgramUtility();
        program.run();
    }

    public void run() {
        ArrayList<Citizen> citizenArrayList = readDataFromCSV();

        citizenArrayList.stream().forEach(System.out::println);
    }

    public ArrayList<Citizen> readDataFromCSV(){
        ArrayList<Citizen> arrayList = new ArrayList<>(); //instantiation of the ArrayList<Citizen>

        //change the src to res
        try (BufferedReader reader = new BufferedReader(new FileReader("src/data.csv"))){ //try-with-resource

            String line;
            while ((line = reader.readLine()) != null) { //returns a boolean value
                if (!line.contains("\"")){
                    String[] citizenDetails = line.split(",");    // use comma as separator

                    String fullName = citizenDetails[0] + " " + citizenDetails[1];
                    String email = citizenDetails[2];
                    String address = citizenDetails[3];
                    int age = Integer.parseInt(citizenDetails[4]);
                    boolean resident = citizenDetails[5].equals("Resident");
                    int district = Integer.parseInt(citizenDetails[6]);
                    char gender = Character.toUpperCase(citizenDetails[7].charAt(0));

                    //object instantiation
                    Citizen citizen = new Citizen(fullName,email,address,age,resident,district,gender);
                    arrayList.add(citizen);

                }else {
                    String[] citizenDetails = new String[8]; //final template for the citizenDetails

                    String[] myArr; //temporary array that will hold the substring of the text na erase na yung "P.O. Box 621, 5231 Eros Av."

                    //Given String Ramona,Bowman,dui.Cum@adipiscingnonluctus.co.uk,"P.O. Box 621, 5231 Eros Av.",62,Non-Resident,5,Female
                    String temporaryDescriptiveTitle = line.substring((line.indexOf('\"')+1),line.lastIndexOf('\"'));
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
                    citizenDetails[0]=myArr[0];
                    citizenDetails[1]=myArr[1];
                    citizenDetails[2]=myArr[2];
                    citizenDetails[3]=temporaryDescriptiveTitle;
                    citizenDetails[4]=myArr[3];
                    citizenDetails[5]=myArr[4];
                    citizenDetails[6]=myArr[5];
                    citizenDetails[7]=myArr[6];

                    String fullName = citizenDetails[0] + " " + citizenDetails[1];
                    String email = citizenDetails[2];
                    String address = citizenDetails[3];
                    int age = Integer.parseInt(citizenDetails[4]);
                    boolean resident = citizenDetails[5] == "Resident";
                    int district = Integer.parseInt(citizenDetails[6]);
                    char gender = Character.toUpperCase(citizenDetails[7].charAt(0));

                    //object instantiation
                    Citizen citizen = new Citizen(fullName,email,address,age,resident,district,gender);
                    arrayList.add(citizen);
                }
            }
        }catch (FileNotFoundException fnf){
            System.out.println("File is not found");
        } catch (IOException e) {
            System.out.println("Input output exception");
        }
        return arrayList;
    }
}



