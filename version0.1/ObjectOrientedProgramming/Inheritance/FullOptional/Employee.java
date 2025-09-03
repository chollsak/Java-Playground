// Base class (parent class)
class Employee {
    // Protected attributes to allow access in child classes
    protected String id;
    protected String name;
    protected Double salary;
    protected String position;
    protected int yearsOfExperience;

    // Constructor with all parameters
    public Employee(String id, String name, Double salary, String position, int yearsOfExperience) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.position = position;
        this.yearsOfExperience = yearsOfExperience;
    }

    // Constructor with essential parameters
    public Employee(String id, String name, Double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.position = "General Employee";
        this.yearsOfExperience = 0;
    }

    // Default constructor
    public Employee() {
        this.id = "Undefined";
        this.name = "Undefined";
        this.salary = 0.0;
        this.position = "Not Assigned";
        this.yearsOfExperience = 0;
    }

    // Constructor with minimal parameters
    public Employee(String id, String name) {
        this.id = id;
        this.name = name;
        this.salary = 0.0;
        this.position = "New Hire";
        this.yearsOfExperience = 0;
    }

    // Method to greet
    public void sayHello() {
        System.out.println("Hello, I'm " + this.name + "!");
    }

    // Display employee data
    public void showData() {
        System.out.println("---------- Employee Details ----------");
        System.out.println("Employee ID: " + this.id);
        System.out.println("Name: " + this.name);
        System.out.println("Position: " + this.position);
        System.out.println("Salary: $" + this.salary);
        System.out.println("Years of Experience: " + this.yearsOfExperience);
        System.out.println("-------------------------------------");
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getSalary() {
        return salary;
    }

    public String getPosition() {
        return position;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSalary(Double newSalary) {
        this.salary = newSalary;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
    
    // Calculate annual bonus (can be overridden by subclasses)
    public double calculateBonus() {
        return salary * 0.05; // Basic 5% bonus for all employees
    }
}