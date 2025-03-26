public class BasicConcept {
      public static void main(String[] args) {
        Car myCar = new Car();
        myCar.brand = "Toyota";
        myCar.color = "White";
        myCar.year = 2022;
        myCar.showCar();
        myCar.detect(10);
      }  
}

class Car {
    // Attributes
    String brand;
    String color;
    int year;

    // Behaviors
    void showCar(){
      System.out.println(this.brand);
      System.out.println(this.color);
      System.out.println(this.year);
    }

    String detect(int distance){
      String res = "Good";
      if(distance >= 10){
        res = "Peep Peep";
      }
      System.out.println(res);
      return res;
    }
}
