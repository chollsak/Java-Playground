import java.util.*;
import java.util.ArrayList;

enum Grade{
    A,B,C,D,F
}

public class Grading {
    public static void main(String[] args) {
        int time = 3;
        String res = Lister(time);
        System.out.println(res);
    }

    static String Lister(int time){
        List<Integer> li = new ArrayList<>();
        try(Scanner kb = new Scanner(System.in)){
            int result = 0;
            for(int count=1; count<=time; count++){
                int n = kb.nextInt();
                li.add(n);
            }

            // System.out.println(li.get(1)); // access to List(Array) index
    
            for (int x : li) {
                result += x;
            }
    
            String res = Checker(result);
            return res;
        }
    }

    static String Checker(int result){

        if(result >= 80){
            return ""+Grade.A;
        }else if (result >=75) {
            return ""+Grade.B+"+";
        }else if (result >=70) {
            return "B";
        }else if (result >=65) {
            return "C+";
        }else if (result >=60) {
            return "C";
        }else if (result >=55) {
            return "D+";
        }else if (result >=75) {
            return "D";
        }else{
            return "F";
        }
    }
}


