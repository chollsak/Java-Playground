
public class main {
    public static void main(String[] args) {
        System.out.println("===== Employee Management System =====\n");
        
        // Create a basic employee
        Employee emp1 = new Employee("E001", "John Doe", 50000.0);
        emp1.setPosition("Software Engineer");
        emp1.setYearsOfExperience(3);
        
        // Create a software engineer
        String[] techStack = {"Java", "Spring", "MySQL", "Git"};
        SoftwareEngineer eng1 = new SoftwareEngineer("SE001", "Jane Smith", 75000.0, techStack, true);
        eng1.setYearsOfExperience(5);
        eng1.setProgrammingLanguage("Java");
        
        // Create another software engineer with minimal info
        SoftwareEngineer eng2 = new SoftwareEngineer("SE002", "Bob Johnson");
        eng2.setSalary(60000.0);
        eng2.setProgrammingLanguage("Python");
        
        // Create a manager
        Manager mgr1 = new Manager("M001", "Alice Williams", 90000.0, "Engineering", 5);
        mgr1.setYearsOfExperience(8);
        
        // Add team members to the manager
        mgr1.addTeamMember(eng1, 0);
        mgr1.addTeamMember(eng2, 1);
        
        // Demonstrate functionality
        emp1.sayHello();
        emp1.showData();
        System.out.println("Annual Bonus: $" + emp1.calculateBonus());
        
        System.out.println("\n");
        
        eng1.sayHello();
        eng1.programming();
        eng1.displayTechnologies();
        eng1.showData();
        System.out.println("Annual Bonus: $" + eng1.calculateBonus());
        
        System.out.println("\n");
        
        mgr1.sayHello();
        mgr1.conductMeeting();
        mgr1.showData();
        mgr1.displayTeam();
        System.out.println("Annual Bonus: $" + mgr1.calculateBonus());
    }
    
}
