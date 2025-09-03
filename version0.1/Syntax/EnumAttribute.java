enum Grade {
    A(4.0, "Genius"),
    B(3.0, "Very Good"),
    C(2.0, "Good"),
    D(1.0, "Not Bad"),
    F(0.0, "Fail");

    private Grade(double p, String cm){
        this.point = p;
        this.comment = cm;
    }

    public final double point;
    public final String comment;
}

public class EnumAttribute {
    public static void main(String[] args) {
        Grade g = Grade.A;
        System.out.println(g.point + " | " + g.comment + " | " + g);
    }
}
