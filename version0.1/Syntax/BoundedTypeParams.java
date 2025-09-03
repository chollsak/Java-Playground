

class Item<T, U>{          // T = Type
    public final T data;
    public final U comment;

    public Item(T value, U cm){
        this.data = value;
        this.comment = cm;
    }
}

class Human<T extends String, U extends Integer>{
    public final T name;
    public final U age;

    public Human(T name, U age){
        this.name = name;
        this.age = age;
    }
}

public class BoundedTypeParams {
    public static void main(String[] args) {
        Item<Integer, String> obj1 = new Item<>(10, "Hello"); //<Integer> = Wrapper class
        
        Item<String, String> obj2 = new Item<>("Hello", " Newton");

        Human<String, Integer> human1 = new Human<>("Chollasak", 21);
        System.out.println(obj1.comment + " " +obj2.data);

        System.out.println(human1+"\n"+human1.name+"\n"+human1.age);
    }
}
