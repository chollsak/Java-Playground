// Another derived class
class Manager extends Employee {
    private int teamSize;
    private String department;
    private Employee[] teamMembers;
    
    // Constructor
    public Manager(String id, String name, Double salary, String department, int teamSize) {
        super(id, name, salary);
        this.position = "Manager";
        this.department = department;
        this.teamSize = teamSize;
        this.teamMembers = new Employee[teamSize];
    }
    
    // Method specific to Manager
    public void conductMeeting() {
        System.out.println(name + " is conducting a meeting for the " + department + " department.");
    }
    
    // Add team member
    public void addTeamMember(Employee employee, int position) {
        if (position >= 0 && position < teamSize) {
            teamMembers[position] = employee;
            System.out.println(employee.getName() + " has been added to " + name + "'s team.");
        } else {
            System.out.println("Invalid position in the team.");
        }
    }
    
    // Display team information
    public void displayTeam() {
        System.out.println("\n======== " + department + " Team (" + name + ") ========");
        for (int i = 0; i < teamSize; i++) {
            if (teamMembers[i] != null) {
                System.out.println((i+1) + ". " + teamMembers[i].getName() + " - " + teamMembers[i].getPosition());
            } else {
                System.out.println((i+1) + ". [Position Open]");
            }
        }
        System.out.println("=====================================\n");
    }
    
    // Override the parent method
    @Override
    public void showData() {
        super.showData();
        System.out.println("Department: " + department);
        System.out.println("Team Size: " + teamSize);
        System.out.println("-------------------------------------");
    }
    
    // Override the bonus calculation for Managers
    @Override
    public double calculateBonus() {
        return salary * (0.15 + (0.01 * teamSize)); // 15% + 1% per team member
    }
}