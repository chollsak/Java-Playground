class ItemInt{
    public ItemInt(int value){
        this.data = value;
    }

    public final int data;
}

class ItemDouble{
    public ItemDouble(double value){
        this.data = value;
    }

    public final double data;
}

public class Generic {
    public static void main(String[] args) {
        ItemInt obj1 = new ItemInt(15);
        ItemDouble obj2 = new ItemDouble(10.22);

        System.out.println(obj1.data + " " +String.valueOf(obj2.data));
    }
}