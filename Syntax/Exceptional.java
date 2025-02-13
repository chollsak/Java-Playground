import java.util.Scanner;

public class Exceptional {
    public static void main(String[] args) {
        try (Scanner kb = new Scanner(System.in)){
            System.out.print("Enter number(positive): ");
            int n = kb.nextInt();
            if (n < 0){
                throw new Exception();
            }
            System.out.println("You are good reader bro, You've inputed " + n);
        } catch (Exception e) {
            System.out.println("Bro, Why u not read?");
        }
    }
}
