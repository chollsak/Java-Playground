// Derived class (child class)
class SoftwareEngineer extends Employee {
    private String programmingLanguage;
    private String[] technologies;
    private boolean isRemote;
    
    // Constructor
    public SoftwareEngineer(String id, String name, Double salary, String[] technologies, boolean isRemote) {
        // Call parent constructor
        super(id, name, salary);
        
        // Set specific properties
        this.programmingLanguage = "Java"; // Default 
        this.technologies = technologies;
        this.isRemote = isRemote;
        this.position = "Software Engineer"; // Override the default position
    }
    
    // Another constructor
    public SoftwareEngineer(String id, String name) {
        super(id, name);
        this.position = "Junior Software Engineer";
        this.technologies = new String[]{"Basic Java"};
        this.isRemote = false;
    }
    
    // Method specific to SoftwareEngineer
    public void programming() {
        System.out.println(name + " is coding in " + programmingLanguage + ", please be quiet!");
    }
    
    // Method to display skills
    public void displayTechnologies() {
        System.out.println(name + "'s Technical Skills:");
        for (String tech : technologies) {
            System.out.println("- " + tech);
        }
    }
    
    // Override the parent method to add additional information
    @Override
    public void showData() {
        super.showData(); // Call the parent method first
        System.out.println("Primary Language: " + programmingLanguage);
        System.out.println("Working Remotely: " + (isRemote ? "Yes" : "No"));
        System.out.println("-------------------------------------");
    }
    
    // Getters and Setters
    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }
    
    public String getProgrammingLanguage() {
        return programmingLanguage;
    }
    
    // Override the bonus calculation for Software Engineers
    @Override
    public double calculateBonus() {
        return salary * (0.1 + (0.01 * yearsOfExperience)); // 10% + 1% per year of experience
    }
}