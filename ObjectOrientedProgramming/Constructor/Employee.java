public class Employee {
    // Attribute
    private String id;
    private String name;
    private Double salary;

    // Default Constructor
    public Employee(){

    }

    public Employee(String id, String name){
        this.id = id;
        this.name = name;
        this.salary = 0.0;
    }

    // Constructor
    public Employee(String id, String name, Double salary){
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    // Method

    public void showData(){
        System.out.println("Employee ID: " + this.id);
        System.out.println("Name: " + this.name);
        System.out.println("Salary: " + this.salary);
        System.out.println("-------------");
    }

}
