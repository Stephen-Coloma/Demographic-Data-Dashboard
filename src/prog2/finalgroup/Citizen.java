package prog2.finalgroup;

import java.util.ArrayList;

public class Citizen implements Comparable<Citizen> {
    /**
     * This is coded by JERWIN RAMOS at April 26, 2023
     */
    private String fullName;
    private String email;
    private String address;
    private int age;
    private boolean resident;
    private int district;
    char gender;


    /**
     *This is a Default Constructor of Citizen
    */
    public Citizen(){
        fullName="";
        email = "";
        address = "";
        age = 1;
        resident = true;
        district = 1;
        gender = 'M';
    }


    /**
     * This is a parameterized Constructor in creating Citizen Object
     * @param fullName this shows the name of the Citizen
     * @param email this shows the email of the Citizen
     * @param age this shows the age of the Citizen
     * @param resident this shows whether the Citizen is a resident or not
     * @param district this shows the district location of the Citizen
     * @param gender this show whether the Citizen is a Male or Female
     * */

    public Citizen(String fullName, String email, String address,int age,boolean resident,int district, char gender){
        this.fullName = fullName;
        this.email = email;
        this.address = address;
        this.age = age;
        this.resident = resident;
        this.district = district;
        this.gender = gender;
    }


    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    public boolean isResident() {
        return resident;
    }

    public int getDistrict() {
        return district;
    }

    public char getGender() {
        return gender;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAge(int age) {

        this.age = age;
    }

    public void setDistrict(int district) {
        this.district = district;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setResident(boolean resident) {
        this.resident = resident;
    }

    /**
     * This method prints the fullName, email, address, age, resident, district, gender of The citizen
     * @return String;
     */
    public String toString(){
        String[] result;
        result = fullName.split(" ", 2);

         if (address.contains(",")) {
             return result[0] + "," + result[1] +"," + email + ",\"" + address + "\""+ "," + age + "," + resident + "," + district + "," + gender;
         } else{
             return result[0] + "," + result[1] +","  + email + "," + address + ","+ age + "," + resident + "," + district + "," + gender;
         }
    }



    @Override
    public int compareTo(Citizen another) {
        return (this.fullName.compareToIgnoreCase(another.getFullName()));
    }

}// end of class

