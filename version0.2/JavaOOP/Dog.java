class Dog {
    String name;
    String breed;
    int age;

    public Dog(String name, String breed, int age) {
        this.name = name;
        this.breed = breed;
        this.age = age;
    }

    void sayName(){
        System.out.println(this.name);
    }

    void bark(){
        System.out.println("Hello");
    }

    void fetch(String item){
        System.out.println(item);
    }

    public static void main(String[] args) {
        Dog dog1 = new Dog("newyear", "yes", 1);

        dog1.sayName();
    }
}