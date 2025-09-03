public class Display {

    public static void main(String[] args) {
        System.out.println("Display");
        System.out.println("Java Playground");

        int number = 10;
        final int constantNumber = 120;
        final String hello = "kai kai";
        System.out.println(hello);
        System.out.println("Number is "+number);
        System.out.println(constantNumber + number);

        double numDouble = 10.62232131;
        int numInt = (int) numDouble;
        System.out.println(numInt);

        String a = "abc"; //class String 
        String b = "120";
        Double c;

        c = Double.parseDouble(b) + 10;
        boolean result = c instanceof Double;

        System.out.println(a + "def " +c+b+result);

    }
}
