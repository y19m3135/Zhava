package expression.generic;

import expression.Expression;
import expression.exceptions.EvaluationException;
import expression.exceptions.IllegalModeException;
import expression.parser.ExpressionParser;

public class GenericTabulator implements Tabulator {
    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {
        Expression ex = new ExpressionParser().parse(expression);

        switch (mode) {
            case "i":
                return solve(ex, new IntegerEvaluator(), x1, x2, y1, y2, z1, z2);
            case "d":
                return solve(ex, new DoubleEvaluator(), x1, x2, y1, y2, z1, z2);
            case "bi":
                return solve(ex, new BigIntegerEvaluator(), x1, x2, y1, y2, z1, z2);
            default:
                throw new IllegalModeException("Unknown mode: " + mode);
        }

    }

    private <T extends Number> Object[][][] solve(Expression expression, Evaluator<T> evaluator, int x1, int x2, int y1, int y2, int z1, int z2) {
        Object[][][] result = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];


        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                for (int k = 0; k < result[i][j].length; k++) {
                    try {
                        result[i][j][k] = expression.evaluate(i + x1, j + y1, k + z1, evaluator);
                    } catch (EvaluationException e){
                        result[i][j][k] = null;
                    }
                }
            }
        }
        return result;
    }
}
