

class Item<T, U>{          // T = Type
    public final T data;
    public final U comment;

    public Item(T value, U cm){
        this.data = value;
        this.comment = cm;
    }
}

public class GenericClasses {
    public static void main(String[] args) {
        Item<Integer, String> obj1 = new Item<>(10, "Hello"); //<Integer> = Wrapper class
        
        Item<String, String> obj2 = new Item<>("Hello", " Newton");
        System.out.println(obj1.comment + " " +obj2.data);
    }
}
