package expression.parser;

import expression.*;

import java.util.ArrayList;
import java.util.List;

public class ExpressionParser extends GeneralParser {

    private CommonExpression mergeExpressions(List<CommonExpression> expressions,
                                              List<Operation> operations) {
        CommonExpression prev, next;
        prev = expressions.get(0);

        for (int priority = 1; priority < 4; priority++) {
            List<CommonExpression> merged = new ArrayList<>();
            List<Operation> laterOperations = new ArrayList<>();
            for (int i = 0; i < operations.size(); i++) {
                next = expressions.get(i + 1);
                if (operations.get(i).priority != priority) {
                    merged.add(prev);
                    prev = next;
                    laterOperations.add(operations.get(i));
                    continue;
                }

                switch (operations.get(i).symbol){
                    case '+':
                        prev = new Add(prev, next);
                        break;
                    case '-':
                        prev = new Subtract(prev, next);
                        break;
                    case '*':
                        prev = new Multiply(prev, next);
                        break;
                    case '/':
                        prev = new Divide(prev, next);
                        break;
                    case '<':
                        prev = new ShiftLeft(prev, next);
                        break;
                    case '>':
                        prev = new ShiftRight(prev, next);
                        break;
                }
            }
            merged.add(prev);

            prev = merged.get(0);
            expressions = merged;
            operations = laterOperations;
        }
        return prev;
    }


    private CommonExpression parseExpression() {
        List<CommonExpression> expressions = new ArrayList<>();
        List<Operation> operations = new ArrayList<>();
        boolean isNextUnaryMinus = true, isCurrentNegative = false;     // Идея isNextUnaryMinus в том, что (операция '-' && (стоит первой или после другой операции)) <=> это унарный минус

        while (hasNext()) {
            skipWhitespace();
            if (isOperation()) {
                Operation operation = parseOperation(isNextUnaryMinus);
                if (operation.priority == 0) {                          // Если распарсили унарный минус, то поменяем знак у ближайшего выражения
                    isCurrentNegative = !isCurrentNegative;
                } else {
                    operations.add(operation);                          // В списке операций всё, что имеет приоритет 1 или 2
                }
                isNextUnaryMinus = true;                                // Если мы получим следующим токеном операцию, то обзовём её унарным минусом
            } else {
                CommonExpression expression;
                if (test('(')) {
                    expression = isCurrentNegative ? new Negate(parseExpression()) : parseExpression();

                } else {
                    expression = parseTerm(isCurrentNegative);
                }
                isCurrentNegative = false;
                isNextUnaryMinus = false;
                expressions.add(expression);
            }
            skipWhitespace();
        }
        test(')');
        return mergeExpressions(expressions, operations);
    }

    private CommonExpression parseNumber(boolean isCurrentNegative) {
        StringBuilder sb = new StringBuilder();
        while (Character.isDigit(current)) {
            sb.append(current);
            nextChar();
        }
        return new Const(Integer.parseInt((isCurrentNegative ? "-" : "")
                + sb.toString()));
    }

    private CommonExpression parseVariable() {
        char name = current;
        nextChar();
        if (name != 'x' && name != 'y' && name != 'z') {
            throw new ParseExceprion("strange variable name");
        } else {
            return new Variable(String.valueOf(name));
        }
    }

    private CommonExpression parseTerm(boolean isCurrentNegative) {
        boolean isNumber = Character.isDigit(current);
        return isNumber ? parseNumber(isCurrentNegative)
                : isCurrentNegative ? new Negate(parseVariable()) : parseVariable();
    }

    private Operation parseOperation(boolean isNextUnaryMinus) {
        if (test('+')) {
            return new Operation('+', 2);
        } else if (test('-')) {
            return new Operation('-', isNextUnaryMinus ? 0 : 2);
        } else if (test('*')) {
            return new Operation('*', 1);
        } else if (test('/')) {
            return new Operation('/', 1);
        } else if (test('>')) {
            expect('>');
            return new Operation('>', 3);
        } else if (test('<')) {
            expect('<');
            return new Operation('<', 3);
        }
        throw new IllegalStateException("I'm dead inside");
    }

    @Override
    public CommonExpression parse(String expression) {
        source = new StringSource(expression);
        nextChar();
        return parseExpression();
    }

    private class Operation {
        char symbol;
        int priority;

        public Operation(char operation, int priority) {
            this.symbol = operation;
            this.priority = priority;
        }

        @Override
        public String toString() {
            return Character.toString(symbol);
        }
    }
}
