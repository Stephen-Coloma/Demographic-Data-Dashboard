package FinalGroupProject2;

public class MyProgram extends MyProgramUtility{
    public static void main(String[] args) {
        MyProgram program = new MyProgram();
        program.run();
    }

    public void run(){
        MyProgramUtility programUtility = new MyProgramUtility();
        System.out.println(programUtility);
    }

}
