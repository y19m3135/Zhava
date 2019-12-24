package expression;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
//public interface Expression<N extends Number> extends ToMiniString {
//    N evaluate(N var);
//}

public interface Expression extends ToMiniString {
    int evaluate(int var);
}