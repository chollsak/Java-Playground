public class BasicConcept {
      public static void main(String[] args) {
        Car myCar = new Car();
        myCar.brand = "Toyota";
        myCar.color = "White";
        myCar.year = 2022;
        myCar.showCar();
      }  
}

class Car {
    // Attributes
    String brand;
    String color;
    int year;

    void showCar(){
      System.out.println(this.brand);
      System.out.println(this.color);
      System.out.println(this.year);
    }
}
