package expression.generic;

public interface Evaluator<T extends Number> {
    T add(T l, T r);

    T subtract(T l, T r);

    T multiply(T l, T r);

    T divide(T l, T r);

    T min(T l, T r);

    T max(T l, T r);

    T convertInt(int x);

    T negate(T x);

    int count(T x);
}
