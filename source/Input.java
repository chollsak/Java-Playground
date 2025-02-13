
import java.util.Scanner;

public class Input {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)){

            //Scanner sc = new Scanner(System.in); // object scanner
            System.out.print("Enter your name: ");
            String name = sc.nextLine(); // input string all line, next() is read only first input after blank " "

            System.out.print("Enter your money: ");
            Integer money = sc.nextInt();

            int a = 5;
            System.out.println("My name is "+name);
            System.out.println("Your money total is " + ++money +" "+ --a);
        } 

    }
}
