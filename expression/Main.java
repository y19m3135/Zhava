package expression; 
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Unit c = new Add(
                new Subtract(
                        new Multiply(
                                new Variable("x"),
                                new Variable("x")
                        ),
                        new Multiply(
                                new Variable("x"),
                                new Const(2)
                        )
                ),
                new Const(1)
        );
//        System.out.println(c.equals(new Add(
//                new Substract(
//                        new Multiply(
//                                new Variable("x"),
//                                new Variable("x")
//                        ),
//                        new Multiply(
//                                new Variable("x"),
//                                new Const(2)
//                        )
//                ),
//                new Const(1)
//        )));
//        System.out.println(c.hashCode());
//        System.out.println(new Add(
//                new Substract(
//                        new Multiply(
//                                new Variable("x"),
//                                new Variable("x")
//                        ),
//                        new Multiply(
//                                new Variable("x"),
//                                new Const(2)
//                        )
//                ),
//                new Const(1)
//        ).hashCode());
//        System.out.println(c.equals(new Add(new Const(2), new Const(3))));
//        System.out.println(c.toString());
//        System.out.println(c.toMiniString());
//        System.out.println(c.evaluate(Map.of("x", 1.0)));
//        System.out.println(c.evaluate(Map.of("x", 5.0)));
//        System.out.println(new Add(new Const(2), new Variable("x")).hashCode());
//        System.out.println(new Add(new Const(2), new Variable("x")).hashCode());
//        Unit s =  new Add(new Const(2), new Variable("x"));
//        System.out.println(s.hashCode());
//
        System.out.println(new Subtract(new Const(2), new Add(new Const(3), new Const(6))).toMiniString());
        System.out.print("enter var x: ");
//        double n = new Scanner(System.in).nextDouble();
//        System.out.println("(" + c.toMiniString() + ")(" + n + ") = " + c.evaluate(
//                Map.of("x", n)
//        ));
    }
}
