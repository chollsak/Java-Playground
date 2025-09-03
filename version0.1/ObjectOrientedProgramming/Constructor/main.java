public class main {

    public static void main(String[] args) {

        Employee e1 = new Employee("1", "Chollasak", 30000.0);   // Create Object
        Employee e2 = new Employee("2", "Chollada");   // Create Object
        Employee e3 = new Employee();   // Create Object
        e1.showData();
        e2.showData();
        e3.showData();

        System.out.println(e1.minSalary);
    }
}
