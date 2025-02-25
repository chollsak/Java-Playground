enum Grade {
    A(4.0, "Very Good"),B(3.0, "Good Job"),C(2.0, "Not Bad"),D(1.0, "Keep it up!"),F(0.0, "Not today T-T");

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
