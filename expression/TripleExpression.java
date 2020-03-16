package expression;

import expression.generic.Evaluator;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface TripleExpression extends ToMiniString {
    <T extends Number> T evaluate(int x, int y, int z, Evaluator<T> evaluator);
}
