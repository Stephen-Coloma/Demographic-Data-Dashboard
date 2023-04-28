/**
 * may error na etoh diko alm ayusin hehe
 * Exception in thread "main" java.lang.NumberFormatException: For input string: "Resident"
 * */


package FinalGroupProject2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MyProgramUtility {
    public static void main(String[] args) {
        MyProgramUtility program = new MyProgramUtility();
        program.run();
    }

    public void run() {
        System.out.println(readDataFromCSV());
    }

    public ArrayList<Citizen> readDataFromCSV(){
        ArrayList<Citizen> arrayList = new ArrayList<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader("src/data.csv"));
            String line;
            while((line = reader.readLine()) != null) {
                if (!line.contains(",")) {
                    String[] citizenData = line.split(",");

                    String fullName = citizenData[0]+""+citizenData[1];
                    String email = citizenData[2];
                    String address = citizenData[3];
                    int age = Integer.parseInt(citizenData[4]);
                    boolean resident = Boolean.parseBoolean(citizenData[5]);
                    int district = Integer.parseInt(citizenData[6]);
                    char gender = citizenData[7].charAt(0);

                    Citizen citizen = new Citizen(fullName,email,address,age,resident,district,gender);
                    arrayList.add(citizen);

                }else {
                    String[] citizenDetails = new String[8];
                    String[] myArr;

                    String citizenDataAddress = line.substring((line.indexOf(',')+1),line.lastIndexOf('.'));
                    String template = line.replaceAll(citizenDataAddress,"").replaceAll("[^,]+\\.","");

                    myArr = template.split(",");

                    citizenDetails[0] = myArr[0];
                    citizenDetails[1] = myArr[1];
                    citizenDetails[2] = citizenDataAddress;
                    citizenDetails[3] = myArr[2];
                    citizenDetails[4] = myArr[3];
                    citizenDetails[5] = myArr[4];
                    citizenDetails[6] = myArr[5];

                    if(myArr.length > 6){
                        citizenDetails[7] = myArr[6];
                    } else {
                        citizenDetails[7] = "";
                    }



                    String fullName = citizenDetails[0]+""+citizenDetails[1];
                    String email = citizenDetails[2];
                    String address = citizenDetails[3];
                    int age = Integer.parseInt(citizenDetails[4]);
                    boolean resident = Boolean.parseBoolean(citizenDetails[5]);
                    int district = Integer.parseInt(citizenDetails[6]);
                    char gender = citizenDetails[7].charAt(0);

                    Citizen citizen = new Citizen(fullName,email,address,age,resident,district,gender);
                    arrayList.add(citizen);
                }
            }
        }catch (FileNotFoundException fnf){
            fnf.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return arrayList;
    }
}



