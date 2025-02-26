import java.util.ArrayList;
import java.util.Scanner;

public class ArrayLists {
    public static void main(String[] args) {
        ArrayList<String> data = new ArrayList<>();
        OUTER:
        while (true) {
            Scanner kb = new Scanner(System.in);
            System.out.print("Please enter something to add in ArrayList: ");
            String res = kb.next();
            switch (res) {
                case "." -> {
                    break OUTER;
                }
                case "-" -> {
                    if(data.isEmpty()){
                        System.out.println("Cannot delete more");
                    }else{
                        data.remove(data.size()-1);
                        System.out.println(data);
                    }
                }
                default -> data.add(res);
            }

        }

        System.out.println("Thank you, for your attentions!!");
        System.out.println("Contents of the ArrayList: " + data);
    }
}
