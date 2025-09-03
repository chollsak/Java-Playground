
import java.util.Scanner;


enum Grade{
    A,B,C,D,F;

    double getPoint(){
        return  switch (this){
            case A -> 4.0;
            case B -> 3.0;
            case C -> 2.0;
            case D -> 1.0;
            default -> 0.0;
        };
    }

    public static boolean isValidGrade(String input){
        try{
            Grade.valueOf(input);
            return true;
        }catch(IllegalArgumentException e){
            return false;
        }
    }
}

enum Gender {
    Female, Male
}

public class Enums {
    public static void main(String[] args) {
        
        Scanner kb = new Scanner(System.in);
        String myChoice = kb.next().toUpperCase();
        if(Grade.isValidGrade(myChoice)){
            Grade myGrade = Grade.valueOf(myChoice);
            System.out.println("Valid grade: " + myGrade + ", GPA points: " + myGrade.getPoint());
        }
    }
}
