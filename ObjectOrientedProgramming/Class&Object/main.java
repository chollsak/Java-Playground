public class main {

    public static void main(String[] args) {
        Employee e1 = new Employee();   // Create Object
        e1.setID("65010195");
        e1.setName("Chollasak Anuwareepong");
        e1.setSalary(6000.0);
        System.out.println("----------------");
        e1.showData();

        System.out.println(" ");

        Employee e2 = new Employee();
        e2.setID("65040254");
        e2.setName("Marcus Rashford");
        e2.setSalary(4239.0);
        System.out.println("----------------");
        e2.showData();


        
    }
}
