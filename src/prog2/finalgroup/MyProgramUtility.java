package prog2.finalgroup;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MyProgramUtility {
    //constructors
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
                    boolean resident = citizenDetails[5].equals("Resident");
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

    /**
     * This method counts the total number of Citizens.
     */
    public int totalPopulation() {
        ArrayList<Citizen> citizens = readDataFromCSV();
        int totalPopulation = (int) citizens.stream().count();
        return totalPopulation;
    }

    /**
     * This method counts the total number of Citizens who is a Resident.
     */
    public int countResidents() {
        ArrayList<Citizen> citizens = readDataFromCSV();
        int count = (int) citizens.stream().filter(x -> x.isResident() == true).count();
        return count;
    }

    /**
     * This method counts the total number of Citizens who is a Non-Resident.
     */
    public int countNonResidents() {
        ArrayList<Citizen> citizens = readDataFromCSV();
        int count = (int) citizens.stream().filter(x -> x.isResident() == false).count();
        return count;
    }

    /**
     * This method counts the total number of Citizens who has an age of 1-12.
     *
     * @return count;
     */
    public int childrenAge(ArrayList<Citizen> citizens) {
        int count = 0;
        for (Citizen citizen : citizens) {
            if (citizen.getAge() >= 1 && citizen.getAge() <= 12) {
                count++;
            }
        }
        return count;
    }

    /**
     * This method counts the total number of Citizens who has an age of 13-18.
     *
     * @return count;
     */
    public int teenageCount() {
        ArrayList<Citizen> citizens = readDataFromCSV();
        int count = (int) citizens.stream().filter(x -> x.getAge()<=18 && x.getAge()>=13).count();
        return count;
    }

    /**
     * This method counts the total number of Citizens who has an age of 19-Above.
     *
     * @return count
     */
    public int adultCount() {
        ArrayList<Citizen> citizens = readDataFromCSV();
        int count = (int) citizens.stream().filter(x -> x.getAge()>=19).count();
        return count;
    }

    /**
     * This method counts the total number of Citizens who identifies as a MALE.
     *
     * @return count
     */
    public int countMales(ArrayList<Citizen> citizens) {
        int count = 0;
        for (Citizen citizen : citizens) {
            if (citizen.getGender() == 'M') {
                count++;
            }
        }
        return count;
    }

    /**
     * This method counts the total number of Citizens who identifies as a FEMALE.
     *
     * @return count
     */
    public int countFemales(ArrayList<Citizen> citizens) {
        int count = 0;
        for (Citizen citizen : citizens) {
            if (citizen.getGender() == 'F') {
                count++;
            }
        }
        return count;
    }
}//end of class




