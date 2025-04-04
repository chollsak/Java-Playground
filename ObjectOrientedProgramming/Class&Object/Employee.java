public class Employee {
    // Attribute
    private String id;
    private String name;
    private Double salary;

    // Method
    // setter
    public void setID(String id){
        this.id = id;
        System.out.println("Employee ID is setted -> " + id);
    }

    public void setName(String name){
        this.name = name;
        System.out.println("Employee name is setted -> " + name);
    }

    public void setSalary(Double salary){
        this.salary = salary;
        System.out.println("Employee salary is setted -> " + salary);
    }

    //getter
    public String getID(){
        System.out.println(this.id);
        return this.id;
    }

    public String getName(){
        System.out.println(this.name);
        return this.name;
    }

    public Double getSalary(){
        System.out.println(this.salary);
        return this.salary;
    }

    public void showData(){
        System.out.println("Employee ID: " + this.id);
        System.out.println("Name: " + this.name);
        System.out.println("Salary: " + this.salary);

    }

}
