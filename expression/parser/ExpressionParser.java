package expression.parser;

import expression.*;
import expression.exceptions.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ExpressionParser extends GeneralParser {
    private static final Map<String, Class<? extends BinaryOperation>> binaryOperations;
    private static final Map<String, Class<? extends UnaryOperation>> unaryOperations;

    static {
        binaryOperations = Map.of("+", CheckedAdd.class,
                "-", CheckedSubtract.class,
                "*", CheckedMultiply.class,
                "/", CheckedDivide.class,
                "//", CheckedLog.class,
                "**", CheckedPow.class);
        unaryOperations = Map.of("-", CheckedNegate.class,
                "log2", CheckedLog2.class,
                "pow2", CheckedPow2.class);
    }

//    enum Token {
//        ADD("+") {
//            @Override
//            protected TripleExpression getOperation(TripleExpression a, TripleExpression b) {
//                return new CheckedAdd(a, b);
//            }
//        }
//
//        private final String op;
//
//        Token(String op) {
//            this.op = op;
//        }
//
//        protected abstract TripleExpression getOperation(TripleExpression a, TripleExpression b);
//    }

    private Expression mergeExpressions(List<ExpressionElement> elements) throws ParseException {
        LinkedList<ExpressionElement> nextStep = new LinkedList<>();
        ExpressionElement prev, curr, next;

        if (elements.size() == 0) {
            throw new ArgumentsMismatchException("neither operands nor arguments found");
        }

        //обработка всех унарных операций

        curr = elements.get(elements.size() - 1);

        if (curr.getType() != ElementType.Expression) {
            throw new ArgumentsMismatchException("operation '" + curr.toString()
                    + "' ends string instead of any argument");
        }

        for (int i = elements.size() - 2; i > -1; i--) {
            next = elements.get(i);
            if (next.getType() == ElementType.UnOp) {
                try {
                    curr = unaryOperations.get(((Operator) next).symbol)
                            .getConstructor(Expression.class).newInstance((Expression) curr);
                } catch (ReflectiveOperationException e) {
                    e.printStackTrace();
                }
            } else {
                nextStep.addFirst(curr);
                curr = next;
            }
        }
        nextStep.addFirst(curr);

        elements.clear();
        elements.addAll(nextStep);
        nextStep.clear();

        //остались только бинарные операции, есть хотя бы одно выражение, потому что иначе произошло бы исключение
        //проходясь по ним, схлопнем остальное выражение в одно

        for (int priority = 1; priority < 4; priority++) {
            prev = elements.get(0);
            int i = 1;
            for (; i < elements.size(); i += 2) {
                curr = elements.get(i);
                if (curr.getType() == ElementType.BinOp) {
                    next = elements.get(i + 1);
                    if (((Operator) curr).priority == priority) {
                        try {
                            prev = binaryOperations.get(((Operator) curr).symbol)
                                    .getConstructor(Expression.class, Expression.class).newInstance(prev, next);
                        } catch (ReflectiveOperationException e) {
                            e.printStackTrace();
                        }
                    } else {
                        nextStep.add(prev);
                        nextStep.add(curr);
                        prev = next;
                    }
                } else {
                    throw new ArgumentsMismatchException(exceptionMessage(
                            new StringBuilder(curr.toString()).append(" unexpected")));
                }
            }
            nextStep.add(prev);
            elements.clear();
            elements.addAll(nextStep);
            nextStep.clear();
        }

        if (elements.size() != 1) {
            throw new ParseException("unknown exception");
        }
        return (Expression) elements.get(0);
    }

    private Expression parseExpression(int depth) throws ParseException {
        List<ExpressionElement> elements = new ArrayList<>();
        boolean isNextUnary = true, isCurrentNegate = false;

        while (hasNext()) {
            skipWhitespace();
            if (isOperation()) {
                Operator operator = parseOperator(isNextUnary);
                if (isNextUnary ^ (operator.getType() == ElementType.UnOp)) {
                    throw new ArgumentsMismatchException(exceptionMessage(
                            new StringBuilder("operand comes without argument: ")
                                    .append(operator.symbol)));
                }
                isCurrentNegate = !isCurrentNegate && (operator.symbol.equals("-") && operator.getType() == ElementType.UnOp);
                elements.add(operator);
                isNextUnary = true;
            } else {
                Expression expression;
                if (test('(')) {
                    elements.add(parseExpression(depth + 1));
                } else {
                    if (isCurrentNegate) {
                        elements.set(elements.size() - 1, parseTerm(true));
                    } else {
                        elements.add(parseTerm(false));
                    }
                }
                isCurrentNegate = false;
                isNextUnary = false;
            }
            skipWhitespace();
        }
        if (depth > 0) {
            if (!test(')')) {
                throw new BracketsMismatchException(exceptionMessage(new StringBuilder("')' expected")));
            }
        } else if (test(')')) {
            throw new BracketsMismatchException(exceptionMessage(new StringBuilder("')' unexpected")));
        }

        return mergeExpressions(elements);
    }

    private Expression parseTerm(boolean isCurrentNegative) throws ParseException {
        boolean isNumber = Character.isDigit(current);
        return isNumber ? parseNumber(isCurrentNegative)
                : isCurrentNegative ? new CheckedNegate(parseVariable()) : parseVariable();
    }

    private Expression parseNumber(boolean isCurrentNegative) throws ParseException {
        StringBuilder sb = new StringBuilder();
        while (hasNext() && !isOperation() && Character.isDigit(current)) {
            sb.append(current);
            nextChar();
        }
        try {
            return new Const(Integer.parseInt((isCurrentNegative ? "-" : "")
                    + sb.toString()));
        } catch (NumberFormatException e) {
            throw new ImpossibleNumberException(exceptionMessage(sb.append(" is too big to parse")));
        }
    }

    private Expression parseVariable() throws ParseException {
        String name;
        if (test('x')) {
            name = "x";
        } else if (test('y')) {
            name = "y";
        } else if (test('z')) {
            name = "z";
        } else {
            throw new IllegalSymbolException(exceptionMessage(
                    new StringBuilder("'").append(current).append("' unexpected")));
        }
        return new Variable(name);
    }

    private Operator parseOperator(boolean isNextUnary) throws ParseException {
        if (test('+')) {
            return new Operator("+", 3);
        } else if (test('-')) {
            return new Operator("-", isNextUnary ? 0 : 3);
        } else if (test('*')) {
            return test('*')
                    ? new Operator("**", 1)
                    : new Operator("*", 2);
        } else if (test('/')) {
            return test('/')
                    ? new Operator("//", 1)
                    : new Operator("/", 2);
        } else if (test('p')) {
            expect("ow2");
            if (Character.isLetter(current)) {
                throw new ParseException("inability to use variable after pow2 for unknown reason");
            }
            return new Operator("pow2", 0);
        } else if (test('l')) {
            expect("og2");
            if (Character.isLetter(current)) {
                throw new ParseException("inability to use variable after log2 for unknown reason");
            }
            return new Operator("log2", 0);
        }
        throw new ParseException("strange operator");
    }

    @Override
    public Expression parse(String expression) throws ParseException {
        sauce = new StringSource(expression);
        nextChar();
        Expression res = parseExpression(0);
        if (hasNext()) {
            throw new ParseException("end of expression expected");
        }
        return res;
    }

    private class Operator extends ExpressionElement {
        String symbol;
        int priority;

        public Operator(String operation, int priority) {
            type = priority == 0 ? ElementType.UnOp : ElementType.BinOp;
            this.symbol = operation;
            this.priority = priority;
        }

        @Override
        public String toString() {
            return symbol;
        }
    }
}
