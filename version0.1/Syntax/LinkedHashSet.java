import java.util.*;

public class LinkedHashSet {
    public static void main(String[] args) {
        TreeSet<String> data = new TreeSet<>();

        data.add("Java");
        data.add("PHP");
        data.add("C#");
        data.add("Rust");
        data.add("Go");
        System.out.println(data);
        System.out.println(data.size());
    }
}
