package expression; 
import java.util.Map;

public interface Unit extends DoubleExpression, Expression, TripleExpression {
    int evaluate(Map<String, Integer> variables);       //why to use Map? I can add new methods for more variables easily
    String toString();
    int getPriority();
//    N evaluate(Map<String, N> variables);            //can't use both double evaluate(Map<String, Double>) and
//    double evaluate(Map<String, Double> variables);  // int evaluate(Map<String, Integer>). couldn't also find a way to avoid this.
                                                       // even with generics. N evaluate(Map<String, N>)? WAAARNINGS!  (could ignore though, not sure i should)
    double evaluate(double var);                       //so will use different realisations of double- and standard version.

    default int evaluate(int var) {
        return evaluate(Map.of("x", var));
    }
    default int evaluate(int x, int y, int z) {
        return evaluate(Map.of("x", x, "y", y, "z", z));
    }
//    default Number evaluate(Number var) {
//        return evaluate(Map.of("x", (N) var));
//    }
}
