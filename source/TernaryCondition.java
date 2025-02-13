import java.util.Scanner;

public class TernaryCondition {
    public static void main(String[] args) {
        // Program find even or odd number

        try(Scanner sc = new Scanner(System.in)){
            System.out.print("Enter your number: ");
            int n = sc.nextInt();
            String result;

            // variable = condition ? if true do : if false do
            result = n % 2 == 0 ? n + " is even" : n+ " is odd";

            System.out.println(result);
        }
    }
}
