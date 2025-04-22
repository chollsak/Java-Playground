

class Employee {
    private String id;
    private String name;
    private Double salary;
    private String position;

    // Constructor
    public Employee(String id, String name, Double salary){
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public Employee() {
        this.id = "Undefined";
        this.name = "Undefined";
        this.salary = 0.0;
    }

    public Employee(String id, String name){
        this.id = id;
        this.name = name;
        this.salary = 0.0;
    }

    // Method
    public void sayHello(){
        System.out.println("Hello!");
    }

    public void showData(){
        System.out.println("Employee ID: " + this.id);
        System.out.println("Name: " + this.name);
        System.out.println("Salary: " + this.salary);
        System.out.println("-------------");
    }

    public void setPosition(String position){
        this.position = position;
    }

    public void setSalary(Double newSalary){
        this.salary = newSalary;
    }
}
